package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the pathogens_diseases database table.
 * 
 */
@Entity
@Table(name="pathogens_diseases")
@NamedQuery(name="PathogensDiseas.findAll", query="SELECT p FROM PathogensDiseas p")
public class PathogensDiseas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private Timestamp updated;

	//bi-directional many-to-one association to Diseas
	@ManyToOne
	@JoinColumn(name="disease_id")
	private Diseas diseas;

	//bi-directional many-to-one association to Pathogen
	@ManyToOne
	private Pathogen pathogen;

	public PathogensDiseas() {
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

	public Pathogen getPathogen() {
		return this.pathogen;
	}

	public void setPathogen(Pathogen pathogen) {
		this.pathogen = pathogen;
	}

}