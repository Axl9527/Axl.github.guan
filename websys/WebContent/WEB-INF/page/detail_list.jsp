<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% //在jsp上设置根路径
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<style>  
    /* 使用绝对定位将按钮放置在页面的右下角 */  
    .layui-btn {  
        position: absolute;  
        right: 20px;  
        bottom: 20px;  
    }  
</style>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="static/layui/css/layui.css"rel="stylesheet">

<title>Insert title here</title>
</head>
<body>
<table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th >菜名</th>
      <th >价格</th>
      <th >数量</th>
    </tr> 
  </thead>
  <tbody id="tbody">
    
    
  </tbody>
</table>

  <a class="layui-btn " lay-event="exit" id='exit' >退出</a>

</form>
</div>


<script src="static/layui/layui.js"></script>

<script type="text/javascript">
layui.use(['table'], function(){
	var $=layui.$;
	$("#exit").click(function(){
		
		parent.closeopen();
	});
	
	
});
</script>
</body>
</html>