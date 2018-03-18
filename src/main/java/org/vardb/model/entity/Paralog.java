package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the paralogs database table.
 * 
 */
@Entity
@Table(name="paralogs")
@NamedQuery(name="Paralog.findAll", query="SELECT p FROM Paralog p")
public class Paralog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="alignment_id")
	private Integer alignmentId;

	private Timestamp created;

	private String description;

	@Column(name="genome_id")
	private Integer genomeId;

	private String identifier;

	private String name;

	private Integer numsequences;

	private Timestamp updated;

	//bi-directional many-to-one association to Family
	@ManyToOne
	private Family family;

	public Paralog() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAlignmentId() {
		return this.alignmentId;
	}

	public void setAlignmentId(Integer alignmentId) {
		this.alignmentId = alignmentId;
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

	public Integer getGenomeId() {
		return this.genomeId;
	}

	public void setGenomeId(Integer genomeId) {
		this.genomeId = genomeId;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumsequences() {
		return this.numsequences;
	}

	public void setNumsequences(Integer numsequences) {
		this.numsequences = numsequences;
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

}