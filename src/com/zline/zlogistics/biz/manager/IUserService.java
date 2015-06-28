package com.zline.zlogistics.biz.manager;

import com.zline.zlogistics.biz.dal.entity.User;

public interface IUserService
{
	/**
	 * 登录验证
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public User selectUserByUserName(User loginUser);
}
