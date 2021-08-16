package com.rvy.store.exceptions;

public class TaxationException extends Exception {
	private static final long serialVersionUID = 1L;	
	
    public TaxationException() {
		super("Can't process the Tax Data!");
	}
    
    public TaxationException(String message) {
		super(message);	
	}


	public TaxationException(String message,Throwable e) {
		super(message,e);
	}
    
}