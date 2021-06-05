<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file ="/WEB-INF/views/header.jsp" %>
<%@include file ="/WEB-INF/views/storetab.jsp" %>
<div id = "changestoretab">
	<button class = "changestoretab_button"><a href = "${ pageContext.request.contextPath }/searchstore">지점 정보 수정</a></button>
	<button class = "changestoretab_button"><a href = "${ pageContext.request.contextPath }/newstore">새 지점 등록</a></button>
</div>
	<hr>
	
	<c:forEach var = "store" items = "${ stores }">
		<p><a href = "${ pageContext.request.contextPath }/changestore/${ store.getStore_name() }"> <c:out value = "${ store.getStore_name() }"> </c:out> </a></p>
	</c:forEach>

<%@include file ="/WEB-INF/views/footer.jsp" %>