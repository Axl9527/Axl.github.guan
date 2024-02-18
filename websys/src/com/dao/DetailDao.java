package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.easy.bean.Bill;
import com.easy.bean.Detail;
import com.easy.util.JDBCUtil;

public class DetailDao {
	public List<Detail> list(int bid) {
		String sql = "select * from t_detail where bid=? ";
		List<Detail> list = null;
		try {
			list = JDBCUtil.query(Detail.class, sql,bid);
			System.out.println(list);
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<Detail> list(String vname,int bid, int start, int limit) {
		String sql = "select * from t_detail  ";
		//����һ����¼������ѯ�ַ����ı���  where...
		String where_sql="";
		//������,��¼��������ĸ���  where������м�������
		int temp=0;
		//����һ�����ϱ���SQL�������Ҫ����Ĳ���
		List param_list=new ArrayList();
		//�ж�ƴ��SQL��� �����յ�id,sname�Żᾫȷ����,�������ȫ����
		if(bid!=0) {
			where_sql=where_sql+" bid = ? and";
			param_list.add(bid);//���մ����Ĳ���
			temp++;
		}
		 if (vname != null && vname.length() > 0 ) {
			where_sql=where_sql+" vname like ? and";
			param_list.add("%"+vname+"%");//���մ����Ĳ���
			temp++;
		}
		//�����whereSQL����ƴ��,�Ǿ���SQL�����ƴ��where���,�������ԭ���Ĳ�ѯ���е�SQL���
		if(temp!=0) {//ȥ�����һ��ƴ������and
			sql=sql+"where"+where_sql.substring(0,where_sql.length()-3);
		}
		//ƴ��limit���
	sql=sql+"limit ?,?";
	param_list.add(start);
	param_list.add(limit);
		List<Detail> list = null;
		try {
			System.out.println(param_list.toString());
			System.out.println(bid);
			System.out.println(sql);
			//�����еĲ������ڵ�����ת�����鴫�뷽��ִ��
			list = JDBCUtil.query(Detail.class, sql, param_list.toArray());
			System.out.println(list+"Bill");
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//��ǰ������һ���ж���������
	public int getCount(String vname ,int bid) {
		int result=0;
		List params=new ArrayList();
		String sql="select count(*) from t_detail";
		String where_sql="";
		if(bid!=0) {
			where_sql=where_sql+" bid = ? and";
			params.add(bid);//���մ����Ĳ���
			
		}
		if(vname!=null&&vname.length()>0) {
			where_sql=where_sql+" vname like ? and";
			params.add("%"+vname+"%");
		}
		if(where_sql.length()>0) {
			sql=sql+" where "+where_sql.substring(0,where_sql.length()-3);
			System.out.println(sql);
		}
		try {
			result=Integer.parseInt(JDBCUtil.queryOne(sql, params.toArray())+"");
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}
