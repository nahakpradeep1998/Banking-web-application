package com.nit.dao;

import com.nit.helper.AccountHolderNotFoundException;
import com.nit.model.AccountHolderBO;
import com.nit.model.AccountHolderVO;

public interface IAccountHolderDAO {
	int registerAccountHolder(AccountHolderBO ahBo);

	public int updateAccountHolder(AccountHolderBO ahBo);

	public int getAccountHolderCountByEmail(String email);

	public AccountHolderBO getAccountHoldrByEmail(String email);

	public AccountHolderVO getAccountHolderById(Long accountHolderId) throws AccountHolderNotFoundException;

	public AccountHolderVO getAccountByAccountNo(Long accountNumber);

	public int getBalanceByAccountNumber(Long accountNumber);

	public void updateAccountBalance(Long accountHolderId, double newBalance);

	public AccountHolderVO transferMoney(Long senderAccNo, Long receiverAccNo, double amount);
	
	public AccountHolderVO updateAccount(AccountHolderVO ahVo);
}
