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
import com.service.ClassService;
import com.service.impl.ClassServiceImple;

/**
 * Servlet implementation class ClassServlet
 */
@WebServlet("/class/*")
public class ClassServlet extends FenxiServlet {
	ClassService claser=new ClassServiceImple();
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
			String cid=req.getParameter("cid");
			String cclass=req.getParameter("cclass");
			System.out.println(cid+"layui");
			System.out.println(cclass+"layui2");
			//Ӧ�÷��ؾ����ĸ�����  ��û��id nameʱ����ȫ����
			LayuiTableData result = claser.list_layui(cid, cclass, page, limit);//new LayuiTableData(count,list);//�õ����ݿ�������������Ͷ�Ӧ����
			//����װ�õĶ��������json
			String json=JSON.toJSONString(result);
			//��json����д��
			resp.getWriter().write(json);
		}else if("deletelist".equals(action)) {
			
			int id=Integer.parseInt(req.getParameter("id"));
			String result="";
			boolean msg=claser.del(id);
			if(msg) {
				result="1";
			}
			resp.getWriter().write(result);
		}else if("add".equals(action)) {
			String cclass=req.getParameter("cclass");
		
			String cid=req.getParameter("cid");
			System.out.println(cclass+"add");
			int count =claser.add(cclass,cid);
			resp.getWriter().write(count+"");
		}else if("update".equals(action)) {
			String a=req.getParameter("id");
			System.out.println(a+"123123");
			int id=Integer.parseInt(req.getParameter("id"));
			String cid=req.getParameter("cid");
			String cclass=req.getParameter("cclass");
		
			System.out.println(cclass+"---update");
			int count=claser.update(id,cclass, cid);
			System.out.println(count+"---update");
			resp.getWriter().write(count+"");
		}
		
	}
}
