<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create a Project</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="container">
	<h1>Create a Project</h1>
	<form:form action="/projects/new" method="post" modelAttribute="project">
		<form:input type="hidden" path="teamLead" value="${user.id}"/>
	    <p>
	        <form:label path="title">Title</form:label>
			<p class="error" style="color: red;"><form:errors path="title"/></p>
	        <form:input path="title"/>
	    </p>
	    <p>
	        <form:label path="description">Description</form:label>
			<p class="error" style="color: red;"><form:errors path="description"/></p>
	        <form:input path="description"/>
	    </p>
	    <p>
	        <form:label path="dueDate">Due Date</form:label>
			<p class="error" style="color: red;"><form:errors path="dueDate"/></p>
	        <form:input type="date" path="dueDate"/>
	    </p>   
		<button class="btn btn-secondary"><a href="/dashboard">Cancel</a></button>
	    <input type="submit" value="Submit" class="btn btn-primary"/>
	</form:form>
</body>
</html>