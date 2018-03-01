package com.frank.lms;

import com.frank.lms.account.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login", name = "Login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Account account = new Account(email, password);
		if (account.isValid()) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("account", account.getValidationMessage());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
			requestDispatcher.forward(request, response);
		} else {
			PrintWriter printWriter = response.getWriter();
			printWriter.print(account.getValidationMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

	}
}
