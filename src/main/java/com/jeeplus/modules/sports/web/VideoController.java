package com.jeeplus.modules.sports.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jeeplus.modules.sports.entity.Image;
import com.jeeplus.modules.sports.entity.UploadResult;
import com.jeeplus.modules.sports.entity.Video;
import com.jeeplus.modules.sports.service.SportSiteImageService;
import com.jeeplus.modules.sports.service.SportSiteVideoService;
import com.jeeplus.modules.sports.util.UploadUtil;

@Controller
@RequestMapping(value = "${adminPath}/sports/video")
public class VideoController {
	
    /** AdminPath **/  
    private String strAdminPath;  
    
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private	SportSiteVideoService	videoService;
	
	@Autowired
	private SportSiteImageService	imageService;
	
	public	VideoController()
	{
//		strAdminPath = "${adminPath}";
		strAdminPath = "/a";
	}
	
	/**
	 * 获得视频信息
	 * @return
	 */
	@RequestMapping(value = "/VideoReadByID")
	public ModelAndView	VideoReadById(
			@RequestParam int 	video_id,
			HttpServletRequest 	request,
			HttpServletResponse response)
	{
		ModelAndView	mv = new ModelAndView("modules/sports/VideoContent");
				
		Video	entity = new Video();
		entity.setVideo_id(video_id);
		
		Video	video = videoService.get(entity);
		mv.addObject("video", video);
		
		return	mv;
	}
	
	/**
	 * 添加视频
	 * @return
	 */
	@RequestMapping(value = "/VideoAdd")
	public ModelAndView	VideoAdd(
			@RequestParam int 	route_id,
			HttpServletRequest 	request,
			HttpServletResponse response,
			Model				model)
	{
		ModelAndView	mv = new ModelAndView("modules/sports/videoAdd-qiniu");

		Video	entity = new Video();
		
		entity.setRoute_id(route_id);
		
		model.addAttribute("videotoAdd", entity);
		
		return	mv;
	}	
	
	/**
	 * 保存视频
	 * @param route_id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/VideoAddSave")
	public String VideoAddSave(
			@ModelAttribute 	Video entity,
			HttpServletRequest 	request,
			HttpServletResponse response)
	{
		String	redirectUrl;
		Image	image_to_add;
		
		entity.setCreate_time(new Date());
		entity.setLastmodifytime(new Date());
		
		image_to_add = imageService.getImageByName(entity.getCover_image().getImg_name());
		if	(image_to_add != null)
		{			
			entity.setVideo_photo_id(image_to_add.getImg_id());
		}
		else
		{
			entity.setVideo_photo_id(0);
		}
		
		//	Add video into the database.
		videoService.addVideo(entity);
		
		//	Redirect
		redirectUrl = "redirect:" + strAdminPath;
		redirectUrl += "/sports/route/RouteReadByID?route_id=" + entity.getRoute_id();
		
		logger.debug("redirect to " + redirectUrl);
		
		return	redirectUrl;
	}
	
	/**
	 * 编辑视频
	 * @return
	 */
	@RequestMapping(value = "/VideoEditByID")
	public ModelAndView	VideoEditByID(
			@RequestParam int 	video_id,
			HttpServletRequest 	request,
			HttpServletResponse response)
	{
		ModelAndView	mv = new ModelAndView("modules/sports/VideoEdit");
		
		Video	entity = new Video();
		entity.setVideo_id(video_id);
		
		Video	video = videoService.get(entity);
		mv.addObject("video", video);
		
		return	mv;
	}	
	
	/**
	 * 删除视频
	 * @return
	 */
	@RequestMapping(value = "/VideoDelByID")
	public String	VideoDelByID(
			@RequestParam int 	video_id,
			HttpServletRequest 	request,
			HttpServletResponse response)
	{
		String	redirectUrl;
		Video	videoToLoad = new Video();
		Video	videoToDelete;
		int		route_id;
		
		videoToLoad.setVideo_id(video_id);
		
		videoToDelete = videoService.get(videoToLoad);
		route_id = videoToDelete.getRoute_id();
		
		//	Delete the image in the database
		
		//	Delete the Video
		videoService.delete(videoToDelete);
		
		redirectUrl = ("redirect:" + strAdminPath);
		redirectUrl += "/sports/route/RouteReadByID?route_id=" + route_id;
		
		logger.debug("VideoDelByID - redirect to :" + redirectUrl);
		
		return	redirectUrl;			
	}		
	
	/**
	 * 更新视频
	 * @param route_id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/VideoUpdate")
	public String VideoUpdate(
			@ModelAttribute 	Video entity,
			HttpServletRequest 	request,
			HttpServletResponse response)
	{
		String		redirectUrl;
				
		//	Add video into the database.
		videoService.updateVideo(entity);
		
		//	Redirect
		redirectUrl = "redirect:" + strAdminPath;
		redirectUrl += "/sports/route/RouteReadByID?route_id=" + entity.getRoute_id();
		
		logger.debug("redirect to " + redirectUrl);
		
		return	redirectUrl;
	}	
	
	/**
	 * 
	 * @param request
	 * @param file
	 * @return
	 */    
	@RequestMapping(value="/uploadVideo", method=RequestMethod.POST)
	public ModelAndView uploadVideo(
			HttpServletRequest 	request, 
			HttpServletResponse response)
	{
		UploadResult	uploadResult;
		String 			fileName;
		
		ModelAndView mv = new ModelAndView();
		
		UploadUtil		upload_util = new UploadUtil();
		
		uploadResult = new UploadResult();
		
		uploadResult.setName("");
    	uploadResult.setStatus(0);
    	uploadResult.setUrl("");
    	
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
        /* 页面控件的文件流 */
        List<MultipartFile> multipartFileList = multipartRequest.getFiles("filenameUploader");
        if 	(multipartFileList.isEmpty()) {
        	
			uploadResult.setName("");
	    	uploadResult.setStatus(0);
	    	uploadResult.setUrl("");
	    	
	    	mv.addObject("result", uploadResult);
	    	
	    	return	mv;
        }
        
		try 
		{
            for (MultipartFile multipartFile : multipartFileList) 
            {
            	fileName  = multipartFile.getOriginalFilename();
            	
            	if 	("".equals(fileName)) {
                	
        			uploadResult.setName("");
        	    	uploadResult.setStatus(0);
        	    	uploadResult.setUrl("");
        	    	
        	    	mv.addObject("result", uploadResult);
        	    	
        	    	return	mv;
                }
            	
                uploadResult = upload_util.uploadByStreamwithResult(multipartFile.getInputStream(), fileName);                
            }
		}
		catch(Exception ex)
		{	
			//
			//	Set up default upload result
			//
			
			uploadResult.setName("");
	    	uploadResult.setStatus(0);
	    	uploadResult.setUrl("");
		}
		
		//
		//	Write into database
		//
		
		if	(uploadResult.getStatus() != 0)
		{
			//	
			//	upload success
			//
			
			uploadResult.setUpload_id(0);
			
			logger.debug("upload video success : fileName : " + uploadResult.getName());
			logger.debug("video url : "  + uploadResult.getUrl());
		}
		else
		{
			uploadResult.setUpload_id(0);	
			
			logger.debug("upload video failed : fileName : " + uploadResult.getName());
		}
		
		mv.addObject("result", uploadResult);
		
		return	mv;
	}	
}
