package com.nit.helper;

@SuppressWarnings("serial")
public class AccountHolderNotFoundException extends Exception {

	public AccountHolderNotFoundException() {
		super("Account holder not found.");
	}

	public AccountHolderNotFoundException(String message) {
		super(message);
	}

	public AccountHolderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
