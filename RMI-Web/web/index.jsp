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
    <TITLE>Accueil</TITLE>
    <meta charset="UTF-8">
</HEAD>
<BODY>
<CENTER><BIG>Authentification</BIG></CENTER>
<form name="form" method="post" action="UserController">
    <CENTER>Identifiant : <input type="text" name="login"></CENTER>
    <CENTER>Mot de passe : <input type="password" name="password"></CENTER>
    <CENTER><INPUT type="submit" name="Submit" value="Ok"></CENTER>
</form>
</BODY>
</HTML>
