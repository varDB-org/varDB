package org.vardb.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vardb.model.response.GeneItem;
import org.vardb.tool.StringTool;

/**
 * gene manager
 */
public class GeneManager {
	/** singleton object */
	private static GeneManager instance = null;

	/** family map */
	private Map< String, List< GeneItem > > familyMap;

	/** gene map */
	private Map< String, GeneItem > geneMap;

	/**
	 * constructor
	 */
	private GeneManager() {
		this.familyMap = new HashMap< String, List< GeneItem > >();
		this.geneMap = new HashMap< String, GeneItem >();

		this.loadGenes();
	}

	/**
	 * loads genes
	 */
	private void loadGenes() {
		String[] families = {
			"bir", "cir", "cyir", "ema", "kir", "rifin", "stevor", "var", "ves", "vir", "vsg", "vsp", "yir"
		};

		for( String family: families ) {
			List< GeneItem > genes = this.loadFamily( family );
			if( genes != null ) {
				this.familyMap.put( family,  genes );
				for( GeneItem gene : genes ) {
					this.geneMap.put( gene.getId(),  gene );
				}
			}
		}
	}

	/**
	 * loads family
	 * @param family
	 * @return
	 */
	private List< GeneItem > loadFamily( String family ) {
		ArrayList< GeneItem > genes = new ArrayList< GeneItem >();

		try {
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(
					this.getClass().getResourceAsStream( "/genes/" + family + ".txt" )
				)
			);

			String line = reader.readLine();
			while( ( line = reader.readLine() ) != null ) {
				List< String > tokens = StringTool.split( line,  "\t" );
				GeneItem gene = this.readGene( tokens );
				if( gene != null ) {
					genes.add( gene );
				}
			}

			reader.close();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}

		return genes;
	}

	/**
	 * gets genes
	 * @param family family name
	 * @return genes
	 */
	public List< GeneItem > getGenes( String family ) {
		List< GeneItem > genes = null;
		if( this.familyMap.containsKey( family ) ) {
			genes = this.familyMap.get( family );
		}
		return genes;
	}

	/**
	 * gets the gene
	 * @param id gene ID
	 * @return gene
	 */
	public GeneItem getGene( String id ) {
		GeneItem gene = null;
		if( this.geneMap.containsKey( id ) ) {
			gene = this.geneMap.get( id );
		}
		return gene;
	}

	/**
	 * loads gene
	 * @param tokens tokens
	 * @return gene
	 */
	private GeneItem readGene( List< String > tokens ) {
		if( tokens.size() < 10 ) {
			return null;
		}

		String description = tokens.get( 5 );
		int index = description.indexOf( " " );
		if( index < 0 ) {
			return null;
		}

		String id = description.substring( 0, index );
		String link = "<a href=\"gene?id=" + id + "\">" + id + "</a>";

		GeneItem gene = new GeneItem();
		gene.setId( id );
		gene.setLink( link );
		gene.setDescription( description.substring( index + 1 ).trim() );
		gene.setFamily( tokens.get( 0 ) );
		gene.setSpecies( tokens.get( 1 ) );
		gene.setOc( tokens.get( 7 ) );
		try {
			gene.setAaLength( Integer.parseInt( tokens.get( 8 ) ) );
		}
		catch( Exception e ) {
		}
		try {
			gene.setNtLength( Integer.parseInt( tokens.get( 9 ) ) );
		}
		catch( Exception e ) {
		}

		return gene;
	}

	/**
	 * gets the instance
	 * @return gene manager instance
	 */
	public static GeneManager getInstance() {
		if( GeneManager.instance == null ) {
			GeneManager.instance = new GeneManager();
		}
		return GeneManager.instance;
	}
}
