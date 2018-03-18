package org.vardb.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the refs database table.
 *
 */
@Entity
@Table(name="refs")
@NamedQuery(name="Ref.findAll", query="SELECT r FROM Ref r")
public class Ref implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="abstract")
	private String abstract_;

	private String authors;

	private String city;

	private Timestamp created;

	private String identifier;

	private String journal;

	private String name;

	private Integer numsequences;

	private String pages;

	private Integer pmid;

	private String publisher;

	private String title;

	private String type;

	private Timestamp updated;

	private Boolean visible;

	private String volume;

	private String year;

	//bi-directional many-to-one association to DiseasesRef
	@OneToMany(mappedBy="ref")
	private List<DiseasesRef> diseasesRefs;

	//bi-directional many-to-one association to FamiliesRef
	@OneToMany(mappedBy="ref")
	private List<FamiliesRef> familiesRefs;

	//bi-directional many-to-one association to OrthologsRef
	@OneToMany(mappedBy="ref")
	private List<OrthologsRef> orthologsRefs;

	//bi-directional many-to-one association to PathogensRef
	@OneToMany(mappedBy="ref")
	private List<PathogensRef> pathogensRefs;

	public Ref() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getAuthors() {
		return this.authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
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

	public String getPages() {
		return this.pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public Integer getPmid() {
		return this.pmid;
	}

	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<DiseasesRef> getDiseasesRefs() {
		return this.diseasesRefs;
	}

	public void setDiseasesRefs(List<DiseasesRef> diseasesRefs) {
		this.diseasesRefs = diseasesRefs;
	}

	public DiseasesRef addDiseasesRef(DiseasesRef diseasesRef) {
		getDiseasesRefs().add(diseasesRef);
		diseasesRef.setRef(this);

		return diseasesRef;
	}

	public DiseasesRef removeDiseasesRef(DiseasesRef diseasesRef) {
		getDiseasesRefs().remove(diseasesRef);
		diseasesRef.setRef(null);

		return diseasesRef;
	}

	public List<FamiliesRef> getFamiliesRefs() {
		return this.familiesRefs;
	}

	public void setFamiliesRefs(List<FamiliesRef> familiesRefs) {
		this.familiesRefs = familiesRefs;
	}

	public FamiliesRef addFamiliesRef(FamiliesRef familiesRef) {
		getFamiliesRefs().add(familiesRef);
		familiesRef.setRef(this);

		return familiesRef;
	}

	public FamiliesRef removeFamiliesRef(FamiliesRef familiesRef) {
		getFamiliesRefs().remove(familiesRef);
		familiesRef.setRef(null);

		return familiesRef;
	}

	public List<OrthologsRef> getOrthologsRefs() {
		return this.orthologsRefs;
	}

	public void setOrthologsRefs(List<OrthologsRef> orthologsRefs) {
		this.orthologsRefs = orthologsRefs;
	}

	public OrthologsRef addOrthologsRef(OrthologsRef orthologsRef) {
		getOrthologsRefs().add(orthologsRef);
		orthologsRef.setRef(this);

		return orthologsRef;
	}

	public OrthologsRef removeOrthologsRef(OrthologsRef orthologsRef) {
		getOrthologsRefs().remove(orthologsRef);
		orthologsRef.setRef(null);

		return orthologsRef;
	}

	public List<PathogensRef> getPathogensRefs() {
		return this.pathogensRefs;
	}

	public void setPathogensRefs(List<PathogensRef> pathogensRefs) {
		this.pathogensRefs = pathogensRefs;
	}

	public PathogensRef addPathogensRef(PathogensRef pathogensRef) {
		getPathogensRefs().add(pathogensRef);
		pathogensRef.setRef(this);

		return pathogensRef;
	}

	public PathogensRef removePathogensRef(PathogensRef pathogensRef) {
		getPathogensRefs().remove(pathogensRef);
		pathogensRef.setRef(null);

		return pathogensRef;
	}

}