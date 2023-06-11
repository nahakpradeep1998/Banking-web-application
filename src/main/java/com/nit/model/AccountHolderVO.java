package com.nit.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AccountHolderVO implements Serializable {
	private String fullName;
	private String email;
	private Long phoneNo;
	private String nomineeName;
	private String dateOfBirth;
	private String password;
	private String ReEnterPassword;
	private Long accountHolderId;
	private Long accountNumber;
	public double balance;

	public AccountHolderVO() {
		super();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnterPassword() {
		return ReEnterPassword;
	}

	public void setReEnterPassword(String reEnterPassword) {
		ReEnterPassword = reEnterPassword;
	}

	public Long getAccountHolderId() {
		return accountHolderId;
	}

	public void setAccountHolderId(Long accountHolderId) {
		this.accountHolderId = accountHolderId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

}
