package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.Paralog;
import org.vardb.model.entity.QParalog;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.ParalogItem;
import org.vardb.repository.ParalogRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class ParalogService {
	@Autowired
	ParalogRepository repository;

	/**
	 * finds all paralogs
	 * @return all paralogs
	 */
	public List< ParalogItem > findAll() {
		List< Paralog > paralogs = this.repository.findAll();
		ArrayList< ParalogItem > array = new ArrayList< ParalogItem >();

		for( Paralog paralog : paralogs ) {
			ParalogItem item = ParalogService.convertParalogToItem( paralog );
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

		if( sort.equals( "accession" ) ) {
			sort = "identifier";
		}

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		Page< Paralog > paralogs = null;
		if( keyword.isEmpty() ) {
			paralogs = this.repository.findAll( pageable );
		}
		else {
			QParalog qParalog = QParalog.paralog;
			StringPath[] columns = {
				qParalog.name, qParalog.identifier, qParalog.family.name, qParalog.description
			};

			BooleanExpression expression = DatabaseTool.getExpression( columns, keyword );

			paralogs = this.repository.findAll( expression, pageable );
			count = paralogs.getTotalElements();
		}

		ArrayList< ParalogItem > items = new ArrayList< ParalogItem >();

		paralogs.forEach(
			( paralog ) -> {
				ParalogItem item = ParalogService.convertParalogToItem( paralog );
				items.add( item );
			}
		);

		Page< ParalogItem > itemsPage = new PageImpl< ParalogItem >( items, pageable, count );
		PageResult result = new PageResult( itemsPage );
		result.setTotal_count( total );
		result.setFiltered_count( count );

		return result;
	}

	/**
	 * convert paralog to paralog item
	 * @param paralog paralog
	 * @return paralog item
	 */
	public static ParalogItem convertParalogToItem( Paralog paralog ) {
		ParalogItem item = new ParalogItem();
		item.setId( paralog.getId() );
		item.setAccession( paralog.getIdentifier() );
		item.setName( paralog.getName() );
		item.setDescription( paralog.getDescription() );
		item.setAlignmendId( paralog.getAlignmentId() );
		item.setGenomeId( paralog.getGenomeId() );
		item.setNumsequence( paralog.getNumsequences() );
		item.setFamily( paralog.getFamily().getName() );

		return item;
	}
}
