package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.Pathogen;
import org.vardb.model.entity.PathogensDiseas;
import org.vardb.model.entity.PathogensDrug;
import org.vardb.model.entity.PathogensRef;
import org.vardb.model.entity.QPathogen;
import org.vardb.model.entity.QPathogensDiseas;
import org.vardb.model.entity.QPathogensDrug;
import org.vardb.model.entity.QPathogensRef;
import org.vardb.model.response.DiseaseItem;
import org.vardb.model.response.DrugItem;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.PathogenItem;
import org.vardb.model.response.ReferenceItem;
import org.vardb.repository.PathogenRepository;
import org.vardb.repository.PathogensDiseasRepository;
import org.vardb.repository.PathogensDrugRepository;
import org.vardb.repository.PathogensRefRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class PathogenService {
	@Autowired
	PathogenRepository repository;

	@Autowired
	PathogensDiseasRepository diseaseRepository;

	@Autowired
	PathogensRefRepository refRepository;

	@Autowired
	PathogensDrugRepository drugRepository;

	/**
	 * finds all taxonomies
	 * @return all taxonomies
	 */
	public List< PathogenItem > findAll() {
		List< Pathogen > pathogens = this.repository.findAll();
		ArrayList< PathogenItem > array = new ArrayList< PathogenItem >();

		for( Pathogen pathogen : pathogens ) {
			PathogenItem item = PathogenService.convertPathogenToItem( pathogen );
			array.add( item );
		}

		return array;
	}

	/**
	 * finds pathogen
	 * @param id pathogen ID
	 * @return pathogen
	 */
	public Pathogen findPathogen( Integer id ) {
		Pathogen pathogen = this.repository.findOne( id );
		return pathogen;
	}

	/**
	 * finds pathogen
	 * @param kegg kegg
	 * @return pathogen
	 */
	public Pathogen findPathogenByKeggOrganism( String keggOrg ) {
		QPathogen qPathogen = QPathogen.pathogen;
		BooleanExpression expression = qPathogen.keggOrganism.eq( keggOrg );
		Pathogen pathogen = this.repository.findOne( expression );

		return pathogen;
	}

	/**
	 * reference count
	 * @param id  ID
	 * @return reference count
	 */
	public Long referenceCount( Integer id ) {
		QPathogensRef qRef = QPathogensRef.pathogensRef;
		BooleanExpression expression = qRef.pathogen.id.eq( id );
		Long count = this.refRepository.count( expression );
		return count;
	}

	/**
	 * drug count
	 * @param id ID
	 * @return drug count
	 */
	public Long drugCount( Integer id ) {
		QPathogensDrug qDrug = QPathogensDrug.pathogensDrug;
		BooleanExpression expression = qDrug.pathogen.id.eq( id );
		Long count = this.drugRepository.count( expression );
		return count;
	}

	/**
	 * disease count
	 * @param id ID
	 * @return disease count
	 */
	public Long diseaseCount( Integer id ) {
		QPathogensDiseas qDisease = QPathogensDiseas.pathogensDiseas;
		BooleanExpression expression = qDisease.pathogen.id.eq( id );
		Long count = this.diseaseRepository.count( expression );
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

		Page< Pathogen > pathogens = null;
		if( keyword.isEmpty() ) {
			pathogens = this.repository.findAll( pageable );
		}
		else {
			QPathogen qPathogen = QPathogen.pathogen;
			StringPath[] columns = {
				qPathogen.name, qPathogen.identifier, qPathogen.description, qPathogen.abbr, qPathogen.alias,
				qPathogen.antigenicvariation, qPathogen.bacteriaAppendages, qPathogen.bacteriaGram,
				qPathogen.bacteriaMorphology, qPathogen.bacteriaPlasmids, qPathogen.bacteriaSize, qPathogen.chromosomes,
				qPathogen.distribution, qPathogen.codonusage, qPathogen.dtype, qPathogen.gccontent, qPathogen.genome,
				qPathogen.hosts, qPathogen.kegg, qPathogen.keggOrganism, qPathogen.keggPathway, qPathogen.lifecycle,
				qPathogen.notes,  qPathogen.taxgroup, qPathogen.virusBaltimore, qPathogen.virusNucleicacidtype,
				qPathogen.virusSense, qPathogen.virusShape, qPathogen.virusSize, qPathogen.virusStrandedness
			};

			BooleanExpression expression = DatabaseTool.getExpression( columns, keyword );

			pathogens = this.repository.findAll( expression, pageable );
			count = pathogens.getTotalElements();
		}

		ArrayList< PathogenItem > items = new ArrayList< PathogenItem >();

		pathogens.forEach(
			( pathogen ) -> {
				PathogenItem item = PathogenService.convertPathogenToItem( pathogen );
				items.add( item );
			}
		);

		Page< PathogenItem > itemsPage = new PageImpl< PathogenItem >( items, pageable, count );
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
	public PageResult diseasePage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "accession" ) || sort.equals( "link" ) ) {
			sort = "identifier";
		}
		sort = "diseas." + sort;

		QPathogensDiseas qDiseas = QPathogensDiseas.pathogensDiseas;
		BooleanExpression expression = qDiseas.pathogen.id.eq( id );
		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		Page< PathogensDiseas > diseases = this.diseaseRepository.findAll( expression, pageable );
		Long count = diseases.getTotalElements();

		ArrayList< DiseaseItem > items = new ArrayList< DiseaseItem >();

		diseases.forEach(
			( disease ) -> {
				DiseaseItem item = DiseaseService.convertDiseaseToItem( disease.getDiseas() );
				items.add( item );
			}
		);

		Page< DiseaseItem > itemsPage = new PageImpl< DiseaseItem >( items, pageable, count );
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

		QPathogensDrug qDrug = QPathogensDrug.pathogensDrug;
		BooleanExpression expression = qDrug.pathogen.id.eq( id );
		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		Page< PathogensDrug > drugs = this.drugRepository.findAll( expression, pageable );
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

		QPathogensRef qRef = QPathogensRef.pathogensRef;
		BooleanExpression expression = qRef.pathogen.id.eq( id );
		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		Page< PathogensRef > refs = this.refRepository.findAll( expression, pageable );
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
	 * convert pathogen to pathogen item
	 * @param pathogen pathogen
	 * @return pathogen item
	 */
	public static PathogenItem convertPathogenToItem( Pathogen pathogen ) {
		Integer id = pathogen.getId();
		String accession = pathogen.getIdentifier();
		String link = "<a href=\"pathogen?id=" + id + "\">" + accession + "</a>";
		String genome = pathogen.getGenome();

		PathogenItem item = new PathogenItem();
		item.setId( id );
		item.setAccession( accession );
		item.setLink( link );
		item.setName( pathogen.getName() );
		item.setDescription( pathogen.getDescription() );
		item.setAbbr( pathogen.getAbbr() );
		item.setAlias( pathogen.getAlias() );
		item.setAntigenicvariation( pathogen.getAntigenicvariation() );
		item.setBacteriaAppendages( pathogen.getBacteriaAppendages() );
		item.setBacteriaGram( pathogen.getBacteriaGram() );
		item.setBacteriaMorphology( pathogen.getBacteriaMorphology() );
		item.setBacteriaPlasmids( pathogen.getBacteriaPlasmids() );
		item.setBacteriaSize( pathogen.getBacteriaSize() );
		item.setChromosomes( pathogen.getChromosomes() );
		item.setCodonusage( pathogen.getCodonusage() );
		item.setDistribution( pathogen.getDistribution() );
		item.setDtype( pathogen.getDtype() );
		item.setGccontent( pathogen.getGccontent() );
		item.setGenome( genome );
		item.setHosts( pathogen.getHosts() );
		item.setHtml( pathogen.getHtml() );
		item.setKegg( pathogen.getKegg() );
		item.setKeggOrganism( pathogen.getKeggOrganism() );
		item.setKeggPathway( pathogen.getKeggPathway() );
		item.setLifecycle( pathogen.getLifecycle() );
		item.setNotes( pathogen.getNotes() );
		item.setNumbases( pathogen.getNumbases() );
		item.setNumgenes( pathogen.getNumgenes() );
		item.setNumproteins( pathogen.getNumproteins() );
		item.setNumsequences( pathogen.getNumsequences() );
		item.setTaxgroup( pathogen.getTaxgroup() );
		item.setUrl( pathogen.getUrl() );
		item.setVirusBaltimore( pathogen.getVirusBaltimore() );
		item.setVirusNucleicacidtype( pathogen.getVirusNucleicacidtype() );
		item.setVirusSense( pathogen.getVirusSense() );
		item.setVirusShape( pathogen.getVirusShape() );
		item.setVirusSize( pathogen.getVirusSize() );
		item.setVirusStrandedness( pathogen.getVirusStrandedness() );

		return item;
	}
}
