package com.service.impl;

import java.util.List;

import com.dao.MenuDao;
import com.easy.bean.Class;
import com.easy.bean.LayuiTableData;
import com.easy.bean.Menu;
	
import com.service.MenuService;

public class MenuServiceImple implements MenuService{
MenuDao dao=new MenuDao();
	@Override
	public List<Menu> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
@Override
	public boolean del(String mid) {
		
		return dao.del(mid);
	}
@Override//直接将查询的数据给layui表格,首次没有id,name,默认全搜索,当有id,name就精确搜索
public LayuiTableData list_layui(String cid,String mname,String page, String limit) {
	List<Menu> list=null;
	//当没有页码或条数默认搜索出全部,基本不可能为空
	int count=0;
	if(page==null||limit==null) {
		list=dao.list();
	}else {//当接收到页码和条数时,
		//将前台的字符串参数转换为数值
		int i_page=Integer.parseInt(page);
		int i_limit=Integer.parseInt(limit);
		//算出SQL语句中所需要的开始位置
		int start=(i_page-1)*i_limit;
		list=dao.list(cid,mname,start,i_limit);
		count=dao.getCount(cid,mname);
	}//得到数据库的总数据条数和对应数据,传给layui显示对应条数和页数
		LayuiTableData result=new LayuiTableData(count,list);

		return result;
}
@Override
	public int add(String mname, String mprice, String cid) {
		// TODO Auto-generated method stub
		return dao.add(mname, mprice, cid);
	}
@Override
public int update(String mid, String mname, String mprice, String cid) {
	// TODO Auto-generated method stub
	return dao.updata(mid, mname, mprice, cid);
}
@Override
public List<Class> listc() {
	// TODO Auto-generated method stub
	return dao.listc();
}
}
