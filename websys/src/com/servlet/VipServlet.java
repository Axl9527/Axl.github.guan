package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
import com.easy.bean.LayuiTableData;
import com.easy.bean.Vip;
import com.easy.util.Sys;
import com.service.*;
import com.service.impl.MenuServiceImple;
import com.service.impl.VipServiceImple;

/**
 * Servlet implementation class VipServlet
 */
@WebServlet("/vip/*")
public class VipServlet extends FenxiServlet {
	VipService vipser=new VipServiceImple();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		Object action=req.getAttribute(Sys.SYS_ACTION);
		System.out.println(action+"123");
		if("layuilist".equals(action)) {
			//分页参数 layui自动发送
			String page=req.getParameter("page");
			String limit=req.getParameter("limit");
			System.out.println(page);
			//查询参数
			String isdel=req.getParameter("isdel");
			String vsex=req.getParameter("vsex");
			String vname=req.getParameter("vname");
			System.out.println(isdel+"layui");
			//应该返回就有四个属性  当没有id name时就是全搜索
			LayuiTableData result = vipser.list_layui(vsex, vname, page, limit);//new LayuiTableData(count,list);//得到数据库的总数据条数和对应数据
			//将封装好的对象解析成json
			String json=JSON.toJSONString(result);
			//将json对象写出
			resp.getWriter().write(json);
		}else if("deletelist".equals(action)) {
			
			String mid=req.getParameter("vid");
			String result="";
			boolean msg=vipser.del(mid);
			if(msg) {
				result="1";
			}
			resp.getWriter().write(result);
		}else if("add".equals(action)) {
			int count = 0;
			String name=null;
			String vname=req.getParameter("vname");
			String vsex=req.getParameter("vsex");
			String vphone=req.getParameter("vphone");
			float vdiscount=Float.parseFloat(req.getParameter("vdiscount"));
			String vjointime=req.getParameter("vjointime");
			int a=0;
			List<Vip> list =vipser.list();
			for(Vip v:list) {
				name=v.getVname();
				if(name.equals(vname)) {
					count=0;
					 a=1;
					 break;
				}
			}
			if(a!=1) {
			 count =vipser.add(vname, vsex,vdiscount,vphone,vjointime);
			}
			resp.getWriter().write(count+"");
		}else if("update".equals(action)) {
			int count;
			String name=null;
			String vid=req.getParameter("vid");
			String vname=req.getParameter("vname");
			String vsex=req.getParameter("vsex");
			String vphone=req.getParameter("vphone");
			String vjointime=req.getParameter("vjointime");
			List<Vip> list =vipser.list();
			for(Vip v:list) {
				name=v.getVname();
			}
			if(name.equals(vname)) {
				count=0;
			}else {
				count=vipser.update(vid,vname,vsex,vphone,vjointime);
			}
			
			resp.getWriter().write(count+"");
		}
}
}
