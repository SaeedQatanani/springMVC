<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<h4><a href="/languages">Dashboard</a></h4>
	<p><c:out value="${language.name}"/></p>
	<p><c:out value="${language.creator}"/></p>
	<p><c:out value="${language.version}"/></p>
	<p><a href="/languages/<c:out value="${language.id}"/>/edit">edit</a><form action="/languages/${language.id}" method="post"><input type="hidden" name="_method" value="delete"><input type="submit" value="Delete"></form></p>
</body>
</html>