package com.jeeplus.modules.sports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.sports.dao.ImageDao;
import com.jeeplus.modules.sports.dao.VideoDao;
import com.jeeplus.modules.sports.entity.Image;
import com.jeeplus.modules.sports.entity.Video;

@Service
@Transactional
public class SportSiteVideoService extends CrudService<VideoDao, Video> {

	@Autowired
	private	ImageDao	imageDao;
	
	/**
	 * 
	 * @return
	 */
	public List<Video>	getAllVideos()
	{
		Video	entity = new Video();
		
		return	dao.findAllList(entity);
	}
	
	/**
	 * 
	 * @param route_id
	 * @return
	 */
	public List<Video>	getVideosByRouteId(int route_id)
	{
//		Video	entity = new Video();
//		
//		entity.setRoute_id(route_id);
		Image	image = new Image();
		Image	imageLoaded;
		
		List<Video>	videolist = dao.getVideosByRouteId(route_id);
		
		for	(Video v : videolist)
		{			
			image.setImg_id(v.getVideo_photo_id());
			
			imageLoaded = imageDao.get(image);
			
			v.setCover_image(imageLoaded);
		}
		
		return	videolist;
	}
	
	/**
	 * 
	 */
	public Video 	get(Video entity)
	{
		Video	video = dao.get(entity);
		Image	image = new Image();
		Image	imageLoaded;
		
		if	(video == null)
			return	video;
		
		image.setImg_id(video.getVideo_photo_id());
		
		imageLoaded = imageDao.get(image);
		
		video.setCover_image(imageLoaded);
		
		return	video;
	}
	
	/**
	 * 
	 * @param route
	 * @return
	 */
	public int	addVideo(Video video)
	{
		return	dao.insert(video);
	}
	
	/**
	 * 
	 * @param route
	 * @return
	 */
	public int	deleteVideo(Video video)
	{
		return	dao.delete(video);
	}
	
	/**
	 * 
	 * @param route
	 * @return
	 */
	public int	updateVideo(Video video)
	{
		return	dao.update(video);
	}
	
}
