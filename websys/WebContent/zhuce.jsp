<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*"%>
<% //在jsp上设置根路径
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<link  rel="stylesheet" href="static/css/weekwork.css"/>
</head>
<body>



<div class="login_box">

	<form action="user/add" method="get">
		
		<div class="title">超大碗系统</div>
		<div class="input_box">
			<input type="text" name="username" placeholder="请输入用户名" />
			</div>
			<div class="input_box" >
				<input type="password" name="password" placeholder="请输入密码" />
			</div>
			
					
			<div class="button_box">
				
		
				<button  type="submit" name="zc">注册</button>
			</div>
		
		
		</form>
	
	</div>
 <script src="./static/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
/*ajax 异步的Javascript和xml 修改或刷新页面的部分页面*/
 
 //#是id选择器,建议使用属性选择器
 $("[name='dl']").click(function () {
	//获取输入框的内容
	var text=$("[name='username']").val();
	//将该内容发生到服务器
	$.ajax({
		url:'logina',//地址
		data:{checkname:text},//传递给服务器的参数  checkname是字符串(省略了)属性名,text是变量
		success:function(result){//接收服务器返回的数据进行判断
			if(result==0){
				alert("用户名可以使用")
			}else if(result==1){
				alert("用户名违规")
			}
		
		},//成功访问服务器后要执行的代码
		error:function(){
			alter("服务器开小车了")
		}//请求失败 404 500 503
	});
	//处理服务器返回的结果
});

 $("input[name='username']").blur(function(){
		checkUserName();
	});
	$("button[name='dl']").click(function(){
		checkUserName();
		checkPassword();
	});													
		
	$("input[name='password']").blur(function(){
		
		checkPassword();
	});
	
function checkUserName(){
	//$("input[name='username']")找到组件对应的属性名,并获取username的值,用于判断命名是否规范
	var user_name=$("input[name='username']").val();
	console.log();
	var user_name_pattern=/^[a-zA-Z0-9_-]{6,10}$/;
	console.log(user_name_pattern);
	if(user_name_pattern.test(user_name)){
		return true;
		
	}else{
		//弹出窗口提示
		alert("用户名的长度必须在6-10之间")
		return false;
	}
	
		
	
}
function checkPassword(){
	var user_password=$("input[name='password']").val();
	var user_password_pattern=/^[a-zA-Z0-9_-]{6,16}$/;
	if(user_password_pattern.test(user_password)){
return true;
		
	}else{
		//弹出窗口提示
		alert("用户名的密码必须在6-16之间")
		return false;
	}
	
		
	
}
</script>

</body>
</html>