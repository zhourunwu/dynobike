package com.jeeplus.modules.sports.service;

import com.jeeplus.modules.sports.entity.Connection;

public interface ConnectionService {


	/**
	 * 添加|修改连接
	 * @param connection
     */
	void saveOrUpdateConnection(Connection connection);
}
