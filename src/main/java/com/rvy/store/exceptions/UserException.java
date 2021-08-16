package com.rvy.store.exceptions;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserException() {
		super("Can't process the user Data!");
	}
    
    public UserException(String message) {
		super(message);	
	}


	public UserException(String message,Throwable e) {
		super(message,e);
	}
    
}
