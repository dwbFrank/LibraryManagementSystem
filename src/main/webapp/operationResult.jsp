<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2018/03/02
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>記入確認</title>
</head>
<body>
<%
    boolean operationConfirm = Boolean.parseBoolean(session.getAttribute("operationConfirm").toString());
    if (operationConfirm) {
%>
<h1>記入できました</h1>
<%
    }else {
%>
記入失敗しました
<%
}
%>
</body>
</html>