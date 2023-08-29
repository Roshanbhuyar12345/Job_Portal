package com.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiWrapper {

	@JsonProperty("count")
	private Long count;

	@JsonProperty("entries")
	private List<ApiResponseStore> entries;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<ApiResponseStore> getEntries() {
		return entries;

	}

	public void setEntries(List<ApiResponseStore> entries) {
		this.entries = entries;
	}

}
