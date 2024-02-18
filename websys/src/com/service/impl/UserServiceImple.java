package com.service.impl;

import com.dao.UserDAO;
import com.easy.bean.User;
import com.easy.util.RegUtil;
import com.service.UserService;

public class UserServiceImple implements UserService{
	UserDAO dao=new UserDAO();
	@Override
	public int add(String username, String userpass) {
		
		User user=dao.queryOne("select * from t_user where username=?", username);
		if(user!=null) {
		return 0;}
		else {
			String sql="insert into t_user(username,userpass) values (?,?)";
			 return dao.updata(sql, username,userpass);
		}
	}

	@Override
	public int change(String username, String olduserpass, String newuserpass1, String newuserpass2) {
		// TODO Auto-generated method stub
		if(username==null||olduserpass==null||newuserpass1==null||newuserpass2==null) {
			return 0;
		}
		int result=-1;
		if(RegUtil.test6_16(username,olduserpass,newuserpass1,newuserpass2)) {
			User u=dao.queryOne("select * from t_user where username=?", username);
			System.out.println(u);
			if(u!=null) {
				if(u.getUserpass().equals(olduserpass)&&newuserpass1.equals(newuserpass2)) {
					String sql="update t_user set userpass= ? where username=?";
					System.out.println(sql);
					result=dao.updata(sql, newuserpass1,username);
				}
			}
		}
		return result;
	}
@Override
public boolean del(String username) {
	// TODO Auto-generated method stub
	return dao.del(username);
}
}
