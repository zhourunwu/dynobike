package com.jeeplus.modules.sports.dao;

import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Device;
import com.jeeplus.modules.sports.entity.Token;

@MyBatisDao
public interface TokenDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);

    String getToken(Device device);

    void logoutUser(Integer userId);

    Token findByTokenAndStatus(String token, int status);

    Token findByToken(String token);

    int invalidUser(Integer userId);
}