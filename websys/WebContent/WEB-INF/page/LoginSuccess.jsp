<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.easy.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 放到受保护的项目中,用servlet单独服务 -->
登录成功
<%= session.getAttribute(Sys.LOGIN_USER) %>
</body>
</html>