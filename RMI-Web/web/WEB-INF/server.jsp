<%-- 
    Document   : index
    Created on : Apr 18, 2014, 8:50:31 PM
    Author     : cynthia,damien
--%>

<%@page import="controllers.ServerController"%>
<%@page import="models.Server"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
    <TITLE>Monitor Server</TITLE>
    <meta charset="UTF-8">
</HEAD>
<BODY>
<div id="header">
    <jsp:include page="menu.jsp"/>
</div>
<CENTER><BIG>Server information</BIG></CENTER>
<form name="form" method="get" action="Monitor">
    <%
        ArrayList<Server> servers = ServerController.getServers();
    %>
    <CENTER>Server address : <input type="text" name="url" list="servers">
        <DATALIST id="servers">
            <%
                for(Server server : servers){
                    String host = server.getHost();
                
            %>
                <option value="<%=host%>"></option>
            <%}%>
        </DATALIST>
    </CENTER>
    <CENTER>CPU usage : <INPUT type="checkbox" name="cpu" value="cpu"></CENTER>
    <CENTER>Memory information : <INPUT type="checkbox" name="mem" value="mem"></CENTER>
    <CENTER>Process list : <INPUT type="checkbox" name="process" value="process"></CENTER>
    <CENTER><INPUT type="submit" name="Submit" value="Ok"></CENTER>
</form>
</BODY>
</HTML>
