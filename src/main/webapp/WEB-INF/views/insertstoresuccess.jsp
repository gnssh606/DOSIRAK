<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<%@include file ="/WEB-INF/views/stocktab.jsp" %>

	지점을 성공적으로 추가했습니다.<br> 
 	지점명 : ${ store.getStore_name() }<br>
	위치 : ${ store.getLocation() }<br>
	
<%@include file ="/WEB-INF/views/footer.jsp" %>
