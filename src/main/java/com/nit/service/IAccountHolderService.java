package com.nit.service;

import com.nit.helper.AccountHolderNotFoundException;
import com.nit.model.AccountHolderBO;
import com.nit.model.AccountHolderVO;

public interface IAccountHolderService {
	public int registerAccountHolder(AccountHolderVO ahVo);

	public int updateAccountHolder(AccountHolderVO ahVo);

	public boolean checkAccountHolderByEmail(String email);

	public AccountHolderBO login(AccountHolderVO ahVo) throws AccountHolderNotFoundException;

	public AccountHolderVO getAccountHolderByEmail(String email);

	public AccountHolderVO getAccountHolderById(Long accountHolderId) throws AccountHolderNotFoundException;

	public AccountHolderVO getAccountByAccountNo(Long accountNumber);

	public int getBalanceByAccountNumber(Long accountNumber);

	public void updateAccountBalance(Long accountHolderId, double newBalance);

	public boolean transferMoney(Long senderAccNo, Long receiverAccNo, Double amount);
}
