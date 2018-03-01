package com.frank.lms;

import com.frank.lms.localdb.LocalDB;
import com.frank.lms.online.OnlineSearch;
import com.frank.lms.online.SearchTerm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

@WebServlet(urlPatterns = "/Add", name = "Add")
public class Add extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String isbn10 = request.getParameter("isbn");
		SearchTerm searchTerm = Enum.valueOf(SearchTerm.class, "isbn");
		OnlineSearch onlineSearch = new OnlineSearch(searchTerm, isbn10);
		try {
			Book book = onlineSearch.search();
			LocalDB localDB = new LocalDB();
			if (localDB.addBook(book)) {
				PrintWriter printWriter = response.getWriter();
				printWriter.print("記入しました");
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

	}
}
