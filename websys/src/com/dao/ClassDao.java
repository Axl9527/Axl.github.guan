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
	// 传入sql语句,使用JDBC的方法获取数据库的内容
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
			//声明一个记录条件查询字符串的变量  where...
			String where_sql="";
			//计数器,记录处理参数的个数  where语句中有几个条件
			int temp=0;
			//声明一个集合保存SQL语句中需要传入的参数
			List param_list=new ArrayList();
			//判断拼接SQL语句 当接收到sid,sname才会精确搜索,否则就是全搜索
			if (cid != null && cid.length() > 0) {
				where_sql=where_sql+" cid = ? and";
				param_list.add(cid);
				temp++;
			}
			if (cclass != null && cclass.length() > 0) {
				where_sql=where_sql+" cclass like ? and";
				param_list.add("%"+cclass+"%");//接收传来的参数
				temp++;
			}
			//如果有whereSQL语句的拼接,那就在SQL语句中拼接where语句,否则就是原来的查询所有的SQL语句
			if(temp!=0) {//去掉最后一个拼接语句的and
				sql=sql+" where "+where_sql.substring(0,where_sql.length()-3)+" and isdel=0 ";
			}else {
				sql=sql+" where isdel=0";
			}
			//拼接limit语句
		sql=sql+" limit ?,? ";
		param_list.add(start);
		param_list.add(limit);
			List<Class> list = null;
			try {
				System.out.println(sql);
//				System.out.println(param_list.toArray());
				//将所有的参数所在的数组转换数组传入方法执行
				list = JDBCUtil.query(Class.class,sql,param_list.toArray());
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		//当前条件下一共有多少条数据(根据查询字段判断有总行数)
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
			//update t_staff set name ='张三' ,age=23 where id=3;
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
