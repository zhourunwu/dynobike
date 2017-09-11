package com.jeeplus.modules.sports.service;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.entity.Device;
import com.jeeplus.modules.sports.entity.DeviceType;

import java.util.List;


public interface DeviceService {

	/**
	 * 获取设备列表
	 * @param device
	 * @return
	 */
	public Page<Device> getDeviceList(Page<Device> devicePage,Device device);

	public List<Device> getDeviceList(Device device);
	/**
	 * 获取设备总件数
	 * @param device
	 * @return
	 */
	public int	getDeviceListCount(Device device);

	/**
	 * 获取设备详情
	 * @param id
	 * @return
	 */
	public Device getDevice(Integer id);
	public Device getDeviceByUuid(Device device);

	/**
	 * 添加|修改设备
	 */
	public void saveOrUpdateDevice(Device device);

	/**
	 * 删除设备
	 * @param deviceId
	 * @return
	 */
	public int	deleteDevice(int deviceId);


	/**
	 * 获取设备类型列表
	 * @param deviceType
	 * @return
	 */
	public Page<DeviceType> getDeviceTypeList(Page<DeviceType> deviceTypePage, DeviceType deviceType);
	public List<DeviceType> getDeviceTypeList(DeviceType deviceType);

	/**
	 * 获取设备类型总件数
	 * @param deviceType
	 * @return
	 */
	public int	getDeviceTypeListCount(DeviceType deviceType);


	/**
	 * 获取设备类型详情
	 * @param id
	 * @return
	 */
	public DeviceType getDeviceType(Integer id);

	/**
	 * 添加|修改设备
	 */
	public void saveOrUpdateDeviceType(DeviceType devicetype);

	/**
	 * 删除设备
	 * @param deviceTypeId
	 * @return
	 */
	public int	deleteDeviceType(int deviceTypeId);

	/**
	 * 根据token获取设备
	 * @param tokenStr
	 * @return
     */
	public Device getDeviceByToken(String tokenStr);
}
