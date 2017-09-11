package com.jeeplus.modules.sports.service.impl;

import com.jeeplus.modules.sports.dao.ConnectionDao;
import com.jeeplus.modules.sports.entity.Connection;
import com.jeeplus.modules.sports.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private ConnectionDao connectionDao;


	public void saveOrUpdateConnection(Connection connection) {
		if (connection.getId() != null && connection.getId() > 0){
			connectionDao.updateByPrimaryKeySelective(connection);
		}else{
			connection.setConnectionTime(new Date());
			connectionDao.insertSelective(connection);
		}
	}
}
