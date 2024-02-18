package com.service;

import java.util.List;

import com.easy.bean.Detail;
import com.easy.bean.LayuiTableData;

public interface DetailService {
	List<Detail> list(int bid) ;
	LayuiTableData list_layui(String vname,int bid,String page, String limit);
}
