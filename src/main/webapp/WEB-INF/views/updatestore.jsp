<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>

	<div id = "updatestore">
		<sf:form method = "post" action = "${ pageContext.request.contextPath }/updatestore/${ store.getStore_name() } " modelAttribute = "store">
			<p>지점명 : </p><sf:input type = "text" path = "store_name" value = "${ store.getStore_name() }" /><br>
				<sf:errors path = "store_name" class = "error"/><span class = "br"><br></span>
			<p>위치 : </p><sf:input type = "text" path = "location" value = "${ store.getLocation() }" /><br>
				<sf:errors path = "location" class = "error"/><span class = "br"><br></span>
			<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
			<input type = "submit" value = "수정"/>
		</sf:form>
	</div>
	<form id = "delete" method = "post" action = "${ pageContext.request.contextPath }/deletestore/${ store.getStore_name() } ">
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
	 	<input type = "submit" value = "삭제" onclick = "if (!confirm('삭제하시겠습니까?')) { return false; }"/>
	</form>
	
<%@include file ="/WEB-INF/views/footer.jsp" %>