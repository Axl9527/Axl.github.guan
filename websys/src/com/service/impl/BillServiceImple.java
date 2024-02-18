package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.dao.BillDao;
import com.easy.bean.Bill;
import com.easy.bean.LayuiTableData;
import com.easy.bean.Table;
import com.service.BillService;
import com.service.VipService;

public class BillServiceImple implements BillService{
	BillDao dao=new BillDao();
	VipService vipser=new VipServiceImple();
	@Override
	public List<Bill> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public boolean del(int bid) {
		// TODO Auto-generated method stub
		return dao.del(bid);
	}

	@Override
	public LayuiTableData list_layui(String vname, String page, String limit) {
		List<Bill> list=null;
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
			list=dao.list(vname,start,i_limit);
			count=dao.getCount(vname);
		}//得到数据库的总数据条数和对应数据,传给layui显示对应条数和页数
			LayuiTableData result=new LayuiTableData(count,list);

			return result;
	
	}

	@Override
	public int add(String vname, String tid, String bdatetime, String[] mname) {
		// TODO Auto-generated method stub
		try {
			return dao.add(vname, tid, bdatetime, mname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<Table> listc() {
		// TODO Auto-generated method stub
		return dao.listc();
	}

}
