<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

	<h1>readPage.jsp</h1>
	<!-- board -->
	<table border="1">
	  <tr>
	  	<td>제목</td>
	  	<td><c:out value="${board.title}"/></td>
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
	<hr/>
	<div>
		<!-- 첨부파일 목록 추가 -->
		<ul class="uploadList">
			<c:if test="${!empty board.files}">
				<c:forEach var="file" items="${board.files}">
					<li data-src='${file}'>
						<c:choose>
							<c:when test="${fn:contains(file,'s_')}">
								<!-- 이미지 파일 -->
								<img src='${path}/displayFile?fileName=${file}'/>
								<div>
									<a href="${path}/displayFile?fileName=${fn:replace(file,'s_','')}" target='_blank'>
										${fn:substringAfter(fn:replace(file,'s_',''),'_')}
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<!-- 일반 파일 -->
								<img src='${path}/resources/img/file.png'/>
								<div>
									<a href="${path}/displayFile?fileName=${file}">
											${fn:substringAfter(file,'_')}
									</a>
								</div>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
			</c:if>		
		</ul>
	</div>
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
	<form id="readForm">
		<input type="hidden" name="bno" value="${board.bno}"/>
		<input type="hidden" name="csrf_token" value="${csrf_token}"/>
	</form>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		let obj = $("#readForm");
		// 게시글 목록 페이지 이동
		$("#listBtn").click(function(){
			location.href='listReply';
		});
		
		// 게시글 수정 페이지 요청
		$("#modifyBtn").click(function(){
			obj.attr("action","modify").submit();
		});
		
		// 답변글 작성 페이지 요청
		$("#replyBtn").click(function(){
			obj.attr("action","replyRegister").submit();			
		});

		$("#deleteBtn").click(function(){
			let isDeleted = confirm("첨부된 파일이 모두 삭제됩니다. 삭제하시겠습니까?");
			if(isDeleted){
				// 게시글 삭제 요청 처리
				// 첨부파일 삭제 처리 후
				let arr = [];
				$(".uploadList li").each(function(){
					// arr 배열에 li 요소의 data-src 속성값(파일) 이름 저장
					arr.push($(this).attr("data-src"));
				});
				console.log(arr);
				
				if(arr.length > 0){
					// ajax : type POST
					// $.post(URL,PARAMETERS,CALL BACK FUNCTION)
					$.post('${path}/deleteAllFiles',{files : arr}, function(result){
						alert(result);			
					});
				}
				// 게시글 삭제 처리
				obj.attr("action","remove");
				obj.attr("method","POST");
				obj.submit();	
			}else{
				alert('삭제 요청이 취소되었습니다.');	
			}
		});
	</script>
	<br/>
	<br/>
	<hr/>
<%@ include file="../comment/home.jsp" %>
</body>
</html>