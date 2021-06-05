<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>

	<div id = "newstore">
		<sf:form method = "post" action = "${ pageContext.request.contextPath }/addstore " modelAttribute = "store">
			<p>지점명 : </p><sf:input type = "text" path = "store_name"/><br>
				<sf:errors path = "store_name" class = "error"/><span class = "br"><br></span>
			<p>위치 : </p><sf:input type = "text" path = "location"/><br>
				<sf:errors path = "location" class = "error"/><span class = "br"><br></span>
			<input type = "submit" value = "등록"/>
			<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
		</sf:form>
	</div>

<%@include file ="/WEB-INF/views/footer.jsp" %>