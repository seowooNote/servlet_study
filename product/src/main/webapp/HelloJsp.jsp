<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 스크립트릿 
	String name2 = "홍길동";
	List<String> names = List.of("홍길동1", "홍길동2", "홍길동3");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	<h1><%=name2%></h1>
</body>
</html>