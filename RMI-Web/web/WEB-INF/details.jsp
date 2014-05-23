<%-- 
    Document   : details
    Created on : May 18, 2014, 5:53:00 PM
    Author     : cynthia,Damien
--%>

<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="models.Request"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Request detailedRequest = (Request) session.getAttribute("request");

    if(detailedRequest == null){
        request.getRequestDispatcher("HistoryController").forward(request, response);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Details</title>
    </head>
    <body>
        <div id="header">
            <jsp:include page="menu.jsp"/>
        </div>
        <h1>Request Details</h1>
        <h2>Common Fields :</h2>
        <table BORDER="1" width="50%">
            <thead>
                <%for(String key : detailedRequest.getFields().keySet()){%>
                    <th>
                        <%=key%>
                    </th>
                <%}%>
            </thead>
            <tbody>
                <tr>
                    <%for(String value : detailedRequest.getFields().values()){%>
                        <td><%=value%></td>
                    <%}%>
                </tr>
            </tbody>
        </table>
        <h2>Specific request details:</h2>
        <table BORDER="1" width="50%">
            <%for(Entry<String,String> pair : detailedRequest.getDetails().entrySet()){%>
            <% 
                String key = pair.getKey();
                String value = pair.getValue();
            %>
            <tr>
                <td><strong><%=key%></strong></td>
                <td><%=value%></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
