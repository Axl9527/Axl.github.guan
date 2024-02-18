package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jx/*")
public class JieXiServlet extends HttpServlet {
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri=	req.getRequestURI();
		System.out.println(uri);
		//最后出现的位置/jsp/* //public int lastIndexOf(String str): 返回指定子字符串在此字符串中最右边出现处的索引,获取在URI中最后出现"/"的位置的索引。
		int index=uri.lastIndexOf("/");
		System.out.println(index);
		//文件名,将从URI中获取从最后一个"/"开始的部分,作为文件名
		String pagename=uri.substring(index+1);
		System.out.println(pagename);
		//转发路径,构造一个新的URL，它指向在"../WEB-INF/page/"目录下的以获取的文件名或页面名为名称的JSP文件。
		String url="../WEB-INF/page/"+pagename+".jsp";
		System.out.println(url);
		//转发
		req.getRequestDispatcher(url).forward(req, resp);
		}
}
