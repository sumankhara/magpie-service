package com.magpie.model;

import lombok.Data;

@Data
public class GoodreadsResponse {

	private String imageUrl;
	
	private String description;
	
	public String toString() {
		return "description: " + description;
	}
	
	public GoodreadsResponse(String imageUrl, String description) {
		this.imageUrl = imageUrl;
		this.description = description;
	}
}
