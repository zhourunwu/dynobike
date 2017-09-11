package com.jeeplus.modules.sports.service;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.entity.Manufacturer;

import java.util.List;


public interface ManufacturerService {

	/**
	 * 获取厂商列表
	 * @param manufacturer
	 * @return
	 */
	public Page<Manufacturer> getManufacturerList(Page<Manufacturer> manufacturerPage, Manufacturer manufacturer);

	public List<Manufacturer> getManufacturerList(Manufacturer manufacturer);

	/**
	 * 获取厂商总件数
	 * @param manufacturer
	 * @return
	 */
	public int	getManufacturerListCount(Manufacturer manufacturer);

	/**
	 * 获取厂商详情
	 * @param id
	 * @return
     */
	public Manufacturer getManufacture(Integer id);
	/**
	 * 添加|修改厂商
	 */
	public void saveOrUpdateManufacturer(Manufacturer manufacturer);

	/**
	 * 删除厂商
	 * @param id
	 * @return
	 */
	public int	deleteManufacturer(int id);

}
