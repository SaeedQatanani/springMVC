<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<h4><a href="/languages">Dashboard</a></h4>
	<h4><form action="/languages/${language.id}" method="post"><input type="hidden" name="_method" value="delete"><input type="submit" value="Delete"></form></h4>
	<form:form action="/languages/${language.id}" method="post" modelAttribute="language">
	    <input type="hidden" name="_method" value="put">
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