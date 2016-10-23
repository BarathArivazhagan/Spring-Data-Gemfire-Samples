package com.barath.bookstore.app;

import org.springframework.data.gemfire.repository.GemfireRepository;

public interface BookRepository extends GemfireRepository<Book, Long> {

	public Book findByBookName(String bookName);
	
}
