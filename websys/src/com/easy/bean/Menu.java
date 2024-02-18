package com.easy.bean;

import com.easy.util.Lists;
import com.easy.util.One;

public class Menu {

private String mid;
private String mname;
private String mprice;
private String cid;
private int isdel;
@One(columnName = "cid",sql="select * from t_class where id=?")
private Class clas;
public Menu() {
	super();
}
public Menu(String mid, String mname, String mprice, String cid, int isdel, Class clas) {
	super();
	this.mid = mid;
	this.mname = mname;
	this.mprice = mprice;
	this.cid = cid;
	this.isdel = isdel;
	this.clas = clas;
}
@Override
public String toString() {
	return "Menu [mid=" + mid + ", mname=" + mname + ", mprice=" + mprice + ", cid=" + cid + ", isdel=" + isdel
			+ ", clas=" + clas + "]";
}
public String getMid() {
	return mid;
}
public void setMid(String mid) {
	this.mid = mid;
}
public String getMname() {
	return mname;
}
public void setMname(String mname) {
	this.mname = mname;
}
public String getMprice() {
	return mprice;
}
public void setMprice(String mprice) {
	this.mprice = mprice;
}
public String getCid() {
	return cid;
}
public void setCid(String cid) {
	this.cid = cid;
}
public int getIsdel() {
	return isdel;
}
public void setIsdel(int isdel) {
	this.isdel = isdel;
}
public Class getClas() {
	return clas;
}
public void setClas(Class clas) {
	this.clas = clas;
}




}
