<%--
    Document   : monitor
    Created on : 22 mars 2014, 22:57:23
    Author     : Damien
--%>
<%@page import="osutils.Memory"%>
<%@page import="osutils.CPU"%>
<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="interfaces.IProcess"%>
<%@page import="interfaces.ICPU"%>
<%@page import="interfaces.IPhysicalMemory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monitor</title>
    </head>
    <body>
    <div id="header">
        <jsp:include page="menu.jsp"/>
    </div>
    <center>
        <h1>Server response :</h1>
        <%! Object cpuExist; %>
        <% cpuExist = request.getAttribute("cpuExist"); %>
        <% if (cpuExist != null && cpuExist.toString().equals("true")) { %>
        <%! String cpuTotal;
        %>
        <%
            CPU cp = (CPU) request.getAttribute("icpu");
            cpuTotal = cp.getTotalUsed();
        %>
        <table BORDER="1">
            <tr> 
                <th>CPU usage</th>
            </tr>
            <tr>
                <th><%= cpuTotal %> %</th>
            </tr>
        </table>
        <%}%>
        <%! Object memExist; %>
        <% memExist = request.getAttribute("memExist"); %>
        <% if (memExist != null && memExist.toString().equals("true")) { %>
        <%! String totalMem;
            String usedMem;
            String freeMem;
        %>
        <%
            Memory im = (Memory) request.getAttribute("imem");
            totalMem = im.getTotal();
            usedMem = im.getUsed();
            freeMem = im.getFree();
        %>
        <br>
        <table BORDER="1">
            <tr> 
                <th>Memory information</th>
            </tr>
            <tr>
                <th>Total : <%= totalMem%> Mo</th>
            </tr>
            <tr>
                <th>Used : <%= usedMem%> Mo</th>
            </tr>
            <tr>
                <th>Free : <%= freeMem%> Mo</th>
            </tr>
        </table>
        <%}%>
        <br>
        <%! Object processExist; %>
        <% processExist = request.getAttribute("processExist"); %>
        <% if (processExist != null && processExist.toString().equals("true")) { %>
        <table BORDER="1" width="50%">
            <tr> 
                <th colspan="4">Process list</th>
            </tr>
            <tr> 
                <th>PID</th>
                <th>Name</th>
                <th>Memory</th>
                <th>CPU use</th>
            </tr>   
        <%! String pid;
            String name;
            String mem;
            String proc;
        %>
        <%
            List<osutils.Process> pr = (List<osutils.Process>) request.getAttribute("iprocess");
            for (osutils.Process p:pr) {
                pid = p.getPID();
                name = p.getName();
                mem = p.getUsingMemory();
                proc = p.getCPUTime();
        %>
            
            <tr>
                <td><%= pid%></td>
                <td><%= name%></td>
                <td><%= mem%></td>
                <td><%= proc%></td>
            </tr>
            <%}%>
        </table>
        <%}%>
    </center>
    </body>
</html>
