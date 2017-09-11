package com.jeeplus.modules.sports.restcontroller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jeeplus.modules.sports.entity.Image;
import com.jeeplus.modules.sports.entity.ImageOpResult;
import com.jeeplus.modules.sports.entity.UploadResult;
import com.jeeplus.modules.sports.entity.UploadToken;
import com.jeeplus.modules.sports.entity.Video;
import com.jeeplus.modules.sports.service.SportSiteImageService;
import com.jeeplus.modules.sports.util.PublicConfig;
import com.jeeplus.modules.sports.util.UploadUtil;
import com.jeeplus.modules.sports.util.UploaderUtil;
import com.jeeplus.modules.sports.util.UploaderUtil.UploaderResult;


@RestController
@RequestMapping(value = "${adminPath}/sports/resmgmt")
public class ImgUploadController {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private	SportSiteImageService	sportSiteImageService;
	
	/**
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/uploadVideoFile", method=RequestMethod.POST)
	public @ResponseBody UploadResult	uploadVideoFile(
			HttpServletRequest 	request,
			MultipartFile 		file)
	{
		UploadResult	uploadResult;
//		int				upload_id;
		    	
/*		
    	String 	path 	 = 	request.getSession().getServletContext().getRealPath(PublicConfig.VIDEO_FILE_PATH);  
        String 	fileName = 	file.getOriginalFilename();  

        File 	targetFile = new File(path, fileName);  
         
        if	(!targetFile.exists())
        {  
            targetFile.mkdirs();  
        }  
   
        //	保存  
        try 
        {  
            file.transferTo(targetFile);  
        } 
        catch (Exception e) {  
        	
            e.printStackTrace();  
        }  
		
        //	
        //	Transfer to Qiniu
        //
        
        String 	localFilePath = (path + File.separator + fileName);
*/

		UploadUtil	upload_util = new UploadUtil();

		String 	fileName = 	file.getOriginalFilename();  
		
//		uploadResult = upload_util.uploadwithResult(localFilePath, fileName);

		try 
		{
			uploadResult = upload_util.uploadByStreamwithResult(file.getInputStream(), fileName);
		}
		catch(Exception ex)
		{
			uploadResult = new UploadResult();
			
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
			
			logger.debug("upload success : fileName : " + fileName);
			logger.debug("video url : "  + uploadResult.getUrl());
			logger.debug("video name : " + uploadResult.getName());			
		}
		else
		{
			uploadResult.setUpload_id(0);	
			
			logger.debug("upload failed : fileName : " + fileName);
		}
		
        return	uploadResult;
	}
	
	/**
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/uploadImage", method=RequestMethod.POST)
	public @ResponseBody UploadResult	uploadImage(
			HttpServletRequest 	request,
			MultipartFile 		file)
	{
		UploadResult	uploadResult;
		Image			image_to_add;
		int				upload_id;
    	
/*		
    	String 	path = request.getSession().getServletContext().getRealPath(PublicConfig.IMAGE_FILE_PATH);  
        String 	fileName = file.getOriginalFilename();  

        File 	targetFile = new File(path, fileName);  
         
        if	(!targetFile.exists())
        {  
            targetFile.mkdirs();  
        }  
   
        //	����  
        try 
        {  
            file.transferTo(targetFile);  
        } 
        catch (Exception e) {  
        	
            e.printStackTrace();  
        }  
                
        //	
        //	Transfer to Qiniu
        //
        
        String 	localFilePath = (path + File.separator + fileName);
*/
		
		UploadUtil	upload_util = new UploadUtil();

		String 	fileName = 	file.getOriginalFilename();  
		    	    	
//		uploadResult = upload_util.uploadwithResult(localFilePath, fileName);

		try 
		{
			uploadResult = upload_util.uploadByStreamwithResult(file.getInputStream(), fileName);
		}
		catch(Exception ex)
		{
			uploadResult = new UploadResult();
			
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
		
			sportSiteImageService.addImage(image_to_add);
		
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
		
        return	uploadResult;
	}
	
	/**
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/getUpToken", method=RequestMethod.GET)
	public @ResponseBody UploadToken	getUpToken(HttpServletRequest request, HttpServletResponse response)
	{
		UploadUtil	uputil = new UploadUtil();
		
		return	uputil.getUploadToken();
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="AddImage", method=RequestMethod.POST)
	public @ResponseBody ImageOpResult AddImage(
			@RequestBody	Image 	image,
			HttpServletRequest 		request, 
			HttpServletResponse 	response,
			BindingResult 			bindingResult)
	{
		ImageOpResult	result = new ImageOpResult();
		int				upload_id;
		
		if	(bindingResult.hasErrors())
		{
			result.setStatus(1);
			
			return	result;
		}
		
		//	Add Image
		sportSiteImageService.addImage(image);
		
		upload_id = image.getImg_id();		
		
		result.setStatus(0);
		result.setName(image.getImg_name());
		result.setUpload_id(upload_id);
		result.setUrl(image.getImg_url());
		
		return	result;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="UpdateImage", method=RequestMethod.POST)
	public @ResponseBody ImageOpResult UpdateImage(
			@RequestBody	Image 	image,
			HttpServletRequest 		request, 
			HttpServletResponse 	response,
			BindingResult 			bindingResult)
	{
		ImageOpResult	result = new ImageOpResult();
		int				upload_id;
		
		if	(bindingResult.hasErrors())
		{
			result.setStatus(1);
			
			return	result;
		}
		
		//	Update Image		
		sportSiteImageService.updateImage(image);
		
		upload_id = image.getImg_id();		
		
		result.setStatus(0);
		result.setName(image.getImg_name());
		result.setUpload_id(upload_id);
		result.setUrl(image.getImg_url());
		
		return	result;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="DeleteImage", method=RequestMethod.POST)
	public @ResponseBody ImageOpResult DeleteImage(
			@RequestBody	Image 	image,
			HttpServletRequest 		request, 
			HttpServletResponse 	response,
			BindingResult 			bindingResult)
	{
		ImageOpResult	result = new ImageOpResult();
		int				upload_id;
		
		if	(bindingResult.hasErrors())
		{
			result.setStatus(1);
			
			return	result;
		}
		
		upload_id = image.getImg_id();	

		//	delete Image
		sportSiteImageService.delete(image);
		
		result.setStatus(0);
		result.setName(image.getImg_name());
		result.setUpload_id(upload_id);
		result.setUrl(image.getImg_url());
		
		return	result;
	}	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = {"/uploadVideo", "/uploadVideo.json"}, method = RequestMethod.POST)
	public @ResponseBody UploadResult uploadVideoV2(
			HttpServletRequest	request,
			HttpServletResponse	response)
	{
		UploadResult   	upResult = new UploadResult();
    	List<String>	allowPatterns = new ArrayList<String>();

    	//	allow file patterns
    	allowPatterns.add(".mp4");
    	allowPatterns.add(".avi");
    	
    	//	Do upload
        UploaderResult result = UploaderUtil.uploader_qiniu(request, "/data", "filenameUploader", "/uploader", allowPatterns, 1024 * 1024);    
        
        //	
    	//	upload success
    	//

        if	(result.getCode() == "0")
        {        	
        	//	return	result
            upResult.setName(result.getNames().get(0));
            upResult.setStatus(0);
            upResult.setUrl(result.getUrls().get(0)); 
            upResult.setUpload_id(0);
        }
        else
        {
        	//	return	result
            upResult.setName(result.getNames().get(0));
            upResult.setStatus(1);
            upResult.setUrl(result.getUrls().get(0));
            upResult.setUpload_id(0);
        }
        
        return 	upResult;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * ${adminPath}/sports/resmgmt/upload
	 */
    @RequestMapping(value = {"/upload", "/upload.json"}, method = RequestMethod.POST)
    public @ResponseBody UploadResult uploadImageV2(
    		HttpServletRequest 	request, 
    		HttpServletResponse response) 
    {    	
    	UploadResult   	upResult = new UploadResult();
    	Image			image_to_add;
    	int				upload_id;
    	
    	//	Do upload
        UploaderResult result = UploaderUtil.uploader_qiniu(request, "/data", "filenameUploader", "/uploader", null, 1024 * 1024);    
        
        //	Add Image to database
        //	
    	//	upload success
    	//

        if	(result.getCode() == "0")
        {
        	image_to_add = new Image();
    		
        	image_to_add.setCreate_time(new Date());
        	image_to_add.setCreator_id(1);
        	image_to_add.setImg_id(0);
        	image_to_add.setImg_url(result.getUrls().get(0));
        	image_to_add.setIsuploaded(true);
        	image_to_add.setImg_name(result.getNames().get(0));
    		
        	sportSiteImageService.addImage(image_to_add);
    		
        	upload_id = image_to_add.getImg_id();
    		        	    		
        	logger.debug("upload success : Image Name : " + result.getNames().get(0));
        	logger.debug("image url : "  + image_to_add.getImg_url());
        	logger.debug("image name : " + image_to_add.getImg_name());    
        	logger.debug("image id:"     + image_to_add.getImg_id());
        	
        	//	return	result
            upResult.setName(result.getNames().get(0));
            upResult.setStatus(0);
            upResult.setUrl(result.getUrls().get(0)); 
            upResult.setUpload_id(upload_id);
        }
        else
        {
        	//	return	result
            upResult.setName(result.getNames().get(0));
            upResult.setStatus(1);
            upResult.setUrl(result.getUrls().get(0));
            upResult.setUpload_id(0);
        }
        
        return 	upResult;
    }    	
}
