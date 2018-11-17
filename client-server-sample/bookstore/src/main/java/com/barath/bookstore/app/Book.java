
package com.barath.bookstore.app;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.ClientRegion;


@ClientRegion("BOOKS")
public class Book implements Serializable {
	
	private static final long serialVersionUID = -1768249225099651970L;

	@Id
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
