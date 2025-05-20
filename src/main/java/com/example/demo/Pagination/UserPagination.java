package com.example.demo.Pagination;

import java.util.List;

public class UserPagination {
	private Integer page;

	private Integer size;

	private String sortBy;

	private String sortOrder;
	
   private List<SearchFilter> searchFilter;
 
	

	public UserPagination(Integer page, Integer size, String sortBy, String sortOrder) {
		super();
		this.page = page;
		this.size = size;
		this.sortBy = sortBy;
		this.sortOrder = sortOrder;
	}

	public UserPagination() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<SearchFilter> getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(List<SearchFilter> searchFilter) {
		this.searchFilter = searchFilter;
	}

	
}