package com.jeeplus.modules.sports.entity;

import java.io.Serializable;

public class UploadToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7742007374425732223L;
	
	private String uptoken;

	public String getUptoken() {
		return uptoken;
	}

	public void setUptoken(String uptoken) {
		this.uptoken = uptoken;
	}
}
