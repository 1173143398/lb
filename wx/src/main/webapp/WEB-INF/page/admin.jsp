<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/1.1.3/weui.min.css"/>
<title>Insert title here</title>
</head>
<body>
<div style="width:40%;margin: auto">
	<a class="weui-btn weui-btn_primary" href="javascript:" id="getAccessToken">获取access_token</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="getJsapiTicket">获取jsapi_ticket</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="refreshConfig">刷新配置</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="createMenu">创建菜单</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="deleteMenu">删除菜单</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="getSandboxSignkey">获取支付沙箱signkey</a><br/>
	
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
</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
	<script type="text/javascript">
	window.onload = function(){
		document.getElementById('showTooltips').onclick = function(){
			document.forms['payForm'].submit(); 
		};
	};
	</script>
	
</body>
</html>