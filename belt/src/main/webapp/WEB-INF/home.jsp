<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<title>Kickball League Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript" src="test.js"></script>
</head>
<body>
	<main class="container mt-3">
		<h1>Welcome, ${currentUser.userName}</h1>
		<p><a href="/logout">log out</a></p>
		<table class="table table-striped table-bordered table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">Team Name</th>
					<th scope="col">Skill Level (1-5)</th>
					<th scope="col">Players</th>
					<th scope="col">Game Day</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="team" items="${allTeams}">
					<tr>
						<td scope="row"><a href="/teams/<c:out value="${team.id}"/>"><c:out value="${team.name}"/></a></td>
						<td><c:out value="${team.skillLevel}"/></td>
						<td>${fn:length(team.users)}/9</td>
						<td><c:out value="${team.gameDay}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button class="btn btn-outline-primary"><a href="/teams/new">Create New Team</a></button>
	</main>
</body>
</html>