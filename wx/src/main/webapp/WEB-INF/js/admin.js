(function($){
	$("#getAccessToken").bind("click",function(){
		$.get("/wx/send",{"funcno":"00000"},
				  function(data){
				    alert(data);
				  });
	});
	
	$("#getJsapiTicket").bind("click",function(){
		$.get("/wx/send",{"funcno":"00004"},
				  function(data){
				    alert(data);
				  });
	});
	
	$("#createMenu").bind("click",function(){
		$.ajax({
			  type: 'POST',
			  url: "/wx/send?funcno=00003",
			  contentType:"application/json;charset=utf-8",
			  data: JSON.stringify({
					"button":[
					          {    
					               "type":"view",
					               "name":"测试",
					               "url":"http://ia4m6n.natappfree.cc/wx/index"
					           }]
				}),
			  success: function(data){
				  alert(data);
			  },
			  error : function(){
				  alert("error");
			  }
			 
			});
	});
	
	$("#deleteMenu").bind("click",function(){
		$.get("/wx/send",{"funcno":"00005"},
				  function(data){
				    alert(data);
				  });
	});
	
	$("#wxPay").bind("click",function(){
		$.ajax({
			  type: 'POST',
			  url: "/wx/send?funcno=00006",
			  contentType:"application/json;charset=utf-8",
			  data: JSON.stringify({
					"fee_type":"CNY",
					"total_fee":"1",
					"spbill_create_ip":"192.168.0.1",
					"trade_type":"JSAPI",
					"product_id":"T20190217",
					"openid":"123"
				}),
			  success: function(data){
				  alert(data);
			  },
			  error : function(){
				  alert("error");
			  }
			 
			});
	});
})(jQuery);