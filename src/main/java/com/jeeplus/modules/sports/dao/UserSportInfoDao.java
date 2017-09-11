package com.jeeplus.modules.sports.dao;


import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.UserSportInfo;
import com.jeeplus.modules.sports.entity.Users;

@MyBatisDao
public interface UserSportInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSportInfo record);

    int insertSelective(UserSportInfo record);

    UserSportInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSportInfo record);

    int updateByPrimaryKey(UserSportInfo record);

    UserSportInfo getLastUserSportInfo(Users users);
}