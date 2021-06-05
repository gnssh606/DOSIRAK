<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

	<form method = "post" action = "${ pageContext.request.contextPath }/updatestock/${ product.getBarcode() } ">
	<table class = "table">
		<tr>
			<th>제품명</th>
			<th>바코드</th>
			<th>가격</th>
			<th>카테고리</th>
			<th>재고</th>
		</tr>
		<tr>
			<td>${ product.getProduct_name() }<input type = "text" name = "product_name" value = "${ product.getProduct_name() }" style = "display : none;"/></td>
			<td>${ product.getBarcode() }<input type = "text" name = "barcode" value = "${ product.getBarcode() }" style = "display : none;"/></td>
			<td>${ product.getPrice() }<input type = "number" name = "price" value = "${ product.getPrice() }" style = "display : none;"/></td>
			<td>${ product.getCategory() }<input type = "text" name = "category" value = "${ product.getCategory() }" style = "display : none;"/></td>
			<td><input type = "number" name = "count" value = "${ stock.getCount() }"/></td>
		</tr>
	</table>
		<input type = "text" name = "store_name" value = "${ stock.getStore_name() }" style = "display : none;"/>
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
		<input type = "submit" value = "수정"/>
	</form>
	<form id = "delete" method = "post" action = "${ pageContext.request.contextPath }/delete/${ product.getBarcode() }">
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
  		<input type = "submit" value = "삭제" onclick = "if (!confirm('삭제하시겠습니까?')) { return false; }"/>
	</form>

<%@include file ="/WEB-INF/views/footer.jsp" %>