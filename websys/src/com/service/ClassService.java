package com.service;

import java.util.List;

import com.easy.bean.LayuiTableData;
import com.easy.bean.Class;

public interface ClassService {
	List<Class> list() ;
	boolean del(int id);
	LayuiTableData list_layui(String cclass,String cid,String page, String limit);
	int add(String cclass,String cid);
	int update(int id,String cid,String cclass);
}
