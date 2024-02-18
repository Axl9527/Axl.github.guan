package com.service.impl;

import java.util.List;

import com.dao.DetailDao;
import com.easy.bean.Bill;
import com.easy.bean.Detail;
import com.easy.bean.LayuiTableData;
import com.service.DetailService;

public class DetailServiceImple implements DetailService{
DetailDao dao=new DetailDao();
	@Override
	public List<Detail> list(int bid) {
		// TODO Auto-generated method stub
		return dao.list( bid);
	}
	@Override
	public LayuiTableData list_layui(String vname,int bid, String page, String limit) {
		List<Detail> list=null;
		//当没有页码或条数默认搜索出全部,基本不可能为空
		int count=0;
		if(page==null||limit==null) {
			list=dao.list(bid);
		}else {//当接收到页码和条数时,
			//将前台的字符串参数转换为数值
			int i_page=Integer.parseInt(page);
			int i_limit=Integer.parseInt(limit);
			//算出SQL语句中所需要的开始位置
			int start=(i_page-1)*i_limit;
			list=dao.list(vname,bid,start,i_limit);
			count=dao.getCount(vname,bid);
		}//得到数据库的总数据条数和对应数据,传给layui显示对应条数和页数
			LayuiTableData result=new LayuiTableData(count,list);

			return result;
	
	}
}
