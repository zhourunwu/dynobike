package com.jeeplus.modules.sports.service;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.entity.Owner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OwnerService {

	/**
	 * 获取所有者列表
	 * @param owner
	 * @return
	 */
	public Page<Owner> getOwnerList(Page<Owner> ownerPage, Owner owner);

	public List<Owner> getOwnerList(Owner owner);
	/**
	 * 获取所有者总件数
	 * @param owner
	 * @return
	 */
	public int	getOwnerListCount(Owner owner);

	/**
	 * 获取所有者详情
	 * @param id
	 * @return
     */
	public Owner getOwner(Integer id);
	/**
	 * 添加|修改 所有者
	 */
	public void saveOrUpdateOwner(Owner owner);

	/**
	 * 删除所有者
	 * @param id
	 * @return
	 */
	public int	deleteOwner(int id);

}
