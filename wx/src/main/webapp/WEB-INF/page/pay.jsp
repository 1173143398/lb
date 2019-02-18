<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/1.1.3/weui.min.css"/>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
<title>Insert title here</title>
</head>
<body>
	<a href="javascript:;" class="weui-btn weui-btn_primary" id="h5Pay">微信内H5调起支付</a><br/>
	<script type="text/javascript">
	 function onBridgeReady(){
		  if (typeof WeixinJSBridge == "undefined"){
			  alert("WeixinJSBridge undefined");
			  return ;
		  }
		   WeixinJSBridge.invoke(
		      'getBrandWCPayRequest', {
		         "appId":"${appId}",     //公众号名称，由商户传入     
		         "timeStamp":"${timeStamp}",         //时间戳，自1970年以来的秒数     
		         "nonceStr":"${nonceStr}", //随机串     
		         "package":"${package}",     
		         "signType":"${signType}",         //微信签名方式：     
		         "paySign":"${paySign}" //微信签名 
		      },
		      function(res){
		      if(res.err_msg == "get_brand_wcpay_request:ok" ){
		      // 使用以上方式判断前端返回,微信团队郑重提示：
		            //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
		      } 
		      alert(JSON.stringify(res));
		   }); 
		}
		
		
	  document.querySelector('#h5Pay').onclick = function () {
		  onBridgeReady();
		  };
	</script>
</body>
</html>