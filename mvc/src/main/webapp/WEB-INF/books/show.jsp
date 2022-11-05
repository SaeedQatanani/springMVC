<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reading Books</title>
<link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
	<h2><c:out value="${book.title}"/></h2>
  	<p>Description: <c:out value="${book.description}"/></p>
  	<p>Language: <c:out value="${book.language}"/></p>
  	<p>Number Of Pages: <c:out value="${book.numberOfPages}"/></p>
</body>
</html>