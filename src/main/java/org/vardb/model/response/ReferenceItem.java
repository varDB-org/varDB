package org.vardb.model.response;

public class ReferenceItem {
	private Integer id;
	private String accession;
	private String name;
	private String link;
	private String type;
	private String title;
	private String authors;
	private String journal;
	private String year;
	private String pages;
	private Integer pmid;
	private String publisher;
	private String abst;
	private String city;
	private String visible;
	private String volume;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return this.authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPages() {
		return this.pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public Integer getPmid() {
		return this.pmid;
	}

	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAbstract() {
		return this.abst;
	}

	public void setAbstract(String abst ) {
		this.abst = abst;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVisible() {
		return this.visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
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
