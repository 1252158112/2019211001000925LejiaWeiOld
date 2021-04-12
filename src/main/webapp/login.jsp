<%--
  Created by IntelliJ IDEA.
  User: 乐嘉伟
  Date: 2021/3/31
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="header.jsp"%>>
<body>
<h2>Login</h2>
<%
    if(!(request.getAttribute("message")==null)) {
        out.print("<h3>"+request.getAttribute("message")+"<h3>");
    }
%>
<form action="login" method="post">
    Username:<input type="text" id="username" name="username"><br>
    Password:<input type="password" id="psw" name="password"><br>
    <input type="submit" value="login">
</form>
</body>
<%@ include file="footer.jsp"%>
</html>