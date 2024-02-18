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
@Override//ֱ�ӽ���ѯ�����ݸ�layui���,�״�û��id,name,Ĭ��ȫ����,����id,name�;�ȷ����
public LayuiTableData list_layui(String cid,String cclass,String page, String limit) {
	List<Class> list=null;
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
		list=dao.list(cid,cclass,start,i_limit);
		count=dao.getCount(cid,cclass);
	}//�õ����ݿ�������������Ͷ�Ӧ����,����layui��ʾ��Ӧ������ҳ��
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
