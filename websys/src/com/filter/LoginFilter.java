package com.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.easy.util.Sys;
@WebFilter(urlPatterns = {"/jx/*"})
public class LoginFilter implements Filter{
//在访问到正式资源之前进行过滤检查
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("-------dofilter");
		//验证用户的请求是否拥有登录状态
		HttpServletRequest req=null;
		HttpServletResponse resp=null;
		if(request instanceof HttpServletRequest) {
			req=(HttpServletRequest) request;
			resp=(HttpServletResponse) response;
		}
		//通过请求获取session对象
		HttpSession session=req.getSession();
		//检查session中是否存储了登录的数据
		Object loginobj=session.getAttribute(Sys.LOGIN_USER);
		if(loginobj!=null) {
		//如果登陆了,就放行
			//放行
			chain.doFilter(request, response);
			
		}else {
//			response.getWriter().write("未登录");
			resp.sendRedirect("http://localhost:8080/websys/login.jsp?login=false");
			
		}				
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
