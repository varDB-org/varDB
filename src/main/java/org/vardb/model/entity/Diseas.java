package org.vardb.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the diseases database table.
 *
 */
@Entity
@Table(name="diseases")
@NamedQuery(name="Diseas.findAll", query="SELECT d FROM Diseas d")
public class Diseas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private String description;

	private String diagnosis;

	private String distribution;

	private String drugs;

	private String history;

	private String host;

	private String html;

	private Boolean human;

	private String icd10;

	private String identifier;

	@Column(name="kegg_disease")
	private String keggDisease;

	@Column(name="kegg_pathway")
	private String keggPathway;

	private Boolean list;

	private String morbidity;

	private String mortality;

	private String name;

	private String notes;

	private Integer numsequences;

	private String pathogenesis;

	private String prevention;

	private String symptoms;

	private String transmission;

	private String treatment;

	private Timestamp updated;

	private String url;

	private String vaccines;

	private String vector;

	//bi-directional many-to-one association to Diseas
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Diseas diseas;

	//bi-directional many-to-one association to Diseas
	@OneToMany(mappedBy="diseas")
	private List<Diseas> diseases;

	//bi-directional many-to-one association to DiseasesDrug
	@OneToMany(mappedBy="diseas")
	private List<DiseasesDrug> diseasesDrugs;

	//bi-directional many-to-one association to DiseasesRef
	@OneToMany(mappedBy="diseas")
	private List<DiseasesRef> diseasesRefs;

	//bi-directional many-to-one association to PathogensDiseas
	@OneToMany(mappedBy="diseas")
	private List<PathogensDiseas> pathogensDiseases;

	public Diseas() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDistribution() {
		return this.distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public String getDrugs() {
		return this.drugs;
	}

	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}

	public String getHistory() {
		return this.history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Boolean getHuman() {
		return this.human;
	}

	public void setHuman(Boolean human) {
		this.human = human;
	}

	public String getIcd10() {
		return this.icd10;
	}

	public void setIcd10(String icd10) {
		this.icd10 = icd10;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getKeggDisease() {
		return this.keggDisease;
	}

	public void setKeggDisease(String keggDisease) {
		this.keggDisease = keggDisease;
	}

	public String getKeggPathway() {
		return this.keggPathway;
	}

	public void setKeggPathway(String keggPathway) {
		this.keggPathway = keggPathway;
	}

	public Boolean getList() {
		return this.list;
	}

	public void setList(Boolean list) {
		this.list = list;
	}

	public String getMorbidity() {
		return this.morbidity;
	}

	public void setMorbidity(String morbidity) {
		this.morbidity = morbidity;
	}

	public String getMortality() {
		return this.mortality;
	}

	public void setMortality(String mortality) {
		this.mortality = mortality;
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

	public String getPathogenesis() {
		return this.pathogenesis;
	}

	public void setPathogenesis(String pathogenesis) {
		this.pathogenesis = pathogenesis;
	}

	public String getPrevention() {
		return this.prevention;
	}

	public void setPrevention(String prevention) {
		this.prevention = prevention;
	}

	public String getSymptoms() {
		return this.symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getTransmission() {
		return this.transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getTreatment() {
		return this.treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
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

	public String getVaccines() {
		return this.vaccines;
	}

	public void setVaccines(String vaccines) {
		this.vaccines = vaccines;
	}

	public String getVector() {
		return this.vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public Diseas getDiseas() {
		return this.diseas;
	}

	public void setDiseas(Diseas diseas) {
		this.diseas = diseas;
	}

	public List<Diseas> getDiseases() {
		return this.diseases;
	}

	public void setDiseases(List<Diseas> diseases) {
		this.diseases = diseases;
	}

	public Diseas addDiseas(Diseas diseas) {
		getDiseases().add(diseas);
		diseas.setDiseas(this);

		return diseas;
	}

	public Diseas removeDiseas(Diseas diseas) {
		getDiseases().remove(diseas);
		diseas.setDiseas(null);

		return diseas;
	}

	public List<DiseasesDrug> getDiseasesDrugs() {
		return this.diseasesDrugs;
	}

	public void setDiseasesDrugs(List<DiseasesDrug> diseasesDrugs) {
		this.diseasesDrugs = diseasesDrugs;
	}

	public DiseasesDrug addDiseasesDrug(DiseasesDrug diseasesDrug) {
		getDiseasesDrugs().add(diseasesDrug);
		diseasesDrug.setDiseas(this);

		return diseasesDrug;
	}

	public DiseasesDrug removeDiseasesDrug(DiseasesDrug diseasesDrug) {
		getDiseasesDrugs().remove(diseasesDrug);
		diseasesDrug.setDiseas(null);

		return diseasesDrug;
	}

	public List<DiseasesRef> getDiseasesRefs() {
		return this.diseasesRefs;
	}

	public void setDiseasesRefs(List<DiseasesRef> diseasesRefs) {
		this.diseasesRefs = diseasesRefs;
	}

	public DiseasesRef addDiseasesRef(DiseasesRef diseasesRef) {
		getDiseasesRefs().add(diseasesRef);
		diseasesRef.setDiseas(this);

		return diseasesRef;
	}

	public DiseasesRef removeDiseasesRef(DiseasesRef diseasesRef) {
		getDiseasesRefs().remove(diseasesRef);
		diseasesRef.setDiseas(null);

		return diseasesRef;
	}

	public List<PathogensDiseas> getPathogensDiseases() {
		return this.pathogensDiseases;
	}

	public void setPathogensDiseases(List<PathogensDiseas> pathogensDiseases) {
		this.pathogensDiseases = pathogensDiseases;
	}

	public PathogensDiseas addPathogensDiseas(PathogensDiseas pathogensDiseas) {
		getPathogensDiseases().add(pathogensDiseas);
		pathogensDiseas.setDiseas(this);

		return pathogensDiseas;
	}

	public PathogensDiseas removePathogensDiseas(PathogensDiseas pathogensDiseas) {
		getPathogensDiseases().remove(pathogensDiseas);
		pathogensDiseas.setDiseas(null);

		return pathogensDiseas;
	}

}