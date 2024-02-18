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
			list=dao.list(tstate,tname,start,i_limit);
			count=dao.getCount(tstate,tname);
		}//�õ����ݿ�������������Ͷ�Ӧ����,����layui��ʾ��Ӧ������ҳ��
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
