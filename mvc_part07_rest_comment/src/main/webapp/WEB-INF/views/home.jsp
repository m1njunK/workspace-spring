<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COMMENT TEST</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>	
	<h2>AJAX - REST TEST</h2>
	<div>
		<div>
			comment author : <input type="text" id="cAuth"/>
		</div>
		<div>
			comment content : <input type="text" id="cText"/>
		</div>
		<button id="addBtn">ADD COMENT</button>
	</div>
	<div>
		<!-- 댓글 리스트 -->
		<ul id="comments"></ul>
		
		<!-- 페이징 블럭 정보 -->
		<ul id="pagination"></ul>
		<br/><br/><br/><br/><br/><br/><br/><br/>
	</div>
	<script>
		var bno = 1;
		
		getCommentList();
		
		// 전체 댓글 목록 호출
		function getCommentList(){
			let url = "commnets/all/"+bno;
			// type == get
			// dataType == json
			$.getJSON(url,(data)=>{
				console.log(data);
			});
		}
		
		// 댓글 삽입 요청 처리
		$("#addBtn").click(()=>{
			let auth = $("#cAuth").val();
			let text = $("#cText").val();
			
			$.ajax({
				type : "POST",
				url : "comments",
				data : {
					bno : bno,
					commentText : text,
					commentAuth : auth
				},
				dataType : "text",
				success : (result)=>{
					alert(result);
				},
				error : function(res,status){
						console.log(res);
						console.log(status);
				}
			});
		});
	</script>
</body>
</html>