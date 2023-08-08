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
					let str = "";
					$(result).each(function(){
						console.log(this);
						if(checkImageType(this)){
							console.log("이미지 파일");
						}else{
							console.log("일반 파일");
						}
					});
				}
			});
		});
		
		// 업로드된 파일이 이미지 파일인지 확인
		function checkImageType(fileName){
			let pattern = /jpg|jpeg|gif|png/i;
			let result = fileName.match(pattern);
			console.log(result ? '이미지' : '안이미지');
			
			let img = ['jpg','jpeg','png','gif'];
			for(let i = 0; i < img.length; i++){
				if(fileName.toLowerCase().endsWith(img[i])){
					return true;
				}	
			}
			return false;
		}		
		
	</script>
</body>
</html>