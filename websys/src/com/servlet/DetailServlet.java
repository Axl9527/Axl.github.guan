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
import com.service.DetailService;
import com.service.impl.DetailServiceImple;


@WebServlet("/detail/*")
public class DetailServlet extends FenxiServlet {
	DetailService detser=new DetailServiceImple();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		Object action=req.getAttribute(Sys.SYS_ACTION);
		System.out.println(action);
		if("layuilist".equals(action)) {
			//��ҳ���� layui�Զ�����
			String page=req.getParameter("page");
			String limit=req.getParameter("limit");
			System.out.println(page);
			//��ѯ����
			String vname=req.getParameter("vname");	
			Object a=req.getSession().getAttribute("bid");
			System.out.println(a+"��ϸid");
			int b=1;
			
		int bid=Integer.parseInt(req.getParameter("bid"));
			System.out.println(bid+"layui2");
		
			
			
			LayuiTableData result = detser.list_layui(vname,bid,page, limit);
			//����װ�õĶ��������json
			String json=JSON.toJSONString(result);
			//��json����д��
			resp.getWriter().write(json);
			
		}
		
	}
}
