package com.clinic.common.datarec;

public class UserRole {

	private int uid;
	private String urname;
	private String urdesc;

	public UserRole() {
		// TODO Auto-generated constructor stub
		uid = -1;
		urname = "";
		urdesc = "";
	}

	public int UID () {
		return this.uid;
	}
	public void UID (int iuid) {
		this.uid = iuid;
	}
	
	public String URName(){
		return urname;
	}

	public void URName(String urname){
		this.urname=urname;
	}

	public String URDesc(){
		return urdesc;
	}

	public void URDesc(String urdesc){
		this.urdesc=urdesc;
	}
}
