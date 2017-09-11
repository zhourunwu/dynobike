package com.jeeplus.modules.sports.dao;

import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Manufacturer;

import java.util.List;

@MyBatisDao
public interface ManufacturerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Manufacturer record);

    int insertSelective(Manufacturer record);

    Manufacturer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manufacturer record);

    int updateByPrimaryKey(Manufacturer record);

    List<Manufacturer> getManufacturerList(Manufacturer manufacturer);

    int getManufacturerListCount(Manufacturer manufacturer);
}