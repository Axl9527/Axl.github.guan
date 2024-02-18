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
@Override//ֱ�ӽ���ѯ�����ݸ�layui���,�״�û��id,name,Ĭ��ȫ����,����id,name�;�ȷ����
public LayuiTableData list_layui(String cid,String mname,String page, String limit) {
	List<Menu> list=null;
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
		list=dao.list(cid,mname,start,i_limit);
		count=dao.getCount(cid,mname);
	}//�õ����ݿ�������������Ͷ�Ӧ����,����layui��ʾ��Ӧ������ҳ��
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
