<%-- 
    Document   : PatientView
    Created on : Nov 2, 2018, 3:46:04 PM
    Author     : rtataru
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="WEB-INF/nav.jspf"%>
        <h1>Welcome to patient View!</h1>
        <div class="row">
            <div class="col">
                <ul>
                    <c:forEach items="${requestScope.patientData}" var="itemData">
                        <li>${itemData}</li>
                        </c:forEach>
                </ul>
            </div>
            <div class="col">
                <div class="box">
                    <h3>Add Allergy</h3>
                    <form method="post" action="AddThingsController">
                        <div class="form-element">
                            <label for="allergy">Allergy type</label>
                            <input type="text" name="allergy" id="allergy" required>
                            <input type="text" name="id" id="id" value="${requestScope.id}" readonly>
                        </div>
                        <input type="submit" value="Submit">  
                    </form>
                </div>
                <div class="box">
                    <h3>Remove Allergy</h3>
                    <form method="post" action="AddThingsController">
                        <div class="form-element">
                            <label for="delAllergy">Allergy type</label>
                            <input type="text" name="delAllergy" id="delAllergy" required>
                            <input type="text" name="id" id="id" value="${requestScope.id}" readonly>
                        </div>
                        <input type="submit" value="Submit">  
                    </form>
                </div>
                <div class="box">
                    <h3>Add medical encounter</h3>
                    <form method="post" action="AddThingsController">
                        <div class="form-element">
                            <label for="diagnostic">Diagnostic</label>
                            <input type="text" name="diagnostic" id="diagnostic" required>
                        </div>
                        <div class="form-element">
                            <label for="medication">Medication</label>
                            <input type="text" name="medication" id="medication" required>
                        </div>
                        <div class="form-element">
                            <label for="treatmentPlan">Treatment Plan</label>
                            <input type="text" name="treatmentPlan" id="treatmentPlan" required>
                            
                        </div>
                        <div class="form-element">
                            <label for="date">Date</label>
                            <input type="text" name="date" id="date" required>
                            <input type="text" name="id" id="id" value="${requestScope.id}" readonly>
                        </div>
                        <input type="submit" value="Submit">  
                    </form>
                </div>
                <div class="box">
                    <h3>Add Immunization</h3>
                    <form method="post" action="AddThingsController">
                        <div class="form-element">
                            <label for="imuDate">Immunization Date</label>
                            <input type="text" name="imuDate" id="imuDate" required>
                        </div>
                        <div class="form-element">
                            <label for="imuName">Immunization Name</label>
                            <input type="text" name="imuName" id="imuName" required>
                            <input type="text" name="id" id="id" value="${requestScope.id}" readonly>
                        </div>
                        <input type="submit" value="Submit">  
                    </form>
                </div>
                <div class="box">
                    <h3>Change Immunization Date</h3>
                    <form method="post" action="AddThingsController">
                        <div class="form-element">
                            <label for="chDate">Immunization Date</label>
                            <input type="text" name="chDate" id="chDate" required>
                        </div>
                        <div class="form-element">
                            <label for="imuName">Immunization Name</label>
                            <input type="text" name="imuName" id="imuName" required>
                            <input type="text" name="id" id="id" value="${requestScope.id}" readonly>
                        </div>
                        <input type="submit" value="Submit">  
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
