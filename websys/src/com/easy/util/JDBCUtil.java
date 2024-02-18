package com.easy.util;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.catalina.tribes.util.Arrays;

import com.alibaba.druid.pool.DruidDataSourceFactory;


public class JDBCUtil {

	private static DataSource ds;
	static {
		InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
		Properties pros = new Properties();
		try {
			pros.load(in);
			ds = DruidDataSourceFactory.createDataSource(pros);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void close() {
		if (ds != null) {

		}
	}

	public static void close(Connection con, PreparedStatement psta, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psta != null) {
			try {
				psta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 修改数据
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 * @throws SQLException sql select * from A where id=? and name=? objs [12,张三]
	 */
	public static int update(String sql, Object... objs) throws SQLException {
		Connection con = ds.getConnection();
		PreparedStatement psta = setParam(con, sql, objs);
		int result = psta.executeUpdate();
		close(con, psta, null);
		return result;
	}

	private static PreparedStatement setParam(Connection con, String sql, Object... objs) throws SQLException {
		PreparedStatement presta = con.prepareStatement(sql);
		for (int i = 0; i < objs.length; i++) {
			presta.setObject(i + 1, objs[i]);
		}
		return presta;
	}

	public static int updateById(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException, SQLException {
		// 获取对应的表名
		Class clazz = obj.getClass();// Staff类
		Class tableclazz = Table.class;
		Annotation a_table = clazz.getAnnotation(tableclazz);// @Table(tablename='t_staff')
		Method m = tableclazz.getDeclaredMethod("tablename");
		Object table_name = m.invoke(a_table);

		StringBuilder strb = new StringBuilder();
		strb.append("update ");
		strb.append(table_name);
		strb.append(" set ");

		String wherestr = "";
		Object id = null;
		Field[] f_arr = clazz.getDeclaredFields();
		List params = new ArrayList();

		for (Field f : f_arr) {
			f.setAccessible(true);
			String fname = f.getName();
			Object fval = f.get(obj);
			if (f.isAnnotationPresent(ID.class)) {
				wherestr = "where " + fname + "=?";
				id = fval;
			} else {
				strb.append(fname);
				strb.append("=? ,");
				params.add(fval);
			}
		}
		String sql = strb.substring(0, strb.length() - 1);
		sql = sql + wherestr;
		params.add(id);

		Connection con = ds.getConnection();
		PreparedStatement psta = setParam(con, sql, params.toArray());
		int result = psta.executeUpdate();
		close(con, psta, null);
		return result;
	}

	public static Object queryOne(String sql, Object... objs) throws SQLException {
		Connection con = ds.getConnection();
		PreparedStatement presta = setParam(con, sql, objs);
		ResultSet rs = presta.executeQuery();
		Object result = null;
		if (rs.next()) {
			result = rs.getObject(1);
		}
		close(con, presta, rs);
		return result;
	}

	// 事务处理的方法

	public static int update(Connection con, String sql, Object... objs) throws SQLException {
		PreparedStatement psta = setParam(con, sql, objs);
		int result = psta.executeUpdate();
		close(null, psta, null);
		return result;
	}

	public static <E> List<E> query(Class<E> clazz, String sql, Object... objs)
			throws SQLException, InstantiationException, IllegalAccessException {
		Connection con = ds.getConnection();

		return query(con, clazz, sql, objs);
	}

	public static <E> List<E> query(Connection con, Class<E> clazz, String sql, Object... objs)
			throws SQLException, InstantiationException, IllegalAccessException {
		// 预编译执行对象
		PreparedStatement presta = setParam(con, sql, objs);
		// 执行SQL语句
		ResultSet rs = presta.executeQuery();
		// 准备存储数据
		List<E> result = new ArrayList<E>();
		// 获取clazz中所有的字段
		Field[] f_arr = clazz.getDeclaredFields();
		// 设置所有属性可访问
		for (Field f : f_arr) {
			f.setAccessible(true);
		}
		while (rs.next()) {
			E e = clazz.newInstance();
			// 将获取的内容封装到对象中
			for (Field f : f_arr) {
				// 属性的字段名和列名一样
				String f_name = f.getName();
				Annotation ann_one = f.getDeclaredAnnotation(One.class);
				Annotation ann_lists = f.getDeclaredAnnotation(Lists.class);
				try {
					// 首先检查是否是One或者是Lists注解标注的
					if (ann_one != null) {
						getOne(con, rs, e, f);
					} else if (ann_lists != null) {
						getList(con, rs, e, f);
					} else {
						if (f.getType() == Integer.class || f.getType() == int.class) {
							int val = rs.getInt(f_name);
							f.set(e, val);
						} else if (f.getType() == String.class) {
							String val = rs.getString(f_name);
							f.set(e, val);
						} else {
							Object val = rs.getObject(f_name);
							f.set(e, val);
						}
					}
				} catch (SQLException ee) {
					//ee.printStackTrace();
				}
			}
			result.add(e);
		}
		close(con, presta, rs);
		return result;
	}

	public static Object queryOne(Connection con, String sql, Object... objs) throws SQLException {
		PreparedStatement presta = setParam(con, sql, objs);
		ResultSet rs = presta.executeQuery();
		Object result = null;
		if (rs.next()) {
			result = rs.getObject(1);
		}
		close(null, presta, rs);
		return result;
	}

	public static void getOne(Connection con, ResultSet rsp, Object obj, Field field) {
		// 属性声明的类型
		Class field_class = field.getType();
		Class one_class = One.class;
		// 获取该属性上的注解
		Annotation ann_one = field.getAnnotation(one_class);
		Method method_sql = null;
		Method method_column_name = null;
		Object sql = null;
		Object columnName = null;
		Object param = null;
		PreparedStatement presta = null;
		ResultSet rs = null;
		try {
			method_sql = one_class.getDeclaredMethod("sql");
			method_column_name = one_class.getDeclaredMethod("columnName");
			sql = method_sql.invoke(ann_one);
			columnName = method_column_name.invoke(ann_one);
			// 获取columnName列中对应的数据
			param = rsp.getObject((String) columnName);

			presta = setParam(con, sql.toString(), param);
			// 执行SQL语句
			rs = presta.executeQuery();

			// 获取clazz中所有的字段
			Field[] f_arr = field_class.getDeclaredFields();
			// 设置所有属性可访问
			for (Field f : f_arr) {
				f.setAccessible(true);
			}
			rs.next();
			Object e = field_class.newInstance();
			// 将获取的内容封装到对象中
			for (Field f : f_arr) {
				// 属性的字段名和列名一样
				String f_name = f.getName();
				try {
					if (f.getType() == Integer.class || f.getType() == int.class) {
						int val = rs.getInt(f_name);
						f.set(e, val);
					} else if (f.getType() == String.class) {
						String val = rs.getString(f_name);
						f.set(e, val);
					} else {
						Object val = rs.getObject(f_name);
						f.set(e, val);
					}
				} catch (SQLException ee) {
					//ee.printStackTrace();
				}
				field.set(obj, e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, presta, rs);
		}
	}

	public static void getList(Connection con, ResultSet rsp,Object obj,  Field field) {
		// 属性声明的类型
		Class field_class = field.getType();
		Class list_class = Lists.class;
		// 获取该属性上的注解
		Annotation ann_one = field.getAnnotation(list_class);
		Method method_sql = null;
		Method method_column_name = null;
		Method method_class_name = null;
		Object sql = null;
		Object columnName = null;
		Object param = null;
		Object className = null;
		PreparedStatement presta = null;
		ResultSet rs = null;
		Class clazz = null;
		try {
			method_sql = list_class.getDeclaredMethod("sql");
			method_column_name = list_class.getDeclaredMethod("columnName");
			method_class_name = list_class.getDeclaredMethod("className");
			sql = method_sql.invoke(ann_one);
			columnName = method_column_name.invoke(ann_one);
			className = method_class_name.invoke(ann_one);
			clazz = Class.forName(className.toString());
			// 获取columnName列中对应的数据
			param = rsp.getObject((String) columnName);

			presta = setParam(con, sql.toString(), param);
			// 执行SQL语句
			rs = presta.executeQuery();

			// 获取clazz中所有的字段
			Field[] f_arr = clazz.getDeclaredFields();
			System.out.println(Arrays.toString(f_arr));
			// 设置所有属性可访问
			for (Field f : f_arr) {
				f.setAccessible(true);
			}
			List list = new ArrayList();
			while (rs.next()) {
				Object e = clazz.newInstance();
				// 将获取的内容封装到对象中
				for (Field f : f_arr) {
					// 属性的字段名和列名一样
					String f_name = f.getName();
					try {
						if (f.getType() == Integer.class || f.getType() == int.class) {
							int val = rs.getInt(f_name);
							f.set(e, val);
						} else if (f.getType() == String.class) {
							String val = rs.getString(f_name);
							f.set(e, val);
						} else {
							Object val = rs.getObject(f_name);
							f.set(e, val);
						}
					} catch (SQLException ee) {
						//ee.printStackTrace();
					}
				}
				list.add(e);
			}
			field.set(obj, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, presta, rs);
		}
	}

}
