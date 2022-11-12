<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project Manager Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
   <main class="container">
		<h1>Welcome, <c:out value="${currentUser.firstName}"/> !</h1>
		<h4><a href="/logout">logout</a></h4>
		<h4><a href="/projects/new">+ new project</a></h4>
		<h4>All Projects</h4>
		<table class="table">
			<thead>
				<tr>
					<th>Project</th>
					<th>Team Lead</th>
					<th>Due Date</th>
					<th>Actions</th>
				</tr>
			</thead>
			<c:forEach var="project" items="${allProjects}">
			<c:if test="${project.teamLead.id!=user_id}">
				<tr>
					<td scope="row"><a href="/projects/<c:out value="${project.id}"/>"><c:out value="${project.title}"/></a></td>
					<td><c:out value="${project.teamLead.firstName}"/></td>
					<td><fmt:formatDate value="${project.dueDate}" pattern="MMM dd"/></td>
					<td><a href="/projects/join/<c:out value="${project.id}"/>">Join team</a></td>
				</tr>
			</c:if>
			</c:forEach>
		</table>
		<h4>Your Projects</h4>
		<table class="table">
			<thead>
				<tr>
					<th>Project</th>
					<th>Team Lead</th>
					<th>Due Date</th>
					<th>Actions</th>
				</tr>
			</thead>
			<c:forEach var="project" items="${yourProjects}">
			<tr>
				<td scope="row"><a href="/projects/<c:out value="${project.id}"/>"><c:out value="${project.title}"/></a></td>
				<td><c:out value="${project.teamLead.firstName}"/></td>
				<td><fmt:formatDate value="${project.dueDate}" pattern="MMM dd"/></td>
				<c:choose>
				    <c:when test="${project.teamLead.id==user_id}">
				        <td><a href="/projects/${project.id}/edit">Edit</a></td>
				    </c:when>    
				    <c:otherwise>
				        <td><a href="/projects/leave/<c:out value="${project.id}"/>">Leave team</a></td>
				    </c:otherwise>
				</c:choose>
			</tr>
			</c:forEach>
		</table>

	</main>
</body>
</html>