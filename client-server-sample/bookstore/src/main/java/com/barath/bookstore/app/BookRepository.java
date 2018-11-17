package com.barath.bookstore.app;

import java.util.List;

import org.springframework.data.gemfire.repository.GemfireRepository;

public interface BookRepository extends GemfireRepository<Book, String> {

	Book findByBookName(String bookName);
	
	List<Book> findAll();
	
	
}
