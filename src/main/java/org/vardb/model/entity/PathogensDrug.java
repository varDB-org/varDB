package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the pathogens_drugs database table.
 * 
 */
@Entity
@Table(name="pathogens_drugs")
@NamedQuery(name="PathogensDrug.findAll", query="SELECT p FROM PathogensDrug p")
public class PathogensDrug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private Timestamp updated;

	//bi-directional many-to-one association to Drug
	@ManyToOne
	private Drug drug;

	//bi-directional many-to-one association to Pathogen
	@ManyToOne
	private Pathogen pathogen;

	public PathogensDrug() {
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

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Drug getDrug() {
		return this.drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Pathogen getPathogen() {
		return this.pathogen;
	}

	public void setPathogen(Pathogen pathogen) {
		this.pathogen = pathogen;
	}

}