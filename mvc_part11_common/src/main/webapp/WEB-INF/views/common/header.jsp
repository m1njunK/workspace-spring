<!-- header.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 인증된 사용자 정보를 Session 에 userInfo로 저장 -->
	<br/><hr/>
	<a href="<c:url value='/'/>">HOME</a> 
	<c:choose>
		<c:when test="${not empty sessionScope.userInfo}">
			&nbsp;&nbsp;<span>${userInfo.uname}</span>&nbsp;&nbsp;
			<a href="#">글작성</a>
			<a href="<c:url value='/user/signOut'/>">SIGN OUT</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/user/signIn'/>">SIGN IN</a>
			<a href="<c:url value='/user/signUp'/>">SIGN UP</a>
		</c:otherwise>
	</c:choose>
	<a href="#">게시물 보러 가기</a>
	<br/><hr/>
<script>
	const invalidate = '${invalidate}';
	if(invalidate != ''){
		alert(invalidate);
		location.href='<c:url value="/user/signOut"/>';
	}
</script>


























