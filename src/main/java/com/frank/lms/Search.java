package com.frank.lms;

import com.frank.lms.google.GoogleSearch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;

@WebServlet(urlPatterns = "/Search", name = "Search")
public class Search extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String isbn = request.getParameter("isbn");
        GoogleSearch test = new GoogleSearch(GoogleSearch.KeyWord.isbn, isbn);
        try {
            Book book = test.search();
            response.setContentType("text/html;charset=UTF-8");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("book", book);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("GoogleSearch.jsp");
            requestDispatcher.forward(request, response);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
