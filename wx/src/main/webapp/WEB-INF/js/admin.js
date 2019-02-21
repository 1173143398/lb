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
	$("#refreshConfig").bind("click",function(){
		$.get("/wx/send",{"funcno":"00008"},
				function(data){
			alert(data);
		});
	});
	
	$("#createMenu").bind("click",function(){
		var t = $("#createMenuTxt").val();
		$.ajax({
			  type: 'POST',
			  url: "/wx/send?funcno=00003",
			  contentType:"application/json;charset=utf-8",
			  data: t,
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
	
	$("#getSandboxSignkey").bind("click",function(){
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