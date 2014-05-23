<%-- 
    Document   : index
    Created on : 16 mai 2014, 11:15:20
    Author     : Damien
--%>

<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User) session.getAttribute("user");

    if(user != null && user.getId()>0){
        request.getRequestDispatcher("/WEB-INF/server.jsp").forward(request, response);
    }
%>
<!DOCTYPE html>
<HTML>
<HEAD>
    <TITLE>Activity Monitor</TITLE>
    <meta charset="UTF-8">
</HEAD>
<BODY>
<center><H1>Activity Monitor</H1></center>
<CENTER><BIG>Authentication</BIG></CENTER>
<form name="form" method="post" action="AuthController">
    <CENTER>Login : <input type="text" name="login"></CENTER>
    <CENTER>Password : <input type="password" name="password"></CENTER>
    <CENTER><INPUT type="submit" name="Submit" value="Ok"></CENTER>
</form>
</BODY>
</HTML>
