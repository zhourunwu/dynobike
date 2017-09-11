package com.jeeplus.modules.sports.service.impl;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.dao.ManufacturerDao;
import com.jeeplus.modules.sports.entity.Manufacturer;
import com.jeeplus.modules.sports.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerDao manufacturerDao;

	/**
	 * 获取厂商列表
	 * @param manufacturer
	 * @return
	 */
	public Page<Manufacturer> getManufacturerList(Page<Manufacturer> manufacturerPage, Manufacturer manufacturer)
	{
		manufacturer.setPage(manufacturerPage);
		manufacturerPage.setList(manufacturerDao.getManufacturerList(manufacturer));
		return	manufacturerPage;
	}

	public List<Manufacturer> getManufacturerList(Manufacturer manufacturer)
	{
		return	manufacturerDao.getManufacturerList(manufacturer);
	}
	/**
	 * 获取厂商总件数
	 * @param manufacturer
	 * @return
	 */
	public int	getManufacturerListCount(Manufacturer manufacturer)
	{
		return manufacturerDao.getManufacturerListCount(manufacturer);
	}

	/**
	 * 获取厂商详情
	 * @param id
	 * @return
     */
	public Manufacturer getManufacture(Integer id) {
		return manufacturerDao.selectByPrimaryKey(id);
	}
	/**
	 * 添加|修改厂商
	 */
	public void saveOrUpdateManufacturer(Manufacturer manufacturer)
	{
        if (manufacturer.getId() != null && manufacturer.getId() > 0){
			manufacturerDao.updateByPrimaryKeySelective(manufacturer);
        }else{
			manufacturerDao.insertSelective(manufacturer);
        }
	}

	/**
	 * 删除厂商
	 * @param id
	 * @return
	 */
	public int	deleteManufacturer(int id)
	{
		return	manufacturerDao.deleteByPrimaryKey(id);
	}

}
