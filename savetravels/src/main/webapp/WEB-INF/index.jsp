<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read Share</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<h1>Save Travels</h1>
	<table class="table table-striped table-bordered table-hover">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Expense</th>
				<th scope="col">Vendor</th>
				<th scope="col">Amount</th>
				<th scope="col">Actions</th>
			</tr>
		</thead>
		<c:forEach var="expense" items="${expenses}">
			<tr>
				<td><a href="/expenses/<c:out value="${expense.id}"/>"><c:out value="${expense.name}"/></a></td>
				<td><c:out value="${expense.vendor}"/></td>
				<td><c:out value="${expense.amount}"/>$</td>
				<td><a href="/expenses/edit/<c:out value="${expense.id}"/>">edit</a><form action="/expenses/${expense.id}" method="post"><input type="hidden" name="_method" value="delete"><input type="submit" value="Delete"></form></td>
			</tr>
		</c:forEach>
	</table>
	
	<h1>Add an expense:</h1>
	<form:form action="/expenses" method="post" modelAttribute="expense">
	    <p>
	        <form:label path="name">Name</form:label>
	        <form:input path="name"/>
	    </p>
	    <p>
	        <form:label path="vendor">Vendor</form:label>
	        <form:input path="vendor"/>
	    </p>
	    <p>
	        <form:label path="amount">Amount</form:label>
	        <form:input path="amount"/>
	    </p>
	    <p>
	        <form:label path="description">Description</form:label>
	        <form:textarea path="description"/>    
	    </p>    
	    <input type="submit" value="Submit"/>
		<p class="error" style="color: red;"><form:errors path="name"/></p>
		<p class="error" style="color: red;"><form:errors path="vendor"/></p>
		<p class="error" style="color: red;"><form:errors path="amount"/></p>
		<p class="error" style="color: red;"><form:errors path="description"/></p>
	</form:form>
</body>
</html>