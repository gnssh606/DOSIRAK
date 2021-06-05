<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

<div id = "newproduct">
	<sf:form method = "post" action = "${ pageContext.request.contextPath }/addproduct" modelAttribute = "product">
		<p>제품명 : </p><sf:input type = "text" path = "product_name"/><br>
		<sf:errors path = "product_name" class = "error"/><span class = "br"><br></span>
		<p>바코드 : </p><sf:input type = "text" path = "barcode"/><br>
		<sf:errors path = "barcode" class = "error"/><span class = "br"><br></span>
		<p>가격 : </p><sf:input type = "number" path = "price"/><br>
		<sf:errors path = "price" class = "error"/><span class = "br"><br></span>
		<p>카테고리 : </p><sf:input type = "text" path = "category"/><br>
		<sf:errors path = "category" class = "error"/><span class = "br"><br></span>
		<input type = "submit" value = "등록"/>
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
	</sf:form>
</div>

<%@include file ="/WEB-INF/views/footer.jsp" %>