<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Languages</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<table class="table table-striped table-bordered table-hover">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Name</th>
				<th scope="col">Creator</th>
				<th scope="col">Version</th>
				<th scope="col">Actions</th>
			</tr>
		</thead>
		<c:forEach var="language" items="${languages}">
			<tr>
				<td><a href="/languages/<c:out value="${language.id}"/>"><c:out value="${language.name}"/></a></td>
				<td><c:out value="${language.creator}"/></td>
				<td><c:out value="${language.version}"/></td>
				<td><a href="/languages/<c:out value="${language.id}"/>/edit">edit</a><form action="/languages/${language.id}" method="post"><input type="hidden" name="_method" value="delete"><input type="submit" value="Delete"></form></td>
			</tr>
		</c:forEach>
	</table>
	
	<form:form action="/languages" method="post" modelAttribute="language">
	    <p>
	        <form:label path="name">Name</form:label>
	        <form:input path="name"/>
	    </p>
	    <p>
	        <form:label path="creator">Creator</form:label>
	        <form:input path="creator"/>
	    </p>
	    <p>
	        <form:label path="version">Version</form:label>
	        <form:input path="version"/>
	    </p>  
	    <input type="submit" value="Submit"/>
		<p class="error" style="color: red;"><form:errors path="name"/></p>
		<p class="error" style="color: red;"><form:errors path="creator"/></p>
		<p class="error" style="color: red;"><form:errors path="version"/></p>
	</form:form>
</body>
</html>