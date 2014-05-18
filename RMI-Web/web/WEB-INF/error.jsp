<%-- 
    Document   : error
    Created on : Apr 18, 2014, 10:14:59 PM
    Author     : cynthia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exception occurred</title>
    </head>
    <body>
        <div id="header">
            <jsp:include page="menu.jsp"/>
        </div>
        <h1>Error</h1>
        <% 
            Exception ex = (Exception)request.getAttribute("error");
            if(ex == null)
            {
                ex = new Exception("Unknown error. Sorry, this shouldn't be happening.");
            }
        %>
        <h2>Type : <%=ex.getClass().getName()%></h2>
        <h2>Description : </h2>
        <p>
            <%=ex.getMessage()%>
        </p>
    </body>
</html>
