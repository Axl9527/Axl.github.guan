package com.service.impl;

import java.util.List;

import com.dao.TableDao;
import com.easy.bean.LayuiTableData;
import com.easy.bean.Menu;
import com.easy.bean.Table;
import com.service.TableService;

public class TableServiceImple implements TableService {
	TableDao dao=new TableDao();
	@Override
	public List<Table> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public boolean del(int tid) {
		// TODO Auto-generated method stub
		return dao.del(tid);
	}

	@Override
	public LayuiTableData list_layui(String tstate, String tname, String page, String limit) {
		List<Table> list=null;
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
			list=dao.list(tstate,tname,start,i_limit);
			count=dao.getCount(tstate,tname);
		}//得到数据库的总数据条数和对应数据,传给layui显示对应条数和页数
			LayuiTableData result=new LayuiTableData(count,list);

			return result;
	}

	@Override
	public int add(String tname, int tnumbers, String tstate) {
		// TODO Auto-generated method stub
		return dao.add(tname, tnumbers, tstate);
	}

	@Override
	public int update(int tid, String tname, int tnumbers, String tstate) {
		// TODO Auto-generated method stub
		return dao.updata(tid, tname, tnumbers, tstate);
	}

	

}
