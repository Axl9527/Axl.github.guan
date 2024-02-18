package com.service;

import java.util.List;

import com.easy.bean.LayuiTableData;
import com.easy.bean.*;

public interface VipService {
	List<Vip> list() ;
	boolean del(String vid);
	LayuiTableData list_layui(String vsex,String vname,String page, String limit);
	int add(String vname,String vsex,float vdiscount,String vphone,String vjointime);
	int update(String vid,String vname,String vsex,String vphone,String vjointime);
}
