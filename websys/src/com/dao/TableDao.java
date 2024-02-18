package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.easy.bean.*;
import com.easy.util.JDBCUtil;

public class TableDao {
	public boolean del(int tid) {
		String sql="update  t_table set isdel=1 where tid= ?";
		boolean msg=false;
		try {
			if(JDBCUtil.update(sql, tid)==1) {
				msg=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	// 传入sql语句,使用JDBC的方法获取数据库的内容
		public List<Table> list() {
			String sql = "select * from t_table where isdel=0";
			List<Table> list = null;
			try {
				list = JDBCUtil.query(Table.class, sql);
				System.out.println(list);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		public List<Table> list(String tstate, String tname, int start, int limit) {
			String sql = "select * from t_table ";
			//声明一个记录条件查询字符串的变量  where...
			String where_sql="";
			//计数器,记录处理参数的个数  where语句中有几个条件
			int temp=0;
			//声明一个集合保存SQL语句中需要传入的参数
			List param_list=new ArrayList();
			//判断拼接SQL语句 当接收到sid,sname才会精确搜索,否则就是全搜索
			if (tstate != null && tstate.length() > 0 ) {
				where_sql=where_sql+" tstate like ? and";
				param_list.add(tstate);
				temp++;
			}
			if (tname != null && tname.length() > 0) {
				where_sql=where_sql+" tname like ? and";
				param_list.add("%"+tname+"%");//接收传来的参数
				temp++;
			}
			//如果有whereSQL语句的拼接,那就在SQL语句中拼接where语句,否则就是原来的查询所有的SQL语句
			if(temp!=0) {//去掉最后一个拼接语句的and
				sql=sql+"where"+where_sql.substring(0,where_sql.length()-3)+" and isdel=0 ";
			}else {
				sql=sql+" where isdel=0 ";
			}
			//拼接limit语句
		sql=sql+"limit ?,?";
		param_list.add(start);
		param_list.add(limit);
			List<Table> list = null;
			try {
				//将所有的参数所在的数组转换数组传入方法执行
				list = JDBCUtil.query(Table.class, sql, param_list.toArray());
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		//当前条件下一共有多少条数据
		public int getCount(String tstate ,String tname) {
			int result=0;
			List params=new ArrayList();
			String sql="select count(*) from t_table";
			String where_sql="";
			if(tstate!=null&&tstate.length()>0) {
				where_sql=where_sql+" tstate like ? and";
				params.add(tstate);
			}
			if(tname!=null&&tname.length()>0) {
				where_sql=where_sql+" tname like ? and";
				params.add("%"+tname+"%");
			}
			if(where_sql.length()>0) {
				sql=sql+" where "+where_sql.substring(0,where_sql.length()-3)+" and isdel=0 ";
				System.out.println(sql);
			}else {
				sql=sql+" where isdel = 0";
			}
			try {
				result=Integer.parseInt(JDBCUtil.queryOne(sql, params.toArray())+"");
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}
		public int add(String tname,int tnumbers,String tstate) {
			
			String sql="insert into t_table (tname,tnumbers,tstate) values(?,?,?)";
			int count=0;
			try {
				count=JDBCUtil.update(sql,tname,tnumbers,tstate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
		public int updata(int tid,String tname,int tnumbers,String tstate) {
			//update t_staff set name ='张三' ,age=23 where id=3;
			String sql="update  t_table set ";
			String set_sql="";
			List param_list=new ArrayList();
			if(tname!=null) {
				set_sql=set_sql+" tname = ? ,";
				param_list.add(tname);
			}
			if(tnumbers!=0) {
				set_sql=set_sql+" tnumbers = ?,";
				param_list.add(tnumbers);
			}
			if(tstate!=null) {
				set_sql=set_sql+" tstate = ?,";
				param_list.add(tstate);
			}
		if(set_sql.length()>0) {
			sql=sql+set_sql.substring(0,set_sql.length()-1);
		}
		sql=sql+" where tid=? ";
		param_list.add(tid);
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
