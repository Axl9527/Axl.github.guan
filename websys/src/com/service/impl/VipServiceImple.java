package com.service.impl;

import java.util.List;

import com.dao.VipDao;
import com.easy.bean.LayuiTableData;
import com.easy.bean.Menu;
import com.easy.bean.Vip;
import com.service.VipService;

public class VipServiceImple implements VipService{
	VipDao dao=new VipDao();
	@Override
	public List<Vip> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public boolean del(String vid) {
		// TODO Auto-generated method stub
		return dao.del(vid);
	}

	@Override
	public LayuiTableData list_layui(String vsex, String vname, String page, String limit) {
		List<Vip> list=null;
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
			list=dao.list(vsex,vname,start,i_limit);
			count=dao.getCount(vsex,vname);
		}//得到数据库的总数据条数和对应数据,传给layui显示对应条数和页数
			LayuiTableData result=new LayuiTableData(count,list);

			return result;
	}

	@Override
	public int add(String vname, String vsex,float vdiscount,String vphone, String vjointime) {
		// TODO Auto-generated method stub
		return dao.add(vname, vsex,vdiscount, vphone, vjointime);
	}

	@Override
	public int update(String vid, String vname, String vsex, String vphone, String vjointime) {
		// TODO Auto-generated method stub
		return dao.updata(vid, vname, vsex, vphone, vjointime);
	}

}
