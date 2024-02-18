<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% //在jsp上设置根路径
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="static/layui/css/layui.css"rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
    <!--修改密码-->
    <div class="modify-pwd-layer aux-self" id="modifypwdlayer" style="display: none">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">原密码</label>
                <div class="layui-input-block">
                    <input id="mopwd" type="password" name="title" required  lay-verify="required" autofocus placeholder="请输入原密码" autocomplete="off" class="layui-input">
                </div>
                <div id="mopwd-aux" class="aux-self-word"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">新密码</label>
                <div class="layui-input-block">
                    <input id="newpwd1" type="password" name="title" required  lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
                </div>
                <div id="newpwd1-aux" class="aux-self-word"></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">再次输入</label>
                <div class="layui-input-block">
                    <input id="newpwd2" type="password" name="title" required  lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
                </div>
                <div id="newpwd2-aux" class="aux-self-word"></div>
            </div>
        </form>
    </div>
  
  <!-- 表格的行内按钮 -->
 <script type="text/html" id="in_table">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="detail">详情</a>
</script>



<script src="static/layui/layui.js"></script>

<script>
var closeopen;
layui.use(['table'], function(){
	closeopen=function(){
		layer.closeAll();
	}
  var table = layui.table;
  var form=layui.form;
  var $=layui.$; //内置jQuery
/*表格头部按钮工具栏事件*/
table.on("toolbar(form)",function(obj){
	//点击按钮的lay-event属性
	var event=obj.event;
	  if(event=="query"){
		//点击了查询按钮
		//获取文本框中的内容
		var text_name=$("#topbox [name='name']").val();
		
		console.log(text_name);
		//根据文本内容查询
		table.reload("form",{where:{//当点击查询时,向Java服务端程序发送name,重新装载页面,没有这两个数据时既是全搜索
			vname:text_name,
			
		},page:{curr:1}//重载表格时,默认从第一页开始
		});
		//将查询的数据重写到查询输入框中
		var text_name=$("#topbox [name='name']").val(text_name);
	}	
});


   //监听工具条,表格行内按钮处理
  table.on('tool(form)', function(obj){
	  //得到监听对象的数据
    var data = obj.data;
	  console.log(data);
	  var base=$("base").attr("href");
	  
		if(obj.event=="detail"){
			console.log(data.bid);
			
			
			layer.open({
				
				title:"明细",
				type:2,
				/*设置大小*/
				area : [ '50%', '80%' ],
				//弹出层的内容
				content:"jx/detail_list",
			});
            $.ajax({
	    		 
	    		  url:"detail/layuilist",
	    		
	    		  data:data,
	    		  
	    	
	    	  }); 
		}
	  else if(obj.event === 'del'){//点击删除按钮后,要执行的代码,事件是删除时
    	//业务逻辑
      layer.confirm('真的删除行么',{icon:6,title:"提示"}, function(index){//提示用户确认删除
    	  //将该行数据发送给servlet,servlet调用其他工具删除该数据
    	  $.ajax({
    		  
    		  url:"bill/deletelist",
    		  data:{
    			  bid:data.bid,
    		  },
    	  success:function(result){
    		  if(result==1){
    			layer.alert("删除成功",{icon:1,time:2800});
    			//刷新界面//或
    			obj.del();
    		  }else{
    		 //关闭当前提示框
    	        layer.close(index);}
    	  }
    	  });  
    	  });
      } 
      });
});
 
  

</script>



</body>
</html>