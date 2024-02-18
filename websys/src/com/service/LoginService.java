package com.service;

import com.easy.bean.User;

public interface LoginService {
//登录验证方法接口
	User login(String username,String password);
}
