<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2018/03/04
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>エラー処理</title>
</head>
<body>
<%
    String errorMessage = (String) session.getAttribute("errorMessage");
%>
<h1>
    <%=errorMessage%>
</h1>
</body>
</html>
