package org.vardb.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;
import org.vardb.model.response.KeggOrthologCategory;

@Service
public class KeggOrthologService {
	/**
	 * gets ortholog categories
	 * @param orthologId KEGG Ortholog
	 * @return KEGG Ortholog categories
	 */
	public List< KeggOrthologCategory > getOrthologCategories( String orthologId ) throws Exception {
		List< KeggOrthologCategory > array = new ArrayList< KeggOrthologCategory >();

		URL url = new URL( "http://rest.genome.jp/oc/" + orthologId );
		URLConnection connection = url.openConnection();
		BufferedReader reader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );

		String line = null;
		while( ( line = reader.readLine() ) != null )  {
			line = line.trim();
			if( !line.startsWith( "#" ) ) {
				StringTokenizer tokenizer = new StringTokenizer( line );
				ArrayList< String > tokens = new ArrayList< String >();
				while( tokenizer.hasMoreTokens() ) {
					tokens.add( tokenizer.nextToken() );
				}

				if( tokens.size() >= 4 ) {
					KeggOrthologCategory category = new KeggOrthologCategory();
					category.setId( tokens.get( 0 ) );
					category.setHits( Integer.parseInt( tokens.get( 1 ) ) );
					category.setSize( Integer.parseInt( tokens.get( 2 ) ) );
					category.setType( tokens.get( 3 ) );
					array.add( category );
				}
			}

		}

		reader.close();

		return array;
	}
}
