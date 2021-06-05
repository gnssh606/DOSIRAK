<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

	<form method = "post" action = "${ pageContext.request.contextPath }/search ">
		바코드 : <input type = "text" name = "barcode"/>		
		<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
		<input type = "submit" value = "검색"/>
	</form>
	<c:choose>
		<c:when test = "${ not empty errorMsg }">
			<p class = "error"> ${ errorMsg }</p>
		</c:when>
		<c:otherwise>
			<%
				Stock stock = (Stock)request.getAttribute("stock");
				if (stock != null) {
					Product product = (Product)request.getAttribute("stockName");
					out.print("판매 제품 : " + product.getProduct_name() + "<br>");
					out.print("재고 : " + (stock.getCount() + 1) + " -> " + stock.getCount() + "<br>");
				}
			%>
		</c:otherwise>
	</c:choose>

<%@include file ="/WEB-INF/views/footer.jsp" %>