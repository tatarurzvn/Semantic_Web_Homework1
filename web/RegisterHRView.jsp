<%-- 
    Document   : RegisterHRView
    Created on : Oct 28, 2018, 12:38:41 PM
    Author     : rtataru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
            public void jspInit() {
                System.out.print("RegisterHR started");
            }
        %>
        <%@include file="WEB-INF/nav.jspf"%>
        <h1>HR registration form</h1>
        <form method="post" action="RegistrationController">
            <div class="form-element">
                <label for="name">Patient's Name</label>
                <input type="text" name="name" id="name" required>
            </div>
            <div class="form-element">
                <label for="id">Patient ID</label>
                <input type="text" name="id" id="id" required>
            </div>
            <div class="form-element">
                <label for="diagnostic">Diagnostic</label>
                <input type="text" name="diagnostic" id="diagnostic" required>
            </div>
            <div class="form-element">
                <label for="medication">Medication</label>
                <input type="text" name="medication" id="medication" required>
            </div>
            <div class="form-element">
                <label for="treatment_plan">Treatment Plan</label>
                <input type="text" name="treatment_plan" id="treatment_plan" required>
            </div>
            <div class="form-element">
                <input type="submit" value="Submit">
                <input type="reset" value="Reset">
            </div>  
        </form>
    </body>
</html>