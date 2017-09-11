/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sports.service.impl;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.sports.common.ReturnModel;
import com.jeeplus.modules.sports.common.TokenConstants;
import com.jeeplus.modules.sports.common.shiro.ShiroUsernamePasswordToken;
import com.jeeplus.modules.sports.common.utils.EndecryptUtils;
import com.jeeplus.modules.sports.dao.TokenDao;
import com.jeeplus.modules.sports.dao.UsersDao;
import com.jeeplus.modules.sports.entity.Token;
import com.jeeplus.modules.sports.entity.Users;
import com.jeeplus.modules.sports.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * 会员管理Service
 * @author baiyang
 * @version
 */
@Service
@Transactional
public class UsersServiceimpl extends CrudService<UsersDao,Users> implements UsersService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private TokenDao tokendao;

	/**
	 * 获取用户信息
	 * @param
	 * @return
	 */
	public Users getUserInfo(String id) {
		Users entity = dao.get(id);
		return entity;
	}

	public Users getUserByAccount(String account) {
		Users users = new Users();
		users.setAccount(account);
		return usersDao.getUserByAccount(users);
	}

	/**
	 * 获取用户信息
	 * @param page
	 * @param users
     * @return
     */
	public Page<Users> find(Page<Users> page, Users users) {
		users.setPage(page);
		page.setList(dao.findList(users));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(Users users) {
		super.save(users);
	}

	public ReturnModel saveOrUpdateUser(Users user) {
		Users isExist = usersDao.getUserByAccount(user);
		ReturnModel js = new ReturnModel();
		if (isExist != null){
			js.setCode(TokenConstants.error);
			js.setMessage("此账号已存在！");
			return js;
		}
		Users u = EndecryptUtils.md5Password(user.getAccount(), user.getPassword());
		if (u != null) {
			user.setPassword(u.getPassword());
			user.setSalt(u.getSalt());
		}
		user.setUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		user.setCreateTime(new Date());
		usersDao.insertSelective(user);
		js.setData(user.getUuid());
		return js;
	}

	public ReturnModel login(Users user,String tokenStr) {
		ReturnModel js = new ReturnModel();
		Subject subject = SecurityUtils.getSubject();
		String account = user.getAccount();
		String password = user.getPassword();
		try {
			Users u = usersDao.getUserByAccount(user);
			if (u == null) {
				js.setCode(TokenConstants.fail);
				js.setMessage("用户[" + account + "]不存在！");
			}else{
				ShiroUsernamePasswordToken token1 = new ShiroUsernamePasswordToken(
						u.getAccount(), password, u.getPassword(), u.getSalt(),
						null);
//				UsernamePasswordToken token2 = new UsernamePasswordToken(account, password.toCharArray(), false, null, null, true);
//				boolean flag = EndecryptUtils.checkMd5Password(account,password,u.getSalt(),u.getPassword());
				subject.login(token1);
				if (subject.isAuthenticated()) {
					//更新token状态为5
					tokendao.invalidUser(Integer.parseInt(u.getId()));

					//当前token设置为1有效状态，并绑定用户
					Token token = tokendao.findByToken(tokenStr);
					token.setModifieTime(new Date());
					token.setStatus(TokenConstants.VALID);
					token.setUserId(Integer.parseInt(u.getId()));
					tokendao.updateByPrimaryKey(token);

					js.setCode(TokenConstants.success);
					js.setMessage("登录成功!");
					js.setData(u.getUuid());
					js.setJsonType("String");
				} else {
					js.setCode(TokenConstants.fail);
					js.setMessage("登录失败，密码错误!");
				}
			}
		}
		catch (ExcessiveAttemptsException ex) {
			js.setCode(TokenConstants.fail);
			js.setMessage("登录失败，未知错误。");
		} catch (AuthenticationException ex) {
			System.out.println(ex.getMessage());
			js.setCode(TokenConstants.fail);
			js.setMessage("登录失败，密码错误。");
		}
		return js;
	}

	public Users getUserByUuid(String uuid) {
		return usersDao.getUserByUuid(uuid);
	}
}