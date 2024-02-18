package com.service.impl;

import java.util.List;

import com.dao.ClassDao;
import com.easy.bean.LayuiTableData;
import com.service.ClassService;
import com.easy.bean.Class;

public class ClassServiceImple implements ClassService{
	ClassDao dao=new ClassDao();
	@Override
	public List<Class> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
@Override
	public boolean del(int id) {
		
		return dao.del(id);
	}
@Override//直接将查询的数据给layui表格,首次没有id,name,默认全搜索,当有id,name就精确搜索
public LayuiTableData list_layui(String cid,String cclass,String page, String limit) {
	List<Class> list=null;
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
		list=dao.list(cid,cclass,start,i_limit);
		count=dao.getCount(cid,cclass);
	}//得到数据库的总数据条数和对应数据,传给layui显示对应条数和页数
		LayuiTableData result=new LayuiTableData(count,list);

		return result;
}
@Override
	public int add( String cclass,String cid) {
		// TODO Auto-generated method stub
		return dao.add(cclass,cid);
	}
@Override
public int update(int id, String cclass,String cid) {
	// TODO Auto-generated method stub
	return dao.updata(id,cclass,cid);
}
}
