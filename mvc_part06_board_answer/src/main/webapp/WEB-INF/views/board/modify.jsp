<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify.jsp</title>
</head>
<body>
	<h3>MODIFY BAORD- ${criteria}</h3>
	<!-- model boardVO -->
	<!-- board/modify POST -->
	<form method="POST">
		<input type="hidden" name="bno" value="${boardVO.bno}"/>
		<input type="hidden" name="page" value="${criteria.page}"/>
		<input type="hidden" name="perPageNum" value="${criteria.perPageNum}"/>
		<div>
			<label>TITLE</label>
			<input type="text" name="title" value="${boardVO.title}" />
		</div>
		<div>
			<label>CONTENT</label>
			<textarea name="content" rows=3>${boardVO.content}</textarea>
		</div>
		<div>
			<label>WRITER</label>
			<input type="text" name="writer" value="${boardVO.writer}" />
		</div>
		<div>	
			<input type="submit" value="수정 완료"/>
			<a href="listPage?page=${criteria.page}&perPageNum=${criteria.perPageNum}">LIST</a>
		</div>
	</form>
</body>
</html>




