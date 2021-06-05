<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <div id = "storetab">
	    <p id = "a_home">
			<a href = "${ pageContext.request.contextPath }/">HOME</a>
		</p>
		<p id = "a_changestore">
			<a href = "${ pageContext.request.contextPath }/changestore">지점 변경</a>
		</p>
		<p id = "storename">
			<%
				session = request.getSession();
				out.print(session.getAttribute("store"));
			%>
		</p>
	</div>