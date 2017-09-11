package com.jeeplus.modules.sports.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Video;

@MyBatisDao
public interface VideoDao extends CrudDao<Video> {
	
	/**
	 * 
	 */
	public List<Video> findList(Video entity);
	
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	public List<Video> findAllList(Video entity);
	

	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */

	public List<Video>	getVideosByRouteId(int route_id);
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public Video get(Video entity);
	
//	public Video	getVideoByVideoId(int video_id)
//	{
//		Video	video;
//		Image	cover_img;
//		
//		video = _videoMapper.getVideoByVideoId(video_id);
//		
//		if	(video == null)
//			return	video;
//		
//		cover_img = _imageMapper.getImageById(video.getVideo_photo_id());
//		
//		video.setCover_image(cover_img);
//		
//		return	video;
//	}
	
	/**
	 * 
	 * @param video
	 * @return
	 */
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(Video entity);
	
	/**
	 * 
	 * @param video
	 * @return
	 */
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public int delete(Video entity);
	
	/**
	 * 
	 * @param video
	 * @return
	 */
	public int update(Video entity);	
}
