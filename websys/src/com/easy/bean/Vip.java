package com.easy.bean;

public class Vip {
	private String vid;
	private String vname;
	private String vsex;
	private float vdiscount;
	private String vphone;
	private String vjointime;
	private int isdel;
	public Vip() {
		// TODO Auto-generated constructor stub
	}
	
	public Vip(String vid, String vname, String vsex, float vdiscount, String vphone, String vjointime, int isdel) {
		super();
		this.vid = vid;
		this.vname = vname;
		this.vsex = vsex;
		this.vdiscount = vdiscount;
		this.vphone = vphone;
		this.vjointime = vjointime;
		this.isdel = isdel;
	}
	
	public int getIsdel() {
		return isdel;
	}

	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}

	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getVsex() {
		return vsex;
	}
	public void setVsex(String vsex) {
		this.vsex = vsex;
	}
	public float getVdiscount() {
		return vdiscount;
	}
	public void setVdiscount(float vdiscount) {
		this.vdiscount = vdiscount;
	}
	public String getVphone() {
		return vphone;
	}
	public void setVphone(String vphone) {
		this.vphone = vphone;
	}
	public String getVjointime() {
		return vjointime;
	}
	public void setVjointime(String vjointime) {
		this.vjointime = vjointime;
	}

	@Override
	public String toString() {
		return "Vip [vid=" + vid + ", vname=" + vname + ", vsex=" + vsex + ", vdiscount=" + vdiscount + ", vphone="
				+ vphone + ", vjointime=" + vjointime + ", isdel=" + isdel + "]";
	}

	
}
