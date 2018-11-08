package com.barath.bookstore.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.query.FunctionDomainException;
import com.gemstone.gemfire.cache.query.NameResolutionException;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.TypeMismatchException;

@RestController
public class BookController {
	
	
	@Autowired
	private ClientCache clientCache;
	
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/test")
	public Object testRegions(){
		Region<Long, Book> bookRegion=clientCache.getRegion("BOOK");
		System.out.println("Book region "+bookRegion);
		return bookRegion;
	}
	
	@PostMapping("/findAll")
	public Object findAllBooks(@RequestBody List<String> keys){
		System.out.println(keys);
		return bookService.getAllBooks(keys);
	}
	
	@GetMapping("/findAllEntries")
	public Object findAllBookNames() throws FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException{
		
		return bookService.getAllBookNames();
	}
	
	@GetMapping("/addBook")
	public String addBook(@RequestParam("id") String bookId, @RequestParam("name") String bookName){
		
		Book book=new Book(bookId, bookName);
		bookService.addBook(book);
		return "Book is added successfully ";
	}
	
	@GetMapping("/getBook/{bookId}")
	public Book addBook(@PathVariable("bookId") String bookId){
		
		
		return bookService.getBook(bookId);
		
	}
}
