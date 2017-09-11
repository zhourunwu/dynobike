package com.jeeplus.modules.sports.restcontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeeplus.modules.sports.entity.Image;
import com.jeeplus.modules.sports.entity.ReturnResult;
import com.jeeplus.modules.sports.entity.Route;
import com.jeeplus.modules.sports.entity.Video;
import com.jeeplus.modules.sports.service.SportSiteImageService;
import com.jeeplus.modules.sports.service.SportSiteRouteService;
import com.jeeplus.modules.sports.service.SportSiteVideoService;
import com.jeeplus.modules.sports.util.FloatEditor;


@RestController
@RequestMapping(value="/terminal")
public class DynoBikeTerminalController {
	
	@Autowired
	private SportSiteImageService 	sportSiteImageService;
	
	@Autowired
	private SportSiteRouteService	sportSiteRouteService;
	
	@Autowired
	private	SportSiteVideoService	sportSiteVideoService;
	
	/**
	 * get All Routes
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/getAllRoutes", method=RequestMethod.POST)
	public @ResponseBody List<Route> getAllRoutes(HttpServletRequest request, HttpServletResponse response)
	{
		return	sportSiteRouteService.getAllRoutes();
	}
	
	/**
	 * get Route By Id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getRouteById", method=RequestMethod.POST)
	public @ResponseBody Route getRouteById(
			@RequestParam		int	route_id,
			HttpServletRequest 	request, 
			HttpServletResponse response)
	{
		Route	route = new Route();
		
		route.setRoute_id(route_id);
		
		return	sportSiteRouteService.get(route);
	}
	
	/**
	 * get Videos by Route Id
	 * @return
	 */
	@RequestMapping(value="/getVideosByRouteId", method=RequestMethod.POST)
	public @ResponseBody List<Video>	getVideosByRouteId(
			@RequestParam		int	route_id,
			HttpServletRequest	request,
			HttpServletResponse	response)
	{
		return	sportSiteVideoService.getVideosByRouteId(route_id);
	}
	
	/**
	 * Get Image By Id
	 * @return
	 */
	@RequestMapping(value="/getImageById", method=RequestMethod.POST)
	public @ResponseBody Image getImageById(
			@RequestParam		int	image_id,
			HttpServletRequest	request,
			HttpServletResponse	response)
	{
		Image	image = new Image();
		
		image.setImg_id(image_id);
		
		return	sportSiteImageService.get(image);
	}
	
	/**
	 * get All Route Videos
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getAllVideos", method=RequestMethod.POST)
	public @ResponseBody List<Video> getAllRouteVideos(HttpServletRequest request, HttpServletResponse response)
	{
		Video	entity = new Video();
		
		return	sportSiteVideoService.findList(entity);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/AddRoute", method=RequestMethod.POST)
	public @ResponseBody ReturnResult AddRoute(
			@RequestBody		Route route,
			HttpServletRequest 	request, 
			HttpServletResponse response,
			BindingResult 		bindingResult)
	{
		ReturnResult	result = new ReturnResult();
		int				nRes;
		
		if	(bindingResult.hasErrors())
		{
			result.setSuccess(false);
			result.setErrorMsg(bindingResult.getFieldError().getDefaultMessage());
			
			return	result;
		}
		
		System.out.println("creator_id : " + route.getCreator_id());
		System.out.println("route_name : " + route.getRoute_name());
		System.out.println("route_intr : " + route.getRoute_intr());
		System.out.println("route_photo_id : " + route.getRoute_photo_id());
		System.out.println("create_time : " + route.getCreate_time());
		System.out.println("lastmodifytime : " + route.getLastmodifytime());
		
		//	Add Route
		nRes = sportSiteRouteService.addRoute(route);
		
		System.out.println("create_time (after): " + route.getCreate_time());
		System.out.println("lastmodifytime(after) : " + route.getLastmodifytime());
		
		if	(nRes != 0)
		{
			result.setSuccess(true);
			result.setErrorMsg("");
		}
		else
		{
			result.setSuccess(false);
			result.setErrorMsg("Error in handling");
		}
		
		return	result;
	}
	
	/**
	 * 
	 * @param route
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/DeleteRoute", method=RequestMethod.POST)
	public @ResponseBody		ReturnResult	DeleteRoute(
			@RequestBody		Route route,
			HttpServletRequest 	request, 
			HttpServletResponse response)
	{
		ReturnResult	result = new ReturnResult();
		int				nRes;
		
		//	Delete Route
		nRes = sportSiteRouteService.deleteRoute(route);
		
		if	(nRes != 0)
		{
			result.setSuccess(true);
			result.setErrorMsg("");
		}
		else
		{
			result.setSuccess(false);
			result.setErrorMsg("Error in handling");
		}
		
		return	result;
	}
	
	/**
	 * 
	 * @param route
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/UpdateRoute", method=RequestMethod.POST)
	public @ResponseBody	ReturnResult	UpdateRoute(
			@RequestBody		Route route,
			HttpServletRequest 	request, 
			HttpServletResponse response)
	{
		ReturnResult	result = new ReturnResult();
		int				nRes;
		
		//	Update Route
		nRes = sportSiteRouteService.updateRoute(route);
				
		if	(nRes != 0)
		{
			result.setSuccess(true);
			result.setErrorMsg("");
		}
		else
		{
			result.setSuccess(false);
			result.setErrorMsg("Error in handling");
		}
		
		return	result;
	}
	
	@InitBinder    
	protected void initBinder(WebDataBinder binder) {
		
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
//		binder.registerCustomEditor(int.class, new CustomNumberEditor(int.class, true));
//		binder.registerCustomEditor(int.class, new IntegerEditor());
//		binder.registerCustomEditor(long.class, new CustomNumberEditor(long.class, true));
//		binder.registerCustomEditor(long.class, new LongEditor());
//		binder.registerCustomEditor(double.class, new DoubleEditor());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		binder.registerCustomEditor(float.class, new FloatEditor());
	}   
}
