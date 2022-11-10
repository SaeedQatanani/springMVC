<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Share</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="container">
	<h1>Edit Book</h1>
	<h4><a href="/shelves">back to shelves</a></h4>
	<form:form action="/books/${book.id}" method="post" modelAttribute="book">
	    <input type="hidden" name="_method" value="put">
		<form:input type="hidden" path="user" value="${user.id}"/>
		<form:input type="hidden" path="borrower" value="${bUser.id}"/>
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
	<form action="/books/${book.id}" method="post">
	    		<input type="hidden" name="_method" value="delete">
	    		<input type="submit" value="Delete" class="btn btn-outline-danger">
	</form>
</body>
</html>