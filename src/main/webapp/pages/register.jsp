<%@page import="com.entity.Countries"%>
<%@page import="com.dao.addCountry"%>
<%@page import="com.db.HibernateDb"%>
<%@page import="com.entity.Provinces"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.addProvince"%>
<%

String path= request.getContextPath()+"/pages/";
String mainPath=request.getContextPath();
String path1=request.getContextPath()+"/index.jsp";


addCountry country=new addCountry(HibernateDb.getFactory());
List<Countries> c=country.getCountries();


//Users u=us.getAUser();

//System.out.println("System"+u);

//Users f=us.getAUser();
//System.out.println("L2" +f);
 
addProvince ap=new addProvince(HibernateDb.getFactory());
List<Provinces> p= ap.getProvince();

request.setAttribute("countries", c);
request.setAttribute("provinces", p);
%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
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
        <div class="col-md-10">

            <div class="card shadow">
                <div class="card-header bg-primary text-white text-center fw-bold">
                    Add User
                </div>

                <div class="card-body">

                    <form action="<%=mainPath %>/mainController?action=addUser" method="post">
                        <div class="row g-3">

                            <!-- Left Column -->
                            <div class="col-md-6">
                                <!-- Full Name -->
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Full Name</label>
                                    <input type="text" name="fullName" class="form-control" required>
                                </div>

                                <!-- Province Dropdown -->
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Select Province</label>
                                    <select name="provinceId" id="provinceSelect" class="form-select" required onchange="loadDistricts()">
                                        <option value="" disabled selected>Select province</option>
                                        <c:forEach var="item" items="${provinces}">
                                            <option value="${item.provinceId}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- CNIC -->
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">CNIC Number</label>
                                    <input type="text" name="cnic" class="form-control" required>
                                </div>

                                <!-- Tehsil -->
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Tehsil</label>
                                    <input type="text" name="tehsil" class="form-control" required>
                                </div>
                            </div>
                            
                            

                            <!-- Right Column -->
                            <div class="col-md-6">
                                <!-- DOB -->
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Date of Birth</label>
                                    <input type="date" name="dob" class="form-control" required>
                                </div>

                                <!-- District Dropdown -->
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Select District</label>
                                    <select name="districtId" id="districtSelect" class="form-select" required>
                                        <option disabled selected>Select district</option>
                                    </select>
                                </div>

                                <!-- City -->
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">City</label>
                                    <input type="text" name="city" class="form-control" required>
                                </div>
                                
                                
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Password</label>
                                    <input type="password" name="pass" class="form-control" required>
                                </div>

                               
                            </div>

                        </div>

                        <div class="text-center mt-3">
                            <button type="submit" class="btn btn-primary px-4 fw-semibold">
                                Add User
                            </button>
                        </div>

                    </form>

                </div>
            </div>

        </div>
    </div>

</div>

<%@ include file="footer.jsp" %>
