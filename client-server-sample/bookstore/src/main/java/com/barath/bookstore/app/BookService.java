package com.barath.bookstore.app;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.TypeMismatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.barath.bookstore.app.exception.BookNotFoundException;



@Service
public class BookService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final Region<String, String> bookRegion; 
	
	private final ClientCache clientCache;
	
	private final BookRepository bookRepository;
	
	private static final String BOOK_REGION_NAME="BOOKS";
	
	private static final String SELECT_QUERYSTRING="select * from /BOOKS";
	
	public BookService(ClientCache clientCache,BookRepository bookRepository){
		this.bookRegion=clientCache.getRegion(BOOK_REGION_NAME);
		this.clientCache=clientCache;
		this.bookRepository=bookRepository;
	}
	
	public Book addBook(Book book){
		
		if(logger.isInfoEnabled()) { logger.info("adding a book with details {}", Objects.toString(book)); }
		return this.bookRepository.save(book);
	}
	
	public Book getBook(String bookId){
		return this.bookRepository.findById(bookId)				
				.orElseThrow( () ->  new BookNotFoundException(String.format("Book not found with id %s", bookId)));
	}

	public Book updateBook(Book book){
		
		return this.bookRepository.save(book);
	}
	public void deleteBook(String bookId){
		
		if(logger.isInfoEnabled()) { logger.info("deleting a book with bookId {}", Objects.toString(bookId)); }
		this.bookRepository.deleteById(bookId);
	
	}
	public void deleteBook(Book book){
		if(logger.isInfoEnabled()) { logger.info("deleting a book with book {}", Objects.toString(book)); }
		this.bookRepository.delete(book);
	}
	
	public boolean isBookExists(String bookId){
		return bookRegion.containsKey(bookId);
	}
	
	public boolean isBookExists(Book book){
		if(book != null){
			return bookRegion.containsKey(book.getBookId());
		}
		return false;
	}

	public Book getBookByName(String bookName) {
		
		if(logger.isInfoEnabled()) { logger.info("get a book with book name{}", bookName); }
		
		return this.bookRepository.findByBookName(bookName);
	}


	public Map<String, String> getAllBooks(Collection<String> keys) {
			
		
		System.out.println(keys);
		Map<String, String> books=bookRegion.getAll(keys);
		System.out.println(books);
	
		 books.entrySet().stream().forEach(System.out::println);
		 return books;
	}
	
	public List<Book> getBooks() {
		
		if(logger.isInfoEnabled()) { logger.info("getting all the books from books region"); }
		return this.bookRepository.findAll();
	}
	
	public List<String> getAllBookNames() throws FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException {
			
			
		QueryService queryService = this.clientCache.getQueryService();	
		Query query = queryService.newQuery(SELECT_QUERYSTRING);			
		SelectResults<Object> results = (SelectResults<Object>)query.execute();
		logger.info("Results {}",results);
		logger.info("SIZE {}",results.size());	
		List<String> bookNames=new ArrayList<String>(results.size());
		results.stream().forEach(System.out::println);
		 return bookNames;
	}
	
	@PostConstruct
	public void init() {

		IntStream
		.range(0, 100)
		.forEachOrdered( value -> {
			
			Book book = new Book(String.valueOf(value), "Book"+value);
			this.bookRepository.save(book);
		});
	}

}
