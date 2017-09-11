package com.jeeplus.modules.sports.dao;


import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sports.entity.Message;

import java.util.List;

@MyBatisDao
public interface MessageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> getMessageList(Message message);

    List<Message> getMessageByDeviceUuid(String uuid);
}