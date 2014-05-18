<%-- 
    Document   : history
    Created on : May 18, 2014, 3:59:57 PM
    Author     : cynthia
--%>

<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="models.Request"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HashMap<Integer,Request> history = (HashMap) session.getAttribute("history");

    if(history == null){
        request.getRequestDispatcher("/WEB-INF/HistoryController").forward(request, response);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
    <div id="header">
        <jsp:include page="menu.jsp"/>
    </div>
        <h2>Request History</h2>
        <%if(history != null || history.size()>0){%>
        <table>
            <thead>
                <th>Date</th>
                <th>Server</th>
                <th>User</th>
                <th>Type</th>
                <th>Details</th>
            </thead>
            <tbody>
                <% for(Entry<Integer,Request> pair : history.entrySet()){%>
                    <%
                        Request req = pair.getValue();
                        int id = pair.getKey();
                        String date = req.getDate().toString();
                        String server = req.getServer().getHost();
                        String user = req.getUser().getLogin();
                        String type = req.getTypeRequest();
                    %>
                    <tr>
                        <td><%=date%></td>
                        <td><%=server%></td>
                        <td><%=user%></td>
                        <td><%=type%></td>
                        <td><a href="RequestDetailsController?id_request=<%=id%>">click</a></td>
                    </tr>
                <%}%>
            </tbody>
        </table>
        <%}%>
    </body>
</html>
