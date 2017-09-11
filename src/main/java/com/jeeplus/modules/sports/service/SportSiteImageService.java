package com.jeeplus.modules.sports.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.sports.dao.ImageDao;
import com.jeeplus.modules.sports.entity.Image;


@Service
@Transactional
public class SportSiteImageService extends CrudService<ImageDao, Image> {

	/**
	 * 
	 * @param image_name
	 * @return
	 */
	public Image getImageByName(String image_name)
	{
		return	dao.getImageByName(image_name);
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public Image getImage(Image image)
	{
		return	dao.get(image);
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public int	addImage(Image image)
	{
		return	dao.insert(image);
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public int 	updateImage(Image image)
	{
		return	dao.update(image);
	}
	
	/**
	 * 
	 * @param image
	 * @return
	 */
	public int 	deleteImage(Image image)
	{
		return	dao.delete(image);
	}
}


