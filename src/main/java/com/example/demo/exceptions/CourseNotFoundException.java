package com.example.demo.exceptions;

public class CourseNotFoundException extends Exception {

	private static final long serialVersionUID = 5398698854394282508L;
	
	public CourseNotFoundException( ) {
		super();
	}
	
	public CourseNotFoundException( String message ) {
		super(message);
	}
}
