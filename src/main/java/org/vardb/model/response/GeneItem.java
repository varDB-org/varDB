package org.vardb.model.response;

/**
 * gene item
 */
public class GeneItem {
	private String id;
	private String link;
	private String description;
	private String species;
	private String family;
	private String oc;
	Integer aaLength;
	Integer ntLength;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getOc() {
		return oc;
	}
	public void setOc(String oc) {
		this.oc = oc;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public Integer getAaLength() {
		return aaLength;
	}
	public void setAaLength(Integer aaLength) {
		this.aaLength = aaLength;
	}
	public Integer getNtLength() {
		return ntLength;
	}
	public void setNtLength(Integer ntLength) {
		this.ntLength = ntLength;
	}
}
