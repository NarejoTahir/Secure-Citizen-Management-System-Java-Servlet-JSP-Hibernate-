<%@page import="com.dao.UsersAudDao"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%
	String mainPath=request.getContextPath();
	request.setAttribute("basePath",mainPath);
%>
<c:choose>
	<c:when test="${not empty user }">
	
<%@page import="com.entity.Users"%>
<%@page import="com.dao.addUser"%>
<%@page import="com.entity.Provinces"%>
<%@page import="com.dao.addProvince"%>
<%@page import="com.entity.Countries"%>
<%@page import="java.util.List"%>
<%@page import="com.db.HibernateDb"%>
<%@page import="com.dao.addCountry"%>
<%
	String path= request.getContextPath()+"/pages/";
	String path1=request.getContextPath()+"/index.jsp";
	
	
	
	addCountry country=new addCountry(HibernateDb.getFactory());
	List<Countries> c=country.getCountries();
	
	addUser us=new addUser(HibernateDb.getFactory());
	List<Users> u=us.getAllUser();
	

	
	UsersAudDao auditDao=new UsersAudDao(HibernateDb.getFactory());
	List<Object[]> rows = auditDao.getAddedUsers();

	request.setAttribute("rows", rows);
	
	
	
	//Users u=us.getAUser();
	
	//System.out.println("System"+u);
	
	//Users f=us.getAUser();
	//System.out.println("L2" +f);
	 
	addProvince ap=new addProvince(HibernateDb.getFactory());
	List<Provinces> p= ap.getProvince();
	
	request.setAttribute("countries", c);
	request.setAttribute("provinces", p);
	request.setAttribute("users", u);
	request.setAttribute("path",path);
	
	 
	%>


	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<title>Nadra Management System</title>
</head>
<body>


<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="<%= path%>/search.jsp">Nadra System</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="<%= path%>/home.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%= path+"addCountry.jsp"%>">Add Country</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%= path+"addProvince.jsp"%>" aria-disabled="true">Add Province</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="<%= path+"addDistrict.jsp"%>" aria-disabled="true">Add District</a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="<%= path+"addUser.jsp"%>" aria-disabled="true">Add Bio-Data</a>
        </li>
        
         <li class="nav-item">
          <a class="nav-link" href="<%= path+"User-auditing.jsp"%>" aria-disabled="true">Audit User</a>
        </li>
        
        
        <li class="nav-item">
        
         <div class="quiz-footer">
            <div class="timer" id="sessionTimerr"></div>
        </div>
        
        </li>
        
      </ul>
           <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
       			 <li class="nav-item dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							id="dropdownMenuButton1" data-bs-toggle="dropdown"
							aria-expanded="false" > ${ user.name } </button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
							<li><a class="dropdown-item" href="<%=mainPath %>/mainController?action=logout">Logout</a></li>
							
							
						</ul>
						
			</li>
			</ul>
		
      	
      
    </div>
  </div>
</nav>
	</c:when>
		<c:otherwise>
			<c:redirect url="/?errorMsg=Kindly Login First" />
		</c:otherwise>
</c:choose>


