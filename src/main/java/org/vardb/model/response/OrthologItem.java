package org.vardb.model.response;

/**
 * ortholog item
 */
public class OrthologItem {
	private Integer id;
	private String accession;
	private String name;
	private String link;
	private String description;
	private String kegg_ortholog;
	private String kegg_pathway;

	/**
	 * sets the ID
	 * @param id ID
	 */
	public void setId( Integer id ) {
		this.id = id;
	}

	/**
	 * gets the ID
	 * @return
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * gets the Accession
	 * @param accession accession
	 */
	public void setAccession( String accession ) {
		this.accession = accession;
	}

	/**
	 * gets the accession
	 * @return accession
	 */
	public String getAccession() {
		return this.accession;
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
	 * sets the description
	 * @param description description
	 */
	public void setDescription( String description ) {
		this.description = description;
	}

	/**
	 * gets the description
	 * @return descritpion
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * sets the kegg ortholog
	 * @param keggOrtholog kegg ortholog
	 */
	public void setKeggOrtholog( String keggOrtholog ) {
		this.kegg_ortholog = keggOrtholog;
	}


	/**
	 * gets the kegg ortholog
	 * @return kegg ortholog
	 */
	public String getKeggOrtholog() {
		return this.kegg_ortholog;
	}

	/**
	 * sets the kegg pathway
	 * @param keggPathway
	 */
	public void setKeggPathway( String keggPathway ) {
		this.kegg_pathway = keggPathway;
	}

	/**
	 * gets the kegg pathway
	 * @return kegg pathway
	 */
	public String getKeggPathway() {
		return this.kegg_pathway;
	}
}
