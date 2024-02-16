<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 스크립트릿 
	// String name2 = "홍길동";
	// List<String> names = List.of("홍길동1", "홍길동2", "홍길동3");
	
	//	서버 사이드 렌더링(SSR) : model1 -> model2 -> mvc 로 발전되었음
	//	model1	: 스크립트릿에 작성된 Java 코드가 html 코드(태그) 사이사이에 작성되어 있는 것
	//	model2	: model1의 사이사이에 작성된 스크립트릿을 위로 다 올려서 Java 코드를 작성하여 해당하는 값만 html 코드에 스크립트릿에 간단하게 작성하는 것(front 에서 Style 태그안에 CSS 작성하는 것과 유사한 형태)
	//	mvc(model, view, controller)
			// model => data(데이터)	-> DTO
			// view => html(화면)		-> HTML or JSP
			// controller => model, view 를 제어, 요청, 응답 처리 -> Servlet
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello</h1>
	<%
		String name = "홍길동";
	%>
	<h2><%=name %></h2>
	<%
		String inputValue = "test";
	%>
	<input value = "<%=inputValue %>" />
</body>
</html>