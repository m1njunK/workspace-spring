<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>javascript.jsp</title>
</head>
<body>
	<h1>자바 스크립트</h1>
	<input type="button" id="continue_btn" value="continue_btn"/>
	<div id="box1"></div>
	<div id="box2"></div>
	
	<script>
	/*
		AJAX란 비동기 자바스크립트와 XML(Asynchronous JavaScript And Xml)
		javascript를 이용하여 서버와 통신하기 위한 XMLHttpRequest 객체를 사용하는것
	*/
	
	// click 이벤트 처리 추가
	document.getElementById("continue_btn").onclick = ()=>{
		makeRequest("getSample");
	}
	
	//	XMLHttpRequest를 저장하기 위한 변수 선언
	var httpRequest;
	
	// 요청 URL을 전달 받아 요청과 응답에 대한 처리를 수행할 함수 선언
	function makeRequest(url){
		httpRequest = new XMLHttpRequest();
		if(!httpRequest){
			alert("AJAX 통신 불가");
			return false;
		}
		
		// 요청 전달 단계에 따라 결과를 처리할 함수 지정
		httpRequest.onreadystatechange = setContents;
		// true - 비동기, false - 동기 : 순차처리
		httpRequest.open('GET',url+"?name=안녕&age=15",false);
		// server에 요청
		httpRequest.send();
		console.log("send 호출 완료");
		
	}	
	
	// 요청 처리 상태에 따른 처리를 할 함수
	function setContents(){
		console.log(httpRequest.readyState);
		/*
			0(uninitialized) - (request가 초기화 되지 않음)
			1(loading)		 - (서버와의 연결이 성사됨)
			2(loaded)		 - (서버가 request를 받음)
			3(interactive)   - (request(요청)을 처리하는 중)
			4(complate)		 - (request에 대한 처리가 끝났으면 응답 받은 데이터를 사용할 준비가 완료됨)
		*/
		if(httpRequest.readyState === 4){
			if(httpRequest.status === 200){
				
				let str = httpRequest.responseText;
				console.log(str);
				document.getElementById("box1").innerHTML = str;
				// JSON 형식으로 작성된 문자열을 Javascript Object로 변환
				let obj = JSON.parse(str);
				console.log(obj);
				console.log(obj.sampleVO);
				console.log(obj.sample);
				let html = "<table border='1'>";
				html += "<tr><th>이름</th><th>나이</th></tr>"
				html += `<tr><td>\${obj.sampleVO.name}</td><td>\${obj.sampleVO.age}</td></tr>`;
				html += `<tr><td>\${obj.sample.name}</td><td>\${obj.sample.age}</td></tr>`;
				html += "</table>";
				document.getElementById("box2").innerHTML = html;
			}
		}
	}
	</script>
</body>
</html>