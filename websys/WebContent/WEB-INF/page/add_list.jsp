<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% //在jsp上设置根路径
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<style>  
        #add {  
            position: fixed;  
            right: 0;  
            bottom: 0;  
            width: 75px;  
            height: 20px;  
            background-color: #009688;  
        }  
        #tijiao {  
            position: fixed;  
            left: 0;  
            bottom: 0;  
            width: 75px;  
            height: 20px;  
            background-color: #009688;  
        }  
    </style>  
<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="static/layui/css/layui.css"rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div id='addBillBox' >
<form class='layui-form'  id='addform'  lay-filter="addBillForm">
<div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-inline">
      <input type="text" name="name" id="name" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">桌号</label>
    <div class="layui-input-inline">
    
         <select name="tid"  id="tid">
              <option value=""></option>
         </select>
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">时间</label>
    <div class="layui-input-inline">
      <input type="text" name="time" id="time" lay-date="{type: 'datetime', range: true, format: 'yyyy-MM-dd HH:mm:ss'}" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">菜名</label>
    <div class="layui-input-inline">
      <input type="text" name="cname1" id="cname1" class="layui-input">
    </div>
  </div>
</form>
<button id='add' >加菜</button>
<button id='tijiao' >提交</button>
</div>


<script src="static/layui/layui.js"></script>
<script type="text/javascript">

layui.use(['form'], function(){
	layui.form
	var form=layui.form;
	var num=1;
	var $=layui.$;
	var laydate = layui.laydate;
	 laydate.render({
		    elem: '#time', //指定元素
		    type: 'datetime' //选择器类型为时间选择器
		  });

	$("#add").click(function(){
		num++;
		var str='<div class="layui-form-item">'+
		   ' <label class="layui-form-label">菜名</label>'+
		    '<div class="layui-input-inline">'+
		     ' <input type="text" id="cname'+num+'"  class="layui-input">'+
		   ' </div>'+
		 ' </div>';
		$("#addform").append(str);
		form.render();
	});
	$("#tijiao").click(function(){
		var name=$("#name").val();
		var tname=$("#tid option:selected").val();
		var time=$("#time").val();
		var cnames=[];
		
		for(var i=1;i<=num;i++){
			var a=$("#cname"+i).val();
			cnames.push(a);
		}
		console.log(cnames);
		cnames=JSON.stringify(cnames);
		$.ajax({
			url:"bill/add",
			dataType:"json",
			data:{
				name:name,
				tname:tname,
				time:time,
				cnames:cnames
			},
			success:function(result){
				
			}
		});
		layer.msg("提交成功",{icon:1,time:2800});
		parent.location.reload();
		parent.closeopen();
	});
	showNurseType();
	function showNurseType(){
	    $.ajax({
	        url: "bill/listc",
	        data: {},
	        success: function (data) {      	
	            data = JSON.parse(data);
	            $.each(data, function (i) {
	                var trString = " <option value='" + data[i].tname + "'>" + data[i].tname + "</option>";
	                $("[name='tid']").append(trString);
	                form.render();
	            });
	        }
	     });
	}
	
	
});
</script>
</body>

</html>