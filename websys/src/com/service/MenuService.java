package com.service;
import java.util.List;
import com.easy.bean.Class;
import com.easy.bean.*;
public interface MenuService {
List<Menu> list() ;
List<Class> listc();
boolean del(String mid);
LayuiTableData list_layui(String mid,String mname,String page, String limit);
int add(String mname,String mprice,String cid);
int update(String mid,String mname,String mprice,String cid);
}
