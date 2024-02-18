package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.easy.bean.Class;

import com.easy.util.JDBCUtil;

public class ClassDao {
	public boolean del(int id) {
		String sql="update  t_class set isdel=1 where id= ? ";
		String sqlcid="select cid from t_class where id=? ";
		
		
		boolean msg=false;
		try {
			int cid=Integer.parseInt((String) JDBCUtil.queryOne(sqlcid, id)) ;
			System.out.println(cid+"----ClassDao");
			String sql1="update  t_menu set isdel=1 where cid= ?";
			JDBCUtil.update(sql1, cid);
			if(JDBCUtil.update(sql, id)==1) {
				msg=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	// ����sql���,ʹ��JDBC�ķ�����ȡ���ݿ������
		public List<Class> list() {
			String sql = "select * from t_class";
			List<Class> list = null;
			try {
				list = JDBCUtil.query(Class.class, sql);
				System.out.println(list);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		public List<Class> list(String cid, String cclass, int start, int limit) {
			String sql = "select * from t_class";
			//����һ����¼������ѯ�ַ����ı���  where...
			String where_sql="";
			//������,��¼��������ĸ���  where������м�������
			int temp=0;
			//����һ�����ϱ���SQL�������Ҫ����Ĳ���
			List param_list=new ArrayList();
			//�ж�ƴ��SQL��� �����յ�sid,sname�Żᾫȷ����,�������ȫ����
			if (cid != null && cid.length() > 0) {
				where_sql=where_sql+" cid = ? and";
				param_list.add(cid);
				temp++;
			}
			if (cclass != null && cclass.length() > 0) {
				where_sql=where_sql+" cclass like ? and";
				param_list.add("%"+cclass+"%");//���մ����Ĳ���
				temp++;
			}
			//�����whereSQL����ƴ��,�Ǿ���SQL�����ƴ��where���,�������ԭ���Ĳ�ѯ���е�SQL���
			if(temp!=0) {//ȥ�����һ��ƴ������and
				sql=sql+" where "+where_sql.substring(0,where_sql.length()-3)+" and isdel=0 ";
			}else {
				sql=sql+" where isdel=0";
			}
			//ƴ��limit���
		sql=sql+" limit ?,? ";
		param_list.add(start);
		param_list.add(limit);
			List<Class> list = null;
			try {
				System.out.println(sql);
//				System.out.println(param_list.toArray());
				//�����еĲ������ڵ�����ת�����鴫�뷽��ִ��
				list = JDBCUtil.query(Class.class,sql,param_list.toArray());
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		//��ǰ������һ���ж���������(���ݲ�ѯ�ֶ��ж���������)
		public int getCount(String cid,String cclass) {
			int result=0;
			List params=new ArrayList();
			String sql="select count(*) from t_class";
			String where_sql="";
			if(cid!=null&&cid.length()>0) {
				where_sql=where_sql+" cid=? and";
				params.add(cid);
			}
			if(cclass!=null&&cclass.length()>0) {
				where_sql=where_sql+" cclass like ? and";
				params.add("%"+cclass+"%");
			}
			if(where_sql.length()>0) {
				sql=sql+" where "+where_sql.substring(0,where_sql.length()-3)+"and isdel=0 ";
				System.out.println(sql);
			}else {
				sql=sql+" where isdel=0 ";
			}
			try {
				result=Integer.parseInt(JDBCUtil.queryOne(sql, params.toArray())+"");
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}
		public int add(String cclass,String cid) {
			
			String sql="insert into t_class (cclass,cid) values(?,?)";
			int count=0;
			try {
				count=JDBCUtil.update(sql,cclass, cid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
		public int updata(int id,String cclass,String cid) {
			//update t_staff set name ='����' ,age=23 where id=3;
			String sql="update  t_class set ";
			String set_sql="";
			List param_list=new ArrayList();
			if(cclass!=null) {
				set_sql=set_sql+" cclass = ? ,";
				param_list.add(cclass);
			}
			if(cid!=null) {
				set_sql=set_sql+" cid = ? ,";
				param_list.add(cid);
			}
		if(set_sql.length()>0) {
			sql=sql+set_sql.substring(0,set_sql.length()-1);
		}
		sql=sql+" where id=? ";
		param_list.add(id);
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
