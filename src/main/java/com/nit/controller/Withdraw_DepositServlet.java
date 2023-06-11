package com.nit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nit.helper.InsufficientBalanceException;
import com.nit.helper.InvalidAmountException;
import com.nit.service.IAccountHolderService;
import com.nit.service.impl.AccountHolderServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/withdraw_depositServlet")
public class Withdraw_DepositServlet extends HttpServlet {
	IAccountHolderService service;

	@Override
	public void init() throws ServletException {
		service = new AccountHolderServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		try {
			if ("/withdraw_depositServlet".equals(action)) {
				String actionType = req.getParameter("action");
				if ("deposit".equals(actionType)) {
					depositMoney(req, resp);
				} else if ("withdraw".equals(actionType)) {
					withdrawMoney(req, resp);
				} else if ("getBalance".equals(actionType)) {
					getBalance(req, resp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InsufficientBalanceException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void getBalance(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(false);
		Long accountNumber = (Long) session.getAttribute("accountNumber");
		int balance = service.getBalanceByAccountNumber(accountNumber);
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(String.valueOf(balance));
	}

	private void depositMoney(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {
		HttpSession session = req.getSession(false);

		int depositAmount = Integer.parseInt(req.getParameter("deposit"));
		if (depositAmount <= 0) {
			try {
				throw new InvalidAmountException("Invalid deposit amount.");
			} catch (InvalidAmountException e) {
				System.out.println("Error: " + e.getMessage());
				return;
			}
		}
		double balance = (Double) session.getAttribute("balance");
		balance += depositAmount;
		session.setAttribute("balance", balance);
		resp.getWriter().write(String.valueOf(balance));
	}

	private void withdrawMoney(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException, InsufficientBalanceException {
		HttpSession session = req.getSession(false);

		int withdrawAmount = Integer.parseInt(req.getParameter("withdraw"));
		double balance = (Double) session.getAttribute("balance");
		if (withdrawAmount <= balance) {
			balance -= withdrawAmount;
			session.setAttribute("balance", balance);
			resp.getWriter().write(String.valueOf(balance));
		} else {
			throw new InsufficientBalanceException("Withdraw amount exceeds balance.");
		}
	}

}