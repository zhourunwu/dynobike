package com.jeeplus.modules.sports.dao;

import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Owner;

import java.util.List;

@MyBatisDao
public interface OwnerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Owner record);

    int insertSelective(Owner record);

    Owner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Owner record);

    int updateByPrimaryKey(Owner record);

    List<Owner> getOwnerList(Owner owner);

    int getOwnerListCount(Owner owner);
}