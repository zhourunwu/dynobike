package com.jeeplus.modules.sports.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NavBarItem implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8851142875977720316L;

	private int nav_id;
	
	private String nav_text;
	
	private String nav_url;
	
	private int nav_order;
	
	private int nav_pid;

	//	nav_id
	public int getNav_id() {
		return nav_id;
	}

	public void setNav_id(int nav_id) {
		this.nav_id = nav_id;
	}

	//	nav_text
	public String getNav_text() {
		return nav_text;
	}

	public void setNav_text(String nav_text) {
		this.nav_text = nav_text;
	}

	//	nav_url
	public String getNav_url() {
		return nav_url;
	}

	public void setNav_url(String nav_url) {
		this.nav_url = nav_url;
	}

	//	nav_order
	public int getNav_order() {
		return nav_order;
	}

	public void setNav_order(int nav_order) {
		this.nav_order = nav_order;
	}

	//	nav_pid
	public int getNav_pid() {
		return nav_pid;
	}

	public void setNav_pid(int nav_pid) {
		this.nav_pid = nav_pid;
	}	
}
