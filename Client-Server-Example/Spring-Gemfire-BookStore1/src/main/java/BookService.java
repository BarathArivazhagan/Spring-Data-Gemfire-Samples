package com.barath.bookstore.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.query.FunctionDomainException;
import com.gemstone.gemfire.cache.query.NameResolutionException;
import com.gemstone.gemfire.cache.query.Query;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.QueryService;
import com.gemstone.gemfire.cache.query.SelectResults;
import com.gemstone.gemfire.cache.query.TypeMismatchException;


@Service
public class BookService {
	
	private Region<String, String> bookRegion; 
	
	private ClientCache clientCache;
	
	private static final String BOOK_REGION_NAME="BOOK";
	
	private static final String SELECT_QUERYSTRING="select * from /BOOK";
	
	
	@Autowired
	public BookService(ClientCache clientCache){
		this.bookRegion=clientCache.getRegion(BOOK_REGION_NAME);
		this.clientCache=clientCache;
	}
	
	public void addBook(Book book){
		bookRegion.put(book.getBookId(), book.getBookName());
		//clientCache.
	}
	
	public Book getBook(String bookId){
		Book book=null;
		//if(isBookExists(bookId)){
			String bookName=bookRegion.get(bookId);
			if(!StringUtils.isEmpty(bookName)){
				book=new Book(bookId, bookName);
			}
			System.out.println(book.toString());
		//}
		
		return book;
	}

	public void updateBook(Book book){
		//if(isBookExists(book)){
			Book bookFound=getBook(book.getBookId());
			bookFound.setBookId(book.getBookId());
			bookFound.setBookName(book.getBookName());
			bookRegion.put(bookFound.getBookId(),bookFound.getBookName());
		//}
	}
	public void deleteBook(String bookId){
		if(isBookExists(bookId)){
			bookRegion.remove(bookId) ;
		}
	}
	public void deleteBook(Book book){
		if(isBookExists(book)){
			bookRegion.remove(book) ;
		}
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

//	public Book getBook(String bookName) {
//		
//		if(!StringUtils.isEmpty(bookName)){
//			return this.findByBookName(bookName);
//		}
//		return null;
//	}
//	
//	private Book findByBookName(String bookName) {
//		if(isBookExists(book))
//	}

	public Map<String, String> getAllBooks(Collection<String> keys) {
			
		
		System.out.println(keys);
		Map<String, String> books=bookRegion.getAll(keys);
		System.out.println(books);
	
		 books.entrySet().stream().forEach(System.out::println);
		 return books;
	}
	
	public List<String> getAllBookNames() throws FunctionDomainException, TypeMismatchException, NameResolutionException, QueryInvocationTargetException {
			
			
		QueryService queryService = this.clientCache.getQueryService();	
		Query query = queryService.newQuery(SELECT_QUERYSTRING);			
		SelectResults<Object> results = (SelectResults<Object>)query.execute();
		System.out.println("Results "+results);
		System.out.println("SIZE "+results.size());	
		List<String> bookNames=new ArrayList<String>(results.size());
		results.stream().forEach(System.out::println);
		 return bookNames;
	}

}
