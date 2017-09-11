package com.jeeplus.modules.sports.service.impl;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.dao.MessageDao;
import com.jeeplus.modules.sports.entity.Message;
import com.jeeplus.modules.sports.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	/**
	 * 获取消息指令列表
	 * @param messagePage
	 * @param message
	 * @return
	 */
	public Page<Message> getMessageList(Page<Message> messagePage,Message message) {
		message.setPage(messagePage);
		messagePage.setList(messageDao.getMessageList(message));
		return messagePage;
	}

	/**
	 * 添加|修改 消息指令
	 */
	public void saveOrUpdateMessage(Message message)
	{
        if (message.getId() != null && message.getId() > 0){
			messageDao.updateByPrimaryKeySelective(message);
        }else{
			message.setStatus("0");
			message.setCreateTime(new Date());
			message.setIsServer(1);
			messageDao.insertSelective(message);
        }
	}

	public List<Message> getMessageByDeviceUuid(String uuid) {
		return messageDao.getMessageByDeviceUuid(uuid);
	}

	public void pushMessage(Message message) {
		message.setStatus("1");
		message.setCreateTime(new Date());
		message.setIsServer(0);
		messageDao.insertSelective(message);
	}

}
