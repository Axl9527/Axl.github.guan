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
		//声明一个记录条件查询字符串的变量  where...
		String where_sql="";
		//计数器,记录处理参数的个数  where语句中有几个条件
		int temp=0;
		//声明一个集合保存SQL语句中需要传入的参数
		List param_list=new ArrayList();
		//判断拼接SQL语句 当接收到id,sname才会精确搜索,否则就是全搜索
		if(bid!=0) {
			where_sql=where_sql+" bid = ? and";
			param_list.add(bid);//接收传来的参数
			temp++;
		}
		 if (vname != null && vname.length() > 0 ) {
			where_sql=where_sql+" vname like ? and";
			param_list.add("%"+vname+"%");//接收传来的参数
			temp++;
		}
		//如果有whereSQL语句的拼接,那就在SQL语句中拼接where语句,否则就是原来的查询所有的SQL语句
		if(temp!=0) {//去掉最后一个拼接语句的and
			sql=sql+"where"+where_sql.substring(0,where_sql.length()-3);
		}
		//拼接limit语句
	sql=sql+"limit ?,?";
	param_list.add(start);
	param_list.add(limit);
		List<Detail> list = null;
		try {
			System.out.println(param_list.toString());
			System.out.println(bid);
			System.out.println(sql);
			//将所有的参数所在的数组转换数组传入方法执行
			list = JDBCUtil.query(Detail.class, sql, param_list.toArray());
			System.out.println(list+"Bill");
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//当前条件下一共有多少条数据
	public int getCount(String vname ,int bid) {
		int result=0;
		List params=new ArrayList();
		String sql="select count(*) from t_detail";
		String where_sql="";
		if(bid!=0) {
			where_sql=where_sql+" bid = ? and";
			params.add(bid);//接收传来的参数
			
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
