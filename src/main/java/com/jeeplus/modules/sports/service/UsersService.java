/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sports.service;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.common.ReturnModel;
import com.jeeplus.modules.sports.entity.Users;

/**
 * 会员管理Service
 * @author baiyang
 * @version
 */

public interface UsersService {

	/**
	 * 获取用户信息
	 * @param
	 * @return
	 */
	public Users getUserInfo(String id);

	/**
	 * 获取用户信息
	 * @param
	 * @return
	 */
	public Users getUserByAccount(String account);

	/**
	 * 获取用户信息
	 * @param page
	 * @param users
     * @return
     */
	public Page<Users> find(Page<Users> page, Users users);

	public void save(Users users);

	ReturnModel saveOrUpdateUser(Users users);

	ReturnModel login(Users user,String tokenStr);
}