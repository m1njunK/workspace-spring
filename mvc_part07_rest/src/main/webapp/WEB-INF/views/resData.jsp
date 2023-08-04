<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>resData.jsp</title>
</head>
<body>
	<!-- /WEB-INF/views/resData.jsp -->
	<a href="getSampleList">getSampleList</a> <br/>
	<a href="testJSON">testJSON</a> <br/>
	<hr/>
	<form action="getSample">
		<input type="text" name="name" /> <br/>
		<input type="number" name="age" /> <br/>	
		<button>전송</button>	
	</form>
	
</body>
</html>