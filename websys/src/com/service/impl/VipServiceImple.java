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
			list=dao.list(vsex,vname,start,i_limit);
			count=dao.getCount(vsex,vname);
		}//�õ����ݿ�������������Ͷ�Ӧ����,����layui��ʾ��Ӧ������ҳ��
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
