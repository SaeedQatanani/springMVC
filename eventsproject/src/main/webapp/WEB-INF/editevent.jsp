<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Events</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="container">
	<h1><c:out value="${event.name}"/></h1>
	<form:form action="/events/${event.id}" method="post" modelAttribute="event">
	    <input type="hidden" name="_method" value="put">
		<form:input type="hidden" path="host"/>
		<form:input type="hidden" path="users"/>
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
</body>
</html>