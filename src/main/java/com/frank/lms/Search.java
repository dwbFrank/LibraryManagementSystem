package com.frank.lms;

import com.frank.lms.localdb.ColumnLabel;
import com.frank.lms.localdb.LocalDB;
import com.frank.lms.online.OnlineSearch;
import com.frank.lms.online.SearchTerm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@WebServlet(urlPatterns = "/Search", name = "Search")
public class Search extends HttpServlet {
	private static final String ENCODE = "UTF-8";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.setCharacterEncoding(ENCODE);
		response.setCharacterEncoding(ENCODE);
		String localSearchTerm = request.getParameter("localSearchTerm");
		String localSearchValue = request.getParameter("localSearchValue");
		boolean onlineSearch = Boolean.parseBoolean(request.getParameter("adminSearch"));
		if (onlineSearch) {
			SearchTerm searchTerm = Enum.valueOf(SearchTerm.class, localSearchTerm);
			OnlineSearch google = new OnlineSearch(searchTerm, localSearchValue);
			Book book = new Book();
			String localSearchStr = localSearchTerm + ": " + localSearchValue;
			try {
				book = google.search();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("books", book);
			httpSession.setAttribute("localSearch", localSearchStr);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("onlineResult.jsp");
			requestDispatcher.forward(request, response);

		} else {
			if (localSearchTerm.equals("isbn")) {
				if (localSearchValue.length() == 10) {
					localSearchTerm = "isbn10";
				} else if (localSearchValue.length() == 13) {
					localSearchTerm = "isbn13";
				}
			}
			ColumnLabel columnLabel = Enum.valueOf(ColumnLabel.class, localSearchTerm);
			String localSearchStr = localSearchTerm + ": " + localSearchValue;
			//  local database query
			LocalDB localDB = new LocalDB();
			List<Book> books = localDB.queryBook(columnLabel, localSearchValue);
			//  redirect to jsp
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("books", books);
			httpSession.setAttribute("localSearchStr", localSearchStr);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("queryResult.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

	}
}
