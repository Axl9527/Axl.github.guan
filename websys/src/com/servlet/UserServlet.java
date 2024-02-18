package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easy.util.Sys;
import com.service.UserService;
import com.service.impl.UserServiceImple;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user/*")
public class UserServlet extends FenxiServlet {
	UserService user=new UserServiceImple();
	@Override
	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		Object action=req.getAttribute(Sys.SYS_ACTION);
		if("change".equals(action)) {
			String uname=(String) req.getSession().getAttribute(Sys.LOGIN_USER);
			String opass=req.getParameter("old");
			String npass1=req.getParameter("new1");
			String npass2=req.getParameter("new2");
			System.out.println(opass+npass1+npass2);
			int result =user.change(uname, opass, npass1, npass2);
			if(result==1) {
				resp.getWriter().write("success");
			}else {
				resp.getWriter().write("false");
			}
		}else if("add".equals(action)) {
			String uname=req.getParameter("username");
			String upass=req.getParameter("password");
			System.out.println(uname+upass);
			int result=user.add(uname, upass);
			if(result==1) {
				resp.sendRedirect("http://localhost:8080/websys/login.jsp");
				
			}else {
				req.getRequestDispatcher("zhuce.jsp").forward(req, resp);
			}
		}else if("zx".equals(action)) {
			String uname=(String) req.getSession().getAttribute(Sys.LOGIN_USER);
			System.out.println(uname+"×¢Ïú");
			boolean result =user.del(uname);
			if(result) {
				resp.getWriter().write("success");
			}else {
				resp.getWriter().write("false");
			}
		}
		
	}

}
