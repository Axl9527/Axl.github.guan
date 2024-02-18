package com.easy.bean;

public class User {
 private Integer id;
 private String username;
 private String userpass;

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpass() {
	return userpass;
}
public void setUserpass(String userpass) {
	this.userpass = userpass;
}

public User(Integer id, String username, String userpass) {
	super();
	this.id = id;
	this.username = username;
	this.userpass = userpass;
}

public User() {
	super();
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", userpass=" + userpass+ "]";
}
 
}
