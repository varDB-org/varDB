package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.Pathogen;
import org.vardb.model.entity.QPathogen;
import org.vardb.model.entity.QTaxon;
import org.vardb.model.entity.Taxon;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.PathogenItem;
import org.vardb.model.response.TaxonItem;
import org.vardb.repository.PathogenRepository;
import org.vardb.repository.TaxonomyRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class TaxonomyService {
	@Autowired
	TaxonomyRepository repository;

	@Autowired
	PathogenRepository pathogenRepository;

	/**
	 * finds all taxonomies
	 * @return all taxonomies
	 */
	public List< TaxonItem > findAll() {
		List< Taxon > taxons = this.repository.findAll();
		ArrayList< TaxonItem > array = new ArrayList< TaxonItem >();

		for( Taxon taxon : taxons ) {
			TaxonItem item = TaxonomyService.convertTaxonToItem( taxon );
			array.add( item );
		}

		return array;
	}

	/**
	 * finds taxonomy
	 * @param id ID
	 * @return taxonomy
	 */
	public Taxon findTaxon( Integer id ) {
		Taxon taxon = this.repository.findOne( id );
		return taxon;
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

		Page< Taxon > taxons = null;
		if( keyword.isEmpty() ) {
			taxons = this.repository.findAll( pageable );
		}
		else {
			QTaxon qTaxon = QTaxon.taxon1;
			StringPath[] columns = {
				qTaxon.name, qTaxon.identifier, qTaxon.description, qTaxon.level
			};

			BooleanExpression expression = DatabaseTool.getExpression( columns, keyword );

			taxons = this.repository.findAll( expression, pageable );
			count = taxons.getTotalElements();
		}

		ArrayList< TaxonItem > items = new ArrayList< TaxonItem >();

		taxons.forEach(
			( taxon ) -> {
				TaxonItem item = TaxonomyService.convertTaxonToItem( taxon );
				items.add( item );
			}
		);

		Page< TaxonItem > itemsPage = new PageImpl< TaxonItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( total );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * gets the children page information
	 * @param page page number
	 * @param size page size
	 * @param sort sort field
	 * @param dir sort direction
	 * @return page result
	 */
	public PageResult childrenPage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "accession" ) || sort.equals( "link" ) ) {
			sort = "identifier";
		}

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QTaxon qTaxon = QTaxon.taxon1;
		BooleanExpression expression = qTaxon.taxon.id.eq( id );
		Page< Taxon > taxons = this.repository.findAll( expression, pageable );
		Long count = taxons.getTotalElements();

		ArrayList< TaxonItem > items = new ArrayList< TaxonItem >();

		taxons.forEach(
			( taxon ) -> {
				TaxonItem item = TaxonomyService.convertTaxonToItem( taxon );
				items.add( item );
			}
		);

		Page< TaxonItem > itemsPage = new PageImpl< TaxonItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( count );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * gets the children page information
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

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QPathogen qPathogen = QPathogen.pathogen;
		BooleanExpression expression = qPathogen.taxon.id.eq( id );
		Page< Pathogen > taxons = this.pathogenRepository.findAll( expression, pageable );
		Long count = taxons.getTotalElements();

		ArrayList< PathogenItem > items = new ArrayList< PathogenItem >();

		taxons.forEach(
			( pathogen ) -> {
				PathogenItem item = PathogenService.convertPathogenToItem( pathogen );
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
	 * convert taxonomy to taxon item
	 * @param taxon taxonomy
	 * @return taxon item
	 */
	public static TaxonItem convertTaxonToItem( Taxon taxon ) {
		Integer id = taxon.getId();
		String accession = taxon.getIdentifier();
		String link = "<a href=\"taxon?id=" + id + "\">" + accession + "</a>";

		TaxonItem item = new TaxonItem();
		item.setId( id );
		item.setAccession( accession );
		item.setName( taxon.getName() );
		item.setLink( link );
		item.setDescription( taxon.getDescription() );
		item.setLevel( taxon.getLevel() );
		item.setTaxid( taxon.getTaxid() );
		item.setLft( taxon.getLft() );
		item.setRght( taxon.getRght() );

		Taxon parent = taxon.getTaxon();
		if( parent != null ) {
			id = taxon.getId();
			accession = taxon.getIdentifier();
			link = "<a href=\"taxon?id=\"" + id + "\">" + accession + "</a>";
			item.setParent( link );
		}

		return item;
	}
}
