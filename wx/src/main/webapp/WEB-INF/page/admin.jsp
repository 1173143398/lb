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
<div>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="getAccessToken">获取access_token</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="getJsapiTicket">获取jsapi_ticket</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="refreshConfig">刷新配置</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="createMenu">创建菜单</a><br/>
	<textarea class="weui-textarea" placeholder="请输入文本" rows="10" id="createMenuTxt">
	{
		"button":[
		          {    
		               "type":"view",
		               "name":"pay",
		               "url":"http://i9uae9.natappfree.cc/wx/pay"
		           },
		           {    
		               "type":"view",
		               "name":"index",
		               "url":"http://i9uae9.natappfree.cc/wx/index"
		           }]
	}
	</textarea>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="deleteMenu">删除菜单</a><br/>
	<a class="weui-btn weui-btn_primary" href="javascript:" id="getSandboxSignkey">获取支付沙箱signkey</a><br/>
	
	
</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</body>
</html>