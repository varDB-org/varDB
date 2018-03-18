package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the diseases_refs database table.
 * 
 */
@Entity
@Table(name="diseases_refs")
@NamedQuery(name="DiseasesRef.findAll", query="SELECT d FROM DiseasesRef d")
public class DiseasesRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private Timestamp updated;

	//bi-directional many-to-one association to Diseas
	@ManyToOne
	@JoinColumn(name="disease_id")
	private Diseas diseas;

	//bi-directional many-to-one association to Ref
	@ManyToOne
	private Ref ref;

	public DiseasesRef() {
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

	public Ref getRef() {
		return this.ref;
	}

	public void setRef(Ref ref) {
		this.ref = ref;
	}

}