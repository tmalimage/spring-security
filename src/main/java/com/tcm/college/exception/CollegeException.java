package com.tcm.college.exception;

public class CollegeException extends Exception {

	private static final long serialVersionUID = 1L;

	public CollegeException(String message) {
		super(message);
	}
	
	public CollegeException(String message, Exception exception) {
		super(message, exception);
	}
	
}
