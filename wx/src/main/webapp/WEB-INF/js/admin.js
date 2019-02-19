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
					               "name":"网页授权",
					               "url":"http://u5qn9q.natappfree.cc/wx/authorize"
					           },
					           {    
					               "type":"view",
					               "name":"网页测试",
					               "url":"http://u5qn9q.natappfree.cc/wx/index"
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
			  url: "/wx/send?funcno=00007",
			  contentType:"application/json;charset=utf-8",
			  data: JSON.stringify({
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