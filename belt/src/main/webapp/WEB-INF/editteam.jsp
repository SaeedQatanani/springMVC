<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Team</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body class="container">
	<h1>Edit Team</h1>
	<h3><a href="/">dashboard</a></h3>
	<form:form action="/teams/${team.id}" method="post" modelAttribute="team">
	    <input type="hidden" name="_method" value="put">
		<form:input type="hidden" path="user"/>
		<form:input type="hidden" path="users"/>
		<div class="form-group row mb-3">
			<form:label path="name" class="col-sm-4 col-form-label">Name:</form:label>
			<form:errors path="name" style="color:red;"/>
			<div class="col-sm-8">
				<form:input path="name" class="form-control"/>
			</div>
		</div>
		<div class="form-group row mb-3">
			<form:label path="skillLevel" class="col-sm-4 col-form-label">Skill Level:</form:label>
			<form:errors path="skillLevel" style="color:red;"/>
			<div class="col-sm-8">
				<form:input path="skillLevel" class="form-control"/>
			</div>
		</div>
		<div class="form-group row mb-3">
			<form:label path="gameDay" class="col-sm-4 col-form-label">Game day:</form:label>
			<form:errors path="gameDay" style="color:red;"/>
			<div class="col-sm-8">
				<form:input path="gameDay" class="form-control"/>
			</div>
		</div>
	    <input type="submit" value="Submit" class="btn btn-primary"/>
	</form:form>
	<h3><a href="/teams/${team.id}/delete">delete</a></h3>
</body>
</html>