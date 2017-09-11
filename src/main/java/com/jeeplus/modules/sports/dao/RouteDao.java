package com.jeeplus.modules.sports.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Route;

@MyBatisDao
public interface RouteDao extends CrudDao<Route>{
	
	/**
	 * 
	 */
	public List<Route> findList(Route entity);
	
	/**
	 * 
	 * @return
	 */
	public List<Route> findAllList(Route entity);

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
//	public Route get(String id);
//	
//	public Route getRouteById(int route_id);
	
	public Route get(Route entity);
	
	/**
	 * 
	 * @param route
	 * @return
	 */
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(Route entity);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public int delete(Route entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(Route entity);	
}
