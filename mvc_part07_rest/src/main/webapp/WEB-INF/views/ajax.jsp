<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js">

</script>
</head>
<body>
	<div style="background-color:#ccc; height:900px;"></div>
	<div>
		<input type="text" id="name" /> <br/>
		<input type="number" id="age" /> <br/>
		<input type="button" id="submit" value="get Submit"/> <br/>
		<input type="button" id="post" value="post Submit"/> <br/>
		<input type="button" id="put" value="put Submit"/> <br/>
	</div>
	<div id="result" style="border:1px solid black;padding:10px;margin-top:10px;"></div>
	<div style="height:300px;"></div>
	
	<script>
		$("#submit").click(()=>{
			let input_name = $("#name").val();
			let input_age = $("#age").val();
			
			let obj = {
				type : 'GET',		// 전송 방식
				async : true,		// 비동기,동기 제어
				url : "getSample",			// 요청 경로
				data : {			// 전달 파라미터
					name : input_name,
					age : input_age
				},
				dataType : "json",	// responseText를 어떤 타입으로 사용할건지
				// text, json, xml
				success : (data)=>{	
					// 응답이 정상적으로 실행 완료되었을때 실행될 함수
					console.log(data);
					let html = "<table border='1'>";
					html += "<tr><th>이름</th><th>나이</th></tr>"
					html += `<tr><td>\${data.sampleVO.name}</td><td>\${data.sampleVO.age}</td></tr>`;
					html += `<tr><td>\${data.sample.name}</td><td>\${data.sample.age}</td></tr>`;
					html += "</table>";
					$("#result").html(html);
				},
				error : function(res){
					console.log(res);
				}
			};
			$.ajax(obj);	
		});
		// id가 put인 요소의 클릭 이벤트 처리
		$("#put").click(()=>{
			$.ajax({
				type : "PUT",
				url : "getSample2",
				dataType : "json",
				// PUT, PATCH, DELETE 방식으로 데이터가 전달 될때
				headers : {
					'Content-Type' : 'application/json'
				},
				data : JSON.stringify({
					name : $("#name").val(),
					age : $("#age").val()
				}),
				success : (data)=>{
					console.log(data);
					// data == Array();
					let html = "<table border='1'>";
					html += "<tr><th>이름</th><th>나이</th></tr>"
					for(let i = 0; i < data.length; i++){
						html += `<tr><td>\${data[i].name}</td><td>\${data[i].age}</td></tr>`;
					}
					html += "</table>";
					$("#result").append(html);
				}
			});
		})
	</script>
</body>
</html>