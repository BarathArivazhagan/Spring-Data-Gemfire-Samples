package com.barath.app;

import java.io.Serializable;

import org.apache.geode.cache.Cache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.PartitionedRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;



@SpringBootApplication
@CacheServerApplication(name="embedded-gemfire-server")
@EnableLocator
@EnableManager(start = true)
public class GemfireServer {

	public static void main(String[] args) {
		SpringApplication.run(GemfireServer.class, args);
	}
	
	@Bean(name = "Books")
	public PartitionedRegionFactoryBean<String, Book> factorialsRegion(Cache gemfireCache) {

		PartitionedRegionFactoryBean<String, Book> books = new PartitionedRegionFactoryBean<>();
		books.setCache(gemfireCache);
		books.setClose(false);
		books.setName("BOOKS");
		books.setPersistent(false);
		return books;
	}
	
	
	public class Book implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -1768249225099651970L;

		private String bookId;
		
		private String bookName;

		public String getBookId() {
			return bookId;
		}

		public void setBookId(String bookId) {
			this.bookId = bookId;
		}

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		@Override
		public String toString() {
			return "Book [bookId=" + bookId + ", bookName=" + bookName + "]";
		}
		
		@PersistenceConstructor
		public Book(String bookId, String bookName) {
			super();
			this.bookId = bookId;
			this.bookName = bookName;
		}

		public Book() {
			super();
			
		}

		

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Book other = (Book) obj;
			if (bookId != other.bookId)
				return false;
			if (bookName == null) {
				if (other.bookName != null)
					return false;
			} else if (!bookName.equals(other.bookName))
				return false;
			return true;
		}
		
		

	}
}
