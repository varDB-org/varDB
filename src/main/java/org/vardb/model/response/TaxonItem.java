package org.vardb.model.response;

/**
 * taxonomy item
 */
public class TaxonItem {
	private Integer id;
	private String accession;
	private String name;
	private String link;
	private String level;
	private String description;
	private Integer taxid;
	private Integer lft;
	private Integer rght;
	private String parent;

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
	 * sets the accession
	 * @param accession accession
	 */
	public void setAccession( String accession ) {
		this.accession = accession;
	}

	/**
	 * gets the accession;
	 * @return accession
	 */
	public String getAccession() {
		return this.accession;
	}

	/**
	 * sets the name
	 * @param name
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * gets the name
	 * @return name
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
	 * sets the level
	 * @param level level
	 */
	public void setLevel( String level ) {
		this.level = level;
	}

	/**
	 * gets the level
	 */
	public String getLevel() {
		return this.level;
	}

	/**
	 * sets the description
	 * @param description description
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
	 * sets the taxonomy ID
	 * @param taxid taxonomy ID
	 */
	public void setTaxid( Integer taxid ) {
		this.taxid = taxid;
	}

	/**
	 * gets the taxonomy ID
	 * @return taxonomy ID
	 */
	public Integer getTaxid() {
		return this.taxid;
	}

	/**
	 * sets the left
	 * @param lft left
	 */
	public void setLft( Integer lft ) {
		this.lft = lft;
	}

	/**
	 * gets the left
	 * @return left
	 */
	public Integer getLft() {
		return this.lft;
	}

	/**
	 * sets the right;
	 * @param rght right
	 */
	public void setRght( Integer rght ) {
		this.rght = rght;
	}

	/**
	 * gets the right
	 * @return right
	 */
	public Integer getRght() {
		return this.rght;
	}

	/**
	 * sets the parent
	 * @param parent parent
	 */
	public void setParent( String parent ) {
		this.parent = parent;
	}

	/**
	 * gets the parent
	 * @return parent
	 */
	public String getParent() {
		return this.parent;
	}
}