package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the orthologs_refs database table.
 * 
 */
@Entity
@Table(name="orthologs_refs")
@NamedQuery(name="OrthologsRef.findAll", query="SELECT o FROM OrthologsRef o")
public class OrthologsRef implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp created;

	private Timestamp updated;

	//bi-directional many-to-one association to Ortholog
	@ManyToOne
	private Ortholog ortholog;

	//bi-directional many-to-one association to Ref
	@ManyToOne
	private Ref ref;

	public OrthologsRef() {
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

	public Ortholog getOrtholog() {
		return this.ortholog;
	}

	public void setOrtholog(Ortholog ortholog) {
		this.ortholog = ortholog;
	}

	public Ref getRef() {
		return this.ref;
	}

	public void setRef(Ref ref) {
		this.ref = ref;
	}

}