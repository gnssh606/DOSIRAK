<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href = "${ pageContext.request.contextPath }/">HOME</a>
	<c:forEach var = "stock" items = "${ stocks }">
		<p> <c:out value = "${ stock }"> </c:out> </p>
	</c:forEach>
	
	
	<c:forEach var = "product" items = "${ products }">
		<p> <c:out value = "${ product }"> </c:out> </p>
	</c:forEach>
	
	
	<c:forEach var = "store" items = "${ stores }">
		<p> <c:out value = "${ store }"> </c:out> </p>
	</c:forEach>
</body>
</html>