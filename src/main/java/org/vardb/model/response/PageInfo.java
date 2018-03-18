package org.vardb.model.response;

/**
 * page information
 */
public class PageInfo {
	private String name;
	private String title;

	/**
	 * constructor
	 * @param name name
	 * @param title title
	 */
	public PageInfo( String name, String title ) {
		this.name = name;
		this.title = title;
	}

	/**
	 * gets the name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * gets the title
	 * return title
	 */
	public String getTitle() {
		return this.title;
	}

}
