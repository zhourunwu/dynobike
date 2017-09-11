package com.jeeplus.modules.sports.service.impl;

import com.jeeplus.modules.sports.dao.UserSportInfoDao;
import com.jeeplus.modules.sports.entity.UserSportInfo;
import com.jeeplus.modules.sports.entity.Users;
import com.jeeplus.modules.sports.service.UserSportInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class UserSportInfoServiceImpl implements UserSportInfoService {

	@Autowired
	private UserSportInfoDao userSportInfoDao;

	public UserSportInfo getLastUserSportInfo(Users users) {
		return userSportInfoDao.getLastUserSportInfo(users);
	}

	public void saveOrUpdateUserSport(UserSportInfo userSportInfo) {
		if (userSportInfo.getId() != null && userSportInfo.getId() > 0){
			userSportInfoDao.updateByPrimaryKey(userSportInfo);
		}else{
			userSportInfo.setSubmitTime(new Date());
			userSportInfoDao.insertSelective(userSportInfo);
		}
	}
}
