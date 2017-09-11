package com.jeeplus.modules.sports.dao;


import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Connection;

@MyBatisDao
public interface ConnectionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Connection record);

    int insertSelective(Connection record);

    Connection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Connection record);

    int updateByPrimaryKey(Connection record);
}