package com.service.impl;

import com.dao.UserDAO;
import com.easy.bean.User;
import com.easy.util.RegUtil;
import com.service.LoginService;

public class LoginServiceImple implements LoginService {
	UserDAO udao=new UserDAO();
	//����ɹ� ��ת�ĵ�¼�ɹ�ҳ�� , ʧ���ٷ��ص�¼ҳ��
	@Override
	public User login(String username, String password) {
		// ��֤�û��������Ƿ���Ϲ淶,ʹ��SQLָ���ȡ���ݿ���Ϣ
		if(RegUtil.test6_16(username,password)) {
			String sql="select * from t_user where username=?";
			//�������ݿⷽ��,�Ƚ����ݿ��е��û�������
			User user=udao.queryOne(sql, username);
			//��ѯ�û����Ƿ����
			if(user!=null) {
				//��֤����,ƥ��ͷ���user����
				if(password.equals(user.getUserpass())) {
					return user;
				}
			}
			
		}
		
		
		return null;
	}
  
}
