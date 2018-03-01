<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2018/02/27
  Time: 4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>図書管理</title>
</head>
<body>
<form action="Search" method="post">
    <input type="radio" name="localSearchTerm" id="isbn" value="isbn" required>
    <label for="isbn">ISBN</label>
    <br>
    <input type="radio" name="localSearchTerm" id="title" value="title">
    <label for="title">書名</label>
    <br>
    <input type="radio" name="localSearchTerm" id="author" value="author">
    <label for="author">著者</label>
    <input type="hidden" name="adminSearch" value="true">
    <br>
    <br>
    <label for="searchValue">検索語</label>
    <br>
    <textarea cols="50" rows="5" wrap="soft" name="localSearchValue" id="searchValue"
              required>検索する内容を入力してください</textarea>
    <button type="submit" id="submit">検索</button>
</form>
</body>
</html>
