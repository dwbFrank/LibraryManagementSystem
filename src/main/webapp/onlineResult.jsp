<%@ page import="com.frank.lms.Book" %>
<%--
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
    Book book = (Book) session.getAttribute("books");
    if (book.getIsbn13().equals("見付けません") && book.getIsbn10().equals("見付けません")) {
        result = false;
    }
    if (result) {
%>
<ul>
    <li>
            <%
              String imageLink = book.getImageLinks();
            %>
        <img src="<%=imageLink%>" alt="BookImage">
    <li>
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
<form action="Add" method="post">
    <input type="hidden" name="isbn" id="isbn" value="<%=book.getIsbn10()%>">
    <button type="submit">データベースに記入します</button>
</form>
</body>
</html>
