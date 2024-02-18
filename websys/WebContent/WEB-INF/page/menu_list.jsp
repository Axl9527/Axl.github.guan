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
<table class="layui-table" lay-data="{ url:'menu/layuilist', toolbar:'#toptools',page:true, id:'form'}" lay-filter="form">
  <thead>
    <tr>
    <th lay-data="{type:'numbers'}">序号</th>
      <th lay-data="{field:'mid' type:'hidden'}">ID</th>
      <th lay-data="{field:'mname' }">菜名</th>
      <th lay-data="{field:'mprice'}">价格</th>
      <th lay-data="{field:'cid'}">菜系编号</th>
        <th lay-data="{templet:'#cname'}">菜系名称</th>
  <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#in_table'}">处理</th>
    </tr>
  </thead>
  </table> 
  <!-- 表格头部工具栏  -->
  <script type="text/html" id="toptools">
  <div class="layui-form-item" id='topbox'>
  <label class="layui-form-label">菜名</label>
  <div class="layui-input-inline">
   <input type="text" name="name"  placeholder="请输入菜名" autocomplete="off" class="layui-input">

  </div>
<label class="layui-form-label">菜系名称</label>
  <div class="layui-input-inline">
   
  	<select name="id" id="id" >
      <option value=""></option>
    </select>
  </div>
  <a class="layui-btn " lay-event="query">查询</a>
  <a class="layui-btn " lay-event="add">新增</a>

</div>

  
  </script>
  <script type="text/html" id="cname">
    <span>{{d.clas.cclass}}</span>
</script>
  
  
  <!-- 表格的行内按钮 -->
 <script type="text/html" id="in_table">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="edit">编辑</a>
</script>
<!-- start 新增信息窗口 -->
<div id='addMenuBox' style="display: none">
<form class='layui-form' lay-filter="addMenuForm">
<div class="layui-form-item">
    <label class="layui-form-label">菜名</label>
    <div class="layui-input-inline">
      <input type="text" name="mname"  class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">菜系名称</label>
    <div class="layui-input-inline">
      
     <select name="cid" id="cid" >
      <option value=""></option>
    </select>
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">价格</label>
    <div class="layui-input-inline">
      <input type="text" name="mprice"  class="layui-input">
    </div>
  </div>
</form>
</div>
<!-- end 新增信息窗口 -->
<!-- start 编辑信息窗口 -->
<div id='editMenuBox' style="display: none">
<form class='layui-form' lay-filter="editMenuForm">
	<input type='hidden' name='mid'><!-- 数据设置为用户不可见 -->
<div class="layui-form-item">
    <label class="layui-form-label">菜名</label>
    <div class="layui-input-inline">
      <input type="text" name="mname"  class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">菜系名称</label>
    <div class="layui-input-inline">
     
     <select name="cid" id="cid" >
      <option value=""></option>
    </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">价格</label>
    <div class="layui-input-inline">
      <input type="text" name="mprice"  class="layui-input">
    </div>
  </div>
</form>
</div>
<!-- end 新增信息窗口 -->
<script src="static/layui/layui.js"></script>

<script>

layui.use(['table'], function(){
	
  var table = layui.table;
  var form=layui.form;
  var $=layui.$; //内置jQuery
/*表格头部按钮工具栏事件*/
table.on("toolbar(form)",function(obj){
	//点击按钮的lay-event属性
	var event=obj.event;

	 if(event=="add"){
		//给用户填写填写的位置
		//使用弹出层,显示表单
		//在打开窗口之前要先对表单进行重置
		$("#addMenuBox form")[0].reset() ;
		 
		layer.open({
			
			title:"新增",
			type:1,
			/*设置大小*/
			area:['400','260'],
			//弹出层的内容
			content:$("#addMenuBox"),
			
			btn:["确定",'取消','重置'],
			
			btn1:function(){
				
				//layui可以直接获取一个表单中的数据,只要这个组件有class='layui-from'样式即可
				//这个组件必须有一个layfilter='easy'shux 
				//from.val('easy') 就可以获取到这个组件中的所有表单属性
				var formdata=form.val("addMenuForm");
				var reg1 =/[\u4e00-\u9fa5]/;
				var reg2=/^\d{1,}$/;
				if(!reg1.test(formdata.mname)){
					layer.msg("菜名不合乎命名规范!",{
						icon:2,
						time:2800
					});return;
				}else if(!reg2.test(formdata.mprice)){
					layer.msg("价格不能为负数!",{
						icon:2,
						time:2800
					});return;
				}else{
					true;
				}
				$.ajax({
					url:"menu/add",
					data:formdata,
					success:function(result){
						layer.msg("保存成功",{icon:1,time:2800});
						table.reload("form",{page:{curr:1}});
					}
				});
				console.log(formdata);
				console.log("确定按钮");
				
				//关闭所有弹出层
				layer.closeAll();
				
			
			},
			btn3:function(){
				$("#addMenuBox [name='mname']").val("") ;
				$("#addMenuBox [name='mprice']").val("") ;
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
		var text_id=$("#id option:selected").val();
		console.log(text_id);
		//根据文本内容查询
		table.reload("form",{where:{//当点击查询时,向Java服务端程序发送sid,sname,重新装载页面,没有这两个数据时既是全搜索
			mname:text_name,
			cid:text_id
		},page:{curr:1}//重载表格时,默认从第一页开始
		});
		//将查询的数据重写到查询输入框中
		showNurseType();
		var text_name=$("#topbox [name='name']").val(text_name);
		var text_id=$("#id option:selected").val(text_id);
	}
});


   //监听工具条,表格行内按钮处理
  table.on('tool(form)', function(obj){
	  //得到监听对象的数据
    var data = obj.data;
	  console.log(data);
	  if(obj.event ==='edit'){
		  //将当前行数据设置到表单中
		  form.val("editMenuForm",data);
		  //显示弹出层  大括号设置参数
		  layer.open({
			  title:'编辑',
			  type:1,
			  area:['400','260'],
			  content:$("#editMenuBox"),
			  btn:["保存","取消"],
			  btn1:function(){
				  var data=form.val("editMenuForm");
				  var reg1 =/[\u4e00-\u9fa5]/;
					var reg2=/^\d{1,}$/;
					if(!reg1.test(data.mname)){
						layer.msg("菜名不合乎命名规范!",{
							icon:2,
							time:2800
						});return;
					}else if(!reg2.test(data.mprice)){
						layer.msg("价格不能为负数!",{
							icon:2,
							time:2800
						});return;
					}else{
						true;
					}
				  $.ajax({
						url:"menu/update",
						data:data,
						success:function(result){
							layer.msg("保存成功",{icon:1,time:2800});
							table.reload("form",{page:{curr:1}});
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
    		  
    		  url:"menu/deletelist",
    		  data:{
    			  mid:data.mid,
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
  function showitem($dom,items){
      //清空显示数据的组件
      $dom.html('');
      for(var i=0;i<items.length;i++){
          var item=items[i];
          var text = "<option value='"+item.cid+"'>"+item.cclass+"</option>"
          $dom.append($(text));
      }
      
  }
  showNurseType();
	function showNurseType(){
	    $.ajax({
	        url: "menu/listc",
	        data: {},
	        success: function (data) {      	
	            data = JSON.parse(data);
	            $.each(data, function (i) {
	                var trString = " <option value='" + data[i].cid + "'>" + data[i].cclass + "</option>";
	                $("[name='id']").append(trString);
	                $("[name='cid']").append(trString);
	                form.render();
	            });
	        }
	     });
	}

});
 
  

</script>



</body>
</html>