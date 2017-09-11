package com.jeeplus.modules.sports.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadImage implements Serializable 
{
    private static final long serialVersionUID = 1;

    private String 				name;
	private String 				note;
	private String 				urlPath;

	private List<MultipartFile>	images;

    // 	name
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    // 	note
    public String getNote() {
        return note;
    }
    public void setNote(String strNote) {
        this.note = strNote;
	}
	
	// 	images
    public List<MultipartFile> getImages() {
        return images;
    }
    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
    
    //	urlPath
	public String getUrlPath() {
		
		return urlPath;
	}
	
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
}
