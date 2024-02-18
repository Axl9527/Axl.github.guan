package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.easy.bean.User;
import com.easy.util.JDBCUtil;

public class UserDAO {
	// ͨ��JDBCִ��SQL��ѯ����,���ն���ֶ�,���������װ��һ������User���󷵻�,������ִ���ͷ���null��һ������
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

	// ͨ��JDBCִ��SQL��ѯһ������,���ն�����ֶ�,���������װ��һ��User���󷵻�,������ִ���ͷ���null��һ������
	public User queryOne(String sql, Object... objects) {
		// list��������
		List<User> list;
		// ���صĽ��
		User result = null;
		try {
			// �ü��Ͻ�������,���жϲ�Ϊ��,���ܷ��س�ȥ
			list = JDBCUtil.query(User.class, sql, objects);
			if (list != null && list.size() >= 1) {
				// �������е�һ��Ԫ�ظ�ֵ��result,���س�ȥ
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
