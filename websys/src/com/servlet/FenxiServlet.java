package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easy.util.Sys;


public class FenxiServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getRequestURI();
		int index=path.lastIndexOf("/");
		String action=path.substring(index+1);
		System.out.println(action+111);
		req.setAttribute(Sys.SYS_ACTION,action);
	}
}
