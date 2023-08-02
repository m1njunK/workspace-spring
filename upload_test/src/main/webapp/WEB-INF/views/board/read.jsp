<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>read.jsp</title>
</head>
<body>
	<h3>READ BAORD</h3>
	<!-- model boardVO -->
	<div>
			<label>TITLE</label>
			<input type="text" name="title" value="${boardVO.title}" readonly/>
		</div>
		<div>
			<label>CONTENT</label>
			<textarea name="content" readonly rows=3>${boardVO.content}</textarea>
		</div>
		<div>
			<label>WRITER</label>
			<input type="text" name="writer" value="${boardVO.writer}" readonly/>
		</div>
		<div>
			<a href="" id="modify">MODIFY</a> |
			<a href="" id="remove">DELETE</a> |
			<a href="" id="list">LIST</a>
		</div>
		<form id="submitForm" method="POST">
			<input type="hidden" name="bno" value="${boardVO.bno}" />
			<input type="hidden" name="page" value="${cri.page}" />
			<input type="hidden" name="perPageNum" value="${cri.perPageNum}" />
		</form>
	<script>
		var result = '${result}';
		if(result != null && result != ''){
			alert(result);
		}
	</script>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		// 문서가 모두 로드 되었을 때...
		$(function(){
			
			var formObj = $("#submitForm");
			
			$("#list").click(function(e){
				e.preventDefault();
				$("input[name='bno']").remove();
				formObj.attr("action","listPage");
				formObj.attr("method","GET");
				formObj.submit();
			});
			
			// 수정 페이지 요청 
			$("#modify").click(function(e){
				e.preventDefault();
				formObj.attr("action","modify");
				formObj.attr("method","get");
				formObj.submit();
			});
			
			// 게시글 삭제요청
			$("#remove").click((e)=>{
				e.preventDefault();
				let conf = confirm("복구못한다. 삭제?");
				if(conf){
					formObj.attr("action","remove");
					formObj.submit();
				}
			});
		});
	</script>
</body>
</html>




