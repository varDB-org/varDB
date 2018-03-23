package org.vardb.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * string tool
 */
public class StringTool {
	/**
	 * splits string
	 * @param target target string
	 * @param separator separator
	 * @return string array
	 */
	public static List< String > split( String target, String separator ) {
		if( target == null || separator == null ) {
			return null;
		}

		String string = target;

		ArrayList< String > tokens = new ArrayList< String >();
		int index = 0;
		while( ( index = string.indexOf( separator ) ) >= 0 ) {
			tokens.add( string.substring( 0, index ) );
			string = string.substring( index + separator.length() );
		}
		tokens.add( string );

		return tokens;

	}
}
