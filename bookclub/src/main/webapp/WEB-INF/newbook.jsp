<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login and Registration</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="container">
	<h1>Add a book to your shelf!</h1>
	<h4><a href="/books">back to shelves</a></h4>
	<form:form action="/books/new" method="post" modelAttribute="book">
		<form:input type="hidden" path="user" value="${user.id}"/>
		<p class="error" style="color: red;"><form:errors path="title"/></p>
		<p class="error" style="color: red;"><form:errors path="author"/></p>
		<p class="error" style="color: red;"><form:errors path="thoughts"/></p>
	    <p>
	        <form:label path="title">Title</form:label>
	        <form:input path="title"/>
	    </p>
	    <p>
	        <form:label path="author">Author</form:label>
	        <form:input path="author"/>
	    </p>
	    <p>
	        <form:label path="thoughts">My Thoughts</form:label>
	        <form:input path="thoughts"/>
	    </p>   
	    <input type="submit" value="Submit" class="btn btn-primary"/>
	</form:form>
</body>
</html>