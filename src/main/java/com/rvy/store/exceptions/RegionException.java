package com.rvy.store.exceptions;

public class RegionException extends Exception {
	private static final long serialVersionUID = 1L;	
	
    public RegionException() {
		super("Can't process the Region Data!");
	}
    
    public RegionException(String message) {
		super(message);	
	}


	public RegionException(String message,Throwable e) {
		super(message,e);
	}
    
}