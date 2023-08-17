<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
	<h1>signIn.jsp</h1>
	<script>
		const msg = '${message}';
		if(msg != ''){
			alert(msg);
		}
	</script>
		<form action="signInPost" method="POST">
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
				<th colspan="2">
					<input type="checkbox" name="userCookie" id="userCookie"/>
					<label for="userCookie">로그인 정보 저장</label>
				</th>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Sign in"/>
					<button type="button" onclick="location.href='signUp';">Sign UP</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>