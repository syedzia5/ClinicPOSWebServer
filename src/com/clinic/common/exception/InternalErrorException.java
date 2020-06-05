package com.clinic.common.exception;

public class InternalErrorException extends Exception {

	final String msg = "Internal System Error";

	public InternalErrorException() {
		// TODO Auto-generated constructor stub
	}

	public InternalErrorException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InternalErrorException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InternalErrorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InternalErrorException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		String s = msg + " : "+super.getMessage();
		return s;
	}
}
