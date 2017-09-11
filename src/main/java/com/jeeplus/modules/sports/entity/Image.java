package com.jeeplus.modules.sports.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jeeplus.common.persistence.DataEntity;

public class Image extends DataEntity<Image> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3438493198561088117L;
	
	private int 	img_id;
	private String 	img_name;
	private int 	creator_id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date 	create_time;
	
	private String 	img_url;
	private boolean isuploaded;
	
	//	img_id
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	
	//	img_name
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	
	//	creator_id
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	
	//	create_time
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	//	img_url
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	//	isuploaded
	public boolean isIsuploaded() {
		return isuploaded;
	}
	public void setIsuploaded(boolean isuploaded) {
		this.isuploaded = isuploaded;
	}
	
}
