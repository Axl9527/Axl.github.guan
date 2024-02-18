package com.servlet;

import java.io.IOException;
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
import com.easy.bean.Table;
import com.easy.util.Sys;

import com.service.BillService;
import com.service.MenuService;
import com.service.impl.BillServiceImple;
import com.service.impl.MenuServiceImple;

/**
 * Servlet implementation class BillServlet
 */
@WebServlet("/bill/*")
public class BillServlet extends FenxiServlet {
	BillService billser = new BillServiceImple();
	MenuService menuser=new MenuServiceImple();
	MenuDao dao =new MenuDao();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		Object action = req.getAttribute(Sys.SYS_ACTION);
		System.out.println(action);
		if ("layuilist".equals(action)) {
			// 分页参数 layui自动发送
			String page = req.getParameter("page");
			String limit = req.getParameter("limit");
			System.out.println(page);
			// 查询参数
			String vname = req.getParameter("vname");

			System.out.println(vname + "layui2");
			// 应该返回就有四个属性 当没有id name时就是全搜索
			LayuiTableData result = billser.list_layui(vname, page, limit);
			// 将封装好的对象解析成json
			String json = JSON.toJSONString(result);
			// 将json对象写出
			resp.getWriter().write(json);
		} else if ("deletelist".equals(action)) {

			int bid = Integer.parseInt(req.getParameter("bid"));
			System.out.println(bid + "deletelist22222");
			String result = "";
			boolean msg = billser.del(bid);
			if (msg) {
				result = "1";
			}
			resp.getWriter().write(result);
		} else if ("add".equals(action)) {
			String name = req.getParameter("name");
			String tname = req.getParameter("tname");
			String time = req.getParameter("time");
			String cnames = req.getParameter("cnames");
			
			String[] a = (cnames.substring(1, cnames.length() - 1)).split(",");

			System.out.println(Arrays.toString(a));
			for (int i = 0; i < a.length; i++) {
				a[i] = a[i].substring(1, a[i].length() - 1);
			}
			
			billser.add(name, tname, time, a);
			
		} else if ("listc".equals(action)) {
			List<Table> list = billser.listc();
			System.out.println(list + "listc****");
			String count = JSON.toJSONString(list);
			resp.getWriter().write(count);
		}
	}
}
