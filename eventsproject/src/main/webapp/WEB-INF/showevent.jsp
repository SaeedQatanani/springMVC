<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${event.name}"/></title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
</head>
<body>
	<main class="container">
	<section>
		<h1><c:out value="${event.name}"/></h1>
		<h4>Host: <c:out value="${event.host.firstName}"/> <c:out value="${event.host.lastName}"/></h4>
		<h4>Date: <fmt:formatDate value="${event.date}" pattern="MMMM dd, yyyy"/></h4>
		<h4>Location: <c:out value="${event.location}"/>, <c:out value="${event.state}"/></h4>
		<h4>People how are attending this event: <c:out value="${count}"/></h4>
		<table class="table table-striped table-bordered table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Location</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${event.users}">
					<tr>
						<td scope="row"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></td>
						<td><c:out value="${user.location}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	<section>
		<div id="messages" style="margin:1%; padding:1%; width: 100%; height: 150px; border: 2px black solid; overflow: scroll;">
			<c:forEach var="message" items="${messages}">
					<p><c:out value="${message.user.firstName}"/>: <c:out value="${message.content}"/></p>
			</c:forEach>
		</div>
		<form:form action="/events/${event.id}/messages" method="post" modelAttribute="message">
			<form:input type="hidden" path="event" value="${event.id}"/>
			<form:input type="hidden" path="user" value="${user.id}"/>
		    <div>
		        <form:label path="content">Add comment:</form:label>
				<p class="error" style="color: red;"><form:errors path="content"/></p>
		        <form:input path="content" class="form-control"/>
		    </div>  
		    <input type="submit" value="Submit" class="btn btn-primary"/>
		</form:form>
	</section>
		
	</main>
</body>
</html>