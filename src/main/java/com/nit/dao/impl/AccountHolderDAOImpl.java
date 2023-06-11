package com.nit.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nit.dao.IAccountHolderDAO;
import com.nit.model.AccountHolderBO;
import com.nit.model.AccountHolderVO;
import com.nit.util.DBConnection;
import com.nit.helper.AccountHolderNotFoundException;
import com.nit.service.impl.AccountHolderServiceImpl;

public class AccountHolderDAOImpl implements IAccountHolderDAO {
	private Connection con = null;

	public int registerAccountHolder(AccountHolderBO ahBo) {
		int rowInserted = 0;
		try {
			con = DBConnection.getConnection();
			String query = "INSERT into AccountHolder_Details(AccountHolderId, fullName, email, phoneNo, nomineeName, dateOfBirth, password, reEnterPassword, accountNumber, balance) VALUES(AH_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ahBo.getFullName());
			ps.setString(2, ahBo.getEmail());
			ps.setLong(3, ahBo.getPhoneNo());
			ps.setString(4, ahBo.getNomineeName());
			ps.setString(5, ahBo.getDateOfBirth());
			ps.setString(6, ahBo.getPassword());
			ps.setString(7, ahBo.getReEnterPassword());
			long accountNumber = AccountHolderServiceImpl.createAccountNo();
			ps.setLong(8, accountNumber);
			ps.setDouble(9, ahBo.getBalance());
			rowInserted = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowInserted;
	}

	public int updateAccountHolder(AccountHolderBO ahBo) {
		int rowUpdated = 0;
		try {
			con = DBConnection.getConnection();
			String query = "UPDATE AccountHolder_Details SET fullName=?, email=?, phoneNo=?, nomineeName=?, dateOfBirth=?, password=?, reEnterPassword=? where accountHolderId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ahBo.getFullName());
			ps.setString(2, ahBo.getEmail());
			ps.setLong(3, ahBo.getPhoneNo());
			ps.setString(4, ahBo.getNomineeName());
			ps.setString(5, ahBo.getDateOfBirth());
			ps.setString(6, ahBo.getPassword());
			ps.setString(7, ahBo.getReEnterPassword());
			ps.setLong(8, ahBo.getAccountHolderId());
			rowUpdated = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	public int getAccountHolderCountByEmail(String email) {
		int count = 0;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT  count(*) from AccountHolder_Details WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public AccountHolderBO getAccountHoldrByEmail(String email) {
		AccountHolderBO ahBo = null;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * from AccountHolder_Details WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ahBo = new AccountHolderBO();
				ahBo.setAccountHolderId(rs.getLong("AccountHolderId"));
				ahBo.setFullName(rs.getString("fullName"));
				ahBo.setEmail(rs.getString("email"));
				ahBo.setPhoneNo(rs.getLong("phoneNo"));
				ahBo.setNomineeName(rs.getString("nomineeName"));
				ahBo.setDateOfBirth(rs.getString("dateOfBirth"));
				ahBo.setPassword(rs.getString("password"));
				ahBo.setReEnterPassword(rs.getString("reEnterPassword"));
				ahBo.setAccountNumber(rs.getLong("accountNumber"));
				ahBo.setBalance(rs.getDouble("balance"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ahBo;
	}

	public AccountHolderVO getAccountHolderById(Long accountHolderId) throws AccountHolderNotFoundException {
		AccountHolderVO ahVo = null;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * from accountHolder_Details WHERE accountHolderId=?");
			ps.setLong(1, accountHolderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ahVo = new AccountHolderVO();
				ahVo.setAccountHolderId(rs.getLong(1));
				ahVo.setFullName(rs.getString(2));
				ahVo.setEmail(rs.getString(3));
				ahVo.setPhoneNo(rs.getLong(4));
				ahVo.setNomineeName(rs.getString(5));
				ahVo.setDateOfBirth(rs.getString(6));
				ahVo.setPassword(rs.getString(7));
				ahVo.setReEnterPassword(rs.getString(8));
			} else {
				throw new AccountHolderNotFoundException("Account Holder with id " + accountHolderId + " not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountHolderNotFoundException(
					"An error occurred while getting the Account Holder with id " + accountHolderId + ".");
		}
		return ahVo;
	}

	public int getBalanceByAccountNumber(Long accountNumber) {
		int balance = 0;
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT balance from accountHolder_Details WHERE accountNumber=?");
			ps.setLong(1, accountNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				balance = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return balance;
	}

	public void updateAccountBalance(Long accountHolderId, double newBalance) {
		try {
			con = DBConnection.getConnection();
			String query = "UPDATE accountHolder_Details SET balance=? WHERE accountHolderId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, newBalance);
			ps.setLong(2, accountHolderId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AccountHolderVO getAccountByAccountNo(Long accountNumber) {
		AccountHolderVO ahVo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			String sql = "SELECT * FROM AccountHolder_Details WHERE accountNumber = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, accountNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				ahVo = new AccountHolderVO();
				ahVo.setAccountHolderId(rs.getLong(1));
				ahVo.setFullName(rs.getString(2));
				ahVo.setEmail(rs.getString(3));
				ahVo.setPhoneNo(rs.getLong(4));
				ahVo.setNomineeName(rs.getString(5));
				ahVo.setDateOfBirth(rs.getString(6));
				ahVo.setPassword(rs.getString(7));
				ahVo.setReEnterPassword(rs.getString(8));
				ahVo.setAccountNumber(rs.getLong(9));
				ahVo.setBalance(rs.getDouble(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ahVo;
	}

	public AccountHolderVO transferMoney(Long senderAccNo, Long receiverAccNo, double amount) {
		try {
			con = DBConnection.getConnection();

			// Get sender account holder details
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM accountHolder_Details WHERE accountNumber = ?");
			ps1.setLong(1, senderAccNo);
			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				// Sender account does not exist
				return null;
			}
			double senderBalance = rs1.getDouble("balance");
			//long senderId = rs1.getLong("accountHolderId");

			// Get receiver account holder details
			PreparedStatement ps2 = con.prepareStatement("SELECT * FROM accountHolder_Details WHERE accountNumber = ?");
			ps2.setLong(1, receiverAccNo);
			ResultSet rs2 = ps2.executeQuery();
			if (!rs2.next()) {
				// Receiver account does not exist
				return null;
			}
			double receiverBalance = rs2.getDouble("balance");
			//long receiverId = rs2.getLong("accountHolderId");

			// Deduct amount from sender account
			double newSenderBalance = senderBalance - amount;
			PreparedStatement ps3 = con
					.prepareStatement("UPDATE accountHolder_Details SET balance = ? WHERE accountNumber = ?");
			ps3.setDouble(1, newSenderBalance);
			ps3.setLong(2, senderAccNo);
			int rowUpdated1 = ps3.executeUpdate();
			if (rowUpdated1 == 0) {
				// Failed to update sender account balance
				return null;
			}

			// Add amount to receiver account
			double newReceiverBalance = receiverBalance + amount;
			PreparedStatement ps4 = con
					.prepareStatement("UPDATE accountHolder_Details SET balance = ? WHERE accountNumber = ?");
			ps4.setDouble(1, newReceiverBalance);
			ps4.setLong(2, receiverAccNo);
			int rowUpdated2 = ps4.executeUpdate();
			if (rowUpdated2 == 0) {
				// Failed to update receiver account balance
				return null;
			}

			// Get updated account holder details and return as result
			AccountHolderVO sender = getAccountByAccountNo(senderAccNo);
			AccountHolderVO receiver = getAccountByAccountNo(receiverAccNo);
			if (sender == null || receiver == null) {
				return null;
			}
			sender.setBalance(newSenderBalance);
			receiver.setBalance(newReceiverBalance);
			return receiver;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public AccountHolderVO updateAccount(AccountHolderVO ahVo) {
		try {
			con = DBConnection.getConnection();
			String query = "UPDATE AccountHolder_Details SET fullName=?, email=?, phoneNo=?, nomineeName=?, dateOfBirth=?, password=?, reEnterPassword=?, accountNumber=?, balance=? where accountHolderId=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ahVo.getFullName());
			ps.setString(2, ahVo.getEmail());
			ps.setLong(3, ahVo.getPhoneNo());
			ps.setString(4, ahVo.getNomineeName());
			ps.setString(5, ahVo.getDateOfBirth());
			ps.setString(6, ahVo.getPassword());
			ps.setString(7, ahVo.getReEnterPassword());
			ps.setLong(8, ahVo.getAccountNumber());
			ps.setDouble(9, ahVo.getBalance());
			ps.setLong(10, ahVo.getAccountHolderId());
			int rowUpdated = ps.executeUpdate();
			if (rowUpdated == 0) {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ahVo;
	}

}

//	public Long getAccountNumber(Long accountNumber) {
//		try {
//			con = DBConnection.getConnection();
//			PreparedStatement ps = con.prepareStatement("SELECT accountNumber from AccountHolder_Details");
//			ps.setLong(1, accountNumber);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				accountNumber = rs.getLong(1);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return accountNumber;
//	}