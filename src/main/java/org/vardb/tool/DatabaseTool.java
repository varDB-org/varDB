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
		BooleanExpression expression = null;

		StringTokenizer tokenizer = new StringTokenizer( keyword );
		while( tokenizer.hasMoreTokens() ) {
			String token = tokenizer.nextToken();
			BooleanExpression keywordExpression = null;

			for( StringPath column : columns ) {
				BooleanExpression currentExpression = column.containsIgnoreCase( token );
				if( keywordExpression == null ) {
					keywordExpression = currentExpression;
				}
				else {
					keywordExpression = keywordExpression.or( currentExpression );
				}
			}

			if( expression == null ) {
				expression = keywordExpression;
			}
			else {
				expression = expression.and( keywordExpression );
			}
		}

		return expression;
	}
}
