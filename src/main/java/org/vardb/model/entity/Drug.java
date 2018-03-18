package org.vardb.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the drugs database table.
 *
 */
@Entity
@Table(name="drugs")
@NamedQuery(name="Drug.findAll", query="SELECT d FROM Drug d")
public class Drug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String activity;

	private Timestamp created;

	private String description;

	private String formula;

	private String identifier;

	private String mass;

	private String name;

	private String names;

	private String notes;

	private Integer numsequences;

	private String target;

	private Timestamp updated;

	//bi-directional many-to-one association to DiseasesDrug
	@OneToMany(mappedBy="drug")
	private List<DiseasesDrug> diseasesDrugs;

	//bi-directional many-to-one association to PathogensDrug
	@OneToMany(mappedBy="drug")
	private List<PathogensDrug> pathogensDrugs;

	public Drug() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
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

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getMass() {
		return this.mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getNumsequences() {
		return this.numsequences;
	}

	public void setNumsequences(Integer numsequences) {
		this.numsequences = numsequences;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public List<DiseasesDrug> getDiseasesDrugs() {
		return this.diseasesDrugs;
	}

	public void setDiseasesDrugs(List<DiseasesDrug> diseasesDrugs) {
		this.diseasesDrugs = diseasesDrugs;
	}

	public DiseasesDrug addDiseasesDrug(DiseasesDrug diseasesDrug) {
		getDiseasesDrugs().add(diseasesDrug);
		diseasesDrug.setDrug(this);

		return diseasesDrug;
	}

	public DiseasesDrug removeDiseasesDrug(DiseasesDrug diseasesDrug) {
		getDiseasesDrugs().remove(diseasesDrug);
		diseasesDrug.setDrug(null);

		return diseasesDrug;
	}

	public List<PathogensDrug> getPathogensDrugs() {
		return this.pathogensDrugs;
	}

	public void setPathogensDrugs(List<PathogensDrug> pathogensDrugs) {
		this.pathogensDrugs = pathogensDrugs;
	}

	public PathogensDrug addPathogensDrug(PathogensDrug pathogensDrug) {
		getPathogensDrugs().add(pathogensDrug);
		pathogensDrug.setDrug(this);

		return pathogensDrug;
	}

	public PathogensDrug removePathogensDrug(PathogensDrug pathogensDrug) {
		getPathogensDrugs().remove(pathogensDrug);
		pathogensDrug.setDrug(null);

		return pathogensDrug;
	}

}