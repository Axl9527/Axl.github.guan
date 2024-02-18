package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
import com.easy.bean.LayuiTableData;
import com.easy.util.Sys;

/**
 * Servlet implementation class ChangeServlet
 */
@WebServlet("/change/*")
public class ChangeServlet extends FenxiServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		Object action=req.getAttribute(Sys.SYS_ACTION);
		System.out.println(action+"Î´ÅÐ¶ÏÐÐÎª");
		if("change".equals(action)) {
			req.getRequestDispatcher("WEB-INF/page/change.jsp").forward(req, resp);
		}
	}
}
