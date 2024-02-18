package com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
import com.dao.MenuDao;
import com.easy.bean.LayuiTableData;
import com.easy.bean.Menu;
import com.easy.util.Sys;
import com.service.MenuService;
import com.service.impl.MenuServiceImple;
import com.easy.bean.Class;
/**
 * Servlet implementation class SystemServlet
 */
@WebServlet("/menu/*")
public class MenuServlet extends FenxiServlet {
	MenuService menuser=new MenuServiceImple();
	MenuDao dao =new MenuDao();
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	super.service(req, resp);
	Object action=req.getAttribute(Sys.SYS_ACTION);
	System.out.println(action+"未判断行为");
	if("layuilist".equals(action)) {
		//分页参数 layui自动发送
		String page=req.getParameter("page");
		String limit=req.getParameter("limit");
		System.out.println(page);
		//查询参数
		String cid=req.getParameter("cid");
		String mname=req.getParameter("mname");
		System.out.println(cid+"layui");
		//应该返回就有四个属性  当没有id name时就是全搜索
		LayuiTableData result = menuser.list_layui(cid, mname, page, limit);//new LayuiTableData(count,list);//得到数据库的总数据条数和对应数据
		//将封装好的对象解析成json
		String json=JSON.toJSONString(result);
		//将json对象写出
		resp.getWriter().write(json);
	}else if("deletelist".equals(action)) {
		
		String mid=req.getParameter("mid");
		System.out.println(mid);
		String result="";
		boolean msg=menuser.del(mid);
		if(msg) {
			result="1";
		}
		resp.getWriter().write(result);
	}else if("add".equals(action)) {
		int count = 0;
		String name = null;
		String mname=req.getParameter("mname");
		String mprice=req.getParameter("mprice");
		String cid=req.getParameter("cid");
		System.out.println(cid+"add");
		List<Menu> list=menuser.list();
		int a=0;
		for(Menu m:list) {
			name=m.getMname();
			if(name.equals(mname)) {
				count=0;
				a=1;
				break;
			}
		}
		if(a!=1) {
			count =menuser.add(mname, mprice, cid);
		}
	   
		
		resp.getWriter().write(count+"");
	}else if("update".equals(action)) {
		int count;
		String name = null;
		String mid=req.getParameter("mid");
		String mname=req.getParameter("mname");
		String mprice=req.getParameter("mprice");
		String cid=req.getParameter("cid");
		List<Menu> list=menuser.list();
		for(Menu m:list) {
			name=m.getMname();
		}
		if(name.equals(mname)) {
			count=0;
		}else {
		count=menuser.update(mid, mname, mprice, cid);

		}
		resp.getWriter().write(count+"");
		
	}else if("list".equals(action)) {
		List<Menu> list=menuser.list();
		System.out.println(list+"bz");
		String result=JSON.toJSONString(list);
		resp.getWriter().write(result);
	}else if("listc".equals(action)) {
		
		List<Class> list=menuser.listc();
		System.out.println(list+"bz");
		String result=JSON.toJSONString(list);
		resp.getWriter().write(result);
	}
	
}

}
