package org.vardb.model.response;

public class PathogenItem {
	private Integer id;
	private String accession;
	private String name;
	private String link;
	private String description;
	private String abbr;
	private String alias;
	private String antigenicvariation;
	private String bacteria_appendages;
	private String bacteria_gram;
	private String bacteria_morphology;
	private String bacteria_plasmids;
	private String bacteria_size;
	private String chromosomes;
	private String codonusage;
	private String distribution;
	private String dtype;
	private String gccontent;
	private String genome;
	private String hosts;
	private String html;
	private String kegg;
	private String kegg_organism;
	private String kegg_pathway;
	private String lifecycle;
	private String notes;
	private String numbases;
	private String numgenes;
	private String numproteins;
	private Integer numsequences;
	private String taxgroup;
	private String url;
	private String virus_baltimore;
	private String virus_nucleicacidtype;
	private String virus_sense;
	private String virus_shape;
	private String virus_size;
	private String virus_strandedness;

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

	/**(
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
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	public String getAbbr() {
		return this.abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAntigenicvariation() {
		return this.antigenicvariation;
	}

	public void setAntigenicvariation(String antigenicvariation) {
		this.antigenicvariation = antigenicvariation;
	}

	public String getBacteriaAppendages() {
		return this.bacteria_appendages;
	}

	public void setBacteriaAppendages(String bacteriaAppendages) {
		this.bacteria_appendages = bacteriaAppendages;
	}

	public String getBacteriaGram() {
		return this.bacteria_gram;
	}

	public void setBacteriaGram(String bacteriaGram) {
		this.bacteria_gram = bacteriaGram;
	}

	public String getBacteriaMorphology() {
		return this.bacteria_morphology;
	}

	public void setBacteriaMorphology(String bacteriaMorphology) {
		this.bacteria_morphology = bacteriaMorphology;
	}

	public String getBacteriaPlasmids() {
		return this.bacteria_plasmids;
	}

	public void setBacteriaPlasmids(String bacteriaPlasmids) {
		this.bacteria_plasmids = bacteriaPlasmids;
	}

	public String getBacteriaSize() {
		return this.bacteria_size;
	}

	public void setBacteriaSize(String bacteriaSize) {
		this.bacteria_size = bacteriaSize;
	}

	public String getChromosomes() {
		return this.chromosomes;
	}

	public void setChromosomes(String chromosomes) {
		this.chromosomes = chromosomes;
	}

	public String getCodonusage() {
		return this.codonusage;
	}

	public void setCodonusage(String codonusage) {
		this.codonusage = codonusage;
	}

	public String getDistribution() {
		return this.distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public String getDtype() {
		return this.dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getGccontent() {
		return this.gccontent;
	}

	public void setGccontent(String gccontent) {
		this.gccontent = gccontent;
	}

	public String getGenome() {
		return this.genome;
	}

	public void setGenome(String genome) {
		this.genome = genome;
	}

	public String getHosts() {
		return this.hosts;
	}

	public void setHosts(String hosts) {
		this.hosts = hosts;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getKegg() {
		return this.kegg;
	}

	public void setKegg(String kegg) {
		this.kegg = kegg;
	}

	public String getKeggOrganism() {
		return this.kegg_organism;
	}

	public void setKeggOrganism(String keggOrganism) {
		this.kegg_organism = keggOrganism;
	}

	public String getKeggPathway() {
		return this.kegg_pathway;
	}

	public void setKeggPathway(String keggPathway) {
		this.kegg_pathway = keggPathway;
	}

	public String getLifecycle() {
		return this.lifecycle;
	}

	public void setLifecycle(String lifecycle) {
		this.lifecycle = lifecycle;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNumbases() {
		return this.numbases;
	}

	public void setNumbases(String numbases) {
		this.numbases = numbases;
	}

	public String getNumgenes() {
		return this.numgenes;
	}

	public void setNumgenes(String numgenes) {
		this.numgenes = numgenes;
	}

	public String getNumproteins() {
		return this.numproteins;
	}

	public void setNumproteins(String numproteins) {
		this.numproteins = numproteins;
	}

	public Integer getNumsequences() {
		return this.numsequences;
	}

	public void setNumsequences(Integer numsequences) {
		this.numsequences = numsequences;
	}

	public String getTaxgroup() {
		return this.taxgroup;
	}

	public void setTaxgroup(String taxgroup) {
		this.taxgroup = taxgroup;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVirusBaltimore() {
		return this.virus_baltimore;
	}

	public void setVirusBaltimore(String virusBaltimore) {
		this.virus_baltimore = virusBaltimore;
	}

	public String getVirusNucleicacidtype() {
		return this.virus_nucleicacidtype;
	}

	public void setVirusNucleicacidtype(String virusNucleicacidtype) {
		this.virus_nucleicacidtype = virusNucleicacidtype;
	}

	public String getVirusSense() {
		return this.virus_sense;
	}

	public void setVirusSense(String virusSense) {
		this.virus_sense = virusSense;
	}

	public String getVirusShape() {
		return this.virus_shape;
	}

	public void setVirusShape(String virusShape) {
		this.virus_shape = virusShape;
	}

	public String getVirusSize() {
		return this.virus_size;
	}

	public void setVirusSize(String virusSize) {
		this.virus_size = virusSize;
	}

	public String getVirusStrandedness() {
		return this.virus_strandedness;
	}

	public void setVirusStrandedness(String virusStrandedness) {
		this.virus_strandedness = virusStrandedness;
	}

}
