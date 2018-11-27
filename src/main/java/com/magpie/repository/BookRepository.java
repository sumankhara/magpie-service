package com.magpie.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.magpie.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	public List<Book> findByCategory(String category);
	public List<Book> findBySubcategory(String subcategory);
}
