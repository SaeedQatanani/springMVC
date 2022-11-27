<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Team Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
	<main class="container">
		<h1><c:out value="${team.name}"/></h1>
		<h3><a href="/">dashboard</a></h3>
		<h4>Team Name: <c:out value="${team.name}"/></h4>
		<h4>Skill Level: <c:out value="${team.skillLevel}"/></h4>
		<h4>Game Day: <c:out value="${team.gameDay}"/></h4>
		<h4>Players:</h4>
		<ul>
			<c:forEach var="player" items="${team.users}">
				<li>
					<c:out value="${player.userName}"/>
				</li>
			</c:forEach>
		</ul>
		<h4>Add new Players</h4>
		<p style="color:red;"><c:out value="${error}"/></p>
		<form action="/teams/addUser" method="post">
		<input type="hidden" name="team" value="${team.id}">
		<select name="user">
		<c:forEach var="user" items="${otherUsers}">
    		<option value="${user.id}"><c:out value="${user.userName}"/></option>
		</c:forEach>
		</select>
		<input type="submit" value="add">
		</form>
			<c:if test="${team.user.id==user.id}">
				<p><a href="/teams/${team.id}/edit">edit</a></p>
				<p><a href="/teams/${team.id}/delete">delete</a></p>		     
			</c:if>
		
	</main>
</body>
</html>