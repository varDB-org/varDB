package org.vardb.model.response;

public class FamilyItem {
	private Integer id;
	private String name;
	private String link;
	private String abbr;
	private String description;
	private String pathogen;
	private String ortholog;
	private Integer numsequences;

	/**
	 * sets the ID
	 * @param id ID
	 */
	public void setId( Integer id ) {
		this.id = id;
	}

	/**
	 * gets the ID
	 * @return ID
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * sets the name
	 * @param name name
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * gets the name
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * sets the link
	 * @param link link
	 */
	public void setLink( String link ) {
		this.link = link;
	}

	/**
	 * gets the link
	 * @return link
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * sets the abbrigation
	 * @param abbr abbrigation
	 */
	public void setAbbr( String abbr ) {
		this.abbr = abbr;
	}

	/**
	 * gets the abbrigation
	 * @return abbregation
	 */
	public String getAbbr() {
		return this.abbr;
	}

	/**
	 * sets the description
	 * @param description
	 */
	public void setDescription( String description ) {
		this.description = description;
	}

	/**
	 * gets the description
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * sets the pathogen
	 * @param pathogen pathogen
	 */
	public void setPathogen( String pathogen ) {
		this.pathogen = pathogen;
	}

	/**
	 * gets the pathogen
	 * @return pathogen
	 */
	public String getPathogen() {
		return this.pathogen;
	}

	/**
	 * sets the ortholog
	 * @param ortholog ortholog
	 */
	public void setOrtholog( String ortholog ) {
		this.ortholog = ortholog;
	}

	/**
	 * gets the ortholog
	 * @return ortholog
	 */
	public String getOrtholog() {
		return this.ortholog;
	}

	/**
	 * sets the number of sequences
	 * @param numsequences number of sequences
	 */
	public void setNumsequences( Integer numsequences ) {
		this.numsequences = numsequences;
	}

	/**
	 *  gets the number of sequences
	 * @return the number of sequences
	 */
	public Integer getNumsequences() {
		return this.numsequences;
	}
}
