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
<!-- toolbar属性将script的组件加入表格中,放在table上就是在表格的最上面(查询按钮),放到table里就是一行数据按钮(删除按钮) -->
<table class="layui-table" lay-data="{ url:'vip/layuilist', toolbar:'#toptools',page:true, id:'form'}" lay-filter="form">
  <thead>
    <tr>
    <th lay-data="{type:'numbers'}">序号</th>
      <th lay-data="{field:'vid' type:'hidden'}">ID</th>
      <th lay-data="{field:'vname' }">用户名</th>
      <th lay-data="{field:'vsex'}">性别</th>
      <th lay-data="{field:'vdiscount'}">会员折扣</th>
      <th lay-data="{field:'vphone'}">手机号</th>
      
       
      <th lay-data="{field:'vjointime'}">入会时间</th>
      
  <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#in_table'}">处理</th>
    </tr>
  </thead>
  </table> 
  <!-- 表格头部工具栏  -->
  <script type="text/html" id="toptools">
  <div class="layui-form-item" id='topbox'>
  <label class="layui-form-label">姓名</label>
  <div class="layui-input-inline">
    <input type="text" name="name"  placeholder="请输入姓名" autocomplete="off" class="layui-input">
  </div>
<label class="layui-form-label">性别</label>
  <div class="layui-input-inline">
    <input type="text" name="sex"   class="layui-input">
  </div>
  <a class="layui-btn " lay-event="query">查询</a>
  <a class="layui-btn " lay-event="add">新增</a>

</div>

  
  </script>
  
  
  <!-- 表格的行内按钮 -->
 <script type="text/html" id="in_table">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="edit">编辑</a>
</script>
<!-- start 新增信息窗口 -->
<div id='addVipBox' style="display: none">
<form class='layui-form' lay-filter="addVipForm">
<div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-inline">
      <input type="text" name="vname"  class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-inline">
      <input type="radio" name="vsex" title='男' value='男' checked >
      <input type="radio" name="vsex" title='女' value='女'  >
    
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">会员折扣</label>
    <div class="layui-input-inline">
      <input type="text" name="vdiscount"  class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">手机号</label>
    <div class="layui-input-inline">
      <input type="text" name="vphone"  class="layui-input">
    </div>
  </div>
     <div class="layui-form-item">
    <label class="layui-form-label">入会时间</label>
    <div class="layui-input-inline">
     <input type="text" name="vjointime" id="vtime" lay-date="{type: 'datetime', range: true, format: 'yyyy-MM-dd HH:mm:ss'}">
      
    </div>
  </div>
</form>
</div>
<!-- end 新增信息窗口 -->
<!-- start 编辑信息窗口 -->
<div id='editVipBox' style="display: none">
<form class='layui-form' lay-filter="editVipForm">
	<input type='hidden' name='vid'><!-- 数据设置为用户不可见 -->
<div class="layui-form-item">
    <label class="layui-form-label">姓名</label>
    <div class="layui-input-inline">
      <input type="text" name="vname"  class="layui-input">
    </div>
  </div>

  <div class="layui-form-item">
    <label class="layui-form-label">手机号</label>
    <div class="layui-input-inline">
      <input type="text" name="vphone"  class="layui-input">
    </div>
  </div>
</form>
</div>
<!-- end 新增信息窗口 -->
<script src="static/layui/layui.js"></script>

<script>

layui.use(['table'], function(){
	
  var table = layui.table;

  var laydate = layui.laydate;
  var form=layui.form;
  var $=layui.$; //内置jQuery
  laydate.render({
	    elem: '#vtime', //指定元素
	    //type: 'data' //选择器类型为时间选择器
	  });
/*表格头部按钮工具栏事件*/
table.on("toolbar(form)",function(obj){
	//点击按钮的lay-event属性
	var event=obj.event;

	 if(event=="add"){
		//给用户填写填写的位置
		//使用弹出层,显示表单
		//在打开窗口之前要先对表单进行重置
		$("#addVipBox form")[0].reset() ;
		
		layer.open({
			
			title:"新增",
			type:1,
			/*设置大小*/
			area:['400','260'],
			//弹出层的内容
			content:$("#addVipBox"),
			
			btn:["确定",'取消','重置'],
			
			btn1:function(){
				
				//layui可以直接获取一个表单中的数据,只要这个组件有class='layui-from'样式即可
				//这个组件必须有一个layfilter='easy'shux 
				//from.val('easy') 就可以获取到这个组件中的所有表单属性
				var formdata=form.val("addVipForm");
				var reg1 =/[\u4e00-\u9fa5]/;
				var reg2= /^(100\%)|(0\.)|(\d{1,2}\.\d{1,2}\%)|(\d{1,2}\%)$/;
				var reg3= /^1[3-9]\d{9}$/;
				if(!reg1.test(formdata.vname)){
					layer.msg("姓名不合乎命名规范!",{
						icon:2,
						time:2800
					});return;
				}else if(!reg2.test(formdata.vdiscount)){
					layer.msg("折扣不合乎规范!",{
						icon:2,
						time:2800
					});return;
				}else if(!reg3.test(formdata.vphone)){
					layer.msg("手机号不合乎规范!",{
						icon:2,
						time:2800
					});return;
				}
				else{
					true;
				}
				$.ajax({
					url:"vip/add",
					data:formdata,
					success:function(result){
						if(result=="0"){
							layer.msg("用户名已存在,请重新输入",{icon:2,time:2800});
						}else{
						layer.msg("添加成功",{icon:1,time:2800});
						table.reload("form",{page:{curr:1}});
					}
					}
				});
				console.log(formdata);
				console.log("确定按钮");
				
				//关闭所有弹出层
				layer.closeAll();
				
			
			},
			btn3:function(){
				$("#addVipBox [name='vname']").val("") ;
				$("#addVipBox [name='vphone']").val("") ;
				$("#addVipBox [name='vdiscount']").val("") ;
				$("#addVipBox [name='vjointime']").val("") ;
				return false;
			}
			
		});
		//提交用户填写的数据
		//提示新增成功,刷新表格
		
	}
	else if(event=="query"){
		//点击了查询按钮
		//获取文本框中的内容
		var text_name=$("#topbox [name='name']").val();
		var text_sex=$("#topbox [name='sex']").val();
	
		//根据文本内容查询
		table.reload("form",{where:{//当点击查询时,向Java服务端程序发送id,name,重新装载页面,没有这两个数据时既是全搜索
			vname:text_name,
			vsex:text_sex
		},page:{curr:1}//重载表格时,默认从第一页开始
		});
		//将查询的数据重写到查询输入框中
		var text_name=$("#topbox [name='name']").val(text_name);
		var text_sex=$("#topbox [name='sex']").val(text_sex);
	}
});


   //监听工具条,表格行内按钮处理
  table.on('tool(form)', function(obj){
	  //得到监听对象的数据
    var data = obj.data;
	  console.log(data);
	  if(obj.event ==='edit'){
		  //将当前行数据设置到表单中
		  form.val("editVipForm",data);
		  //显示弹出层  大括号设置参数
		  layer.open({
			  title:'编辑',
			  type:1,
			  area:['400','260'],
			  content:$("#editVipBox"),
			  btn:["保存","取消"],
			  btn1:function(){
				  var data=form.val("editVipForm");
				  var reg1 =/[\u4e00-\u9fa5]/;
					var reg2= /^(100\%)|(0\.)|(\d{1,2}\.\d{1,2}\%)|(\d{1,2}\%)$/;
					var reg3= /^1[3-9]\d{9}$/;
					if(!reg1.test(data.vname)){
						layer.msg("姓名不合乎命名规范!",{
							icon:2,
							time:2800
						});return;
					
					}else if(!reg3.test(data.vphone)){
						layer.msg("手机号不合乎规范!",{
							icon:2,
							time:2800
						});return;
					}
					else{
						true;
					}
				  $.ajax({
						url:"vip/update",
						data:data,
						success:function(result){
							if(result=="0"){
								layer.msg("用户名已存在,请重新修改",{icon:2,time:2800});
							}else{
							
							layer.msg("保存成功",{icon:1,time:2800});
							table.reload("form",{page:{curr:1}});
							}
						}
					});
					console.log(data);
					console.log("确定按钮");
					
					//关闭所有弹出层
					layer.closeAll();
			  }
			  
		  });
	  }
	  else if(obj.event === 'del'){//点击删除按钮后,要执行的代码,事件是删除时
    	//业务逻辑
      layer.confirm('真的删除行么',{icon:6,title:"提示"}, function(index){//提示用户确认删除
    	  //将该行数据发送给servlet,servlet调用其他工具删除该数据
    	  $.ajax({
    		  trpe:'DETELE',
    		  url:"vip/deletelist",
    		  data:{
    			  vid:data.vid,
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