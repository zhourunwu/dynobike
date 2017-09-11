package com.jeeplus.modules.sports.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeeplus.modules.sports.util.FileUtil;

/**
 * 用于处理文件下载的Controller
 * @author Mark
 *
 */
@Controller
public class ResourceDownloadController {

	/**
	 * 
	 * @param strPath
	 * @param request
	 * @param response
	 * @param referer
	 */
	@RequestMapping(value = "/image_get/{path:.*}", method=RequestMethod.GET)
	public void getImage(@PathVariable String path,
						HttpServletRequest	request,
						HttpServletResponse	response)
	{
		long	nfileSize;
		
		//if	(referer != null)
		{
			System.out.println("prepare for downloading");
			System.out.println("path = " + path);
			
			String	imageDir = request.getServletContext().getRealPath("image");
			
			File	file = new File(imageDir, path);
			
			System.out.println(file.getName());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());
			
			if	(file.exists())
			{
				response.setContentType("image/jpeg");
				
				nfileSize = FileUtil.getFileSizes(file);
				
				response.setContentLength((int) nfileSize);
				
				response.setStatus(HttpServletResponse.SC_OK);
				
				byte[]	buffer = new byte[1024];
				
				FileInputStream	fis = null;
				BufferedInputStream	bis = null;
				
				try
				{
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					
					OutputStream	os = response.getOutputStream();
					
					int	i	= bis.read(buffer);
					
					while (i != -1)
					{
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}								
				}
				catch(IOException ex) {
					
					System.out.println(ex.toString());
				}
				finally
				{
					if 	(bis != null)
					{
						try 
						{
							bis.close();							
						}
						catch(IOException e) 
						{
							
						}
					}
					
					if	(fis != null)
					{
						try 
						{
							fis.close();							
						}
						catch(IOException ex)
						{
							
						}
					}
				}
			}
			else
			{
				System.out.println("file does not exist");
			}
		}
//		else
//		{
//			System.out.println("referer is null");
//		}
	}
	
	/**
	 * 
	 * @param strPath
	 * @param request
	 * @param response
	 * @param referer
	 */
	@RequestMapping(value = "/video_get/{path:.*}", method=RequestMethod.GET)
	public void getVideo(@PathVariable String path,
				HttpServletRequest	request,
				HttpServletResponse	response)
	{
		long		nfileSize;
		
		//if	(referer != null)
		{
			System.out.println("prepare for video downloading");
			System.out.println("path = " + path);
			
			String	imageDir = request.getServletContext().getRealPath("video");
			
			File	file = new File(imageDir, path);
			
			System.out.println(file.getName());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());
						
			if	(file.exists())
			{
				response.setContentType("video/x-msvideo");
				
				nfileSize = FileUtil.getFileSizes(file);
				
				response.setContentLength((int) nfileSize);
				
				response.setStatus(HttpServletResponse.SC_OK);
				
				byte[]	buffer = new byte[1024];
				
				FileInputStream	fis = null;
				BufferedInputStream	bis = null;
				
				try
				{
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					
					OutputStream	os = response.getOutputStream();
					
					int	i	= bis.read(buffer);
					
					while (i != -1)
					{
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}								
				}
				catch(IOException ex) {
					
					System.out.println(ex.toString());
				}
				finally
				{
					if 	(bis != null)
					{
						try 
						{
							bis.close();							
						}
						catch(IOException e) 
						{
							
						}
					}
					
					if	(fis != null)
					{
						try 
						{
							fis.close();							
						}
						catch(IOException ex)
						{
							
						}
					}
				}
			}
		}
	}
	
}
