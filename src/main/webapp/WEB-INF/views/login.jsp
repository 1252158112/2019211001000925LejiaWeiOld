<%--
  Created by IntelliJ IDEA.
  User: 乐嘉伟
  Date: 2021/3/31
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="header.jsp"%>
<body>
<h2>Login</h2>
<%
    if((request.getAttribute("message")!=null)) {
        out.print("<h3>"+request.getAttribute("message")+"<h3>");
    }
%>
<%
    Cookie[] allCookies=request.getCookies();
    String username = "",password="",rememerMeVal="";
    if(allCookies != null) {
        for(Cookie c:allCookies) {
            if(c.getName().equals("cUsername")) {
                username=c.getValue();
            }
            if(c.getName().equals("cPassword")) {
                password=c.getValue();
            }if(c.getName().equals("rememberMeVal")) {
                rememerMeVal=c.getValue();
            }

        }

    }
%>
<form action="login" method="post">
    Username:<input type="text" id="username" name="username" value="<%=username%>"><br>
    Password:<input type="password" id="password" name="password" value="<%=password%>"><br>
    <input type="checkbox" name="rememberMe" value="1" <%= rememerMeVal.equals("1")?"checked":""%>/>Remember Me<br/>
    <input type="submit" value="login">
</form>
</body>
<%@ include file="footer.jsp"%>
</html>