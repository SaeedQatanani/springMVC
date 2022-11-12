<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Props Page</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
   <main class="container">
		<h1>Project: <c:out value="${project.title}"/></h1>
		<h4><a href="/dashboard">Back to Dashboard</a></h4>
		<h4>Project Lead: <c:out value="${project.teamLead.firstName}"/></h4>
		<form:form action="/projects/${project.id}/tasks" method="post" modelAttribute="task">
			<form:input type="hidden" path="project" value="${project.id}"/>
			<form:input type="hidden" path="user" value="${user.id}"/>
		    <p>
		        <form:label path="content">Task</form:label>
				<p class="error" style="color: red;"><form:errors path="content"/></p>
		        <form:input path="content"/>
		    </p>  
		    <input type="submit" value="Submit" class="btn btn-primary"/>
		</form:form>
		<ul>
			<c:forEach var="task" items="${tasks}">
					<li>Added by: <c:out value="${task.user.firstName}"/> at <fmt:formatDate value="${task.createdAt}" pattern="hh:mm a MMM dd"/>: <c:out value="${task.content}"/></li>
			</c:forEach>
		</ul>
	</main>
</body>
</html>