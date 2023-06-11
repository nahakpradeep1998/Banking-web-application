package com.nit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nit.helper.AccountHolderNotFoundException;
import com.nit.model.AccountHolderVO;
import com.nit.service.IAccountHolderService;
import com.nit.service.impl.AccountHolderServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/editservlet")
public class Edit_AccountHolder extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "An error occurred while processing your request");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Long accountHolderIdParam = Long.parseLong(request.getParameter("accountHolderId"));
			IAccountHolderService service = new AccountHolderServiceImpl();
			AccountHolderVO ahVo = service.getAccountHolderById(accountHolderIdParam);
			if (ahVo == null) {
				throw new AccountHolderNotFoundException();
			}
			request.setAttribute("accountHolder", ahVo);
			request.getRequestDispatcher("registerAH.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Error: Invalid accountHolderId parameter");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (AccountHolderNotFoundException e) {
			request.setAttribute("errorMessage", "Account Holder not found.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
