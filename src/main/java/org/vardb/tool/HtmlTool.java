package org.vardb.tool;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.vardb.model.response.PageInfo;

/**
 * HTML tool
 */
public class HtmlTool {
	/**
	 * gets the pages
	 * @return pages
	 */
	public static List< PageInfo > getPages() {
		ArrayList< PageInfo > array = new ArrayList< PageInfo >();

		array.add( new PageInfo( "family", "Families" ) );
		array.add( new PageInfo( "ortholog", "Orthologs" ) );
		array.add( new PageInfo( "paralog", "Paralogs" ) );
		array.add( new PageInfo( "taxon", "Taxonomies" ) );
		array.add( new PageInfo( "disease", "Diseases" ) );
		array.add( new PageInfo( "pathogen", "Pathogens" ) );
		array.add( new PageInfo( "drug", "Drugs" ) );
		array.add( new PageInfo( "reference", "References" ) );

		return array;
	}

	/**
	 * gets the page request
	 * @param page
	 * @param size
	 * @param sort
	 * @param dir
	 * @return
	 */
	public static Pageable getPageRequest( Integer page, Integer size, String sort, String dir ) {
		Sort.Direction direction = Sort.Direction.ASC;
		if( dir.equals( "desc" ) ) {
			direction = Sort.Direction.DESC;
		}
		 PageRequest request = new PageRequest( ( page - 1 ), size, new Sort( new Order( direction, sort ) ) );
		 return request;
	}
}
