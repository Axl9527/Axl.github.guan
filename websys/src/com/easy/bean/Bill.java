package com.easy.bean;

import java.math.BigDecimal;

public class Bill {
private int bid;
private String vname;
private String bdatetime;
private String tname;
private BigDecimal bpayable;
private float vdiscount;
private BigDecimal bpay;
public Bill() {
	super();
}
public Bill(int bid, String vname, String bdatetime, String tname, BigDecimal bpayable, float vdiscount,
		BigDecimal bpay) {
	super();
	this.bid = bid;
	this.vname = vname;
	this.bdatetime = bdatetime;
	this.tname = tname;
	this.bpayable = bpayable;
	this.vdiscount = vdiscount;
	this.bpay = bpay;
}
@Override
public String toString() {
	return "Bill [bid=" + bid + ", vname=" + vname + ", bdatetime=" + bdatetime + ", tname=" + tname + ", bpayable="
			+ bpayable + ", vdiscount=" + vdiscount + ", bpay=" + bpay + "]";
}
public int getBid() {
	return bid;
}
public void setBid(int bid) {
	this.bid = bid;
}
public String getVname() {
	return vname;
}
public void setVname(String vname) {
	this.vname = vname;
}
public String getBdatetime() {
	return bdatetime;
}
public void setBdatetime(String bdatetime) {
	this.bdatetime = bdatetime;
}
public String getTname() {
	return tname;
}
public void setTname(String tname) {
	this.tname = tname;
}
public BigDecimal getBpayable() {
	return bpayable;
}
public void setBpayable(BigDecimal bpayable) {
	this.bpayable = bpayable;
}
public float getVdiscount() {
	return vdiscount;
}
public void setVdiscount(float vdiscount) {
	this.vdiscount = vdiscount;
}
public BigDecimal getBpay() {
	return bpay;
}
public void setBpay(BigDecimal bpay) {
	this.bpay = bpay;
}



}
