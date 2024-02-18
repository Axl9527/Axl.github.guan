<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*"%>
<% //在jsp上设置根路径
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>超大碗登录界面</title>

<link  rel="stylesheet" href="static/css/weekwork.css"/>
</head>
<body>
<%
		String username = "";
		String password = "";
		//用于记住复选框,默认勾选,如果没有接收到值就代表登录时没有选择记住复选框
		boolean b=true;
		//获取cookie的数据存在数组中
		Cookie[] c = request.getCookies();
		//数组不为空执行
		if (c != null) {
		//遍历数组
			for (int i = 0; i < c.length; i++) {
				//如果名称符合就赋给对应的值
				if ("username".equals(c[i].getName())) {
					username = c[i].getValue();
				} else if ("password".equals(c[i].getName())) {
					password = c[i].getValue();
					
					if("".equals(c[i].getValue())){
						b=false;
					}
				}
			}
		}
	%>


<div class="login_box">

	<form action="${pageContext.request.contextPath}/logina" method="post" class="layui-form">
	
		<div class="title">超大碗系统</div>
		<div class="input_box">
			<input type="text" name="username" placeholder="请输入用户名" value="<%=  username  %>"/>
			</div>
			<div class="input_box" >
				<input type="password" name="password" placeholder="请输入密码" value="<%=  password  %>"/>
			</div>
			    <div class="layui-form-item">
            <div class="layui-row">
                <div class="layui-col-xs7">
                    <div class="layui-input-wrap">
                        <div class="layui-input-prefix">
                            <i class="layui-icon layui-icon-vercode"></i>
                        </div>
  
                        
                   <img name="code" src="${pageContext.request.contextPath}/vercode/creat" onclick="this.src='${pageContext.request.contextPath}/vercode/creat?t='+ new Date().getTime();">
                    <input type="text" name="vercode"  id ="vercode" >
        </div>
          </div>
                </div>
            </div>
			<div class="remember_box"><!-- 复选框 -->
			<input id="remember" type="checkbox" name="remember" value="1"   <%=b?"checked":"" %>/>
			<label for="remember">记住密码</label>	
					</div>
					
			<div class="button_box">
				<button  type="button" name="dl">登录</button>	
		
				<button  type="button" name="zc">注册</button>
			</div>
		
		
		</form>
	
	</div>
 <script src="./static/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
/*ajax 异步的Javascript和xml 修改或刷新页面的部分页面*/
 
 

 
$("[name='zc']").click(function () {
	window. location. href = "zhuce.jsp";

	
});



 $("input[name='username']").blur(function(){
		//checkUserName();
 });
	$("button[name='dl']").click(function(){
		if(!checkUserName()||!checkPassword()||!check()||!check1()){
			console.log(checkUserName()+checkPassword()+check());
			return false;
		}else{
			
			window. location. href = "jx/buju";
			console.log("d打印一些大象");
		}
		
	});													

	function check() {
	var a;
		//获取用户输入的验证码
		var input_code = $("#vercode").val();
		console.log(input_code);
		
		$.ajax({
			url:"vercode/check",
			async: false,
			data:{
				vercode:input_code
			},
			success:function(result){
				a=result;
			
			}
		});
		if(a=="success"){
			
			return true;
		}else{
			
			alert("请输入正确的验证码!");
			window. location. href = "login.jsp";
			return false;
		}

	};
	function check1() {
		var b;
		var user_name=$("input[name='username']").val();
		var user_password=$("input[name='password']").val();
		//var user_remember=$("input[name='remember']").val();
		var user_remember= $('input[name="remember"]:checked').val();
		console.log(user_remember+"jizhu");
		console.log(user_name);
			$.ajax({
				url:"logina",
				async: false,
				data:{
					username:user_name,
					password:user_password,
					remember:user_remember
				},
				
				success:function(result){
					b=result;
					console.log(b+"denglu");
				}
			});
			if(b=="success"){
				console.log("登陆成功");
				return true;
			}else{
				alert("请输入正确的用户名或密码!");
				return false;
			}
			

		};
function checkUserName(){
	//$("input[name='username']")找到组件对应的属性名,并获取username的值,用于判断命名是否规范
	var user_name=$("input[name='username']").val();
	
	var user_name_pattern=/^[a-zA-Z0-9_-]{6,10}$/;
	console.log(user_name_pattern);
	if(user_name_pattern.test(user_name)){
		return true;
		
	}else{
		
		alert("用户名的长度必须在6-10之间");
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
		alert("用户名的密码必须在6-16之间");
		return false;
	}
	
		
	
}
</script>
<!-- 
 <script type="text/javascript">

//#是id选择器,建议使用属性选择器
$("[name='dl']").click(function () {
	//获取输入框的内容
	var text=$("[name='password']").val();
	//将该内容发生到服务器
	$.ajax({
		url:'testlogin',//地址
		data:{checkpass:text},//传递给服务器的参数  checkname是字符串(省略了)属性名,text是变量
		success:function(result){//接收服务器返回的数据进行判断
			if(result==0){
				alert("密码可以使用")
			}else if(result==1){
				alert("密码违规")
			}
		
		},//成功访问服务器后要执行的代码
		error:function(){
			alter("服务器开小车了")
		}//请求失败 404 500 503
	});
	//处理服务器返回的结果
});


</script> -->

</body>
</html>