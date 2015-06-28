package com.zline.zlogistics.biz.manager.impl;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.User;
import com.zline.zlogistics.biz.manager.IUserService;

public class UserServiceImpl extends BaseService implements IUserService
{

	
	public User selectUserByUserName(User loginUser)
	{
		return this.getBaseDao().selectOne("user.selectUserByUserName",loginUser);
	}

}
