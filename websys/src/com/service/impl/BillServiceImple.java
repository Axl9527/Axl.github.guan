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
		//��û��ҳ�������Ĭ��������ȫ��,����������Ϊ��
		int count=0;
		if(page==null||limit==null) {
			list=dao.list();
		}else {//�����յ�ҳ�������ʱ,
			//��ǰ̨���ַ�������ת��Ϊ��ֵ
			int i_page=Integer.parseInt(page);
			int i_limit=Integer.parseInt(limit);
			//���SQL���������Ҫ�Ŀ�ʼλ��
			int start=(i_page-1)*i_limit;
			list=dao.list(vname,start,i_limit);
			count=dao.getCount(vname);
		}//�õ����ݿ�������������Ͷ�Ӧ����,����layui��ʾ��Ӧ������ҳ��
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
