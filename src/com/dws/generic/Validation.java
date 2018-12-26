package com.dws.generic;

public abstract class Validation {
	protected static Boolean isValid = true;
	protected static String message;	
	
	protected static void hasError(Exception e) {
		isValid = false;
		message = "Error: " + e.getMessage();
	}
	
	protected static void hasError(String exception) {
		hasError(new Exception(exception));
	}
}
