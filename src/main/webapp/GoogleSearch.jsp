<%@ page import="com.frank.lms.Book" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2018/02/18
  Time: 2:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    Book book = (Book) request.getSession().getAttribute("book");
    String title = book.getTitle();
    String subtitle = book.getSubtitle();
    String publishedDate = book.getPublishedDate();
    String description = book.getDescription();
    int pageCount = book.getPageCount();
    String imageLink = book.getImageLinks();
    List<String> authors = book.getAuthors();
    String isbn10 = book.getIsbn10();
    String isbn13 = book.getIsbn13();
    String url = book.getUrl();
%>
<ul>
    <li>
        title: <%=title%>
    </li>
    <li>
        subtitle: <%=subtitle%>
    </li>
    <li>
        publishedDate: <%=publishedDate%>
    </li>
    <li>
        descrption: <%=description%>
    </li>
    <li>
        pageCount: <%=pageCount%>
    </li>
    <li>
        <img src="<%=imageLink%>" alt="Book image">
    </li>
    <li>
        author: <% for (String author : authors
            ) {%>
        <%=author%> <br>
        <%} %>
    </li>
    <li>
        ISBN10: <%=isbn10%>
    </li>
    <li>
        ISBN13: <%=isbn13%>
    </li>
</ul>
</body>
</html>