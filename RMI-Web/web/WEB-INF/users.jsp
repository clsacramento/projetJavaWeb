<%-- 
    Document   : users
    Created on : 23 mai 2014, 08:49:12
    Author     : Damien
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.User"%>
<%@page import="models.User"%>
<%@page import="controllers.UsersController"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <div id="header">
            <jsp:include page="menu.jsp"/>
        </div>
    <CENTER><BIG>Users administration</BIG></CENTER>
    <center>
        
        <form name="form" method="get" action="AddUser">
                        Login : <input type="text" name="login">
                        Password : <input type="text" name="password">
                        <input type="submit" name="Submit" value="Add">
        </form> 
        
        <table BORDER="1">
            <%
                ArrayList<User> users = UsersController.getUsers();

                for (User user : users) {
                    String id = "" + user.getId();
                    String login = user.getLogin();

            %>
            <tr> 
                <td> <%= login%>  </td> 
                <td> <form name="form" method="get" action="DelUser">
                        <input type="hidden" name="id" value ="<%=id%>"> 
                        <input type="submit" name="Submit" value="Delete">
                    </form> 
                </td>
            </tr>
            <%}%>
        </table></center>
</body>
</html>