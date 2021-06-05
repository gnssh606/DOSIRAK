<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>로그인</title>

<link rel = "stylesheet" href = "<c:url value="/resources/css/main.css"/>">

</head>
<body>

		<div class = "container" align = "center">
		<h1 style = "margin : 50px 0;">재고 관리 프로그램</h1>
		<h2>관리자 기능을 사용하기 위해서는 로그인이 필요합니다.</h2>
		<form method = "post" action = "${ pageContext.request.contextPath }/login">

			<p style = "width : 40%; margin : 100px 20px 0 20px;">
				<label style = "display : inline-block; width : 100px; text-align : center;">ID</label>
				<input type = "text" id = "username" name = "username" placeholder = "ID" required autofocus>
			</p>
			<p style = "width : 40%; margin : 0 20px;">
				<label style = "display : inline-block; width : 100px; text-align : center;">Password</label>
				<input type = "password" id = "password" name = "password" placeholder = "Password" required>
			</p>
			<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
			<button style = "margin : 20px 200px;" type = "submit">로그인</button>
			<c:if test = "${ not empty errorMsg }">
				<p class = "error"> ${ errorMsg }</p>
			</c:if>
		</form>
	</div>

</body>
</html>