<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

	<form method = "post" action = "${ pageContext.request.contextPath }/searchproduct ">
		<select name = "type">
    	    <option value = "barcode">바코드</OPTION>
    	    <option value = "name">제품명</OPTION>
    	  </select>
		<input type = "text" name = "keyword"/>
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
		<input type = "submit" value = "검색"/>
	</form>
	<c:if test = "${ not empty products }">
		<table class = "table">
			<tr>
				<th>제품명</th>
				<th>바코드</th>
				<th>가격</th>
				<th>카테고리</th>
				<th>수정</th>
			</tr>
			<c:forEach var = "product" items = "${ products }">
				<tr>
					<td>${ product.getProduct_name() }</td>
					<td>${ product.getBarcode() }</td>
					<td>${ product.getPrice() }</td>
					<td>${ product.getCategory() }</td>
					<td><a href = "${ pageContext.request.contextPath }/updateproduct/${ product.getBarcode() }">수정하기</a></td>
				</tr>
			</c:forEach>			
		</table>
	</c:if>

<%@include file ="/WEB-INF/views/footer.jsp" %>