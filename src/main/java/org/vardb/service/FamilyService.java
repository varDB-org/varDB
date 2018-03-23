package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.FamiliesRef;
import org.vardb.model.entity.Family;
import org.vardb.model.entity.Ortholog;
import org.vardb.model.entity.Pathogen;
import org.vardb.model.entity.QFamiliesRef;
import org.vardb.model.entity.QFamily;
import org.vardb.model.response.FamilyItem;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.ReferenceItem;
import org.vardb.repository.FamiliesRefRepository;
import org.vardb.repository.FamilyRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class FamilyService {
	@Autowired
	FamilyRepository repository;

	@Autowired
	FamiliesRefRepository refRepository;

	/**
	 * finds all families
	 * @return all families
	 */
	public List< FamilyItem > findAll() {
		List< Family > families = this.repository.findAll();
		ArrayList< FamilyItem > array = new ArrayList< FamilyItem >();

		for( Family family : families ) {
			FamilyItem item = FamilyService.convertFamilyToItem( family );
			array.add( item );
		}

		return array;
	}

	/**
	 * finds family
	 * @param id family ID
	 * @return family
	 */
	public Family findFamily( Integer id ) {
		Family family = this.repository.getOne( id );
		return family;
	}

	public Family findFamily( String name ) {
		QFamily qFamily = QFamily.family;
		BooleanExpression expression = qFamily.name.eq( name );
		Family family = this.repository.findOne( expression );
		return family;
	}

	/**
	 * reference count
	 * @param id family ID
	 * @return reference count
	 */
	public Long referenceCount( Integer id ) {
		QFamiliesRef qRef = QFamiliesRef.familiesRef;
		BooleanExpression expression = qRef.family.id.eq( id );
		Long count = this.refRepository.count( expression );
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

		Page< Family > families = null;
		if( keyword.isEmpty() ) {
			families = this.repository.findAll( pageable );
		}
		else {
			QFamily qFamily = QFamily.family;
			StringPath[] columns = {
				qFamily.name, qFamily.abbr, qFamily.description, qFamily.pathogen.name, qFamily.ortholog.name
			};

			BooleanExpression expression = DatabaseTool.getExpression( columns, keyword );

			families = this.repository.findAll( expression, pageable );
			count = families.getTotalElements();
		}

		ArrayList< FamilyItem > items = new ArrayList< FamilyItem >();

		families.forEach(
			( family ) -> {
				FamilyItem item = FamilyService.convertFamilyToItem( family );
				items.add( item );
			}
		);

		Page< FamilyItem > itemsPage = new PageImpl< FamilyItem >( items, pageable, count );
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

		QFamiliesRef qFamilyRef = QFamiliesRef.familiesRef;
		BooleanExpression expression = qFamilyRef.family.id.eq( id );

		Page< FamiliesRef > refs = this.refRepository.findAll( expression, pageable );
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
	 * convert family to family item
	 * @param family family
	 * @return family item
	 */
	public static FamilyItem convertFamilyToItem( Family family ) {
		Pathogen pathogen = family.getPathogen();
		Ortholog ortholog = family.getOrtholog();

		Integer id = family.getId();

		String name = family.getName();
		String link = "<a href=\"family?id=" + id + "\">" + name + "</a>";

		String pathogenName = ( pathogen == null ? null : pathogen.getName() );
		if( pathogenName != null ) {
			pathogenName = "<a href=\"pathogen?id=" + pathogen.getId() + "\">" + pathogenName + "</a>";
		}

		String orthologName = ( ortholog == null ? null : ortholog.getName() );
		if( orthologName != null ) {
			orthologName = "<a href=\"ortholog?id=" + ortholog.getId() + "\">" + orthologName + "</a>";
		}

		FamilyItem item = new FamilyItem();
		item.setId( id );
		item.setName( name );
		item.setLink( link );
		item.setAbbr( family.getAbbr() );
		item.setPathogen( pathogenName );
		item.setOrtholog( orthologName );
		item.setDescription( family.getDescription() );
		item.setNumsequences( family.getNumsequences() );

		return item;
	}
}
