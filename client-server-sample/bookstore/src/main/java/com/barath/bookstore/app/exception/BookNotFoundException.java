package com.barath.bookstore.app.exception;

public class BookNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 727035610127080798L;

	public BookNotFoundException() {
		super();
		
	}

	public BookNotFoundException(String message) {
		super(message);
		
	}
	
	 

}
