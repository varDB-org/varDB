package org.vardb.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the pathogens database table.
 *
 */
@Entity
@Table(name="pathogens")
@NamedQuery(name="Pathogen.findAll", query="SELECT p FROM Pathogen p")
public class Pathogen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String abbr;

	private String alias;

	private String antigenicvariation;

	@Column(name="bacteria_aerobic")
	private Boolean bacteriaAerobic;

	@Column(name="bacteria_appendages")
	private String bacteriaAppendages;

	@Column(name="bacteria_gram")
	private String bacteriaGram;

	@Column(name="bacteria_morphology")
	private String bacteriaMorphology;

	@Column(name="bacteria_plasmids")
	private String bacteriaPlasmids;

	@Column(name="bacteria_size")
	private String bacteriaSize;

	private String chromosomes;

	private String codonusage;

	private Timestamp created;

	private String description;

	private String distribution;

	private String dtype;

	private String gccontent;

	private String genome;

	private String hosts;

	private String html;

	private String identifier;

	private String kegg;

	@Column(name="kegg_organism")
	private String keggOrganism;

	@Column(name="kegg_pathway")
	private String keggPathway;

	private String lifecycle;

	private String name;

	private String notes;

	private String numbases;

	private String numgenes;

	private String numproteins;

	private Integer numsequences;

	private String taxgroup;

	private Timestamp updated;

	private String url;

	@Column(name="virus_baltimore")
	private String virusBaltimore;

	@Column(name="virus_envelope")
	private Boolean virusEnvelope;

	@Column(name="virus_nucleicacidtype")
	private String virusNucleicacidtype;

	@Column(name="virus_reversetranscription")
	private Boolean virusReversetranscription;

	@Column(name="virus_sense")
	private String virusSense;

	@Column(name="virus_shape")
	private String virusShape;

	@Column(name="virus_size")
	private String virusSize;

	@Column(name="virus_strandedness")
	private String virusStrandedness;

	//bi-directional many-to-one association to Family
	@OneToMany(mappedBy="pathogen")
	private List<Family> families;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	//bi-directional many-to-one association to PathogensDiseas
	@OneToMany(mappedBy="pathogen")
	private List<PathogensDiseas> pathogensDiseases;

	//bi-directional many-to-one association to PathogensDrug
	@OneToMany(mappedBy="pathogen")
	private List<PathogensDrug> pathogensDrugs;

	//bi-directional many-to-one association to PathogensRef
	@OneToMany(mappedBy="pathogen")
	private List<PathogensRef> pathogensRefs;

	public Pathogen() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getBacteriaAerobic() {
		return this.bacteriaAerobic;
	}

	public void setBacteriaAerobic(Boolean bacteriaAerobic) {
		this.bacteriaAerobic = bacteriaAerobic;
	}

	public String getBacteriaAppendages() {
		return this.bacteriaAppendages;
	}

	public void setBacteriaAppendages(String bacteriaAppendages) {
		this.bacteriaAppendages = bacteriaAppendages;
	}

	public String getBacteriaGram() {
		return this.bacteriaGram;
	}

	public void setBacteriaGram(String bacteriaGram) {
		this.bacteriaGram = bacteriaGram;
	}

	public String getBacteriaMorphology() {
		return this.bacteriaMorphology;
	}

	public void setBacteriaMorphology(String bacteriaMorphology) {
		this.bacteriaMorphology = bacteriaMorphology;
	}

	public String getBacteriaPlasmids() {
		return this.bacteriaPlasmids;
	}

	public void setBacteriaPlasmids(String bacteriaPlasmids) {
		this.bacteriaPlasmids = bacteriaPlasmids;
	}

	public String getBacteriaSize() {
		return this.bacteriaSize;
	}

	public void setBacteriaSize(String bacteriaSize) {
		this.bacteriaSize = bacteriaSize;
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

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getKegg() {
		return this.kegg;
	}

	public void setKegg(String kegg) {
		this.kegg = kegg;
	}

	public String getKeggOrganism() {
		return this.keggOrganism;
	}

	public void setKeggOrganism(String keggOrganism) {
		this.keggOrganism = keggOrganism;
	}

	public String getKeggPathway() {
		return this.keggPathway;
	}

	public void setKeggPathway(String keggPathway) {
		this.keggPathway = keggPathway;
	}

	public String getLifecycle() {
		return this.lifecycle;
	}

	public void setLifecycle(String lifecycle) {
		this.lifecycle = lifecycle;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVirusBaltimore() {
		return this.virusBaltimore;
	}

	public void setVirusBaltimore(String virusBaltimore) {
		this.virusBaltimore = virusBaltimore;
	}

	public Boolean getVirusEnvelope() {
		return this.virusEnvelope;
	}

	public void setVirusEnvelope(Boolean virusEnvelope) {
		this.virusEnvelope = virusEnvelope;
	}

	public String getVirusNucleicacidtype() {
		return this.virusNucleicacidtype;
	}

	public void setVirusNucleicacidtype(String virusNucleicacidtype) {
		this.virusNucleicacidtype = virusNucleicacidtype;
	}

	public Boolean getVirusReversetranscription() {
		return this.virusReversetranscription;
	}

	public void setVirusReversetranscription(Boolean virusReversetranscription) {
		this.virusReversetranscription = virusReversetranscription;
	}

	public String getVirusSense() {
		return this.virusSense;
	}

	public void setVirusSense(String virusSense) {
		this.virusSense = virusSense;
	}

	public String getVirusShape() {
		return this.virusShape;
	}

	public void setVirusShape(String virusShape) {
		this.virusShape = virusShape;
	}

	public String getVirusSize() {
		return this.virusSize;
	}

	public void setVirusSize(String virusSize) {
		this.virusSize = virusSize;
	}

	public String getVirusStrandedness() {
		return this.virusStrandedness;
	}

	public void setVirusStrandedness(String virusStrandedness) {
		this.virusStrandedness = virusStrandedness;
	}

	public List<Family> getFamilies() {
		return this.families;
	}

	public void setFamilies(List<Family> families) {
		this.families = families;
	}

	public Family addFamily(Family family) {
		getFamilies().add(family);
		family.setPathogen(this);

		return family;
	}

	public Family removeFamily(Family family) {
		getFamilies().remove(family);
		family.setPathogen(null);

		return family;
	}

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

	public List<PathogensDiseas> getPathogensDiseases() {
		return this.pathogensDiseases;
	}

	public void setPathogensDiseases(List<PathogensDiseas> pathogensDiseases) {
		this.pathogensDiseases = pathogensDiseases;
	}

	public PathogensDiseas addPathogensDiseas(PathogensDiseas pathogensDiseas) {
		getPathogensDiseases().add(pathogensDiseas);
		pathogensDiseas.setPathogen(this);

		return pathogensDiseas;
	}

	public PathogensDiseas removePathogensDiseas(PathogensDiseas pathogensDiseas) {
		getPathogensDiseases().remove(pathogensDiseas);
		pathogensDiseas.setPathogen(null);

		return pathogensDiseas;
	}

	public List<PathogensDrug> getPathogensDrugs() {
		return this.pathogensDrugs;
	}

	public void setPathogensDrugs(List<PathogensDrug> pathogensDrugs) {
		this.pathogensDrugs = pathogensDrugs;
	}

	public PathogensDrug addPathogensDrug(PathogensDrug pathogensDrug) {
		getPathogensDrugs().add(pathogensDrug);
		pathogensDrug.setPathogen(this);

		return pathogensDrug;
	}

	public PathogensDrug removePathogensDrug(PathogensDrug pathogensDrug) {
		getPathogensDrugs().remove(pathogensDrug);
		pathogensDrug.setPathogen(null);

		return pathogensDrug;
	}

	public List<PathogensRef> getPathogensRefs() {
		return this.pathogensRefs;
	}

	public void setPathogensRefs(List<PathogensRef> pathogensRefs) {
		this.pathogensRefs = pathogensRefs;
	}

	public PathogensRef addPathogensRef(PathogensRef pathogensRef) {
		getPathogensRefs().add(pathogensRef);
		pathogensRef.setPathogen(this);

		return pathogensRef;
	}

	public PathogensRef removePathogensRef(PathogensRef pathogensRef) {
		getPathogensRefs().remove(pathogensRef);
		pathogensRef.setPathogen(null);

		return pathogensRef;
	}

}