<%-- 
    Document   : menu
    Created on : May 18, 2014, 4:49:08 PM
    Author     : cynthia
--%>

<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User user = (User) session.getAttribute("user");
    String login = user.getLogin();
%>
<table>
    <tr>
        <td>Logged in as: <%=login%></td>
        <td><a href="Server">Monitor Server</a></td>
        <td><a href="HistoryController">History</a></td>
        <td><a href="#">Logout</a></td>
    </tr>
</table>
<H1>Activity Monitor</H1>
