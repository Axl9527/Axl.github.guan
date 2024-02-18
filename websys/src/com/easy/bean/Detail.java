package com.easy.bean;

import java.math.BigDecimal;

public class Detail {
private int bid;
private String mname;
private BigDecimal mprice;
private int numbers;

public Detail() {
	super();
}

public Detail(int bid, String mname, BigDecimal mprice, int numbers) {
	super();
	this.bid = bid;
	this.mname = mname;
	this.mprice = mprice;
	this.numbers = numbers;
}

@Override
public String toString() {
	return "Detail [bid=" + bid + ", mname=" + mname + ", mprice=" + mprice + ", numbers=" + numbers + "]";
}

public int getBid() {
	return bid;
}

public void setBid(int bid) {
	this.bid = bid;
}

public String getMname() {
	return mname;
}

public void setMname(String mname) {
	this.mname = mname;
}

public BigDecimal getMprice() {
	return mprice;
}

public void setMprice(BigDecimal mprice) {
	this.mprice = mprice;
}

public int getNumbers() {
	return numbers;
}

public void setNumbers(int numbers) {
	this.numbers = numbers;
}

}
