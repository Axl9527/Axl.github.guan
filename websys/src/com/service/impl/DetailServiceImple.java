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
		//��û��ҳ�������Ĭ��������ȫ��,����������Ϊ��
		int count=0;
		if(page==null||limit==null) {
			list=dao.list(bid);
		}else {//�����յ�ҳ�������ʱ,
			//��ǰ̨���ַ�������ת��Ϊ��ֵ
			int i_page=Integer.parseInt(page);
			int i_limit=Integer.parseInt(limit);
			//���SQL���������Ҫ�Ŀ�ʼλ��
			int start=(i_page-1)*i_limit;
			list=dao.list(vname,bid,start,i_limit);
			count=dao.getCount(vname,bid);
		}//�õ����ݿ�������������Ͷ�Ӧ����,����layui��ʾ��Ӧ������ҳ��
			LayuiTableData result=new LayuiTableData(count,list);

			return result;
	
	}
}
