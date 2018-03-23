package org.vardb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vardb.model.entity.DiseasesRef;
import org.vardb.model.entity.FamiliesRef;
import org.vardb.model.entity.OrthologsRef;
import org.vardb.model.entity.PathogensRef;
import org.vardb.model.entity.QDiseasesRef;
import org.vardb.model.entity.QFamiliesRef;
import org.vardb.model.entity.QOrthologsRef;
import org.vardb.model.entity.QPathogensRef;
import org.vardb.model.entity.QRef;
import org.vardb.model.entity.Ref;
import org.vardb.model.response.DiseaseItem;
import org.vardb.model.response.FamilyItem;
import org.vardb.model.response.OrthologItem;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.PathogenItem;
import org.vardb.model.response.ReferenceItem;
import org.vardb.repository.DiseasesRefRepository;
import org.vardb.repository.FamiliesRefRepository;
import org.vardb.repository.OrthologsRefRepository;
import org.vardb.repository.PathogensRefRepository;
import org.vardb.repository.ReferenceRepository;
import org.vardb.tool.DatabaseTool;
import org.vardb.tool.HtmlTool;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

@Service
public class ReferenceService {
	@Autowired
	ReferenceRepository repository;

	@Autowired
	DiseasesRefRepository diseaseRepository;

	@Autowired
	FamiliesRefRepository familyRepository;

	@Autowired
	OrthologsRefRepository orthologRepository;

	@Autowired
	PathogensRefRepository pathogenRepository;

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
	 * finds reference
	 * @param id reference ID
	 * @return reference
	 */
	public Ref findReference( Integer id ) {
		Ref reference = this.repository.findOne( id );
		return reference;
	}

	/**
	 * family count
	 * @param id ID
	 * @return family count
	 */
	public Long familyCount( Integer id ) {
		QFamiliesRef qFamily = QFamiliesRef.familiesRef;
		BooleanExpression expression = qFamily.ref.id.eq( id );
		Long count = this.familyRepository.count( expression );
		return count;
	}

	/**
	 * ortholog count
	 * @param id ID
	 * @return ortholog count
	 */
	public Long orthologCount( Integer id ) {
		QOrthologsRef qOrtholog = QOrthologsRef.orthologsRef;
		BooleanExpression expression = qOrtholog.ref.id.eq( id );
		Long count = this.orthologRepository.count( expression );
		return count;
	}

	/**
	 * disease count
	 * @param id ID
	 * @return disease count
	 */
	public Long diseaseCount( Integer id ) {
		QDiseasesRef qDisease = QDiseasesRef.diseasesRef;
		BooleanExpression expression = qDisease.ref.id.eq( id );
		Long count = this.diseaseRepository.count( expression );
		return count;
	}

	/**
	 * pathogen count
	 * @param id ID
	 * @return pathogen count
	 */
	public Long pathogenCount( Integer id ) {
		QPathogensRef qPathogen = QPathogensRef.pathogensRef;
		BooleanExpression expression = qPathogen.ref.id.eq( id );
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
	 * gets the page information
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

		sort = "family." + sort;

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QFamiliesRef qFamily = QFamiliesRef.familiesRef;
		BooleanExpression expression = qFamily.ref.id.eq( id );

		Page< FamiliesRef > families = this.familyRepository.findAll( expression, pageable );
		Long count = families.getTotalElements();

		ArrayList< FamilyItem > items = new ArrayList< FamilyItem >();
		families.forEach(
			( family ) -> {
				FamilyItem item = FamilyService.convertFamilyToItem( family.getFamily() );
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
	 * gets the page information
	 * @param page page number
	 * @param size page size
	 * @param sort sort field
	 * @param dir sort direction
	 * @return page result
	 */
	public PageResult orthologPage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "accession" ) || sort.equals( "link") ) {
			sort = "identifier";
		}
		sort = "ortholog." + sort;

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QOrthologsRef qOrtholog = QOrthologsRef.orthologsRef;
		BooleanExpression expression = qOrtholog.ref.id.eq( id );

		Page< OrthologsRef > orthologs = this.orthologRepository.findAll( expression, pageable );
		Long count = orthologs.getTotalElements();

		ArrayList< OrthologItem > items = new ArrayList< OrthologItem >();
		orthologs.forEach(
			( ortholog ) -> {
				OrthologItem item = OrthologService.convertOrthologToItem( ortholog.getOrtholog() );
				items.add( item );
			}
		);
		Page< OrthologItem > itemsPage = new PageImpl< OrthologItem >( items, pageable, count );
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
	public PageResult diseasePage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "accession" ) || sort.equals( "link") ) {
			sort = "identifier";
		}
		sort = "diseas." + sort;

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QDiseasesRef qDisease = QDiseasesRef.diseasesRef;
		BooleanExpression expression = qDisease.ref.id.eq( id );

		Page< DiseasesRef > diseases = this.diseaseRepository.findAll( expression, pageable );
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
	public PageResult pathogenPage( Integer id, Integer page, Integer size, String sort, String dir ) {
		if( sort.equals( "accession" ) || sort.equals( "link") ) {
			sort = "identifier";
		}
		sort = "pathogen." + sort;

		Pageable pageable = HtmlTool.getPageRequest( page,  size,  sort,  dir );

		QPathogensRef qParhogen = QPathogensRef.pathogensRef;
		BooleanExpression expression = qParhogen.ref.id.eq( id );

		Page< PathogensRef > pathogens = this.pathogenRepository.findAll( expression, pageable );
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
	 * convert reference to reference item
	 * @param reference reference
	 * @return reference item
	 */
	public static ReferenceItem convertReferenceToItem( Ref reference ) {
		ReferenceItem item = new ReferenceItem();

		Integer id = reference.getId();
		String name = reference.getName();
		String link = "<a href=\"reference?id=" + id + "\">" + name + "</a>";
		Integer pmid = reference.getPmid();
		String pmidLink = null;
		if( pmid != null ) {
			pmidLink = "<a href=\"http://www.ncbi.nlm.nih.gov/pubmed/" + pmid + "\">" + pmid + "</a>";
		}

		item.setId( id );
		item.setAccession( name );
		item.setName( name );
		item.setLink( link );
		item.setType( reference.getType() );
		item.setTitle( reference.getTitle() );
		item.setAuthors( reference.getAuthors() );
		item.setJournal( reference.getJournal() );
		item.setYear( reference.getYear() );
		item.setPages( reference.getPages() );
		item.setPmid( pmidLink );
		item.setPublisher( reference.getPublisher() );
		item.setAbstract( reference.getAbstract_() );
		item.setCity( reference.getCity() );
		item.setVisible( reference.getVisible() ? "*" : "" );
		item.setVolume( reference.getVolume() );
		item.setNumsequences( reference.getNumsequences() );

		return item;
	}
}
