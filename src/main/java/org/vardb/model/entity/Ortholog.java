package org.vardb.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the orthologs database table.
 * 
 */
@Entity
@Table(name="orthologs")
@NamedQuery(name="Ortholog.findAll", query="SELECT o FROM Ortholog o")
public class Ortholog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="alignment_id")
	private Integer alignmentId;

	private Timestamp created;

	private String description;

	private String html;

	private String identifier;

	@Column(name="kegg_ortholog")
	private String keggOrtholog;

	@Column(name="kegg_pathway")
	private String keggPathway;

	private String name;

	private String notes;

	private Integer numsequences;

	private Timestamp updated;

	//bi-directional many-to-one association to Family
	@OneToMany(mappedBy="ortholog")
	private List<Family> families;

	//bi-directional many-to-one association to OrthologsRef
	@OneToMany(mappedBy="ortholog")
	private List<OrthologsRef> orthologsRefs;

	public Ortholog() {
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

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getKeggOrtholog() {
		return this.keggOrtholog;
	}

	public void setKeggOrtholog(String keggOrtholog) {
		this.keggOrtholog = keggOrtholog;
	}

	public String getKeggPathway() {
		return this.keggPathway;
	}

	public void setKeggPathway(String keggPathway) {
		this.keggPathway = keggPathway;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public List<Family> getFamilies() {
		return this.families;
	}

	public void setFamilies(List<Family> families) {
		this.families = families;
	}

	public Family addFamily(Family family) {
		getFamilies().add(family);
		family.setOrtholog(this);

		return family;
	}

	public Family removeFamily(Family family) {
		getFamilies().remove(family);
		family.setOrtholog(null);

		return family;
	}

	public List<OrthologsRef> getOrthologsRefs() {
		return this.orthologsRefs;
	}

	public void setOrthologsRefs(List<OrthologsRef> orthologsRefs) {
		this.orthologsRefs = orthologsRefs;
	}

	public OrthologsRef addOrthologsRef(OrthologsRef orthologsRef) {
		getOrthologsRefs().add(orthologsRef);
		orthologsRef.setOrtholog(this);

		return orthologsRef;
	}

	public OrthologsRef removeOrthologsRef(OrthologsRef orthologsRef) {
		getOrthologsRefs().remove(orthologsRef);
		orthologsRef.setOrtholog(null);

		return orthologsRef;
	}

}