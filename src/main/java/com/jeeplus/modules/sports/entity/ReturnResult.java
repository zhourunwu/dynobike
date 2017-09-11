package com.jeeplus.modules.sports.entity;

import java.io.Serializable;

public class ReturnResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -976271711138240669L;
	
	private	boolean	success;
	private	String	errorMsg;
	
	//	success
	public boolean	getSuccess() {
		return	success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	//	errorMsg
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}	
}
