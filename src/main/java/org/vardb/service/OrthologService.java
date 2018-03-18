package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.Family;
import org.vardb.model.entity.Ortholog;
import org.vardb.model.entity.OrthologsRef;
import org.vardb.model.entity.QFamily;
import org.vardb.model.entity.QOrtholog;
import org.vardb.model.entity.QOrthologsRef;
import org.vardb.model.response.FamilyItem;
import org.vardb.model.response.OrthologItem;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.ReferenceItem;
import org.vardb.repository.FamilyRepository;
import org.vardb.repository.OrthologRepository;
import org.vardb.repository.OrthologsRefRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class OrthologService {
	@Autowired
	OrthologRepository repository;

	@Autowired
	OrthologsRefRepository refRepository;

	@Autowired
	FamilyRepository familyRepository;

	/**
	 * finds all orthologs
	 * @return all orthologs
	 */
	public List< OrthologItem > findAll() {
		List< Ortholog > orthologs = this.repository.findAll();
		ArrayList< OrthologItem > array = new ArrayList< OrthologItem >();

		for( Ortholog ortholog : orthologs ) {
			OrthologItem item = OrthologService.convertOrthologToItem( ortholog );
			array.add( item );
		}

		return array;
	}

	/**
	 * finds ortholog
	 * @param id ID
	 * @return ortholog
	 */
	public Ortholog findOrtholog( Integer id ) {
		Ortholog ortholog = this.repository.getOne( id );
		return ortholog;
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

		if( sort.equals( "accession" ) || sort.equals( "link") ) {
			sort = "identifier";
		}

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		Page< Ortholog > orthologs = null;
		if( keyword.isEmpty() ) {
			orthologs = this.repository.findAll( pageable );
		}
		else {
			QOrtholog qOrtholog = QOrtholog.ortholog;
			StringPath[] columns = {
				qOrtholog.name, qOrtholog.identifier, qOrtholog.description, qOrtholog.keggOrtholog, qOrtholog.keggPathway
			};

			BooleanExpression expression = DatabaseTool.getExpression( columns, keyword );

			orthologs = this.repository.findAll( expression, pageable );
			count = orthologs.getTotalElements();
		}

		ArrayList< OrthologItem > items = new ArrayList< OrthologItem >();

		orthologs.forEach(
			( ortholog ) -> {
				OrthologItem item = OrthologService.convertOrthologToItem( ortholog );
				items.add( item );
			}
		);

		Page< OrthologItem > itemsPage = new PageImpl< OrthologItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( total );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * gets the references page information
	 * @param id family ID
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

		QOrthologsRef qFamilyRef = QOrthologsRef.orthologsRef;
		BooleanExpression expression = qFamilyRef.ortholog.id.eq( id );

		Page< OrthologsRef > refs = this.refRepository.findAll( expression, pageable );
		long count = refs.getTotalElements();

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
	 * gets the references page information
	 * @param id family ID
	 * @param page page number
	 * @param size page size
	 * @param sort sort field
	 * @param dir sort direction
	 * @return page result
	 */
	public PageResult familyPage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "link" ) ) {
			sort = "name";
		}
		else if( sort.equals( "pathogen" ) ) {
			sort = "pathogen.name";
		}
		else if( sort.equals( "ortholog" ) ) {
			sort = "ortholog.name";
		}

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );
		QFamily qFamily = QFamily.family;
		BooleanExpression expression = qFamily.ortholog.id.eq( id );


		Page< Family > families = this.familyRepository.findAll( expression, pageable );
		long count = families.getTotalElements();

		ArrayList< FamilyItem > items = new ArrayList< FamilyItem >();

		families.forEach(
			( family ) -> {
				FamilyItem item = FamilyService.convertFamilyToItem( family );
				Ortholog ortholog = family.getOrtholog();
				if( ortholog != null ) {
					item.setOrtholog( ortholog.getName() );
				}
				items.add( item );
			}
		);

		Page< FamilyItem > itemsPage = new PageImpl< FamilyItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( count );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * convert ortholog to ortholog item
	 * @param ortholog ortholog
	 * @return ortholog item
	 */
	public static OrthologItem convertOrthologToItem( Ortholog ortholog ) {
		Integer id = ortholog.getId();
		String accession = ortholog.getIdentifier();
		String link = "<a href=\"ortholog?id=" + id + "\">" + accession + "</a>";

		OrthologItem item = new OrthologItem();
		item.setId( id );
		item.setAccession( accession );
		item.setLink( link );
		item.setName( ortholog.getName() );
		item.setKeggPathway( ortholog.getKeggPathway() );
		item.setKeggOrtholog( ortholog.getKeggOrtholog() );

		return item;
	}
}
