<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read Share</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>
<body>
   <main class="container">
		<h1>Welcome, <c:out value="${currentUser.userName}"/> !</h1>
		<h4><a href="/logout">logout</a></h4>
		<h4><a href="/bookmarket">Book Market</a></h4>
		<h4><a href="/books/new">+ Add a book to my shelf!</a></h4>
		<h4>Books from everyone's shelves:</h4>
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Posted By</th>
				</tr>
			</thead>
			<c:forEach var="book" items="${allBooks}">
			<tr>
				<td scope="row"><c:out value="${book.id}"/></td>
				<td><a href="/books/<c:out value="${book.id}"/>"><c:out value="${book.title}"/></a></td>
				<td><c:out value="${book.author}"/></td>
				<td><c:out value="${book.user.userName}"/></td>
			</tr>
			</c:forEach>
		</table>
	</main>
</body>
</html>