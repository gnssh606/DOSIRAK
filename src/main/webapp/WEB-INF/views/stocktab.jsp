<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <div id = "stocktab">
	    <button class = "stocktab_button"><a href = "${ pageContext.request.contextPath }/searchstock">재고 수정</a></button>
		<button class = "stocktab_button"><a href = "${ pageContext.request.contextPath }/newproduct">새 물품 등록</a></button>
		<button class = "stocktab_button"><a href = "${ pageContext.request.contextPath }/searchproduct">물품 정보 수정</a></button>
    </div>
	<hr>