package com.service;

public interface UserService {
 int add(String username,String userpass);
 int change(String username,String olduserpass,String newuserpass1,String newuserpass2);
 boolean del(String username);
}
