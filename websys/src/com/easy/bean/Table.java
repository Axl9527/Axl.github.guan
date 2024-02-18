package com.easy.bean;

public class Table {
private int tid;
private int tnumbers;
private String tstate;
private String tname;
public Table(int tid, int tnumbers, String tstate, String tname) {
	super();
	this.tid = tid;
	this.tnumbers = tnumbers;
	this.tstate = tstate;
	this.tname = tname;
}
public Table() {
	super();
}
@Override
public String toString() {
	return "Table [tid=" + tid + ", tnumbers=" + tnumbers + ", tstate=" + tstate + ", tname=" + tname + "]";
}
public int getTid() {
	return tid;
}
public void setTid(int tid) {
	this.tid = tid;
}
public int getTnumbers() {
	return tnumbers;
}
public void setTnumbers(int tnumbers) {
	this.tnumbers = tnumbers;
}
public String getTstate() {
	return tstate;
}
public void setTstate(String tstate) {
	this.tstate = tstate;
}
public String getTname() {
	return tname;
}
public void setTname(String tname) {
	this.tname = tname;
}

}
