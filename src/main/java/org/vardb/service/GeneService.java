package org.vardb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vardb.model.GeneManager;
import org.vardb.model.response.GeneItem;
import org.vardb.model.response.PageResult;
import org.vardb.tool.NetworkTool;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeneService {
	/**
	 * gets the page information
	 * @param page page number
	 * @param size page size
	 * @param sort sort field
	 * @param dir sort direction
	 * @return page result
	 */
	public PageResult page( String family, Integer page, Integer size, String sort, String dir ) {
		PageResult result = new PageResult();

		GeneManager geneManager = GeneManager.getInstance();

		List< GeneItem > genes = geneManager.getGenes( family );
		if( genes == null ) {
			return result;
		}

		long count = (long)genes.size();

		GeneService.sortGenes( genes, sort, dir );

		ArrayList< GeneItem > items = new ArrayList< GeneItem >();
		int start = ( page - 1 ) * size;
		int end = start + size - 1;
		if( end >= genes.size() ) {
			end = genes.size() - 1;
		}
		for( int i = start; i <= end; i++ ) {
			items.add( genes.get( i ) );
		}

		int last = (int)Math.ceil( (double)count / (double)size );
		if( last == 0 ) {
			last = 1;
		}

		result.setCurrent_page( page );
		result.setPage_size( size );
		result.setTotal_count( count );
		result.setFiltered_count( count );
		result.setLast_page( last );
		result.setData( items );

		return result;
	}

	/**
	 * gets the gene count
	 * @param family family name
	 * @return gene count
	 */
	public Integer geneCount( String family ) {
		GeneManager geneManager = GeneManager.getInstance();
		List< GeneItem > genes = geneManager.getGenes( family );
		if( genes == null ) {
			return 0;
		}
		return genes.size();
	}

	/**
	 * gets the KEGG Gene
	 * @param gene Kegg Gene
	 * @return KEGG gene
	 */
	public Map< String, Object > getKeggGene( GeneItem gene ) {
		Map< String, Object > map = null;

		String url = this.getTogoWsUrl( gene );
		String content = NetworkTool.getContent( url );
		if( content == null ) {
			return map;
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference< ArrayList< HashMap< String, Object > > > typeRef = new TypeReference< ArrayList< HashMap< String, Object > > >(){};
			ArrayList< HashMap< String, Object > > list = mapper.readValue( content, typeRef );
			if( list != null && list.size() > 0 ) {
				map = list.get( 0 );
			}
		}
		catch( Exception e ) {
			e.printStackTrace();
		}

		return map;
	}

	/**
	 *
	 * gets the Togo WS URL
	 * @param gene gene
	 * @return Togo WS URL
	 */
	public String getTogoWsUrl( GeneItem gene ) {
		String url = "http://togows.org/entry/kegg-genes/" + gene.getSpecies() + ":" + gene.getId() + ".json";
		return url;
	}

	/**
	 * sorts genes
	 * @param genes genes
	 * @param sort sort field
	 * @param dir sort direction
	 */
	private static void sortGenes( List< GeneItem > genes, String sort, String dir ) {
		genes.sort(
			( gene1, gene2 ) -> {
				int cmp = 0;
				if( sort.equals( "id" ) || sort.equals( "link" ) ) {
					cmp = gene1.getId().compareTo( gene2.getId() );
				}
				else if( sort.equals( "description" ) ) {
					cmp = gene1.getDescription().compareTo( gene2.getDescription() );
				}
				else if( sort.equals( "species" ) ){
					cmp = gene1.getSpecies().compareTo( gene2.getSpecies() );
				}
				else if( sort.equals( "family" ) ) {
					cmp = gene1.getFamily().compareTo( gene2.getFamily() );
				}
				else if( sort.equals( "aaLength" ) ) {
					cmp = gene1.getAaLength() - gene2.getAaLength();
				}
				else if( sort.equals( "ntLength" ) ) {
					cmp = gene1.getNtLength() - gene2.getNtLength();
				}

				if( dir.equals( "desc" ) ) {
					cmp = - cmp;
				}

				return cmp;
			}
		);
	}
}
