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
import java.util.List;

@WebServlet(urlPatterns = "/Admin", name = "Admin")
public class Admin extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String edit = request.getParameter("edit");
		int index = Integer.parseInt(request.getParameter("index"));
		HttpSession httpSession = request.getSession();
		Account account = (Account) httpSession.getAttribute("account");
		boolean operationConfirm = false;
		if (account != null && account.isAdmin() && account.isValid()) {

			List<Book> bookList = (List<Book>) httpSession.getAttribute("bookList");
			Book book = bookList.get(index);
			if (edit.equals("add") && book != null) {
				operationConfirm = Book.addBook(book);
			}

			httpSession.setAttribute("operationConfirm", operationConfirm);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("operationResult.jsp");
			requestDispatcher.forward(request, response);
		} else if (account == null || !account.isAdmin() || !account.isValid()) {
			httpSession.setAttribute("url", "queryResult.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");
			requestDispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

	}
}
