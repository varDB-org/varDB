package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.Diseas;
import org.vardb.model.entity.DiseasesDrug;
import org.vardb.model.entity.DiseasesRef;
import org.vardb.model.entity.PathogensDiseas;
import org.vardb.model.entity.QDiseas;
import org.vardb.model.entity.QDiseasesDrug;
import org.vardb.model.entity.QDiseasesRef;
import org.vardb.model.entity.QPathogensDiseas;
import org.vardb.model.response.DiseaseItem;
import org.vardb.model.response.DrugItem;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.PathogenItem;
import org.vardb.model.response.ReferenceItem;
import org.vardb.repository.DiseaseRepository;
import org.vardb.repository.DiseasesDrugRepository;
import org.vardb.repository.DiseasesRefRepository;
import org.vardb.repository.PathogensDiseasRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class DiseaseService {
	@Autowired
	DiseaseRepository repository;

	@Autowired
	PathogensDiseasRepository pathogenRepository;

	@Autowired
	DiseasesDrugRepository drugRepository;

	@Autowired
	DiseasesRefRepository refRepository;

	/**
	 * finds all diseases
	 * @return all diseases
	 */
	public List< DiseaseItem > findAll() {
		List< Diseas > diseases = this.repository.findAll();
		ArrayList< DiseaseItem > array = new ArrayList< DiseaseItem >();

		for( Diseas disease : diseases ) {
			DiseaseItem item = DiseaseService.convertDiseaseToItem( disease );
			array.add( item );
		}

		return array;
	}

	/**
	 * finds disease
	 * @param id
	 * @return
	 */
	public Diseas findDisease( Integer id ) {
		Diseas disease = this.repository.findOne( id );
		return disease;
	}

	/**
	 * reference count
	 * @param id  ID
	 * @return reference count
	 */
	public Long referenceCount( Integer id ) {
		QDiseasesRef qRef = QDiseasesRef.diseasesRef;
		BooleanExpression expression = qRef.diseas.id.eq( id );
		Long count = this.refRepository.count( expression );
		return count;
	}

	/**
	 * drug count
	 * @param id ID
	 * @return drug count
	 */
	public Long drugCount( Integer id ) {
		QDiseasesDrug qDrug = QDiseasesDrug.diseasesDrug;
		BooleanExpression expression = qDrug.diseas.id.eq( id );
		Long count = this.drugRepository.count( expression );
		return count;
	}

	/**
	 * pathogen count
	 * @param id ID
	 * @return pathogen count
	 */
	public Long pathogenCount( Integer id ) {
		QPathogensDiseas qPathogen = QPathogensDiseas.pathogensDiseas;
		BooleanExpression expression = qPathogen.diseas.id.eq( id );
		Long count = this.pathogenRepository.count( expression );
		return count;
	}

	/**
	 * gets the page information
	 * @param page page number
	 * @param size page size
	 * @param sort sort field
	 * @param dir sort direction
	 * @return page result
	 */
	public PageResult page( Integer page, Integer size, String sort, String dir, String keyword ) {
		long total = this.repository.count();
		long count = total;

		if( sort.equals( "accession" ) || sort.equals( "link" ) ) {
			sort = "identifier";
		}

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		Page< Diseas > diseases = null;
		if( keyword.isEmpty() ) {
			diseases = this.repository.findAll( pageable );
		}
		else {
			QDiseas qDiseas = QDiseas.diseas1;
			StringPath[] columns = {
				qDiseas.name, qDiseas.identifier, qDiseas.description, qDiseas.diagnosis, qDiseas.distribution,
				qDiseas.drugs, qDiseas.history, qDiseas.host, qDiseas.icd10, qDiseas.keggDisease, qDiseas.keggPathway,
				qDiseas.morbidity, qDiseas.morbidity, qDiseas.notes, qDiseas.pathogenesis, qDiseas.prevention,
				qDiseas.symptoms, qDiseas.transmission, qDiseas.treatment, qDiseas.vaccines, qDiseas.vector
			};

			BooleanExpression expression = DatabaseTool.getExpression( columns, keyword );

			diseases = this.repository.findAll( expression, pageable );
			count = diseases.getTotalElements();
		}

		ArrayList< DiseaseItem > items = new ArrayList< DiseaseItem >();

		diseases.forEach(
			( disease ) -> {
				DiseaseItem item = DiseaseService.convertDiseaseToItem( disease );
				items.add( item );
			}
		);

		Page< DiseaseItem > itemsPage = new PageImpl< DiseaseItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( total );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * gets the page information
	 * @param page page number
	 * @param size page size
	 * @param sort sort field
	 * @param dir sort direction
	 * @return page result
	 */
	public PageResult pathogenPage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "accession" ) || sort.equals( "link" ) ) {
			sort = "identifier";
		}
		sort = "pathogen." + sort;

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QPathogensDiseas qPathogen = QPathogensDiseas.pathogensDiseas;
		BooleanExpression expression = qPathogen.diseas.id.eq( id );

		Page< PathogensDiseas > pathogens = this.pathogenRepository.findAll( expression, pageable );
		Long count = pathogens.getTotalElements();

		ArrayList< PathogenItem > items = new ArrayList< PathogenItem >();

		pathogens.forEach(
			( pathogen ) -> {
				PathogenItem item = PathogenService.convertPathogenToItem( pathogen.getPathogen() );
				items.add( item );
			}
		);

		Page< PathogenItem > itemsPage = new PageImpl< PathogenItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( count );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * gets the page information
	 * @param page page number
	 * @param size page size
	 * @param sort sort field
	 * @param dir sort direction
	 * @return page result
	 */
	public PageResult drugPage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "accession" ) || sort.equals( "link" ) ) {
			sort = "identifier";
		}
		sort = "drug." + sort;

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QDiseasesDrug qDrug = QDiseasesDrug.diseasesDrug;
		BooleanExpression expression = qDrug.diseas.id.eq( id );

		Page< DiseasesDrug > drugs = this.drugRepository.findAll( expression, pageable );
		Long count = drugs.getTotalElements();

		ArrayList< DrugItem > items = new ArrayList< DrugItem >();

		drugs.forEach(
			( drug ) -> {
				DrugItem item = DrugService.convertDrugToItem( drug.getDrug() );
				items.add( item );
			}
		);

		Page< DrugItem > itemsPage = new PageImpl< DrugItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( count );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * gets the page information
	 * @param page page number
	 * @param size page size
	 * @param sort sort field
	 * @param dir sort direction
	 * @return page result
	 */
	public PageResult refPage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "accession" ) || sort.equals( "link" ) ) {
			sort = "identifier";
		}
		sort = "ref." + sort;

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QDiseasesRef qRef = QDiseasesRef.diseasesRef;
		BooleanExpression expression = qRef.diseas.id.eq( id );

		Page< DiseasesRef > refs = this.refRepository.findAll( expression, pageable );
		Long count = refs.getTotalElements();

		ArrayList< ReferenceItem > items = new ArrayList< ReferenceItem >();

		refs.forEach(
			( ref ) -> {
				ReferenceItem item = ReferenceService.convertReferenceToItem( ref.getRef() );
				items.add( item );
			}
		);

		Page< ReferenceItem > itemsPage = new PageImpl< ReferenceItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( count );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * convert disease to disease item
	 * @param disease disease
	 * @return disease item
	 */
	public static DiseaseItem convertDiseaseToItem( Diseas disease ) {
		Integer id = disease.getId();
		String accession = disease.getIdentifier();
		String link = "<a href=\"disease?id=" + id + "\">" + accession + "</a>";

		DiseaseItem item = new DiseaseItem();
		item.setId( id );
		item.setAccession( accession );
		item.setLink( link );
		item.setName( disease.getName() );
		item.setDescription( disease.getDescription() );
		item.setDiagnosis( disease.getDiagnosis() );
		item.setDistribution( disease.getDiagnosis() );
		item.setDrugs( disease.getDrugs() );
		item.setHistory( disease.getHistory() );
		item.setHost( disease.getHost() );
		item.setHtml( disease.getHtml() );
		item.setHuman( disease.getHuman() ? "*" : ""  );
		item.setIcd10( disease.getIcd10() );
		item.setKeggDisease( disease.getKeggDisease() );
		item.setKeggPathway( disease.getKeggPathway() );
		item.setMorbidity( disease.getMorbidity() );
		item.setMortality( disease.getMortality() );
		item.setNotes( disease.getNotes() );
		item.setNumsequences( disease.getNumsequences() );
		item.setPathogenesis( disease.getPathogenesis() );
		item.setPrevention( disease.getPrevention() );
		item.setSymptoms( disease.getSymptoms() );
		item.setTransmission( disease.getTransmission() );
		item.setTreatment( item.getTreatment() );
		item.setUrl( item.getUrl() );
		item.setVaccines( item.getVaccines() );
		item.setVector( disease.getVector() );

		return item;
	}
}
