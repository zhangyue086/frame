package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.User;
import com.zline.zlogistics.biz.manager.IUserService;

public class UserServiceImpl extends BaseService implements IUserService
{

	
	public User selectUserByUserName(User loginUser)
	{
		return this.getBaseDao().selectOne("user.selectUserByUserName",loginUser);
	}

	@Override
	public User findById(Long id) {
		return null;
	}

	@Override
	public List<User> queryList(User user) {
		return getBaseDao().selectList("user.queryList", user);
	}

	@Override
	public Integer queryListCount(User user) {
		return getBaseDao().selectOne("user.queryListCount", user);
	}

	@Override
	public void saveUser(User user) {
		getBaseDao().insert("user.save", user);
	}

	@Override
	public void updateUser(User user) {
		
	}

}
