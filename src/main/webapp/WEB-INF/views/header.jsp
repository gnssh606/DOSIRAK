<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="kr.ac.hansung.model.Stock" %>
<%@page import="kr.ac.hansung.model.Product" %>

<meta name="_csrf" content="${_csrf.token}"/>

<html>
<head>
	<title>매장 관리 프로그램</title>
</head>

<link rel = "stylesheet" href = "<c:url value="/resources/css/main.css"/>">

<body>
<div class = "div_body" align = "center";>