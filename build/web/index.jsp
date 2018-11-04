<%-- 
    Document   : index
    Created on : Oct 28, 2018, 12:37:48 PM
    Author     : rtataru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .column {
                float: left;
                width: 50%;
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }
            .box {
                width: 300px;
                border: 25px solid black;
                padding: 25px;
                margin: 25px;
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="WEB-INF/nav.jspf"%>
        <h1>Welcome to the EHR site</h1>
        <div class="box">
            <form method="post" action="InfoController">
                <input type="submit" value="Last year treatment plans!">  
            </form>
        </div>
        <div class="box">
            <form method="post" action="AllergyStatsController">
                <input type="submit" value="Allegies stats!">  
            </form>
        </div>
        <div class="box">
            <p>Find immunzations done by all patients in a certain date</p>
            <form method="post" action="ImunStatsController">
                <label for="date">Date</label>
                <input type="text" name="date" id="date" required>
                <input type="submit" value="Find!">  
            </form>
        </div>

    </body>
</html>
