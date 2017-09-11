package com.jeeplus.modules.sports.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jeeplus.common.persistence.DataEntity;

public class Route extends DataEntity<Route> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5707411733852393352L;

	private int 	route_id;
	
	private String 	route_name;
	private int  	creator_id;
	private String	route_intr;
	private int 	route_photo_id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date 	create_time = new Date();
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date 	lastmodifytime = new Date();
	
	private boolean isFree;
	private float 	price;
	
	//	cover image
	private	Image	cover_image;
	
	public Route() {
		super();
	}

	public Route(String id){
		super(id);
	}
	
	//	route_id
	public int getRoute_id() {
		return route_id;
	}
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	
	//	route_name
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	
	//	creator_id
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	
	//	route_intr
	public String getRoute_intr() {
		return route_intr;
	}
	public void setRoute_intr(String route_intr) {
		this.route_intr = route_intr;
	}
	
	//	route_photo_id
	public int getRoute_photo_id() {
		return route_photo_id;
	}
	public void setRoute_photo_id(int route_photo_id) {
		this.route_photo_id = route_photo_id;
	}
	
	//	create_time
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	//	返回格式化后的日期时间
	public String getFormattedCreatetime() {
	
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日");
		
		return	myFmt.format(create_time);
	}
		
	//	lastmodifytime
	public Date getLastmodifytime() {
		return lastmodifytime;
	}
	public void setLastmodifytime(Date lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	
	//	返回格式化后的日期时间
	public String getFormattedLastmodifytime() {
	
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日");
		
		return	myFmt.format(lastmodifytime);
	}
	
	//	isFree
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	//	price
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	//	cover_image
	public Image getCover_image() {
		return cover_image;
	}
	public void setCover_image(Image cover_image) {
		this.cover_image = cover_image;
	}
}
