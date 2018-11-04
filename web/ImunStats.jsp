<%-- 
    Document   : ImunStats
    Created on : Nov 4, 2018, 7:44:25 PM
    Author     : rtataru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="WEB-INF/nav.jspf"%>
        <h1>Please check the immunizations done on the selected date:</h1>
        <ul>
            <c:forEach items="${requestScope.patientData}" var="itemData">
                <li>${itemData}</li>
            </c:forEach>
        </ul>
    </body>
</html>
