package com.nit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nit.helper.InvalidAccountHolderException;
import com.nit.model.AccountHolderVO;
import com.nit.service.IAccountHolderService;
import com.nit.service.impl.AccountHolderServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/accountHolderServlet")
public class Register_UpdateServlet extends HttpServlet {
	IAccountHolderService service;

	@Override
	public void init() throws ServletException {
		service = new AccountHolderServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("Register_UpdateServlet.doPost()");
			String action = request.getParameter("action");
			HttpSession session = request.getSession();
			Long ahId = (Long) session.getAttribute("ahId");
			System.out.println("AccountHolderId: " + ahId);
			if ("update".equals(action)) {
				AccountHolderVO ahVo = new AccountHolderVO();
				ahVo.setFullName(request.getParameter("fName"));
				ahVo.setEmail(request.getParameter("email"));
				ahVo.setPhoneNo(Long.parseLong(request.getParameter("phoneNo")));
				ahVo.setNomineeName(request.getParameter("nName"));
				ahVo.setDateOfBirth(request.getParameter("dob"));
				ahVo.setPassword(request.getParameter("password"));
				ahVo.setReEnterPassword(request.getParameter("reEnterPassword"));
				ahVo.setAccountHolderId(Long.parseLong(request.getParameter("accountHolderId")));

				int status = service.updateAccountHolder(ahVo);
				if (status > 0) {
					request.setAttribute("successMessage", "AccountHolder updated Successfully");
					request.setAttribute("accountHolder", ahVo);
					request.getRequestDispatcher("dashboard.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Sorry, unable to update record");
					request.getRequestDispatcher("dashboard.jsp").forward(request, response);
				}
			} else if ("register".equals(action)) {
				String fullName = request.getParameter("fName");
				String email = request.getParameter("email");
				Long phoneNo = Long.parseLong(request.getParameter("phoneNo"));
				if (phoneNo <= 0) {
					throw new InvalidAccountHolderException("Invalid phone number.");
				}
				String nomineeName = request.getParameter("nName");
				String dateOfBirth = request.getParameter("dob");
				String password = request.getParameter("password");
				String reEnterPassword = request.getParameter("reEnterPassword");
				Boolean isAvailable = service.checkAccountHolderByEmail(email);
				if (!isAvailable) {
					AccountHolderVO ahVo = new AccountHolderVO();
					ahVo.setFullName(fullName);
					ahVo.setEmail(email);
					ahVo.setPhoneNo(phoneNo);
					ahVo.setNomineeName(nomineeName);
					ahVo.setDateOfBirth(dateOfBirth);
					ahVo.setPassword(password);
					ahVo.setReEnterPassword(reEnterPassword);
					service.registerAccountHolder(ahVo);
					request.setAttribute("successMessage", "Your Account is created successfully");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage", "An Account with this email already exists");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage", "Invalid action parameter");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("errorMessage", "something went wrong");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
