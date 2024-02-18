<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.easy.util.Sys,java.io.*,javax.servlet.*, javax.servlet.http.*"%>
 <% //在jsp上设置根路径
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
  <title>layout 超大碗管理系统 - Layui</title>
  <style>
  .button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 18px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  color: #87cefa;
  background-color: #ffffff;
  border: 2px solid #008cba;
  border-radius: 8px;
  box-shadow: 0 9px #999;
}
  </style>
 <link href="static/layui/css/layui.css"rel="stylesheet">
</head>
<body>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black">超大碗管理系统</div>
    <!-- 头部区域（可配合layui 已有的水平导航） -->
  
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide layui-show-md-inline-block">
        <a href="javascript:;">
          <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img">
          <%= session.getAttribute(Sys.LOGIN_USER) %>
        </a>
        <dl class="layui-nav-child" >
         <a href="login.jsp" class="layui-btn" >退出</a>
          <dd><button  id = "change" class="layui-btn layui-btn-fluid"  >修改密码</button></dd>
         <dd><button  id = "zx" class="layui-btn layui-btn-fluid"  >注销</button></dd>
        </dl>
      </li>
      <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
        <a href="javascript:;">
          <i class="layui-icon layui-icon-more-vertical"></i>
        </a>
      </li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">管理列表</a>
          <dl class="layui-nav-child">
          <!-- 使用自定义属性,记录要跳转的路径 -->
          <dd><a href="javascript:;" _url="jx/class_list">菜系管理</a></dd>
            <dd><a href="javascript:;" _url="jx/menu_list">菜单管理</a></dd>
             <dd><a href="javascript:;" _url="jx/vip_list">会员管理</a></dd>
             <dd><a href="javascript:;" _url="jx/table_list">餐桌管理</a></dd>
            <dd><a href="javascript:;"_url="jx/bill_list">订单管理</a></dd>
           
            
            
          </dl>
        </li>
      
        
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
   
    
    <iframe src="first.jsp" style="border:none;height:98%;width:99%"></iframe>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    底部固定区域
  </div>
</div>
<div id="modifypwdlayer" style="display: none">
<form class="layui-form" action="">
 
  <div class="layui-form-item">
    <label class="layui-form-label">旧密码框</label>
    <div class="layui-input-inline">
      <input type="password" name="password" id = "old" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">6-16位字母数字下划线</div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">新密码框</label>
    <div class="layui-input-inline">
      <input type="password" name="password" id="new1" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">6-16位字母数字下划线</div>
  </div>
    <div class="layui-form-item">
    <label class="layui-form-label">新密码框</label>
    <div class="layui-input-inline">
      <input type="password" name="password" id="new2" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">6-16位字母数字下划线</div>
  </div>
  
</form>
</div>
<script src="static/layui/layui.js"></script>

<script>
//JS 
layui.use(['element', 'layer', 'util'], function(){
  var element = layui.element
  ,layer = layui.layer
  ,util = layui.util
  ,$ = layui.$  ;

  $("[_url]").click(function(){
	 var _url=$(this).attr("_url");
	 $("iframe").attr("src",_url);
  });
  
  $("#zx").click(function(){
	   layer.confirm('真的注销账号么',{icon:6,title:"提示"}, function(index){//提示用户确认删除
	    	  //将该行数据发送给servlet,servlet调用其他工具删除该数据
	    	  $.ajax({
	    		  url:"user/zx",
	    		  data:{
	    			 
	    		  },
	    	  success:function(result){
	    		  if(result){
	    			layer.alert("注销成功",{icon:1,time:2800});
	    			
	    			 localStorage.clear();
	    		       sessionStorage.clear();

	    			 window.location.href = 'login.jsp';      //跳转登录页
	    		  }else{
	    		 //关闭当前提示框
	    	        layer.close(index);}
	    	  }
	    	  });  
	    	  });
  });
  $("#change").click(function(){
	  showModifyLayer();
  });
  function showModifyLayer() {
	    let index = layer.open({
	        type: 1,
	        btn: ['取消','确定'],
	        title: "修改密码",
	        area: ["660px", "320px"],
	        content: $("#modifypwdlayer"),
	        //++enter
	        success: function(layero, index){
	            $(document).on('keydown', function(e){
	                if(e.keyCode == 13){
	                    deleteFile(index);
	                }
	            })
	           
	        }
	       
	       ,btn2: function (index) {
	    	var old=  $("#old").val();
	    	var new1=  $("#new1").val();
	    	var new2=  $("#new2").val();
	    	console.log(old+new1+new2);
	    	$.ajax({
	    		url:"user/change",
	    		data:{
	    			old:old,
	    			new1:new1,
	    			new2:new2
	    		},
	    		error:function(){
	    			alert("修改失败");
	    		},
	    		success:function(result){
	    			console.log(result);
	    			if(result=="success"){
	    				window. location. href = "login.jsp";
	    			}else{
	    				alert("修改失败");
	    			}
	    		}
	    		
	    	});
	        }
	    });
	}

  
  //头部事件
  util.event('lay-header-event', {
    //左侧菜单事件
    menuLeft: function(othis){
      layer.msg('展开左侧菜单的操作', {icon: 0});
    }
    ,menuRight: function(){
      layer.open({
        type: 1
        ,content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
        ,area: ['260px', '100%']
        ,offset: 'rt' //右上角
        ,anim: 5
        ,shadeClose: true
      });
    }
  });
  
});
</script>
</body>
</body>
</html>