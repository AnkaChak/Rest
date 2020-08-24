package com.example.demo.exceptions;

public class TopicNotFoundException extends Exception {

	private static final long serialVersionUID = 5398698854394282507L;
	
	public TopicNotFoundException( ) {
		super();
	}
	
	public TopicNotFoundException( String message ) {
		super(message);
	}
}
