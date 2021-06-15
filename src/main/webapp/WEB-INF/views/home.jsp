<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>
 
 <div>
	<form method = "post" action = "${ pageContext.request.contextPath }/search ">
		바코드 : <input type = "text" name = "barcode"/>
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
		<input type = "submit" value = "판매"/>
	</form> 
 </div>

<%@include file ="/WEB-INF/views/footer.jsp" %>