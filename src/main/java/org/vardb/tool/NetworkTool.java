package org.vardb.tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

public class NetworkTool {
	/**
	 * gets the network content
	 * @param url url
	 * @return content
	 */
	public static String getContent( String url ) {
		String content = null;

		try {
			StringWriter string = new StringWriter();
			PrintWriter writer = new PrintWriter( string );

			URLConnection connection = new URL( url ).openConnection();

			BufferedReader reader = new BufferedReader(
				new InputStreamReader(
					connection.getInputStream()
				)
			);

			String line = null;

			while( ( line = reader.readLine() ) != null ) {
				writer.println( line );
			}

			reader.close();
			writer.close();
			string.close();

			content = string.toString();

		}
		catch( Exception e ) {
			e.printStackTrace();
		}

		return content;
	}
}
