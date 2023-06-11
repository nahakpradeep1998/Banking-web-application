package com.nit.helper;

@SuppressWarnings("serial")
public class InsufficientBalanceException extends Exception {

	public InsufficientBalanceException() {
		super("Insufficient balance");
	}

	public InsufficientBalanceException(String message) {
		super(message);
	}

}
