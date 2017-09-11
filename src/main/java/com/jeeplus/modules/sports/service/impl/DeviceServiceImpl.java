package com.jeeplus.modules.sports.service.impl;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.dao.DeviceDao;
import com.jeeplus.modules.sports.dao.DeviceTypeDao;
import com.jeeplus.modules.sports.entity.Device;
import com.jeeplus.modules.sports.entity.DeviceType;
import com.jeeplus.modules.sports.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private DeviceTypeDao deviceTypeDao;

	/**
	 * 获取设备列表
	 * @param device
	 * @return
	 */
	public Page<Device> getDeviceList(Page<Device> devicePage,Device device)
	{
		device.setPage(devicePage);
		devicePage.setList(deviceDao.getDeviceList(device));
		return	devicePage;
	}

	public List<Device> getDeviceList(Device device)
	{
		return deviceDao.getDeviceList(device);
	}
	/**
	 * 获取设备总件数
	 * @param device
	 * @return
	 */
	public int	getDeviceListCount(Device device)
	{
		return	deviceDao.getDeviceListCount(device);
	}

	/**
	 * 获取设备详情
	 * @param id
	 * @return
	 */
	public Device getDevice(Integer id) {
		return deviceDao.selectByPrimaryKey(id);
	}

	public Device getDeviceByUuid(Device device) {
		return deviceDao.getByUuid(device.getUuid());
	}

	/**
	 * 添加|修改设备
	 */
	public void saveOrUpdateDevice(Device device)
	{
		if (device.getId() != null && device.getId() > 0){
			deviceDao.updateByPrimaryKeySelective(device);
		}else{
			//用户填写
			//device.setUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
			device.setCreateTime(new Date());
			device.setToken(UUID.randomUUID().toString().replace("-", "").toUpperCase());
			deviceDao.insertSelective(device);
		}
	}

	/**
	 * 删除设备
	 * @param deviceId
	 * @return
	 */
	public int	deleteDevice(int deviceId)
	{
		return	deviceDao.deleteByPrimaryKey(deviceId);
	}


	/**
	 * 获取设备类型列表
	 * @param deviceType
	 * @return
	 */
	public Page<DeviceType> getDeviceTypeList(Page<DeviceType> deviceTypePage, DeviceType deviceType)
	{
		deviceType.setPage(deviceTypePage);
		deviceTypePage.setList(deviceTypeDao.getDeviceTypeList(deviceType));
		return	deviceTypePage;
	}
	public List<DeviceType> getDeviceTypeList(DeviceType deviceType)
	{
		return	deviceTypeDao.getDeviceTypeList(deviceType);
	}
	/**
	 * 获取设备类型总件数
	 * @param deviceType
	 * @return
	 */
	public int	getDeviceTypeListCount(DeviceType deviceType)
	{
		return	deviceTypeDao.getDeviceTypeListCount(deviceType);
	}


	/**
	 * 获取设备类型详情
	 * @param id
	 * @return
	 */
	public DeviceType getDeviceType(Integer id) {
		return deviceTypeDao.selectByPrimaryKey(id);
	}
	/**
	 * 添加|修改设备
	 */
	public void saveOrUpdateDeviceType(DeviceType devicetype)
	{
		if (devicetype.getId() != null && devicetype.getId() > 0){
			deviceTypeDao.updateByPrimaryKeySelective(devicetype);
		}else{
			devicetype.setUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
			deviceTypeDao.insertSelective(devicetype);
		}
	}

	/**
	 * 删除设备
	 * @param deviceTypeId
	 * @return
	 */
	public int	deleteDeviceType(int deviceTypeId)
	{
		return	deviceTypeDao.deleteByPrimaryKey(deviceTypeId);
	}

	/**
	 * 根据token获取设备
	 *
	 * @param tokenStr
	 * @return
	 */
	public Device getDeviceByToken(String tokenStr) {
		return deviceDao.getDeviceByToken(tokenStr);
	}

}
