package org.vardb.model.response;

import org.springframework.data.domain.Page;

/**
 * page result
 */
public class PageResult {

	Integer last_page;

	Object data;

	Long total_count;

	Long filtered_count;

	Integer current_page;

	Integer page_size;

	/**
	 * constructor
	 */
	public PageResult() {
		this.last_page = 0;
		this.data = 0;
		this.total_count = 0L;
		this.filtered_count = 0L;
		this.current_page = 0;
		this.page_size = 0;
	}


	/**
	 * constructor
	 * @param currentPage current page
	 * @param pageSize page size
	 * @param lastPage last page
	 * @param data data
	 * @param totalCount total count
	 * @param filteredCount filtered count
	 */
	public PageResult(
			Integer currentPage,
			Integer pageSize,
			Integer lastPage,
			Object data,
			Long totalCount,
			Long filteredCount
	) {
		this();
		this.setCurrent_page( currentPage );
		this.setPage_size( pageSize );
		this.setLast_page( lastPage );
		this.setData( data );
		this.setTotal_count( totalCount );
		this.setFiltered_count( filteredCount );

	}

	/**
	 * constroctor
	 * @param page page information
	 */
	public PageResult( Page< ? > page ) {
		this.setData( page.getContent() );
		this.setLast_page( page.getTotalPages() );
		this.setTotal_count( page.getTotalElements() );
		this.setFiltered_count( page.getTotalElements() );
		this.setCurrent_page( page.getNumber() + 1 );
		this.setPage_size( page.getSize() );
	}

	/**
	 * sets the last page
	 * @param lastPage
	 */
	public void setLast_page( int lastPage ) {
		this.last_page = Math.max( 1,  lastPage );
	}

	/**
	 * gets the last page
	 * @return last page
	 */
	public int getLast_page() {
		return this.last_page;
	}

	/**
	 * sets the data
	 * @param data data
	 */
	public void setData( Object data ) {
		this.data = data;
	}

	/**
	 * gets the data
	 * @return
	 */
	public Object getData() {
		return this.data;
	}

	/**
	 * sets total count
	 * @param totalCount total count
	 */
	public void setTotal_count( Long totalCount ) {
		this.total_count = totalCount;
	}

	/**
	 * gets the total count
	 * @return total count
	 */
	public Long getTotal_count() {
		return this.total_count;
	}

	/**
	 * sets the filtered count
	 * @param filteredCount filtered count
	 */
	public void setFiltered_count( Long filteredCount ) {
		this.filtered_count = filteredCount;
	}

	/**
	 * gets the filtered count
	 * @return filtered count
	 */
	public Long getFiltered_count() {
		return this.filtered_count;
	}

	/**
	 * sets the current page
	 * @param currentPage current page
	 */
	public void setCurrent_page( Integer currentPage ) {
		this.current_page = currentPage;
	}

	/**
	 * gets the current page
	 * @return current page
	 */
	public Integer getCurrent_page() {
		return this.current_page;
	}

	/**
	 * sets the page size
	 * @param pageSize page size
	 */
	public void setPage_size( Integer pageSize ) {
		this.page_size = pageSize;
	}

	/**
	 * gets the page size
	 * @return page size
	 */
	public Integer getPage_size() {
		return this.page_size;
	}
}
