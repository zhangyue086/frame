package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.Schedule;
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
	
	
	
	/**
	 * 添加用户接口
	 * @param user 用户对象
	 */
	void saveUser(User user);
	
	void updateUser(User user);
	
	User findById(Long id);
	
	List<User> queryList(User user);
	
	Integer queryListCount(User user);
	
	
	
}
