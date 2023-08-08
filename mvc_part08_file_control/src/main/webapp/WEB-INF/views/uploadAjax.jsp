<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadAjax.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	.fileDrop{
		width:100%;
		height:200px;
		background-color:#ccc;
		border:1px solid black;
	}
</style>
</head>
<body>
	<h2>file drag &amp; drop</h2>
	<div class="fileDrop"></div>
	<div id="uploadList">
		
	</div>
	
	<script>
		// 파일 인식 할려는 브라우저의 기본 이벤트 무시
		$(".fileDrop").on("dragenter dragover",function(e){
			e.preventDefault();	
		});
		
		$(".fileDrop").on("drop",function(e){
			e.preventDefault();
			let files = e.originalEvent.dataTransfer.files;
			console.log(files);
			
			let formData = new FormData();
			
			for(let i = 0; i < files.length; i++){
				let file = files[i];
				
				let maxSize = 10485760; // 10MB
				
				if(maxSize < file.size){
					alert("업로드 할 수 없는 크기의 파일입니다.");
					return;
				}
				
				formData.append("files",file);
			}
			
			$.ajax({
				type : "POST",
				url : "uploadFiles",
				data : formData,
				processData : false, // 인코딩하지마라
				contentType : false,
				dataType : "json",
				success : function(result){
					// upload 된 파일 이름 List
					console.log(result);
				}
			});
			
		});
	</script>
</body>
</html>