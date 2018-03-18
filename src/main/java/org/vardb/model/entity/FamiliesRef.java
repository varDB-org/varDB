package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the families_refs database table.
 * 
 */
@Entity
@Table(name="families_refs")
@NamedQuery(name="FamiliesRef.findAll", query="SELECT f FROM FamiliesRef f")
public class FamiliesRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private Timestamp updated;

	//bi-directional many-to-one association to Family
	@ManyToOne
	private Family family;

	//bi-directional many-to-one association to Ref
	@ManyToOne
	private Ref ref;

	public FamiliesRef() {
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

	public Family getFamily() {
		return this.family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public Ref getRef() {
		return this.ref;
	}

	public void setRef(Ref ref) {
		this.ref = ref;
	}

}