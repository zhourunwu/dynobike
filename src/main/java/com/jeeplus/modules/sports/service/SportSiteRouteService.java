package com.jeeplus.modules.sports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.sports.dao.ImageDao;
import com.jeeplus.modules.sports.dao.RouteDao;
import com.jeeplus.modules.sports.entity.Image;
import com.jeeplus.modules.sports.entity.Route;


@Service
@Transactional
public class SportSiteRouteService extends CrudService<RouteDao, Route> {

	@Autowired
	private ImageDao imageDao;
	
	/**
	 * 
	 * @return
	 */
	public List<Route>	getAllRoutes()
	{
		Route	entity = new Route();
		
		return	dao.findAllList(entity);
	}
	
	/**
	 * 
	 * @param route_id
	 * @return
	 */
//	public Route	getRouteById(int route_id)
//	{
//		Route	entity = new Route();
//		
//		entity.setRoute_id(route_id);
//		
//		return	dao.get(entity);
//	}
	
	/**
	 * get Route
	 */
	public Route 	get(Route entity)
	{
		Route	route = dao.get(entity);
		Image	image = new Image();
		Image	imageLoaded;
		
		if	(route == null)
			return	route;
		
		image.setImg_id(route.getRoute_photo_id());
		
		imageLoaded = imageDao.get(image);
		
		route.setCover_image(imageLoaded);
		
		return	route;
	}
	
	/**
	 * Add Route
	 * @param route
	 * @return
	 */
	public int	addRoute(Route route)
	{
		return	dao.insert(route);
	}
	
	/**
	 * delete Route
	 * @param route
	 * @return
	 */
	public int	deleteRoute(Route route)
	{
		return	dao.delete(route);
	}
	
	/**
	 * update Route
	 * @param route
	 * @return
	 */
	public int	updateRoute(Route route)
	{
		return	dao.update(route);
	}
}
