package com.nit.service.impl;

import java.util.Random;

import com.nit.dao.IAccountHolderDAO;
import com.nit.dao.impl.AccountHolderDAOImpl;
import com.nit.model.AccountHolderBO;
import com.nit.model.AccountHolderVO;
import com.nit.service.IAccountHolderService;
import com.nit.helper.AccountHolderNotFoundException;

public class AccountHolderServiceImpl implements IAccountHolderService {
	private IAccountHolderDAO dao;

	public AccountHolderServiceImpl() {
		dao = new AccountHolderDAOImpl();
	}

	public int registerAccountHolder(AccountHolderVO ahVo) {
		AccountHolderBO ahBo = new AccountHolderBO();
		ahBo.setFullName(ahVo.getFullName());
		ahBo.setEmail(ahVo.getEmail());
		ahBo.setPhoneNo(ahVo.getPhoneNo());
		ahBo.setNomineeName(ahVo.getNomineeName());
		ahBo.setDateOfBirth(ahVo.getDateOfBirth());
		ahBo.setPassword(ahVo.getPassword());
		ahBo.setReEnterPassword(ahVo.getReEnterPassword());
		ahBo.setBalance(500.00);
		int result = dao.registerAccountHolder(ahBo);
		return result;
	}

	public int updateAccountHolder(AccountHolderVO ahVo) {
		AccountHolderBO ahBo = new AccountHolderBO();
		ahBo.setAccountHolderId(ahVo.getAccountHolderId());
		ahBo.setFullName(ahVo.getFullName());
		ahBo.setEmail(ahVo.getEmail());
		ahBo.setPhoneNo(ahVo.getPhoneNo());
		ahBo.setNomineeName(ahVo.getNomineeName());
		ahBo.setDateOfBirth(ahVo.getDateOfBirth());
		ahBo.setPassword(ahVo.getPassword());
		ahBo.setReEnterPassword(ahVo.getReEnterPassword());
		int result = dao.updateAccountHolder(ahBo);
		return result;
	}

	public AccountHolderBO login(AccountHolderVO ahVo) throws AccountHolderNotFoundException {
		System.out.println("AccountHolderServiceImpl.login()");
		int count = dao.getAccountHolderCountByEmail(ahVo.getEmail());
		if (count != 0) {
			AccountHolderBO dbAhBo = dao.getAccountHoldrByEmail(ahVo.getEmail());
			System.out.println("For Login purpose: ");
			System.out.println("user given password: " + ahVo.getPassword());
			System.out.println("password stored in db: " + dbAhBo.getPassword());
			if (dbAhBo.getPassword().equals(ahVo.getPassword())) {
				return dbAhBo; // login successful
			}
		}
		throw new AccountHolderNotFoundException("Account holder not found"); // login failed
	}

	public boolean checkAccountHolderByEmail(String email) {
		int count = dao.getAccountHolderCountByEmail(email);
		return count == 1 ? true : false; // return count ==1;
	}

	public AccountHolderVO getAccountHolderByEmail(String email) {
		AccountHolderBO ahBo = dao.getAccountHoldrByEmail(email);
		AccountHolderVO ahVo = new AccountHolderVO();
		ahVo.setFullName(ahBo.getFullName());
		ahVo.setEmail(ahBo.getEmail());
		ahVo.setPhoneNo(ahBo.getPhoneNo());
		ahVo.setNomineeName(ahBo.getNomineeName());
		ahVo.setDateOfBirth(ahBo.getDateOfBirth());
		return ahVo;
	}

	public static long createAccountNo() { // random account number generator
		Random r = new Random();
		long value = r.nextLong();
		if (value < 0) {
			value = value * -1;
		}
		return value;
	}

	public int getBalanceByAccountNumber(Long accountNumber) {
		return dao.getBalanceByAccountNumber(accountNumber);
	}

	public void updateAccountBalance(Long accountHolderId, double newBalance) {
		try {
			dao.updateAccountBalance(accountHolderId, newBalance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AccountHolderVO getAccountHolderById(Long accountHolderId) throws AccountHolderNotFoundException {
		AccountHolderVO ahVo = dao.getAccountHolderById(accountHolderId);
		if (ahVo == null) {
			throw new AccountHolderNotFoundException("AccountHolder with ID " + accountHolderId + " not found");
		}
		return ahVo;
	}

	public AccountHolderVO getAccountByAccountNo(Long accountNumber) {
		return dao.getAccountByAccountNo(accountNumber);
	}

	public boolean transferMoney(Long senderAccNo, Long receiverAccNo, Double amount) {
		System.out.println("AccountHolderServiceImpl.transferMoney()");
		boolean success = false;
		try {
			AccountHolderVO sender = dao.getAccountByAccountNo(senderAccNo); // Get sender and receiver accounts
			AccountHolderVO receiver = dao.getAccountByAccountNo(receiverAccNo);
			double newSenderBalance = sender.getBalance() - amount; // Deduct amount from sender account
			System.out.println("Sender new balance: " + newSenderBalance);
			sender.setBalance(newSenderBalance);
			dao.updateAccount(sender);
			double newReceiverBalance = receiver.getBalance() + amount; // Add amount to receiver account
			System.out.println("Receiver new balance: " + newReceiverBalance);
			receiver.setBalance(newReceiverBalance);
			dao.updateAccount(receiver);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

}