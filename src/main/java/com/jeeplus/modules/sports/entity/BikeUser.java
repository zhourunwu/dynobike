package com.jeeplus.modules.sports.entity;

import java.io.Serializable;

public class BikeUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3284523476994071836L;

	private int 	user_id;
	private String 	user_name;
	
	//	user_id
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	//	user_name
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}
