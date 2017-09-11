package com.jeeplus.modules.sports.service;

import com.jeeplus.modules.sports.entity.UserSportInfo;
import com.jeeplus.modules.sports.entity.Users;

public interface UserSportInfoService {

	/**
	 * 获取用户最后一次运动信息
	 * @param
	 * @return
	 */
	public UserSportInfo getLastUserSportInfo(Users users);

	/**
	 * 保存|修改 用户运动信息
	 * @param userSportInfo
     */
	void saveOrUpdateUserSport(UserSportInfo userSportInfo);
}
