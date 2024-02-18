<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.io.*,javax.servlet.*, javax.servlet.http.*"%>
<% //在jsp上设置根路径
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<style>  
    .container {  
      display: flex;  
      justify-content: center;  
      align-items: center;  
      height: 100vh; /* 根据需要调整容器的高度 */  
    }  
  
    .title {  
      text-align: center;  
    }  
  </style>  
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<link  rel="stylesheet" href="static/css/weekwork.css"/>
</head>
<body>





		
		<div class="title">超大碗系统</div>
		
	

</body>
</html>