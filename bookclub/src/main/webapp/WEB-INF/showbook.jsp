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
<body>
	<main class="container">
		<h1><c:out value="${book.title}"/></h1>
		<h4><a href="/books">back to shelves</a></h4>
		<h4></h4>
		<h4><c:out value="${book.author}"/></h4>
		<h4><c:out value="${book.thoughts}"/></h4>
		<c:if test="${book.user.id==user_id}">
			<button><a href="/books/${book.id}/edit">Edit</a></button>
			<form action="/books/${book.id}" method="post">
	    		<input type="hidden" name="_method" value="delete">
	    		<input type="submit" value="Delete">
	    	</form>
		</c:if>
	</main>
</body>
</html>