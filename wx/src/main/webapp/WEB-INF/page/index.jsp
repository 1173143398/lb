<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/1.1.3/weui.min.css"/>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<title>Insert title here</title>
</head>
<body>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="getUrl">getUrl</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="checkJsApi">判断当前客户端是否支持指定JS接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="onMenuShareTimeline">获取“分享到朋友圈”按钮点击状态及自定义分享内容接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="onMenuShareAppMessage">获取“分享给朋友”按钮点击状态及自定义分享内容接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="onMenuShareQQ">获取“分享到QQ”按钮点击状态及自定义分享内容接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="onMenuShareWeibo">获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="chooseImage">拍照或从手机相册中选图接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="previewImage">预览图片接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="uploadImage">上传图片接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="downloadImage">下载图片接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="startRecord">开始录音接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="stopRecord">停止录音接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="playVoice">播放语音接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="pauseVoice">暂停播放接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="stopVoice">停止播放接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="uploadVoice">上传语音接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="downloadVoice">下载语音接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="translateVoice">识别音频并返回识别结果接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="getNetworkType">获取网络状态接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="openLocation">使用微信内置地图查看位置接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="getLocation">获取地理位置接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="hideOptionMenu">隐藏右上角菜单接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="showOptionMenu">显示右上角菜单接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="closeWindow">关闭当前网页窗口接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="hideMenuItems">批量隐藏功能按钮接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="showMenuItems">批量显示功能按钮接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="hideAllNonBaseMenuItem">隐藏所有非基础按钮接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="showAllNonBaseMenuItem">显示所有功能按钮接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="scanQRCode0">调起微信扫一扫接口scanQRCode(微信处理结果)</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="scanQRCode1">调起微信扫一扫接口scanQRCode(直接返回结果)</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="openProductSpecificView">跳转微信商品页接口</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="chooseWXPay">发起一个微信支付请求</a><br/>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="h5Pay">微信内H5调起支付</a><br/>
	<form action="${pageContext.request.contextPath}/pay">
		<input name="feeType" value="CNY"><br/>
		<input name="totalFee" value="1"><br/>
		<input name="spbillCreateIp" value="192.168.0.1"><br/>
		<input name="tradeType" value="JSAPI"><br/>
		<input name="productId" value="T20190218"><br/>
		<input name="openid" value="${openid}"><br/>
	</form>
	<script type="text/javascript">
	  wx.config({
	      debug: true,
	      appId: '${appId}',
	      timestamp: '${timestamp}',
	      nonceStr: '${nonceStr}',
	      signature: '${signature}',
	      jsApiList: [
	        'checkJsApi',
	        'onMenuShareTimeline',
	        'onMenuShareAppMessage',
	        'onMenuShareQQ',
	        'onMenuShareWeibo',
	        'onMenuShareQZone',
	        'hideMenuItems',
	        'showMenuItems',
	        'hideAllNonBaseMenuItem',
	        'showAllNonBaseMenuItem',
	        'translateVoice',
	        'startRecord',
	        'stopRecord',
	        'onVoiceRecordEnd',
	        'playVoice',
	        'onVoicePlayEnd',
	        'pauseVoice',
	        'stopVoice',
	        'uploadVoice',
	        'downloadVoice',
	        'chooseImage',
	        'previewImage',
	        'uploadImage',
	        'downloadImage',
	        'getNetworkType',
	        'openLocation',
	        'getLocation',
	        'hideOptionMenu',
	        'showOptionMenu',
	        'closeWindow',
	        'scanQRCode',
	        'chooseWXPay',
	        'openProductSpecificView',
	        'addCard',
	        'chooseCard',
	        'openCard'
	      ]
	  });
	  
	</script>
</body>
</html>