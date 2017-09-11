package com.jeeplus.modules.sports.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jeeplus.common.persistence.DataEntity;

public class Video extends DataEntity<Video> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4017300098667421862L;
	
	private int 	video_id;
	private int 	route_id;
	private int 	video_seq;
	private String 	video_name;
	private int 	creator_id;
	private String 	video_intr;
	private int 	video_photo_id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date 	create_time;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date 	lastmodifytime;
	private boolean isFree;
	private float 	price;
	private String 	videofile_url;
	
	//	cover image
	private	Image	cover_image;
	
	//	video_id
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
	
	//	route_id
	public int getRoute_id() {
		return route_id;
	}
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	
	//	video_seq
	public int getVideo_seq() {
		return video_seq;
	}
	public void setVideo_seq(int video_seq) {
		this.video_seq = video_seq;
	}
	
	//	video_name
	public String getVideo_name() {
		return video_name;
	}
	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}
	
	//	creator_id
	public int getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}
	
	//	video_intr
	public String getVideo_intr() {
		return video_intr;
	}
	public void setVideo_intr(String video_intr) {
		this.video_intr = video_intr;
	}
	
	//	video_photo_id
	public int getVideo_photo_id() {
		return video_photo_id;
	}
	public void setVideo_photo_id(int video_photo_id) {
		this.video_photo_id = video_photo_id;
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
	
	//	videofile_url
	public String getVideofile_url() {
		return videofile_url;
	}
	public void setVideofile_url(String videofile_url) {
		this.videofile_url = videofile_url;
	}
	
	//	cover_image
	public Image getCover_image() {
		return cover_image;
	}
	public void setCover_image(Image cover_image) {
		this.cover_image = cover_image;
	}
}
