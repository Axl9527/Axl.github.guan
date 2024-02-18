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
	//��֤��¼�Ƿ��Ϲ淶,�������ݿ�洢���û������������֤,ͨ���������ݿ���֤������ʵ��
	LoginService login=new LoginServiceImple();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//ͨ��service������ȡǰ���û���Ϣ,
		String params_name=req.getParameter("username");
		String params_pass=req.getParameter("password");
		String params_remeber=req.getParameter("remember");
		System.out.println(params_name+params_pass+ params_remeber);
		if (params_remeber!= null && "1".equals(params_remeber)) {
		    Cookie nameCookie = new Cookie("username", params_name);
		    Cookie passCookie = new Cookie("password", params_pass);
		  
		    nameCookie.setMaxAge(60 * 60 * 24 * 30); // cookie��Ч��Ϊ30��
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
			
//		//���õ�¼��֤����������֤��֤��¼�Ƿ�ɹ� 1.�ɹ������û�����,2ʧ�ܷ���null
		User user=login.login(params_name, params_pass);
		System.out.println(user+"--------");
//		//ʧ����ת��¼ʧ�ܽ���,�����������ٷ�һ����¼��ַ
		if(user==null) {
			
				resp.getWriter().write("fail");
			//resp.sendRedirect("http://localhost:8080/websys/login.jsp?testlogin==fail");
			
		}else {
			//ʹ��session����cookie��tomcat
			//��tomcat��¼��¼״̬;Sys.LOGIN_USER��һ������,��loginUser,ֵ�Ƕ���
			req.getSession().setAttribute(Sys.LOGIN_USER,params_name);
//			//ͨ����ת�ɹ�����,������������ɹ���ַ
			
			resp.getWriter().write("success");
			//req.getRequestDispatcher("WEB-INF/page/buju.jsp").forward(req, resp);
		}
		
	}
	
	
}
