package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.easy.bean.*;
import com.easy.util.JDBCUtil;

public class VipDao {
	public boolean del(String vid) {
		String sql="update  t_vip set isdel=1 where vid= ?";
		boolean msg=false;
		try {
			if(JDBCUtil.update(sql, vid)==1) {
				msg=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	// ����sql���,ʹ��JDBC�ķ�����ȡ���ݿ������
		public List<Vip> list() {
			String sql = "select * from t_vip";
			List<Vip> list = null;
			try {
				list = JDBCUtil.query(Vip.class, sql);
				System.out.println(list);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		public List<Vip> list(String vsex, String vname, int start, int limit) {
			String sql = "select * from t_vip ";
			//����һ����¼������ѯ�ַ����ı���  where...
			String where_sql="";
			//������,��¼��������ĸ���  where������м�������
			int temp=0;
			//����һ�����ϱ���SQL�������Ҫ����Ĳ���
			List param_list=new ArrayList();
			//�ж�ƴ��SQL��� �����յ�sid,sname�Żᾫȷ����,�������ȫ����
			if (vsex != null && vsex.length() > 0) {
				where_sql=where_sql+" vsex = ? and";
				param_list.add(vsex);
				temp++;
			}
			if (vname != null && vname.length() > 0) {
				where_sql=where_sql+" vname like ? and";
				param_list.add("%"+vname+"%");//���մ����Ĳ���
				temp++;
			}
			//�����whereSQL����ƴ��,�Ǿ���SQL�����ƴ��where���,�������ԭ���Ĳ�ѯ���е�SQL���
			if(temp!=0) {//ȥ�����һ��ƴ������and
				sql=sql+"where"+where_sql.substring(0,where_sql.length()-3)+" and isdel = 0 ";
			}else {
				sql=sql+" where isdel = 0 ";
			}
			//ƴ��limit���
		sql=sql+"limit ?,?";
		param_list.add(start);
		param_list.add(limit);
			List<Vip> list = null;
			try {
				//�����еĲ������ڵ�����ת�����鴫�뷽��ִ��
				list = JDBCUtil.query(Vip.class, sql, param_list.toArray());
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		//��ǰ������һ���ж���������
		public int getCount(String vsex,String vname) {
			int result=0;
			List params=new ArrayList();
			String sql="select count(*) from t_vip";
			String where_sql="";
			if(vsex!=null&&vsex.length()>0) {
				where_sql=where_sql+" vsex=? and";
				params.add(vsex);
			}
			if(vname!=null&&vname.length()>0) {
				where_sql=where_sql+" vname like ? and";
				params.add("%"+vname+"%");
			}
			if(where_sql.length()>0) {
				sql=sql+" where "+where_sql.substring(0,where_sql.length()-3)+" and isdel = 0 ";
				//System.out.println(sql);
			}
			else {
				sql=sql+" where isdel = 0 ";
			}
			try {
				result=Integer.parseInt(JDBCUtil.queryOne(sql, params.toArray())+"");
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}
		public int add(String vname,String vsex,float vdiscount,String vphone,String vjointime) {
			String vid=UUID.randomUUID().toString();
			String sql="insert into t_vip (vid,vname,vsex,vdiscount,vphone,vjointime) values(?,?,?,?,?,?)";
			int count=0;
			try {
				count=JDBCUtil.update(sql, vid,vname,vsex,vdiscount,vphone,vjointime);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
		public int updata(String vid,String vname,String vsex,String vphone,String vjointime) {
			//update t_staff set name ='����' ,age=23 where id=3;
			String sql="update  t_vip set ";
			String set_sql="";
			List param_list=new ArrayList();
			if(vname!=null) {
				set_sql=set_sql+" vname = ? ,";
				param_list.add(vname);
			}
			if(vsex!=null) {
				set_sql=set_sql+" vsex = ?,";
				param_list.add(vsex);
			}
			if(vphone!=null) {
				set_sql=set_sql+" vphone = ?,";
				param_list.add(vphone);
			}
			if(vjointime!=null) {
				set_sql=set_sql+" vjointime = ?,";
				param_list.add(vjointime);
			}
		if(set_sql.length()>0) {
			sql=sql+set_sql.substring(0,set_sql.length()-1);
		}
		sql=sql+" where vid=? ";
		param_list.add(vid);
		System.out.println(sql);
		int count=0;
		try {
			count=JDBCUtil.update(sql,param_list.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		}
}
