package com.rvy.store.exceptions;

public class RoleException extends Exception {
	private static final long serialVersionUID = 1L;	

	public RoleException() {
		super("Can't process the Role Data!");
	}

	public RoleException(String message) {
		super(message);	
	}


	public RoleException(String message,Throwable e) {
		super(message,e);
	}
}
