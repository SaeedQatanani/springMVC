<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<main class="container">
		<h1>Project Details</h1>
		<h4><a href="/dashboard">Back to Dashboard</a></h4>
		<h4>Project: <c:out value="${project.title}"/></h4>
		<h4>Description: <c:out value="${project.description}"/></h4>
		<h4>Due Date: <fmt:formatDate value="${project.dueDate}" pattern="MM/dd/yyyy"/></h4>
		<c:if test="${project.teamLead.id==user_id}">
			<form action="/projects/${project.id}" method="post">
	    		<input type="hidden" name="_method" value="delete">
	    		<input type="submit" value="Delete" class="btn btn-outline-danger">
				<button class="btn btn-outline-primary"><a style="text-decoration: none;" href="/projects/${project.id}/edit">Edit</a></button>
	    	</form>
		</c:if>
		<h4><a href="/projects/${project.id}/tasks">See Tasks</a></h4>
	</main>
</body>
</html>