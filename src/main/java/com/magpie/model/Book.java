package com.magpie.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "books")
@Data
public class Book {
	
	@Id
	private String id;

	private String title;
	
	private String author;
	
	private String category;
	
	private String subcategory;
	
	private String language;
	
	private String publisher;
	
	private String price;
	
	private String bookId;
}
