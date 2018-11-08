package com.barath.bookstore.app;

import java.util.List;

import javax.validation.Valid;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

	private final BookService bookService;
	
	
	public BookController( BookService bookService) {
		super();		
		this.bookService = bookService;
	}

	@GetMapping("/test")
	public Object testRegions(){
//		Region<Long, Book> bookRegion=clientCache.getRegion("BOOKS");
//		System.out.println("Book region "+bookRegion);
		return null;
	}
	
	@PostMapping("/byKeys")
	public Object findBooksByKeys(@RequestBody List<String> keys){
		System.out.println(keys);
		return bookService.getAllBooks(keys);
	}
	
	@GetMapping("/names")
	public Object findAllBookNames() throws FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException{
		
		return bookService.getAllBookNames();
	}
	
	@PostMapping("/new")
	public Book addBook(@RequestBody @Valid Book book){
		
		return bookService.addBook(book);
		
	}
	
	@GetMapping("/byId/{bookId}")
	public Book addBook(@PathVariable("bookId") String bookId){
		
		
		return bookService.getBook(bookId);
		
	}
}
