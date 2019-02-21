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
	<form name="payForm" action="${pageContext.request.contextPath}/pay/nativepay">
	<div class="weui-cells weui-cells_form">
           <div class="weui-cell">
               <div class="weui-cell__hd"><label class="weui-label">feeType</label></div>
               <div class="weui-cell__bd">
                   <input class="weui-input" name="feeType" type="text" value="CNY">
               </div>
           </div>
           <div class="weui-cell">
               <div class="weui-cell__hd"><label class="weui-label">totalFee</label></div>
               <div class="weui-cell__bd">
                   <input class="weui-input" name="totalFee" type="text" value="101">
               </div>
           </div>
           <div class="weui-cell">
               <div class="weui-cell__hd"><label class="weui-label">spbillCreateIp</label></div>
               <div class="weui-cell__bd">
                   <input class="weui-input" name="spbillCreateIp" type="text" value="192.168.0.1">
               </div>
           </div>
           <div class="weui-cell">
               <div class="weui-cell__hd"><label class="weui-label">tradeType</label></div>
               <div class="weui-cell__bd">
                   <input class="weui-input" name="tradeType" type="text" value="NATIVE">
               </div>
           </div>
           <div class="weui-cell">
               <div class="weui-cell__hd"><label class="weui-label">productId</label></div>
               <div class="weui-cell__bd">
                   <input class="weui-input" name="productId" type="text" value="T20190218">
               </div>
           </div>
           <div class="weui-cell">
               <div class="weui-cell__hd"><label class="weui-label">openid</label></div>
               <div class="weui-cell__bd">
                   <input class="weui-input" name="openid" type="text" value="${openid}">
               </div>
           </div>
	</div>
	<div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips">确定</a>
    </div>
	</form>
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
	  	document.getElementById('showTooltips').onclick = function(){
			document.forms['payForm'].submit(); 
		};
	};
	</script>
</body>
</html>