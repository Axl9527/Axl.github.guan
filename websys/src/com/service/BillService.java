package com.service;

import java.util.List;

import com.easy.bean.LayuiTableData;
import com.easy.bean.Table;
import com.easy.bean.Bill;
import com.easy.bean.Class;

public interface BillService {
	List<Bill> list() ;
	boolean del(int bid);
	LayuiTableData list_layui(String vname,String page, String limit);
	int add(String vname,String tid,String bdatetime,String[] mname);
	List<Table> listc();
}
