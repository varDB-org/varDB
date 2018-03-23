package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.DiseasesDrug;
import org.vardb.model.entity.Drug;
import org.vardb.model.entity.PathogensDrug;
import org.vardb.model.entity.QDiseasesDrug;
import org.vardb.model.entity.QDrug;
import org.vardb.model.entity.QPathogensDrug;
import org.vardb.model.response.DiseaseItem;
import org.vardb.model.response.DrugItem;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.PathogenItem;
import org.vardb.repository.DiseasesDrugRepository;
import org.vardb.repository.DrugRepository;
import org.vardb.repository.PathogensDrugRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class DrugService {
	@Autowired
	DrugRepository repository;

	@Autowired
	DiseasesDrugRepository diseaseRepository;

	@Autowired
	PathogensDrugRepository pathogenRepository;

	/**
	 * finds all drugss
	 * @return all drug
	 */
	public List< DrugItem > findAll() {
		List< Drug > drugs = this.repository.findAll();
		ArrayList< DrugItem > array = new ArrayList< DrugItem >();

		for( Drug drug : drugs ) {
			DrugItem item = DrugService.convertDrugToItem( drug );
			array.add( item );
		}

		return array;
	}

	/**
	 * finds drug
	 * @param id drug ID
	 * @return drug
	 */
	public Drug findDrug( Integer id ) {
		Drug drug = this.repository.findOne( id );
		return drug;
	}

	/**
	 * pathogen count
	 * @param id ID
	 * @return pathogen count
	 */
	public Long pathogenCount( Integer id ) {
		QPathogensDrug qPathogen = QPathogensDrug.pathogensDrug;
		BooleanExpression expression = qPathogen.drug.id.eq( id );
		Long count = this.pathogenRepository.count( expression );
		return count;
	}

	/**
	 * disease count
	 * @param id ID
	 * @return disease count
	 */
	public Long diseaseCount( Integer id ) {
		QDiseasesDrug qDisease = QDiseasesDrug.diseasesDrug;
		BooleanExpression expression = qDisease.drug.id.eq( id );
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

		Page< Drug > drugs = null;
		if( keyword.isEmpty() ) {
			drugs = this.repository.findAll( pageable );
		}
		else {
			QDrug qDrug = QDrug.drug;
			StringPath[] columns = {
				qDrug.name, qDrug.identifier, qDrug.description, qDrug.activity, qDrug.formula, qDrug.mass,
				qDrug.names, qDrug.notes, qDrug.target
			};

			BooleanExpression expression = DatabaseTool.getExpression( columns, keyword );

			drugs = this.repository.findAll( expression, pageable );
			count = drugs.getTotalElements();
		}

		ArrayList< DrugItem > items = new ArrayList< DrugItem >();

		drugs.forEach(
			( drug ) -> {
				DrugItem item = DrugService.convertDrugToItem( drug );
				items.add( item );
			}
		);

		Page< DrugItem > itemsPage = new PageImpl< DrugItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( total );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * gets the pathogen page information
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

		QPathogensDrug qPathogen = QPathogensDrug.pathogensDrug;
		BooleanExpression expression = qPathogen.drug.id.eq( id );

		Page< PathogensDrug > pathogens = this.pathogenRepository.findAll( expression, pageable );
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
	 * gets the disease page information
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

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QDiseasesDrug qDisease = QDiseasesDrug.diseasesDrug;
		BooleanExpression expression = qDisease.drug.id.eq( id );

		Page< DiseasesDrug > diseases = this.diseaseRepository.findAll( expression, pageable );
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
	 * convert drug to drug item
	 * @param drug drug
	 * @return drug item
	 */
	public static DrugItem convertDrugToItem( Drug drug ) {
		Integer id = drug.getId();
		String accession = drug.getIdentifier();
		String link = "<a href=\"drug?id=" + id + "\">" + accession + "</a>";

		DrugItem item = new DrugItem();
		item.setId( id );
		item.setAccession( accession );
		item.setLink( link );
		item.setName( drug.getName() );
		item.setDescription( drug.getDescription() );
		item.setActivity( drug.getActivity() );
		item.setFormula( drug.getFormula() );
		item.setMass( drug.getMass() );
		item.setNames( drug.getNames() );
		item.setNotes( drug.getNotes() );
		item.setTarget( drug.getTarget() );
		item.setNumsequences( drug.getNumsequences() );

		return item;
	}
}
