package com.jeeplus.modules.sports.dao;

import java.util.List;

import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Image;
import com.jeeplus.common.persistence.CrudDao;

@MyBatisDao
public interface ImageDao extends CrudDao<Image> {
	
	/**
	 * 
	 * @return
	 */
	public List<Image> findAllList(Image entity);

	
	/**
	 * 
	 * @param image_id
	 * @return
	 */
	
	/**
	 * 
	 * @param image_name
	 * @return
	 */
	public Image getImageByName(String image_name);
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
//	public Image get(String id);

	public Image get(Image entity);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(Image entity);

	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public int delete(Image entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(Image entity);	
}
