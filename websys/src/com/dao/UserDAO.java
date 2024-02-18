package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.easy.bean.User;
import com.easy.util.JDBCUtil;

public class UserDAO {
	// 通过JDBC执行SQL查询数据,接收多个字段,并将结果封装成一个数组User对象返回,如果出现错误就返回null的一个方法
	public List<User> list(String sql, Object... objs) {
		List<User> list = null;
		try {
			return JDBCUtil.query(User.class, sql, objs);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 通过JDBC执行SQL查询一行数据,接收多个个字段,并将结果封装成一个User对象返回,如果出现错误就返回null的一个方法
	public User queryOne(String sql, Object... objects) {
		// list接收数据
		List<User> list;
		// 返回的结果
		User result = null;
		try {
			// 用集合接收数据,再判断不为空,才能返回出去
			list = JDBCUtil.query(User.class, sql, objects);
			if (list != null && list.size() >= 1) {
				// 将集合中第一个元素赋值给result,返回出去
				result = list.get(0);
			}
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return result;
		}
	}
	public int updata(String sql,Object...objs) {
		try {
			return JDBCUtil.update(sql, objs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	public boolean del(String username) {
		String sql="delete  from t_user  where username=  ?";
		boolean msg=false;
		try {
			if(JDBCUtil.update(sql, username)==1) {
				msg=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
}
