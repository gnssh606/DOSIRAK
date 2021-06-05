<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

	${ product.getBarcode() }는 이미 사용중인 바코드입니다.<br>
	제품명 : ${ product.getProduct_name() }<br>
	바코드 : ${ product.getBarcode() }<br>
	가격 : ${ product.getPrice() }<br>
	카테고리 : ${ product.getCategory() }<br>

<%@include file ="/WEB-INF/views/footer.jsp" %>