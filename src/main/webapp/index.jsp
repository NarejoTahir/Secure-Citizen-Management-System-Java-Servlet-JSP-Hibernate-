<%
String path= request.getContextPath()+"/pages/";
String path1=request.getContextPath()+"/index.jsp";

	String error=request.getParameter("errorMsg");
	
	request.setAttribute("errorMsg",error);
%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>


<c:choose>
	<c:when test="${not empty user }">
		<c:redirect url="/pages/home.jsp" />
		</c:when>
		<c:otherwise>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<title>Nadra Management System</title>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">

            <div class="card shadow">
                <div class="card-header bg-primary text-white text-center">
                    <h4 class="mb-0">Login</h4>
                </div>

                <div class="card-body">
                    <c:if test="${not empty errorMsg }">
							<p class="text-center text-danger ps-3">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>

                    <form action="mainController?action=login" method="post">

                        <div class="mb-3">
                            <label class="form-label fw-semibold">CNIC</label>
                            <input type="text" name="cnic"
                                   class="form-control"
                                   placeholder="Enter CNIC"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Password</label>
                            <input type="password" name="password"
                                   class="form-control"
                                   placeholder="Enter Password"
                                   required>
                        </div>

                        <!-- Login Button -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary fw-semibold">
                                Login
                            </button>
                        </div>

                    </form>
					<br> do not have any account <a class="text-decoration-none"
							href="<%= path%>/register.jsp">Create One</a>
                </div>

            </div>

        </div>
    </div>
</div>

			
		</c:otherwise>
</c:choose>


