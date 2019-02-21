<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<div style="text-align:center">
	<div id="output"></div>
	<div>${code_url}</div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.qrcode.min.js"></script>
<script>
jQuery(function(){
	$('#output').qrcode("${code_url}");
})
</script>
</body>
</html>