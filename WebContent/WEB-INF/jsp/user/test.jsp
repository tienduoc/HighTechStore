<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
aaaaaaaaaaaaaaaaaaaaaaaaaa
<c:forEach var="p" items="${products }">
	<h1>${p.category.name}</h1>
</c:forEach>