package com.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.easy.bean.Bill;
import com.easy.bean.Menu;
import com.easy.bean.Table;
import com.easy.bean.Vip;
import com.easy.util.JDBCUtil;
import com.service.VipService;
import com.service.impl.VipServiceImple;

public class BillDao {
	MenuDao menuDao=new MenuDao();
	VipService vipser=new VipServiceImple();
	public boolean del(int bid) {
		String sql="update  t_bill set isdel=1 where bid=  ?";
		boolean msg=false;
		try {
			if(JDBCUtil.update(sql, bid)==1) {
				msg=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	// ����sql���,ʹ��JDBC�ķ�����ȡ���ݿ������
		public List<Bill> list() {
			String sql = "select * from t_bill";
			List<Bill> list = null;
			try {
				list = JDBCUtil.query(Bill.class, sql);
				System.out.println(list);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		public List<Bill> list(String vname, int start, int limit) {
			String sql = "select * from t_bill ";
			//����һ����¼������ѯ�ַ����ı���  where...
			String where_sql="";
			//������,��¼��������ĸ���  where������м�������
			int temp=0;
			//����һ�����ϱ���SQL�������Ҫ����Ĳ���
			List param_list=new ArrayList();
			//�ж�ƴ��SQL��� �����յ�id,sname�Żᾫȷ����,�������ȫ����
			
			 if (vname != null && vname.length() > 0 ) {
				where_sql=where_sql+" vname like ? and";
				param_list.add("%"+vname+"%");//���մ����Ĳ���
				temp++;
			}
			//�����whereSQL����ƴ��,�Ǿ���SQL�����ƴ��where���,�������ԭ���Ĳ�ѯ���е�SQL���
			if(temp!=0) {//ȥ�����һ��ƴ������and
				sql=sql+"where"+where_sql.substring(0,where_sql.length()-3)+" and isdel = 0 ";
			}else {
				sql = sql+" where isdel = 0 ";
			}
		
			//ƴ��limit���
		sql=sql+"limit ?,?";
		param_list.add(start);
		param_list.add(limit);
			List<Bill> list = null;
			try {
				//�����еĲ������ڵ�����ת�����鴫�뷽��ִ��
				list = JDBCUtil.query(Bill.class, sql, param_list.toArray());
				System.out.println(list+"Bill");
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		//��ǰ������һ���ж���������
		public int getCount(String vname ) {
			int result=0;
			List params=new ArrayList();
			String sql="select count(*) from t_bill";
			String where_sql="";
			
			if(vname!=null&&vname.length()>0) {
				where_sql=where_sql+" vname like ? and";
				params.add("%"+vname+"%");
			}
			if(where_sql.length()>0) {
				sql=sql+" where "+where_sql.substring(0,where_sql.length()-3)+" and isdel = 0 ";
				
			}else {
				sql=sql+" where isdel = 0";
			}
			try {
				result=Integer.parseInt(JDBCUtil.queryOne(sql, params.toArray())+"");
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}
		public int add (String vname,String tid,String bdatetime,String[] mname) throws SQLException {
			
			BigDecimal price = new BigDecimal("0");
			int numbers=1;
			String sql="select bid from t_bill order by bid desc limit 1 ";
			int bid=(int) JDBCUtil.queryOne(sql);
			bid=bid+1;
			String sql1="insert into t_bill (bid,vname,tname,bdatetime,bpayable,vdiscount,bpay) values (?,?,?,?,?,?,?)";
			
			String sqlDetail="insert into t_detail(bid,mname,mprice,numbers) values(?,?,?,?)";
			String sqlt="update t_table set tstate='ʹ����' where tname=? ";
			JDBCUtil.update(sqlt, tid);
			for(int i=0;i<mname.length;i++) {
			String sqlP="select mprice from t_menu where mname =?";
			System.out.println(mname[i]+"********");
			 price=(BigDecimal) JDBCUtil.queryOne(sqlP, mname[i]);
			 System.out.println(price+"********");
			JDBCUtil.update(sqlDetail, bid,mname[i],price,numbers);
			price=price.add(price);
			}
			double vdiscount=1;
			List<Vip> list =vipser.list();
			for(Vip v:list) {
				vname=v.getVname();
				if(vname.equals(vname)) {
					vdiscount=0.85;
					 
					 break;
				}
			}
			
			BigDecimal bpay=price.multiply(BigDecimal.valueOf(vdiscount));
			
			
			JDBCUtil.update(sql1, bid,vname,tid,bdatetime,price,vdiscount,bpay);
			return 0;
			
			
					
			
			
			
		}
//		public int add(String bcustomer,String bdatetime,int tid,String tstate) {
//			
//			String sql="insert into t_table (tname,tnumbers,tstate) values(?,?,?)";
//			int count=0;
//			try {
//				count=JDBCUtil.update(sql,tname,tnumbers,tstate);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return count;
//		}
//		public int updata(int tid,String tname,int tnumbers,String tstate) {
//			//update t_staff set name ='����' ,age=23 where id=3;
//			String sql="update  t_table set ";
//			String set_sql="";
//			List param_list=new ArrayList();
//			if(tname!=null) {
//				set_sql=set_sql+" tname = ? ,";
//				param_list.add(tname);
//			}
//			if(tnumbers!=0) {
//				set_sql=set_sql+" tnumbers = ?,";
//				param_list.add(tnumbers);
//			}
//			if(tstate!=null) {
//				set_sql=set_sql+" tstate = ?,";
//				param_list.add(tstate);
//			}
//		if(set_sql.length()>0) {
//			sql=sql+set_sql.substring(0,set_sql.length()-1);
//		}
//		sql=sql+" where tid=? ";
//		param_list.add(tid);
//		System.out.println(sql);
//		int count=0;
//		try {
//			count=JDBCUtil.update(sql,param_list.toArray());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return count;
//		}
		public List<Table> listc() {
			String sql= "select * from t_table where tstate='����' and isdel=0";
			List<Table> list=null;
			try {
				list=JDBCUtil.query(Table.class, sql);
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
}
