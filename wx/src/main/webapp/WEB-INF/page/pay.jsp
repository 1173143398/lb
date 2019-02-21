<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui.min.css"/>
<title>Insert title here</title>
</head>
<body>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="jsapiPay">jsapi调起支付</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="h5Pay">h5调起支付</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="getUserInfo">获取用户信息</a><br/>
	${openid}
	<script type="text/javascript">
	window.onload = function(){
		var baseCtx = "${pageContext.request.contextPath}";
		document.querySelector('#jsapiPay').onclick = function () {
	    	location.href = baseCtx + "/pay/authorize";
	  	};
	  	document.querySelector('#h5Pay').onclick = function () {
		  location.href = baseCtx + "/pay/h5paypage";
	  	};
	  	document.querySelector('#getUserInfo').onclick = function () {
		  location.href = baseCtx + "/pay/getuserinfo";
	  	};
	};
	</script>
</body>
</html>