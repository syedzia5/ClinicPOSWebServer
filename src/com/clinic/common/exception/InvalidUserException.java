package com.clinic.common.exception;

public class InvalidUserException extends Exception {

	final String msg = "Invalid User ID or Password";
	
	public InvalidUserException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		String sumsg = super.getMessage();
		String s = msg +((sumsg == null)?"":" : "+sumsg);
		return s;
	}
}
