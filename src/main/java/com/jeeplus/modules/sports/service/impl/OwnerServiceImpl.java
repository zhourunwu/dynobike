package com.jeeplus.modules.sports.service.impl;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.modules.sports.dao.OwnerDao;
import com.jeeplus.modules.sports.entity.Owner;
import com.jeeplus.modules.sports.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	/**
	 * 获取所有者列表
	 * @param owner
	 * @return
	 */
	public Page<Owner> getOwnerList(Page<Owner> ownerPage, Owner owner)
	{
		owner.setPage(ownerPage);
		ownerPage.setList(ownerDao.getOwnerList(owner));
		return	ownerPage;
	}
	public List<Owner> getOwnerList(Owner owner)
	{
		return	ownerDao.getOwnerList(owner);
	}
	/**
	 * 获取所有者总件数
	 * @param owner
	 * @return
	 */
	public int	getOwnerListCount(Owner owner)
	{
		return ownerDao.getOwnerListCount(owner);
	}

	/**
	 * 获取所有者详情
	 * @param id
	 * @return
     */
	public Owner getOwner(Integer id) {
		return ownerDao.selectByPrimaryKey(id);
	}
	/**
	 * 添加|修改 所有者
	 */
	public void saveOrUpdateOwner(Owner owner)
	{
        if (owner.getId() != null && owner.getId() > 0){
			ownerDao.updateByPrimaryKeySelective(owner);
        }else{
			owner.setUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
			owner.setCreateTime(new Date());
			ownerDao.insertSelective(owner);
        }
	}

	/**
	 * 删除所有者
	 * @param id
	 * @return
	 */
	public int	deleteOwner(int id)
	{
		return	ownerDao.deleteByPrimaryKey(id);
	}

}
