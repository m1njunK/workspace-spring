<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>

	<h1>readPage.jsp</h1>
	<!-- board -->
	<table border="1">
	  <tr>
	  	<td>제목</td>
	  	<td>${board.title}</td>
	  </tr>
	  <tr>
	  	<td>작성자</td>
	  	<td>${board.writer}</td>
	  </tr>
	  <tr>
	  	<td>내용</td>
	  	<td>${board.content}</td>
	  </tr>
  	  <tr>
	  	<td>작성시간</td>
	  	<td>
	  		<f:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm"/>
	  	</td>
	  </tr>
	</table>
	
	<div>
		<c:if test="${!empty userInfo}">
			<input type="button" id="replyBtn" value="답변글 작성"/>
			<c:if test="${userInfo.uno eq board.uno}">
				<input type="button" id="modifyBtn" value="수정">
				<input type="button" id="deleteBtn" value="삭제">
			</c:if>
		</c:if>
		<input type="button" id="listBtn" value="게시글 목록"/>
	</div>
</body>
</html>