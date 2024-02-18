package com.service;

import java.util.List;

import com.easy.bean.LayuiTableData;
import com.easy.bean.*;

public interface TableService {
	List<Table> list() ;
	boolean del(int tid);
	LayuiTableData list_layui(String tstate,String tname,String page, String limit);
	int add(String tname,int tnumbers,String tstate);
	int update(int tid,String tname,int tnumbers,String tstate);
	
}
