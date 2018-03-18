package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.QRef;
import org.vardb.model.entity.Ref;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.ReferenceItem;
import org.vardb.repository.ReferenceRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class ReferenceService {
	@Autowired
	ReferenceRepository repository;

	/**
	 * finds all references
	 * @return all references
	 */
	public List< ReferenceItem > findAll() {
		List< Ref > refs = this.repository.findAll();
		ArrayList< ReferenceItem > array = new ArrayList< ReferenceItem >();

		for( Ref ref : refs ) {
			ReferenceItem item = ReferenceService.convertReferenceToItem( ref );
			array.add( item );
		}

		return array;
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

		Page< Ref > refs = null;
		if( keyword.isEmpty() ) {
			refs = this.repository.findAll( pageable );
		}
		else {
			QRef qRef = QRef.ref;
			StringPath[] columns = {
				qRef.name, qRef.identifier, qRef.type, qRef.title, qRef.authors, qRef.journal, qRef.year, qRef.pages,
				qRef.abstract_, qRef.city, qRef.volume
			};

			BooleanExpression expression = DatabaseTool.getExpression( columns, keyword );

			refs = this.repository.findAll( expression, pageable );
			count = refs.getTotalElements();
		}

		ArrayList< ReferenceItem > items = new ArrayList< ReferenceItem >();

		refs.forEach(
			( ref ) -> {
				ReferenceItem item = ReferenceService.convertReferenceToItem( ref );
				items.add( item );
			}
		);

		Page< ReferenceItem > itemsPage = new PageImpl< ReferenceItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( total );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * convert reference to reference item
	 * @param reference reference
	 * @return reference item
	 */
	public static ReferenceItem convertReferenceToItem( Ref reference ) {
		ReferenceItem item = new ReferenceItem();

		item.setId( reference.getId() );
		item.setAccession( reference.getIdentifier() );
		item.setName( reference.getName() );
		item.setType( reference.getType() );
		item.setTitle( reference.getTitle() );
		item.setAuthors( reference.getAuthors() );
		item.setJournal( reference.getJournal() );
		item.setYear( reference.getYear() );
		item.setPages( reference.getPages() );
		item.setPmid( reference.getPmid() );
		item.setPublisher( reference.getPublisher() );
		item.setAbstract( reference.getAbstract_() );
		item.setCity( reference.getCity() );
		item.setVisible( reference.getVisible() ? "*" : "" );
		item.setVolume( reference.getVolume() );
		item.setNumsequences( reference.getNumsequences() );

		return item;
	}
}
