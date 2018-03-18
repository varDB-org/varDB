package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the taxons database table.
 * 
 */
@Entity
@Table(name="taxons")
@NamedQuery(name="Taxon.findAll", query="SELECT t FROM Taxon t")
public class Taxon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private String description;

	private String identifier;

	private Boolean initialized;

	private String level;

	private Integer lft;

	private String name;

	private Integer rght;

	private Integer taxid;

	private Timestamp updated;

	//bi-directional many-to-one association to Pathogen
	@OneToMany(mappedBy="taxon")
	private List<Pathogen> pathogens;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Taxon taxon;

	//bi-directional many-to-one association to Taxon
	@OneToMany(mappedBy="taxon")
	private List<Taxon> taxons;

	public Taxon() {
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

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Boolean getInitialized() {
		return this.initialized;
	}

	public void setInitialized(Boolean initialized) {
		this.initialized = initialized;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getLft() {
		return this.lft;
	}

	public void setLft(Integer lft) {
		this.lft = lft;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRght() {
		return this.rght;
	}

	public void setRght(Integer rght) {
		this.rght = rght;
	}

	public Integer getTaxid() {
		return this.taxid;
	}

	public void setTaxid(Integer taxid) {
		this.taxid = taxid;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public List<Pathogen> getPathogens() {
		return this.pathogens;
	}

	public void setPathogens(List<Pathogen> pathogens) {
		this.pathogens = pathogens;
	}

	public Pathogen addPathogen(Pathogen pathogen) {
		getPathogens().add(pathogen);
		pathogen.setTaxon(this);

		return pathogen;
	}

	public Pathogen removePathogen(Pathogen pathogen) {
		getPathogens().remove(pathogen);
		pathogen.setTaxon(null);

		return pathogen;
	}

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

	public List<Taxon> getTaxons() {
		return this.taxons;
	}

	public void setTaxons(List<Taxon> taxons) {
		this.taxons = taxons;
	}

	public Taxon addTaxon(Taxon taxon) {
		getTaxons().add(taxon);
		taxon.setTaxon(this);

		return taxon;
	}

	public Taxon removeTaxon(Taxon taxon) {
		getTaxons().remove(taxon);
		taxon.setTaxon(null);

		return taxon;
	}

}