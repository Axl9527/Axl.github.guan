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
	// 传入sql语句,使用JDBC的方法获取数据库的内容
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
			//声明一个记录条件查询字符串的变量  where...
			String where_sql="";
			//计数器,记录处理参数的个数  where语句中有几个条件
			int temp=0;
			//声明一个集合保存SQL语句中需要传入的参数
			List param_list=new ArrayList();
			//判断拼接SQL语句 当接收到sid,sname才会精确搜索,否则就是全搜索
			if (vsex != null && vsex.length() > 0) {
				where_sql=where_sql+" vsex = ? and";
				param_list.add(vsex);
				temp++;
			}
			if (vname != null && vname.length() > 0) {
				where_sql=where_sql+" vname like ? and";
				param_list.add("%"+vname+"%");//接收传来的参数
				temp++;
			}
			//如果有whereSQL语句的拼接,那就在SQL语句中拼接where语句,否则就是原来的查询所有的SQL语句
			if(temp!=0) {//去掉最后一个拼接语句的and
				sql=sql+"where"+where_sql.substring(0,where_sql.length()-3)+" and isdel = 0 ";
			}else {
				sql=sql+" where isdel = 0 ";
			}
			//拼接limit语句
		sql=sql+"limit ?,?";
		param_list.add(start);
		param_list.add(limit);
			List<Vip> list = null;
			try {
				//将所有的参数所在的数组转换数组传入方法执行
				list = JDBCUtil.query(Vip.class, sql, param_list.toArray());
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		//当前条件下一共有多少条数据
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
			//update t_staff set name ='张三' ,age=23 where id=3;
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
