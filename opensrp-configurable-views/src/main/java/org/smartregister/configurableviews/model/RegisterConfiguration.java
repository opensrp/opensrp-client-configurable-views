package org.smartregister.configurableviews.model;

import java.util.List;

public class RegisterConfiguration extends BaseConfiguration {
	
	private boolean enableAdvancedSearch;
	
	private boolean enableSortList;
	
	private boolean enableFilterList;
	
	private String searchBarText;
	
	private List<String> searchableFields;

	private List<Field> sortFields;

	private List<Field> filterFields;
	
	public boolean isEnableAdvancedSearch() {
		return enableAdvancedSearch;
	}
	
	public void setEnableAdvancedSearch(boolean enableAdvancedSearch) {
		this.enableAdvancedSearch = enableAdvancedSearch;
	}
	
	public boolean isEnableSortList() {
		return enableSortList;
	}
	
	public void setEnableSortList(boolean enableSortList) {
		this.enableSortList = enableSortList;
	}
	
	public boolean isEnableFilterList() {
		return enableFilterList;
	}
	
	public void setEnableFilterList(boolean enableFilterList) {
		this.enableFilterList = enableFilterList;
	}
	
	public String getSearchBarText() {
		return searchBarText;
	}
	
	public void setSearchBarText(String searchBarText) {
		this.searchBarText = searchBarText;
	}
	
	public List<String> getSearchableFields() {
		return searchableFields;
	}
	
	public void setSearchableFields(List<String> searchableFields) {
		this.searchableFields = searchableFields;
	}

	public List<Field> getSortFields() {
		return sortFields;
	}

	public void setSortFields(List<Field> sortFields) {
		this.sortFields = sortFields;
	}

	public List<Field> getFilterFields() {
		return filterFields;
	}

	public void setFilterFields(List<Field> filterFields) {
		this.filterFields = filterFields;
	}
}
