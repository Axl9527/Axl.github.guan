package com.easy.bean;

public class Class {
private int id;
private String cid;
private String cclass;

public Class() {
	super();
}

public Class(int id, String cid, String cclass) {
	super();
	this.id = id;
	this.cid = cid;
	this.cclass = cclass;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCid() {
	return cid;
}

public void setCid(String cid) {
	this.cid = cid;
}

public String getCclass() {
	return cclass;
}

public void setCclass(String cclass) {
	this.cclass = cclass;
}

@Override
public String toString() {
	return "Class [id=" + id + ", cid=" + cid + ", cclass=" + cclass + "]";
}


}
