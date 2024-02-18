package com.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.easy.bean.*;
import com.easy.bean.Class;
import com.easy.util.JDBCUtil;
public class MenuDao {
	public boolean del(String mid) {

		String sql = "update  t_menu set isdel=1 where mid=?";
		
		boolean msg=false;
		try {
			if(JDBCUtil.update(sql, mid)==1) {
				msg=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	// 传入sql语句,使用JDBC的方法获取数据库的内容
		public List<Menu> list() {
			
			String sql = "select * from t_menu where isdel=0";
			List<Menu> list = null;
			
			try {
				list = JDBCUtil.query(Menu.class, sql);
			
				System.out.println(list);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
			
		}
public List<Class> listc() {
			
			String sql = "select * from t_class where isdel=0 ";
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
		public List<Menu> list(String cid, String mname, int start, int limit) {
			
			String sql = "select * from t_menu ";
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
			if (mname != null && mname.length() > 0) {
				where_sql=where_sql+" mname like  ? and";
				param_list.add("%"+mname+"%");//接收传来的参数
				temp++;
			}
			//如果有whereSQL语句的拼接,那就在SQL语句中拼接where语句,否则就是原来的查询所有的SQL语句
			if(temp!=0) {//去掉最后一个拼接语句的and
				sql=sql+" where "+where_sql.substring(0,where_sql.length()-3)+" and isdel = 0 ";
			}else {
				sql=sql+"where isdel = 0 ";
			}
			//拼接limit语句
		sql=sql+" limit ?,? ";
		param_list.add(start);
		param_list.add(limit);
			List<Menu> list = null;
			try {
				//将所有的参数所在的数组转换数组传入方法执行
				list = JDBCUtil.query(Menu.class, sql, param_list.toArray());
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
			
		}
		//当前条件下一共有多少条数据
		public int getCount(String cid,String mname) {
			int result=0;
			List params=new ArrayList();
			String sql="select count(*) from t_menu";
			String where_sql="";
			if(cid!=null&&cid.length()>0) {
				where_sql=where_sql+" cid=? and";
				params.add(cid);
			}
			if(mname!=null&&mname.length()>0) {
				where_sql=where_sql+" mname like ? and";
				params.add("%"+mname+"%");
			}
			if(where_sql.length()>0) {
				sql=sql+" where "+where_sql.substring(0,where_sql.length()-3)+" and isdel = 0 ";
				
			}else {
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
		public int add(String mname,String mprice,String cid) {
			String mid=UUID.randomUUID().toString();
			
			
			String sql="insert into t_menu (mid,mname,mprice,cid) values(?,?,?,?)";
			int count=0;
			try {
				count=JDBCUtil.update(sql, mid,mname,mprice,cid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
		public int updata(String mid,String mname,String mprice,String cid) {
			//update t_staff set name ='张三' ,age=23 where id=3;
			String sql="update  t_menu set ";
			String set_sql="";
			List param_list=new ArrayList();
			if(mname!=null) {
				set_sql=set_sql+" mname = ? ,";
				param_list.add(mname);
			}
			if(mprice!=null) {
				set_sql=set_sql+" mprice = ?,";
				param_list.add(mprice);
			}
			if(cid!=null) {
				set_sql=set_sql+" cid = ?,";
				param_list.add(cid);
			}
		if(set_sql.length()>0) {
			sql=sql+set_sql.substring(0,set_sql.length()-1);
		}
		sql=sql+" where mid=? ";
		param_list.add(mid);
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
