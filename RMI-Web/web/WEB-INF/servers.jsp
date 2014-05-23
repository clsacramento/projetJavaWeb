<%-- 
    Document   : servers
    Created on : 23 mai 2014, 00:31:06
    Author     : Damien
--%>

<%@page import="controllers.ServerController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Server"%>
<HTML>
    <HEAD>
        <TITLE>Servers administration</TITLE>
        <meta charset="UTF-8">
    </HEAD>
    <BODY>
        <div id="header">
            <jsp:include page="menu.jsp"/>
        </div>
    <CENTER><BIG>Servers administration</BIG></CENTER>
    <center><table BORDER="1">
            <%
                ArrayList<Server> servers = ServerController.getServers();

                for (Server server : servers) {
                    String id = "" + server.getId();
                    String host = server.getHost();

            %>
            <tr> 
                <td> <%= host%>  </td> 
                <td> <form name="form" method="get" action="DelServer">
                        <input type="hidden" name="id" value ="<%=id%>"> 
                        <input type="submit" name="Submit" value="Delete">
                    </form> 
                </td>
            </tr>
            <%}%>
        </table></center>
</BODY>
</HTML>
