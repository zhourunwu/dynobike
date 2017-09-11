package com.jeeplus.modules.sports.web;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jeeplus.modules.sports.entity.Image;
import com.jeeplus.modules.sports.entity.Route;
import com.jeeplus.modules.sports.entity.UploadResult;
import com.jeeplus.modules.sports.entity.Video;
import com.jeeplus.modules.sports.service.SportSiteImageService;
import com.jeeplus.modules.sports.service.SportSiteRouteService;
import com.jeeplus.modules.sports.service.SportSiteVideoService;
import com.jeeplus.modules.sports.util.UploadUtil;

@Controller
@RequestMapping(value = "${adminPath}/sports/route")
public class RouteController {

    /** AdminPath **/  
    private String strAdminPath;  
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private	SportSiteRouteService	routeService;
	
	@Autowired
	private SportSiteVideoService	videoService;
	
	@Autowired
	private SportSiteImageService	imageService;
	
	public	RouteController()
	{
//		strAdminPath = "${adminPath}";
		strAdminPath = "/a";
	}
	
	/**
	 * 添加运动路线
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/routeAdd")
	public ModelAndView	routeAdd(
				HttpServletRequest 	request, 
				HttpServletResponse response,
				Model model)
	{
		ModelAndView	mv = new ModelAndView();
		
		Route			routeToAdd = new Route();
		
		mv.setViewName("modules/sports/routeAdd");		
		
		model.addAttribute("routetoAdd", routeToAdd);
		
		return	mv;
	}
	
	/**
	 * 添加运动路线保存
	 * @return
	 */
	@RequestMapping(value="/routeAddSave")
	public String routeAddSave(
			@ModelAttribute	Route	routetoAdd)
	{
		String	redirectUrl;
		Image	image_to_add;
		
		routetoAdd.setCreate_time(new Date());
		routetoAdd.setLastmodifytime(new Date());
		
		image_to_add = imageService.getImageByName(routetoAdd.getCover_image().getImg_name());
		if	(image_to_add != null)
		{			
			routetoAdd.setRoute_photo_id(image_to_add.getImg_id());
		}
		else
		{
			routetoAdd.setRoute_photo_id(0);
		}
		
		//	write into database
		routeService.addRoute(routetoAdd);
		
		redirectUrl = ("redirect:" + strAdminPath);
		redirectUrl += "/sports/route/getAllRoutes";
		
		logger.debug("redirect to :" + redirectUrl);
		logger.debug("cover image name : " + routetoAdd.getCover_image().getImg_name());
		
		return	redirectUrl;
	}
	
	/**
	 * 通过ID，编辑运动路线
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/RouteEditByID")
	public ModelAndView routeEdit(
			@RequestParam(value="route_id") int route_id,
			HttpServletRequest 	request, 
			HttpServletResponse response,
			Model	model)
	{
		ModelAndView	mv = new ModelAndView();
		
		Image	cover_image_toload = new Image();
		Image	cover_image;
		Route	routeToLoad = new Route();
		Route	routeToEdit;
		
		//	Load this route
		routeToLoad.setRoute_id(route_id);		
		routeToEdit = routeService.get(routeToLoad);
		
		//	Load the cover image of this route.
		cover_image_toload.setImg_id(routeToEdit.getRoute_photo_id());
		cover_image = imageService.get(cover_image_toload);
		routeToEdit.setCover_image(cover_image);
		
		//	Attach this route to the jsp
		model.addAttribute("routetoedit", routeToEdit);
		
		mv.setViewName("modules/sports/routeEdit");
		
		return	mv;		
	}
	
	/**
	 * 保存编辑后的运动路线
	 * @return
	 */
	@RequestMapping(value="/RouteUpdate")
	public String routeEditSave(
			@ModelAttribute Route	routetoSave,	
			HttpServletRequest 		request,
			HttpServletResponse 	response)
	{
		String	redirectUrl;
		Image	image_to_add;
		
		//	change Last-Modify-Time
		routetoSave.setLastmodifytime(new Date());
	
		//	Check whether user changes the cover image.
		if	(routetoSave.getCover_image() != null)
		{
			image_to_add = imageService.getImageByName(routetoSave.getCover_image().getImg_name());
			if	(image_to_add != null)
			{			
				//if	(routetoSave.getRoute_photo_id() != image_to_add.getImg_id())
				routetoSave.setRoute_photo_id(image_to_add.getImg_id());
			}
			else
			{
				routetoSave.setRoute_photo_id(0);
			}
		}
		
		//	write into database
		routeService.updateRoute(routetoSave);
		
		redirectUrl = ("redirect:" + strAdminPath);
		redirectUrl += "/sports/route/getAllRoutes";
		
		logger.debug("RouteUpdate - redirect to :" + redirectUrl);
		
		return	redirectUrl;			
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/RouteDelByID")
	public String RouteDelByID(
				@RequestParam(value="route_id") int route_id,
				HttpServletRequest	request,
				HttpServletResponse	response)
	{
		String	redirectUrl;
		Route	routeToDelete = new Route();
		
		routeToDelete.setRoute_id(route_id);
		
		//	Delete the image in the database
		
		//	Delete the Route
		routeService.delete(routeToDelete);
		
		redirectUrl = ("redirect:" + strAdminPath);
		redirectUrl += "/sports/route/getAllRoutes";
		
		logger.debug("RouteDelByID - redirect to :" + redirectUrl);
		
		return	redirectUrl;		
	}
	
	/**
	 * Get All routes available
	 * @return
	 */
	@RequestMapping(value = "/getAllRoutes")
	public ModelAndView	getAllRoutes()
	{
		ModelAndView	mv =  new ModelAndView("modules/sports/listRoute");
		
		List<Route>		routelist = routeService.getAllRoutes();
		Image			image = new Image();
		Image 			imageLoaded;
		
		mv.addObject("resultroute", routelist);
		
		if	(routelist == null)
		{
			logger.debug("routelist is null");			
		}
		
		for	(Route r : routelist)
		{
			logger.debug("route_id : " + r.getRoute_id());
			logger.debug("route_intr : " + r.getRoute_intr());
			logger.debug("route_name : " + r.getRoute_name());
			logger.debug("route create time : " + r.getFormattedCreatetime());
			logger.debug("route last modify time : " + r.getFormattedLastmodifytime());
			
			image.setImg_id(r.getRoute_photo_id());
			
			imageLoaded = imageService.get(image);
			
			r.setCover_image(imageLoaded);
			
			if	(imageLoaded != null)
				logger.debug("route cover image url:" + imageLoaded.getImg_url());
		}
		
		return	mv;
	}
	
	/**
	 * Get All of the videos in the specified route
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/RouteReadByID")
	public ModelAndView	listRoute(
			@RequestParam(value="route_id") int route_id,
			HttpServletRequest 	request, 
			HttpServletResponse response)
	{
		ModelAndView	mv = new ModelAndView();
		Route			routeToload = new Route();
		
		routeToload.setRoute_id(route_id);
		
		Route 			route	  = routeService.get(routeToload);
//		List<Video>		videolist = _videoDao.getVideosByRouteId(route_id);
		List<Video>		videolist = videoService.getVideosByRouteId(route_id);
				
		mv.addObject("route", 		route);			//	route info
		mv.addObject("videolist", 	videolist);		//	route video list
		
		mv.setViewName("modules/sports/RouteContent");
		
		return	mv;
	}
	
	/**
	 * 
	 * @param request
	 * @param file
	 * @return
	 */    
	@RequestMapping(value="/uploadImage", method=RequestMethod.POST)
	public ModelAndView uploadImage(
			HttpServletRequest 	request, 
			HttpServletResponse response)
	{
		UploadResult	uploadResult;
		Image			image_to_add;
		String 			fileName;
		int				upload_id;
		
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

            	/* 获取文件的后缀 */
//                suffix = fileName.substring(fileName.lastIndexOf("."));
//                
//                /* 使用时间戳生成文件名称 */
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//                String 	nowTime = sdf.format(new Date());
//                String 	qiniu_filename = nowTime + suffix;// 构建文件名称
//                
//                uploadResult = upload_util.uploadByStreamwithResult(multipartFile.getInputStream(), qiniu_filename);
            	
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
			
			image_to_add = new Image();
		
			image_to_add.setCreate_time(new Date());
			image_to_add.setCreator_id(1);
			image_to_add.setImg_id(0);
			image_to_add.setImg_url(uploadResult.getUrl());
			image_to_add.setIsuploaded(true);
			image_to_add.setImg_name(uploadResult.getName());
		
			imageService.addImage(image_to_add);
		
			upload_id = image_to_add.getImg_id();
		
			uploadResult.setUpload_id(upload_id);
			
			logger.debug("upload success : Image Name : " + uploadResult.getName());
			logger.debug("image url : " + image_to_add.getImg_url());
			logger.debug("image name : " + image_to_add.getImg_name());
		}
		else
		{
			uploadResult.setUpload_id(0);	
			
			logger.debug("upload failed : Image Name : " + uploadResult.getName());
		}
		
		mv.addObject("result", uploadResult);
		
		return	mv;
	}
}
