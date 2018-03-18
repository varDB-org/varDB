package org.vardb.model.response;

public class DrugItem {
	private Integer id;
	private String accession;
	private String name;
	private String link;
	private String description;
	private String activity;
	private String formula;
	private String mass;
	private String names;
	private String notes;
	private String target;
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
	 * @return
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
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}


	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getMass() {
		return this.mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public String getNames() {
		return this.names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
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
