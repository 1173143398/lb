<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	
	请输入：
	<input id="url" value="ws://localhost:8080/wx/websocket"><br/>
	<textarea rows="5" cols="10" id="inputMsg" name="inputMsg"></textarea><br/>
	<button onclick="doSend();">发送</button>
	<button onclick="close();">关闭</button>
	<script type="text/javascript"
		src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
	<script type="text/javascript"
		src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script>
	<script type="text/javascript">
		var websocket = null;
		var url = document.getElementById("url").value;
		if ('WebSocket' in window) {
			console.log("WebSocket");
			websocket = new WebSocket(url);
		} else if ('MozWebSocket' in window) {
			console.log("MozWebSocket");
			websocket = new MozWebSocket(url);
		} else {
			console.log("SockJS");
			websocket = new SockJS(url);
		}
		websocket.onopen = onOpen;
		websocket.onmessage = onMessage;
		websocket.onerror = onError;
		websocket.onclose = onClose;

		function onOpen(openEvt) {
			console.log("websocket open");
		}

		function onMessage(evt) {
			console.log("websocket message:"+evt.data);
		}
		function onError() {
			console.log("websocket error");
		}
		function onClose() {
			console.log("websocket close");
		}

		function doSend() {
			if (websocket.readyState == websocket.OPEN) {
				var msg = document.getElementById("inputMsg").value;
				websocket.send(msg);//调用后台handleTextMessage方法
				alert("发送成功!");
			} else {
				alert("连接失败!");
			}
		}
		function close() {
			websocket.onclose();
		}
	</script>
</body>
</html>