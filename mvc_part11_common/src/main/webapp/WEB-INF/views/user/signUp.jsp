<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
	<h1>signUp.jsp</h1>
	<form action="signUpPost" method="POST">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="uid" placeholder="USER ID" required/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="upw" placeholder="USER PASS" required/>
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type="password" name="rePw" placeholder="USER REPW" required/>
				</td>
			</tr>
			<tr>
				<td>사용자 이름</td>
				<td>
					<input type="text" name="uname" placeholder="USER NAME" required/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Sign Up"/>
					<button type="button" onclick="location.href='signIn';">Sign IN</button>
				</td>
			</tr>
		</table>
	</form>
	<script>
		const msg = '${message}';
		if(msg != ''){
			alert(msg);
		}
	</script>
</body>
</html>