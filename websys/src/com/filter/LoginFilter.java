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
//�ڷ��ʵ���ʽ��Դ֮ǰ���й��˼��
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("-------dofilter");
		//��֤�û��������Ƿ�ӵ�е�¼״̬
		HttpServletRequest req=null;
		HttpServletResponse resp=null;
		if(request instanceof HttpServletRequest) {
			req=(HttpServletRequest) request;
			resp=(HttpServletResponse) response;
		}
		//ͨ�������ȡsession����
		HttpSession session=req.getSession();
		//���session���Ƿ�洢�˵�¼������
		Object loginobj=session.getAttribute(Sys.LOGIN_USER);
		if(loginobj!=null) {
		//�����½��,�ͷ���
			//����
			chain.doFilter(request, response);
			
		}else {
//			response.getWriter().write("δ��¼");
			resp.sendRedirect("http://localhost:8080/websys/login.jsp?login=false");
			
		}				
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
