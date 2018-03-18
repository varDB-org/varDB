package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the diseases_drugs database table.
 * 
 */
@Entity
@Table(name="diseases_drugs")
@NamedQuery(name="DiseasesDrug.findAll", query="SELECT d FROM DiseasesDrug d")
public class DiseasesDrug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private Timestamp updated;

	//bi-directional many-to-one association to Diseas
	@ManyToOne
	@JoinColumn(name="disease_id")
	private Diseas diseas;

	//bi-directional many-to-one association to Drug
	@ManyToOne
	private Drug drug;

	public DiseasesDrug() {
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

	public Diseas getDiseas() {
		return this.diseas;
	}

	public void setDiseas(Diseas diseas) {
		this.diseas = diseas;
	}

	public Drug getDrug() {
		return this.drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

}