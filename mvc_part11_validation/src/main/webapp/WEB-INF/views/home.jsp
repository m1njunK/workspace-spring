<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>
	<h3>MAIN HOME</h3>
	
	<%-- <h2><a href="${path}/mailTest">mailTest</a></h2> --%>
	<div>
		<button id="sendOne">send-one</button>
		<button id="balance">get-balance</button>
	</div>
	<script>
		document.getElementById("sendOne").addEventListener("click", function() {
			ajaxSendCheck('send-one','POST');
		});
		
		document.getElementById("balance").addEventListener("click", function() {
			ajaxSendCheck('get-balance','GET');
		});
	
			
		function ajaxSendCheck(url,method) {
		    var xhr = new XMLHttpRequest();
	
		    xhr.onreadystatechange = function() {
		        if (xhr.readyState === XMLHttpRequest.DONE) {
		            if (xhr.status === 200) {
		                var result = xhr.responseText;
		                console.log(result);
		            }
		        }
		    };
	
		    xhr.open(method, url, true);
		    xhr.send();
		}
	</script>
</body>
</html>

