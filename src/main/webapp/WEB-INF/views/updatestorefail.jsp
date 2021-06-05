<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

	${ store.getStore_name() }는 이미 사용중인 지점명입니다.<br>
	지점명 : ${ store.getStore_name() }<br>
	위치 : ${ store.getLocation() }<br>

<%@include file ="/WEB-INF/views/footer.jsp" %>