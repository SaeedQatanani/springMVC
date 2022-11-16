<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<title>Events</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript" src="test.js"></script>
</head>
<body>
	<main class="container mt-3">
		<h1>Welcome, ${currentUser.firstName}</h1>
		<h3>Here are some of the events in your state:</h3>
		<table class="table table-striped table-bordered table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Date</th>
					<th scope="col">Location</th>
					<th scope="col">Host</th>
					<th scope="col">Action/ Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="event" items="${stateEvents}">
					<tr>
						<td scope="row"><a href="/events/<c:out value="${event.id}"/>"><c:out value="${event.name}"/></a></td>
						<td><fmt:formatDate value="${event.date}" pattern="MMM dd, yyyy"/></td>
						<td><c:out value="${event.location}"/></td>
						<td><c:out value="${event.host.firstName}"/></td>
						<c:choose>
						    <c:when test="${event.host.id==currentUser.id}">
						        <td>
						        	<a href="/events/${event.id}/edit">Edit</a>  <a href="/events/${event.id}/delete">Delete</a>
						        </td>
						    </c:when>
						    <c:when test="${event.isMember}">
						        <td>Joining <a href="/events/${event.id}/leave">Cancel</a></td>
						    </c:when>    
						    <c:otherwise>
								<td><a href="/events/${event.id}/join">Join</a></td>
						    </c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table class="table table-striped table-bordered table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Date</th>
					<th scope="col">Location</th>
					<th scope="col">State</th>
					<th scope="col">Host</th>
					<th scope="col">Action/ Status</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="event" items="${notStateEvents}">
					<tr>
						<td scope="row"><a href="/events/<c:out value="${event.id}"/>"><c:out value="${event.name}"/></a></td>
						<td><fmt:formatDate value="${event.date}" pattern="MMM dd, yyyy"/></td>
						<td><c:out value="${event.location}"/></td>
						<td><c:out value="${event.state}"/></td>
						<td><c:out value="${event.host.firstName}"/></td>
						<c:choose>
						    <c:when test="${event.isMember}">
						        <td>Joining <a href="/events/${event.id}/leave">Cancel</a></td>
						    </c:when>    
						    <c:otherwise>
								<td><a href="/events/${event.id}/join">Join</a></td>
						    </c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<form:form action="/events/new" method="post" modelAttribute="event">
	<h2>Create an Event</h2>
		<form:input type="hidden" path="host" value="${currentUser.id}"/>
		<div class="form-group row mb-3">
			<form:label path="name" class="col-sm-4 col-form-label">Name:</form:label>
			<form:errors path="name" style="color:red;"/>
			<div class="col-sm-8">
				<form:input path="name" class="form-control"/>
			</div>
		</div>
		<div class="form-group row mb-3">
			<form:label path="date" class="col-sm-4 col-form-label">Date:</form:label>
			<form:errors path="date" style="color:red;"/>
			<div class="col-sm-8">
				<form:input type="date" path="date" class="form-control"/>
			</div>
		</div>
		<div class="form-group row mb-3">
			<form:label path="location" class="col-sm-4 col-form-label">Location:</form:label>
			<form:errors path="location" style="color:red;"/>
			<div class="col-sm-8">
				<form:input path="location" class="form-control"/>
				<form:select path="state" class="form-select">
					<form:option value="CA">CA</form:option>
					<form:option value="WB">WB</form:option>
					<form:option value="PL">PL</form:option>
					<form:option value="GA">GA</form:option>
				</form:select>
			</div>
		</div>
	    <input type="submit" value="Submit" class="btn btn-primary"/>
	</form:form>
	</main>
</body>
</html>