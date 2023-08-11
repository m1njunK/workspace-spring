<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message.jsp</title>
<style>
	#messageList{
		margin-left:0;
	}
	
	#messageList li{
		list-style: none;
		border: 1px solid black;
		padding: 5px;
		height: 50px;
		margin-bottom: 5px;
	}
	
	#messageList li:hover{
		cursor:pointer;
	}
</style>
</head>
<body>
	<input type="text" id="targetid" placeholder="수신자아이디"/> <br/>
	<input type="text" id="sender" placeholder="발신자아이디"/> <br/>
	<input type="text" id="message" placeholder="전달할 메세지"/> <br/>
	<button id="ADD">SEND MESSAGE</button> <br/>
	
	<ul id="messageList">
		
	</ul>
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		getMessageList();
		
		function getMessageList(){
			$.getJSON("messages/list",function(result){
				console.log(result);
				let str = "";
				$(result).each(function(){
					console.log(this);
					let senddate = getDate(this.senddate);
					console.log(senddate);
					console.log(this.opendate);
					let opendate = '';
					if(this.opendate != null){
						opendate = getDate(this.opendate);
					}else{
						opendate = "미확인";
					}
					console.log(opendate);
					str += `<li id='li\${this.mno}' onclick='readMessage(\${this.mno},"\${this.targetid}",this)'>`;
					str += `\${this.mno} | \${this.targetid} | \${this.sender} `;
					str += `| \${this.message} | \${opendate} | \${senddate}`;
					str += "</li>";
				});
				$("#messageList").html(str);
			});		
		}	
		
		function readMessage(mno, uid, element){
			console.log(mno,uid,element);
			$.ajax({
				type : "PATCH",
				url : "messages/read/"+mno+"/"+uid,
				dataType : "JSON",
				success : function(data){
					// data == MessageVO
					let str = `\${data.mno} | \${data.targetid} | \${data.sender} `;
					str += `| \${data.message} | \${getDate(data.opendate)} | \${getDate(data.senddate)}`;
					$(element).html(str);
				},
				error : function(res){
					alert(res.responseText);
				}
			})
		}
		
		function getDate(date){
			// new Date(); 현재 시간
			// new Date(time); 매개변수로 전달된 시간
			let dateTime = new Date(date);
			let dateStr = dateTime.getFullYear()+"년 ";
			dateStr += (dateTime.getMonth()+1)+"월 ";
			dateStr += dateTime.getDate()+"일 ";
			dateStr += dateTime.getHours()+"시 ";
			dateStr += dateTime.getMinutes()+"분 ";
			dateStr += dateTime.getSeconds()+"초";
			return dateStr;
		};
	
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
					getMessageList();
				},
				error : function(res,status,error){
					alert(res.responseText);
				}
			});
		});
	</script>
</body>
</html>