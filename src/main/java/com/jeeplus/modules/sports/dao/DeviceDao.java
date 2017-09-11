package com.jeeplus.modules.sports.dao;

import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Device;

import java.util.List;

@MyBatisDao
public interface DeviceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<Device> getDeviceList(Device device);

    int getDeviceListCount(Device device);

    Device getByUuid(String uuid);

    Device getDeviceByToken(String tokenStr);
}