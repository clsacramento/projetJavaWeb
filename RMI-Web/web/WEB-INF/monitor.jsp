<%--
    Document   : monitor
    Created on : 22 mars 2014, 22:57:23
    Author     : Damien
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monitor</title>
    </head>
    <body>
    <center>
        <h1>Monitor</h1>
        <%! String memExist; %>
        <% memExist = request.getAttribute("memExist").toString(); %>
        <% if (memExist.equals("true")) { %>
        <%! String totalMem;
            String usedMem;
            String freeMem;
        %>
        <% totalMem = request.getAttribute("totalMem").toString();
            usedMem = request.getAttribute("usedMem").toString();
            freeMem = request.getAttribute("freeMem").toString();
        %>
        <table BORDER="1">
            <tr> 
                <th>Information mémoire</th>
            </tr>
            <tr>
                <th>Totale : <%= totalMem%> Mo</th>
            </tr>
            <tr>
                <th>Utilisée : <%= usedMem%> Mo</th>
            </tr>
            <tr>
                <th>Libre : <%= freeMem%> Mo</th>
            </tr>
        </table>
        <%}%>
    </center>
    </body>
</html>
