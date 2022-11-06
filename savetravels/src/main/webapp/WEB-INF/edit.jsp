<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit My Task</title>
</head>
<body>
	<h1>Edit Expense</h1>
	<h4><a href="/expenses">Go Back</a></h4>
	<form:form action="/expenses/${expense.id}" method="post" modelAttribute="expense">
	    <input type="hidden" name="_method" value="put">
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