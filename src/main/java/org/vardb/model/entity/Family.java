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
 * The persistent class for the families database table.
 *
 */
@Entity
@Table(name="families")
@NamedQuery(name="Family.findAll", query="SELECT f FROM Family f")
public class Family implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String abbr;

	private String alias;

	@Column(name="alignment_id")
	private Integer alignmentId;

	private String antigenicvariation;

	private String chromosomes;

	private Timestamp created;

	private String daltons;

	private String description;

	private String expression;

	private String familysize;

	private String function;

	private String html;

	private String identifier;

	private String introns;

	@Column(name="kegg_family")
	private String keggFamily;

	@Column(name="kegg_ortholog")
	private String keggOrtholog;

	@Column(name="kegg_pathway")
	private String keggPathway;

	private String ligands;

	private String location;

	private String name;

	private String notes;

	private Integer numsequences;

	private String protein;

	private String structure;

	private String switchingrate;

	private Timestamp updated;

	private String url;

	//bi-directional many-to-one association to Ortholog
	@ManyToOne
	private Ortholog ortholog;

	//bi-directional many-to-one association to Pathogen
	@ManyToOne
	private Pathogen pathogen;

	//bi-directional many-to-one association to FamiliesRef
	@OneToMany(mappedBy="family")
	private List<FamiliesRef> familiesRefs;

	//bi-directional many-to-one association to Paralog
	@OneToMany(mappedBy="family")
	private List<Paralog> paralogs;

	public Family() {
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

	public Integer getAlignmentId() {
		return this.alignmentId;
	}

	public void setAlignmentId(Integer alignmentId) {
		this.alignmentId = alignmentId;
	}

	public String getAntigenicvariation() {
		return this.antigenicvariation;
	}

	public void setAntigenicvariation(String antigenicvariation) {
		this.antigenicvariation = antigenicvariation;
	}

	public String getChromosomes() {
		return this.chromosomes;
	}

	public void setChromosomes(String chromosomes) {
		this.chromosomes = chromosomes;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getDaltons() {
		return this.daltons;
	}

	public void setDaltons(String daltons) {
		this.daltons = daltons;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getFamilysize() {
		return this.familysize;
	}

	public void setFamilysize(String familysize) {
		this.familysize = familysize;
	}

	public String getFunction() {
		return this.function;
	}

	public void setFunction(String function) {
		this.function = function;
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

	public String getIntrons() {
		return this.introns;
	}

	public void setIntrons(String introns) {
		this.introns = introns;
	}

	public String getKeggFamily() {
		return this.keggFamily;
	}

	public void setKeggFamily(String keggFamily) {
		this.keggFamily = keggFamily;
	}

	public String getKeggOrtholog() {
		return this.keggOrtholog;
	}

	public void setKeggOrtholog(String keggOrtholog) {
		this.keggOrtholog = keggOrtholog;
	}

	public String getKeggPathway() {
		return this.keggPathway;
	}

	public void setKeggPathway(String keggPathway) {
		this.keggPathway = keggPathway;
	}

	public String getLigands() {
		return this.ligands;
	}

	public void setLigands(String ligands) {
		this.ligands = ligands;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Integer getNumsequences() {
		return this.numsequences;
	}

	public void setNumsequences(Integer numsequences) {
		this.numsequences = numsequences;
	}

	public String getProtein() {
		return this.protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
	}

	public String getStructure() {
		return this.structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getSwitchingrate() {
		return this.switchingrate;
	}

	public void setSwitchingrate(String switchingrate) {
		this.switchingrate = switchingrate;
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

	public Ortholog getOrtholog() {
		return this.ortholog;
	}

	public void setOrtholog(Ortholog ortholog) {
		this.ortholog = ortholog;
	}

	public Pathogen getPathogen() {
		return this.pathogen;
	}

	public void setPathogen(Pathogen pathogen) {
		this.pathogen = pathogen;
	}

	public List<FamiliesRef> getFamiliesRefs() {
		return this.familiesRefs;
	}

	public void setFamiliesRefs(List<FamiliesRef> familiesRefs) {
		this.familiesRefs = familiesRefs;
	}

	public FamiliesRef addFamiliesRef(FamiliesRef familiesRef) {
		getFamiliesRefs().add(familiesRef);
		familiesRef.setFamily(this);

		return familiesRef;
	}

	public FamiliesRef removeFamiliesRef(FamiliesRef familiesRef) {
		getFamiliesRefs().remove(familiesRef);
		familiesRef.setFamily(null);

		return familiesRef;
	}

	public List<Paralog> getParalogs() {
		return this.paralogs;
	}

	public void setParalogs(List<Paralog> paralogs) {
		this.paralogs = paralogs;
	}

	public Paralog addParalog(Paralog paralog) {
		getParalogs().add(paralog);
		paralog.setFamily(this);

		return paralog;
	}

	public Paralog removeParalog(Paralog paralog) {
		getParalogs().remove(paralog);
		paralog.setFamily(null);

		return paralog;
	}

}