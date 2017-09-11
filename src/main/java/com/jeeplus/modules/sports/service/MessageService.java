package com.jeeplus.modules.sports.service;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.entity.Message;
import java.util.List;


public interface MessageService {

	/**
	 * 获取消息指令列表
	 * @param messagePage
	 * @param message
	 * @return
	 */
	public Page<Message> getMessageList(Page<Message> messagePage,Message message);

	/**
	 * 添加|修改 消息指令
	 */
	public void saveOrUpdateMessage(Message message);

	/**
	 * 获取设备初始预设指令信息
	 * @param uuid
	 * @return
     */
	List<Message> getMessageByDeviceUuid(String uuid);

	/**
	 * 客户端推送消息
	 * @param message
     */
	void pushMessage(Message message);
}
