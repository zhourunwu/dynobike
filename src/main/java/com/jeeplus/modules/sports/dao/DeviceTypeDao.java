package com.jeeplus.modules.sports.dao;

import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.DeviceType;

import java.util.List;

@MyBatisDao
public interface DeviceTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceType record);

    int insertSelective(DeviceType record);

    DeviceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceType record);

    int updateByPrimaryKey(DeviceType record);

    List<DeviceType> getDeviceTypeList(DeviceType deviceType);

    int getDeviceTypeListCount(DeviceType deviceType);
}