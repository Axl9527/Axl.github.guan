package com.service.impl;

import com.dao.UserDAO;
import com.easy.bean.User;
import com.easy.util.RegUtil;
import com.service.LoginService;

public class LoginServiceImple implements LoginService {
	UserDAO udao=new UserDAO();
	//如果成功 跳转的登录成功页面 , 失败再返回登录页面
	@Override
	public User login(String username, String password) {
		// 验证用户名密码是否符合规范,使用SQL指令获取数据库信息
		if(RegUtil.test6_16(username,password)) {
			String sql="select * from t_user where username=?";
			//调用数据库方法,比较数据库中的用户名密码
			User user=udao.queryOne(sql, username);
			//查询用户名是否存在
			if(user!=null) {
				//验证密码,匹配就返回user对象
				if(password.equals(user.getUserpass())) {
					return user;
				}
			}
			
		}
		
		
		return null;
	}
  
}
