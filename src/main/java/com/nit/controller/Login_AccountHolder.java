package com.nit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nit.helper.AccountHolderNotFoundException;
import com.nit.model.AccountHolderBO;
import com.nit.model.AccountHolderVO;
import com.nit.service.IAccountHolderService;
import com.nit.service.impl.AccountHolderServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/loginservlet")
public class Login_AccountHolder extends HttpServlet {
	IAccountHolderService service;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new AccountHolderServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Login_AccountHolder.doPost()");
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			System.out.println("user is entering email & password: " + email + " " + password);
			AccountHolderVO ahVo = new AccountHolderVO();
			ahVo.setEmail(email);
			ahVo.setPassword(password);

			AccountHolderBO dbAhBo = service.login(ahVo);
			if (dbAhBo != null) {
				HttpSession session = request.getSession();
				session.setAttribute("accountHolderId", dbAhBo.getAccountHolderId());
				session.setAttribute("accountNumber", dbAhBo.getAccountNumber());
				session.setAttribute("balance", dbAhBo.getBalance());

				request.setAttribute("successMessage", "<h4>Welcome To Axis Bank</h4>");
				request.setAttribute("accountHolderId", dbAhBo.getAccountHolderId());
				request.setAttribute("accountHolderName", dbAhBo.getFullName());
				request.setAttribute("accountHolderEmail", dbAhBo.getEmail());
				request.setAttribute("accountHolderPhoneNo", dbAhBo.getPhoneNo());
				request.setAttribute("NomineeName", dbAhBo.getNomineeName());
				request.setAttribute("accountNumber", dbAhBo.getAccountNumber());
				request.setAttribute("accountHolderBalance", dbAhBo.getBalance());
				System.out.println("ACCOUNT_NUMBER>>> " + dbAhBo.getAccountNumber());
				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Invalid email or password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (AccountHolderNotFoundException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
