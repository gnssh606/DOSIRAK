<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

	<sf:form method = "post" action = "${ pageContext.request.contextPath }/updateproduct/${ product.getBarcode() }" modelAttribute = "product">
		<table class = "table">
			<tr>
				<th>제품명</th>
				<th>바코드</th>
				<th>가격</th>
				<th>카테고리</th>
			</tr>
			<tr>
				<td><sf:input type = "text" path = "product_name" value = "${ product.getProduct_name() }"/>
					<sf:errors path = "product_name" class = "error"/><span class = "br"><br></span></td>
				<td><sf:input type = "text" path = "barcode" value = "${ product.getBarcode() }"/>
					<sf:errors path = "barcode" class = "error"/><span class = "br"><br></span></td>					
				<td><sf:input type = "number" path = "price" value = "${ product.getPrice() }"/>
					<sf:errors path = "price" class = "error"/><span class = "br"><br></span></td>
				<td><sf:input type = "text" path = "category" value = "${ product.getCategory() }"/>
					<sf:errors path = "category" class = "error"/><span class = "br"><br></span></td>
			</tr>
		</table>
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
		<input type = "submit" value = "수정"/>
	</sf:form>
	<form id = "delete" method = "post" action = "${ pageContext.request.contextPath }/deleteproduct/${ product.getBarcode() } ">
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
  		<input type = "submit" value = "삭제" onclick = "if (!confirm('삭제하시겠습니까?')) { return false; }"/>
	</form>

<%@include file ="/WEB-INF/views/footer.jsp" %>