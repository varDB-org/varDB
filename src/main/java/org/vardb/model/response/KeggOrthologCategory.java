package org.vardb.model.response;

/**
 * KEGG Ortholog category
 */
public class KeggOrthologCategory {
	private String id;
	private Integer size;
	private Integer hits;
	private String type;

	public void setId( String id ) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setSize( Integer size ) {
		this.size = size;
	}

	public Integer getSize() {
		return this.size;
	}

	public void setHits( Integer hits ) {
		this.hits = hits;
	}

	public Integer getHits() {
		return this.hits;
	}

	public void setType( String type ) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}
