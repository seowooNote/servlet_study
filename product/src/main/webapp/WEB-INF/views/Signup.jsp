<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 내장 객체
	// application, request, session 내장 객체를 주로 이용할 것임
	
	// application 객체 저장소 (ServletContext : Servlet의 모든 설정 / 전역 공간)
		// 생명주기 : 서버가 켜지고 꺼질때 까지의 저장 공간이 살아 있음
		
	// request 객체 저장소
		// 생명주기 : 요청이 들어오고 응답이 될때 까지의 저장 공간이 살아 있음
		
	// session 객체 저장소
		// 생명주기 : 서버가 켜지고 꺼질때 또는 세션 만료 시간까지의 저장 공간이 살아 있음(유효기간이 있음 default 30분 / ex 로그인 유효 시간)
	
	String value4 = (String) request.getAttribute("key4");
	String value44 = (String) application.getAttribute("key4");
	String value444 = (String) session.getAttribute("key4");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" placeholder="username" />
	<input type="password" placeholder="password" />
	<input type="text" placeholder="name" />
	<input type="email" placeholder="email" />
	
	<button onclick="handleSignupSubmit();">회원가입 버튼</button>
	
	<img alt="java이미지" src="/product/hellotest/javaImage.png">
	
	<script src="/product/static/js/signup.js"></script>
</body>
</html>