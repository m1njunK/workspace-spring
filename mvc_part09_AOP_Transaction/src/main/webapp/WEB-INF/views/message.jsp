<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message.jsp</title>
</head>
<body>
	<input type="text" id="targetid" placeholder="수신자아이디"/> <br/>
	<input type="text" id="sender" placeholder="발신자아이디"/> <br/>
	<input type="text" id="message" placeholder="전달할 메세지"/> <br/>
	<button id="ADD">SEND MESSAGE</button>
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		getMessageList();
		
		function getMessageList(){
			$.getJSON("messages/list",function(result){
				console.log(result);
			});		
		}	
	
		$("#ADD").click(function(){
			$.ajax({
				type : "POST",
				url : "messages/add",
				data : {
					targetid : $("#targetid").val(),
					sender : $("#sender").val(),
					message : $("#message").val()
				},
				dataType : "text",
				success : function(data){
					alert(data);
					$("#targetid").val("");
					$("#sender").val("");
					$("#message").val("");
					$("#targetid").focus();
				},
				error : function(res,status,error){
					alert(res.responseText);
				}
			});
		});
	</script>
</body>
</html>