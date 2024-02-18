package com.easy.bean;

import java.util.List;

public class LayuiTableData {
	private int code=0;
	private String msg="";
	private int count;
	private List data;
	public LayuiTableData() {
		// TODO Auto-generated constructor stub
	}
	public LayuiTableData( int count, List data) {
		super();
	
		this.count = count;
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "LayuiTableData [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}
	
	
}
