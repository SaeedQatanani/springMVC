<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reading Books</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
	<h1>All Books</h1>
	<table class="table table-striped table-bordered table-hover">
		<thead class="thead-dark">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Title</th>
				<th scope="col">Language</th>
				<th scope="col"># Pages</th>
			</tr>
		</thead>
		<c:forEach var="book" items="${books}">
			<tr>
				<td scope="row"><c:out value="${book.id}"/></td>
				<td><a href="/books/<c:out value="${book.id}"/>"><c:out value="${book.title}"/></a></td>
				<td><c:out value="${book.language}"/></td>
				<td><c:out value="${book.numberOfPages}"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>