package com.easy.util;

public class RegUtil {
	private static final String reg6_16="^\\w{6,16}$";
	public static boolean test(String reg,String str) {
		return str.matches(reg);
	}
	
	public static boolean test6_16(String str) {
		if(str==null) {
			return false;
		}
		return str.matches(reg6_16);
	}

	public static boolean test6_16(String...strs) {
		if(strs==null||strs.length==0) {
			return false;
		}
		for(int i=0;i<strs.length;i++) {
			if(strs[i]==null||!strs[i].matches(reg6_16)) {
				return false;
			}
		}
		return true;
	}
}
