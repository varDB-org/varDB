package org.vardb.tool;

import java.util.StringTokenizer;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringPath;

/**
 * database tool
 */
public class DatabaseTool {
	/**
	 * gets the keyword search expression
	 * @param columns columns
	 * @param keyword keyword
	 * @return expression
	 */
	public static BooleanExpression getExpression( StringPath[] columns, String keyword ) {
		BooleanExpression expression = columns[ 0 ].like( "%" );

		StringTokenizer tokenizer = new StringTokenizer( keyword );
		while( tokenizer.hasMoreTokens() ) {
			String token = tokenizer.nextToken();
			BooleanExpression keywordExpression = columns[ 0 ].notLike( "%" );

			for( StringPath column : columns ) {
				BooleanExpression currentExpression = column.containsIgnoreCase( token );
				keywordExpression = keywordExpression.or( currentExpression );
			}

			expression = expression.and( keywordExpression );
		}

		return expression;
	}
}
