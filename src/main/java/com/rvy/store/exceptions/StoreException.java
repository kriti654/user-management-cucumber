package com.rvy.store.exceptions;

public class StoreException extends Exception {
	private static final long serialVersionUID = 1L;	
	
    public StoreException() {
		super("Can't process the store Data!");
	}
    
    public StoreException(String message) {
		super(message);	
	}


	public StoreException(String message,Throwable e) {
		super(message,e);
	}
    
}
