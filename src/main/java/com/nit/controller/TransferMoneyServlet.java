package com.nit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nit.model.AccountHolderVO;
import com.nit.service.IAccountHolderService;
import com.nit.service.impl.AccountHolderServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/transferMoneyServlet")
public class TransferMoneyServlet extends HttpServlet {
	private IAccountHolderService service;

	@Override
	public void init() throws ServletException {
		service = new AccountHolderServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TransferMoneyServlet.doPost()");

		Long receiverAccNo = null;
		Double amount = null;
		String errorMessage = null;

		if (request.getParameter("receiverAccNo") != null && request.getParameter("amount") != null) {
			try {
				receiverAccNo = Long.parseLong(request.getParameter("receiverAccNo"));
				amount = Double.parseDouble(request.getParameter("amount"));
			} catch (NumberFormatException e) {
				errorMessage = "Invalid input format!";
			}
		} else {
			errorMessage = "Invalid input parameters!";
		}

		if (errorMessage != null) {
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("transferMoney.jsp").forward(request, response);
			return;
		}

		System.out.println("Receiver Account Number: " + receiverAccNo);
		System.out.println("Amount to Transfer: " + amount);

		HttpSession session = request.getSession();
		Long senderAccNo = (Long) session.getAttribute("accountNumber");
		System.out.println("Sender Account Number: " + senderAccNo);

		AccountHolderVO sender = service.getAccountByAccountNo(senderAccNo); // Get sender and receiver accounts
		AccountHolderVO receiver = service.getAccountByAccountNo(receiverAccNo);
		try {
			if (service.transferMoney(sender.getAccountNumber(), receiver.getAccountNumber(), amount)) {
				request.setAttribute("successMessage", "Money transfer successful!");
			} else {
				request.setAttribute("errorMessage", "Money transfer failed!");
			}
			request.getRequestDispatcher("transferMoney.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occurred while processing your request");
			request.getRequestDispatcher("transferMoney.jsp").forward(request, response);
		}
	}
}
