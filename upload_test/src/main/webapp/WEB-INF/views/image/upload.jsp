<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
	<form method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" name="file" required>
		<input type="submit" value="전송">
	</form>
</body>
</html>