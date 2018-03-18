package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the pathogens_refs database table.
 * 
 */
@Entity
@Table(name="pathogens_refs")
@NamedQuery(name="PathogensRef.findAll", query="SELECT p FROM PathogensRef p")
public class PathogensRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private Timestamp updated;

	//bi-directional many-to-one association to Pathogen
	@ManyToOne
	private Pathogen pathogen;

	//bi-directional many-to-one association to Ref
	@ManyToOne
	private Ref ref;

	public PathogensRef() {
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

	public Pathogen getPathogen() {
		return this.pathogen;
	}

	public void setPathogen(Pathogen pathogen) {
		this.pathogen = pathogen;
	}

	public Ref getRef() {
		return this.ref;
	}

	public void setRef(Ref ref) {
		this.ref = ref;
	}

}