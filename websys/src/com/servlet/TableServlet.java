package com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
import com.easy.bean.Class;
import com.easy.bean.LayuiTableData;
import com.easy.bean.Table;
import com.easy.util.Sys;
import com.service.*;
import com.service.impl.*;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/table/*")
public class TableServlet extends FenxiServlet {
	TableService tabser=new TableServiceImple();
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
			String tstate=req.getParameter("tstate");
			String tname=req.getParameter("tname");
			
			System.out.println(tstate+"layui999");
			System.out.println(tname+"layui2");
			//Ӧ�÷��ؾ����ĸ�����  ��û��id nameʱ����ȫ����
			LayuiTableData result = tabser.list_layui(tstate, tname, page, limit);//new LayuiTableData(count,list);//�õ����ݿ�������������Ͷ�Ӧ����
			//����װ�õĶ��������json
			String json=JSON.toJSONString(result);
			//��json����д��
			resp.getWriter().write(json);
		}else if("deletelist".equals(action)) {
			String a=req.getParameter("tid");
			System.out.println(a+"deletelist22222");
			
			int tid=Integer.parseInt(req.getParameter("tid"));
			System.out.println(tid+"deletelist22222");
			String result="";
			boolean msg=tabser.del(tid);
			if(msg) {
				result="1";
			}
			resp.getWriter().write(result);
		}else if("add".equals(action)) {
			String tname=req.getParameter("tname");
			int tnumbers=Integer.parseInt(req.getParameter("tnumbers"));
			String tstate=req.getParameter("tstate");
			System.out.println(tname+"add");
			int count =tabser.add(tname,tnumbers,tstate);
			resp.getWriter().write(count+"");
		}else if("update".equals(action)) {
			int tid=Integer.parseInt(req.getParameter("tid"));
			System.out.println(tid+"---update");
			String tname=req.getParameter("tname");
			int tnumbers=Integer.parseInt(req.getParameter("tnumbers"));
			String tstate=req.getParameter("tstate");
			System.out.println(tid+"---update");
			int count=tabser.update(tid, tname, tnumbers,tstate);
			System.out.println(count+"---update");
			resp.getWriter().write(count+"");
		}else if("listc".equals(action)) {
			List<Table> list=tabser.list();
			System.out.println(list+"bz");
			String result=JSON.toJSONString(list);
			resp.getWriter().write(result);
		}
		
	}


}
