package org.vardb.model.response;

/**
 * disease item
 */
public class DiseaseItem {
	private Integer id;
	private String accession;
	private String name;
	private String link;
	private String description;
	private String diagnosis;
	private String distribution;
	private String drugs;
	private String history;
	private String host;
	private String html;
	private String human;
	private String icd10;
	private String kegg_disease;
	private String kegg_pathway;
	private String morbidity;
	private String mortality;
	private String notes;
	private Integer numsequences;
	private String pathogenesis;
	private String prevention;
	private String symptoms;
	private String transmission;
	private String treatment;
	private String url;
	private String vaccines;
	private String vector;

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
	 * gets the name
	 * @return name
	 */
	public String getName() {
		return this.name;
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
	 * sets the diagnosis
	 * @param diagnosis diagnosis
	 */
	public void setDiagnosis( String diagnosis ) {
		this.diagnosis = diagnosis;
	}

	/**
	 * gets the diagnosis
	 * @return diagnosis
	 */
	public String getDiagnosis() {
		return this.diagnosis;
	}

	/**
	 * sets the distribution
	 * @param distribution distribution
	 */
	public void setDistribution( String distribution ) {
		this.distribution = distribution;
	}

	/**
	 * gets the distribution
	 * @return distribution
	 */
	public String getDistribution() {
		return this.distribution;
	}

	/**
	 * sets drugs
	 * @param drugs drugs
	 */
	public void setDrugs( String drugs ) {
		this.drugs = drugs;
	}

	/**
	 * gets drugs
	 * @return drugs
	 */
	public String getDrugs() {
		return this.drugs;
	}

	/**
	 * sets the history
	 * @param history history
	 */
	public void setHistory( String history ) {
		this.history = history;
	}

	/**
	 * gets the history
	 * @return history
	 */
	public String getHistory() {
		return this.history;
	}

	/**
	 * sets the host
	 * @param host
	 */
	public void setHost( String host ) {
		this.host = host;
	}

	/**
	 * gets the host
	 * @return host
	 */
	public String getHost() {
		return this.host;
	}

	/**
	 * sets the HTML
	 * @param html html
	 */
	public void setHtml( String html ) {
		this.html = html;
	}

	/**
	 * gets the HTML
	 * @return html
	 */
	public String getHtml() {
		return this.html;
	}

	/**
	 * sets the human
	 * @param human human
	 */
	public void setHuman( String human ) {
		this.human = human;
	}

	/**
	 * gets the human
	 * @return human
	 */
	public String getHuman() {
		return this.human;
	}

	/**
	 * sets the icd10
	 * @param icd10 icd10
	 */
	public void setIcd10( String icd10 ) {
		this.icd10 = icd10;
	}

	/**
	 * gets the icd10
	 * @return icd10
	 */
	public String getIcd10() {
		return this.icd10;
	}

	/**
	 * sets the KEGG disease
	 * @param keggDisease kegg disease
	 */
	public void setKeggDisease( String keggDisease ) {
		this.kegg_disease = keggDisease;
	}

	/**
	 * gets the KEGG disease
	 * @return
	 */
	public String getKeggDisease() {
		return this.kegg_disease;
	}

	/**
	 * sets the KEGG pathway
	 * @param keggPathway KEGG pathway
	 */
	public void setKeggPathway( String keggPathway ) {
		this.kegg_pathway = keggPathway;
	}

	/**
	 * gets the KEGG pathway
	 * @return KEGG pathway
	 */
	public String getKeggPathway() {
		return this.kegg_pathway;
	}

	/**
	 * sets the morbidity
	 * @param morbidity morbidity
	 */
	public void setMorbidity( String morbidity ) {
		this.morbidity = morbidity;
	}

	/**
	 * gets the morbidity
	 * @return morbidity
	 */
	public String getMorbidity() {
		return this.morbidity;
	}

	/**
	 * sets the mortality
	 * @param mortality mortality
	 */
	public void setMortality( String mortality ) {
		this.mortality = mortality;
	}

	/**
	 * gets the mortality
	 * @return mortality
	 */
	public String getMortality() {
		return this.mortality;
	}

	/**
	 * sets the notes
	 * @param notes notes
	 */
	public void setNotes( String notes ) {
		this.notes = notes;
	}

	/**
	 * gets the notes
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * sets the number of sequences
	 * @param numsequence number of sequences
	 */
	public void setNumsequences( Integer numsequences ) {
		this.numsequences = numsequences;
	}

	/**
	 * gets the number of sequences
	 * @return number of sequences
	 */
	public Integer getNumsequences() {
		return this.numsequences;
	}

	/**
	 * sets the pathogenesis
	 * @param pathogenesis pathogenesis
	 */
	public void setPathogenesis( String pathogenesis ) {
		this.pathogenesis = pathogenesis;
	}

	/**
	 * gets the pathogenesis
	 * @return pathogenesis
	 */
	public String getPathogenesis() {
		return this.pathogenesis;
	}

	/**
	 * sets the prevention
	 * @param prevention prevention
	 */
	public void setPrevention( String prevention ) {
		this.prevention = prevention;
	}

	/**
	 * gets the prevention
	 * @return prevention
	 */
	public String getPrevention() {
		return this.prevention;
	}

	/**
	 * set the symptoms
	 * @param symptoms symptoms
	 */
	public void setSymptoms( String symptoms ) {
		this.symptoms = symptoms;
	}

	/**
	 * gets the symptoms
	 * @return symptoms
	 */
	public String getSymptoms() {
		return this.symptoms;
	}

	/**
	 * sets the transmission
	 * @param transmission transmission
	 */
	public void setTransmission( String transmission ) {
		this.transmission = transmission;
	}

	/**
	 * gets the transmission
	 * @return transmission
	 */
	public String getTransmission() {
		return this.transmission;
	}

	/**
	 * set the treatment
	 * @param treatment treatment
	 */
	public void setTreatment( String treatment ) {
		this.treatment = treatment;
	}

	/**
	 * gets the treatment
	 * @return treatment
	 */
	public String getTreatment() {
		return this.treatment;
	}

	/**
	 * sets the URL
	 * @param url URL
	 */
	public void setUrl( String url ) {
		this.url = url;
	}

	/**
	 * gets the URL
	 * @return URL
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * sets the vaccines
	 * @param vaccines vaccines
	 */
	public void setVaccines( String vaccines ) {
		this.vaccines = vaccines;
	}

	/**
	 * gets the vaccines
	 * @return vaccines
	 */
	public String getVaccines() {
		return this.vaccines;
	}

	/**
	 * gets the vector
	 * @param vector vector
	 */
	public void setVector( String vector ) {
		this.vector = vector;
	}

	/**
	 * gets the vector
	 * @return vector
	 */
	public String getVector() {
		return this.vector;
	}
}
