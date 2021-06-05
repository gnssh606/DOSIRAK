<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

	수정이 완료되었습니다.<br>
	
	<table class = "table">
		<tr>
			<th>제품명</th>
			<th>바코드</th>
			<th>가격</th>
			<th>카테고리</th>
		</tr>
		<tr>
			<td>${ product.getProduct_name() }</td>
			<td>${ product.getBarcode() }</td>
			<td>${ product.getPrice() }</td>
			<td>${ product.getCategory() }</td>
		</tr>
	</table>
	
<%@include file ="/WEB-INF/views/footer.jsp" %>