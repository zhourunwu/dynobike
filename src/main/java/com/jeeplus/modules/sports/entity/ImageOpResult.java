package com.jeeplus.modules.sports.entity;

import java.io.Serializable;

public class ImageOpResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5825425615856147099L;
	
	private	String	name;
	private	String	url;
	private int		upload_id;
	private	int		status;
	
	//	name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//	url
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	//	upload_id
	public int getUpload_id() {
		return upload_id;
	}
	public void setUpload_id(int upload_id) {
		this.upload_id = upload_id;
	}
	
	//	status
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}	
}
