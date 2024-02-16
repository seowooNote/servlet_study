<%@page import="com.study.product.dto.SearchProductResponseDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>가격</th>
				<th>사이즈</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="dto" items="${productList}">
				<c:if test="${dto.productId % 2 == 0}">
					<tr>
						<td>${dto.productId}</td>
						<td>${dto.productName}</td>
						<td>${dto.productPrice}</td>
						<td>${dto.productSize}</td>
					</tr>
				</c:if>
				<c:choose>
					<c:when test="">
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</c:forEach>

		</tbody>
	</table>
</body>
</html>