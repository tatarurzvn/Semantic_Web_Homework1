<%-- 
    Document   : Info
    Created on : Nov 3, 2018, 7:05:38 PM
    Author     : rtataru
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="DAO.HRDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="WEB-INF/nav.jspf"%>
        <h1>Hello, please take a look at our immunization statistics!</h1>
        <ul>
        <%
            List<String> lst = HRDAO.getInstance().getImunsInfo();
            for (String str : lst) { %>
            <li><%=str%></li>
          
        <%    }
        %>
        </ul>
    </body>
</html>
