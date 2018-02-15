package com.frank.lms;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@WebServlet(urlPatterns = "/Search", name = "Search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String isbn = request.getParameter("isbn");

        GoogleSearch test = null;
        test = new GoogleSearch(GoogleSearch.KeyWord.isbn, isbn);
        JsonItems json = test.query();
        for (JsonItems.ItemsBean bean : json.getItems()
                ) {
            String publishedDate = bean.getVolumeInfo().getPublishedDate();
            if (publishedDate != null) {
                String[] date = publishedDate.split("-");
                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                switch (date.length) {
                    case 1:
                        calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
                        break;
                    case 2:
                        calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
                        calendar.set(Calendar.MONTH, Integer.parseInt(date[1]));
                        break;
                    case 3:
                        calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
                        calendar.set(Calendar.MONTH, Integer.parseInt(date[1]));
                        calendar.set(Calendar.DATE, Integer.parseInt(date[2]));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
