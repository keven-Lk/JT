$(function(){
	//为adduser添加点击事件 #id div .class
	$("#addUser").click(function(){
		//获取id标签的值
		var nameVal = $("#name").val();
		var age = $("#age").val();
		var sex = $("#sex").val();
		
		$.get("http://localhost:8091/getAddUser",
			{name:nameVal,age:ageVal,sex:sexVal},
			function(data){
				if(data.status == 200){
					alert(data.msg);
				}else{
					alert(data.msg);
				}
			},
			"json"
		);
		
//		String url = "http://localhost:8091/addUser";
		//测试$.post("请求地址","请求参数",回调函数,返回值)
//		$.post(url,name:nameVal,age:ageVal,serx:sexVal),
//		function(data){
//			//data服务器返回的数据.
//			if(data.status == 200){
//				alert(data.msg);
//			}else{
//				alert(data.msg);
//			}
//		},"json");
		
		//ajax提交
//		$.ajax({
//			url:url,	//定义url地址
//			data:{name:nameVal,age:ageVal,serx:sexVal},
//			method:"post",
//			dataType:"json",//表示返回值类型为json
//			success:function(data){
//				//data服务器返回的数据.
//				if(data.status == 200){
//					alert(data.msg);
//				}else{
//					alert(data.msg);
//				}
//			}
//			
//		})
	});
	
})