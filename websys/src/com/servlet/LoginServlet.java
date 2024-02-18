package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easy.bean.User;
import com.easy.util.Sys;
import com.service.LoginService;
import com.service.impl.LoginServiceImple;


@WebServlet("/logina")
public class LoginServlet extends HttpServlet{
	//验证登录是符合规范,再与数据库存储的用户名密码进行验证,通过创建数据库验证方法的实例
	LoginService login=new LoginServiceImple();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//通过service方法获取前端用户信息,
		String params_name=req.getParameter("username");
		String params_pass=req.getParameter("password");
		String params_remeber=req.getParameter("remember");
		System.out.println(params_name+params_pass+ params_remeber);
		if (params_remeber!= null && "1".equals(params_remeber)) {
		    Cookie nameCookie = new Cookie("username", params_name);
		    Cookie passCookie = new Cookie("password", params_pass);
		  
		    nameCookie.setMaxAge(60 * 60 * 24 * 30); // cookie有效期为30天
		    passCookie.setMaxAge(60 * 60 * 24 * 30);
		    resp.addCookie(nameCookie);
		    resp.addCookie(passCookie);
		  }else {
			   	Cookie userCookie = new Cookie("username", "");
			    Cookie passCookie = new Cookie("password", "");
			    resp.addCookie(userCookie);
			    resp.addCookie(passCookie);
		  }
//	
			
//		//调用登录验证方法进行验证验证登录是否成功 1.成功返回用户对象,2失败返回null
		User user=login.login(params_name, params_pass);
		System.out.println(user+"--------");
//		//失败跳转登录失败界面,服务器重新再发一个登录网址
		if(user==null) {
			
				resp.getWriter().write("fail");
			//resp.sendRedirect("http://localhost:8080/websys/login.jsp?testlogin==fail");
			
		}else {
			//使用session保存cookie到tomcat
			//让tomcat记录登录状态;Sys.LOGIN_USER是一个常量,键loginUser,值是对象
			req.getSession().setAttribute(Sys.LOGIN_USER,params_name);
//			//通过跳转成功界面,浏览器请求进入成功网址
			
			resp.getWriter().write("success");
			//req.getRequestDispatcher("WEB-INF/page/buju.jsp").forward(req, resp);
		}
		
	}
	
	
}
