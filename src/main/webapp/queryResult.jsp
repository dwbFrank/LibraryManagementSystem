<%@ page import="com.frank.lms.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2018/02/26
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>検索一覧</title>
</head>
<body>
<%
    boolean result = true;
    List<Book> books = (List<Book>) session.getAttribute("books");
    String localSearchStr = (String) session.getAttribute("localSearchStr");
    if (books.size() < 1) {
        result = false;
    }
    if (result) {
        for (Book book : books
                ) {
%>
<ul>
    <li>
            <%
            String imageLink = book.getImageLinks();
        %>
        <img src="<%=imageLink%>" alt="BookImage">
    <li>
        書名:
        <%
            String title = book.getTitle();
        %>
        <%=title%>
    </li>
    <li>
        副題:
        <%
            String subtitle = book.getSubtitle();
        %>
        <%=subtitle%>
    </li>
    <li>
        内容紹介:
        <%
            String description = book.getDescription();
        %>
        <%=description%>
    </li>
    <li>
        ページ:
        <%
            int pageCount = book.getPageCount();
        %>
        <%=pageCount%>
    </li>
    <li>
        著者:
        <%
            for (String author : book.getAuthors()
                    ) {
        %>
        <%=author%>
        <%
            }
        %>
    </li>
    <li>
        出版社:
        <%
            String publisher = book.getPublisher();
        %>
        <%=publisher%>
    </li>
    <li>
        発売日:
            <%
            String publishedDate = book.getPublishedDate();
        %>
            <%=publishedDate%>
    <li>
        ISBN10:
        <%
            String isbn10 = book.getIsbn10();
        %>
        <%=isbn10%>
    </li>
    <li>
        ISBN13:
        <%
            String isbn13 = book.getIsbn13();
        %>
        <%=isbn13%>
    </li>
</ul>
<%
    }
%>
<%
} else {
%>
<%=localSearchStr%>
<p>データベースに登録していません</p>
<a href="login.html">管理者ページ</a>
<%
    }
%>
</body>
</html>
