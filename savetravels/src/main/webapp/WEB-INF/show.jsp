<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Expense</title>
</head>
<body>
	<h1>Expense Details</h1>
	<h4><a href="/expenses">Go Back</a></h4>
	<p>Expense Name: <c:out value="${expense.name}"/></p>
	<p>Expense Description: <c:out value="${expense.description}"/></p>
	<p>Vendor: <c:out value="${expense.vendor}"/></p>
	<p>Amount Spent: <c:out value="${expense.amount}"/>$</p>
</body>
</html>