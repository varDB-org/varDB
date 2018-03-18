package org.vardb.model.response;

/**
 * paralog item
 */
public class ParalogItem {
	private Integer id;
	private String accession;
	private String name;
	private Integer alignment_id;
	private String description;
	private Integer genome_id;
	private String family;
	private Integer numsequence;

	/**
	 * sets the ID
	 * @param id
	 */
	public void setId( Integer id ) {
		this.id = id;
	}

	/**
	 * gets the ID
	 * @return ID
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * sets the accession
	 * @param accession accession
	 */
	public void setAccession( String accession ) {
		this.accession = accession;
	}

	/**
	 * gets the accession
	 * @return
	 */
	public String getAccession() {
		return this.accession;
	}

	/**
	 * sets the name
	 * @param name
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * gets the name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * sets the alignment ID
	 * @param alignmentId alignment ID
	 */
	public void setAlignmendId( Integer alignmentId ) {
		this.alignment_id = alignmentId;
	}

	/**
	 * gets the alignment ID
	 * @return alignment ID
	 */
	public Integer getAlignmentId() {
		return this.alignment_id;
	}


	/**
	 * sets the description
	 * @param description description
	 */
	public void setDescription( String description ) {
		this.description = description;
	}

	/**
	 * gets the description
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * sets the genome ID
	 * @param genomeId genome ID
	 */
	public void setGenomeId( Integer genomeId ) {
		this.genome_id = genomeId;
	}

	/**
	 * gets the genome ID
	 * @return genome ID
	 */
	public Integer getGenomeId() {
		return this.genome_id;
	}

	/**
	 * sets the family
	 * @param family family
	 */
	public void setFamily( String family ) {
		this.family = family;
	}

	/**
	 * gets the family
	 * @return family
	 */
	public String getFamily() {
		return this.family;
	}

	/**
	 * sets the number of sequences
	 * @param numsequence number of sequences
	 */
	public void setNumsequence( Integer numsequence ) {
		this.numsequence = numsequence;
	}

	/**
	 * gets the number of sequences
	 * @return number of sequences
	 */
	public Integer getNumsequence() {
		return this.numsequence;
	}
}
