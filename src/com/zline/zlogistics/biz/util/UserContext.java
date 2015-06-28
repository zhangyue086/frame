package com.zline.zlogistics.biz.util;

import org.apache.struts2.ServletActionContext;

import com.zline.zlogistics.biz.dal.entity.User;

/**
 * Description:
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-12
 * 
 * @version 1.0
 */
public class UserContext
{

	public final static String USER = "user";

	public static User getUser()
	{
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute(USER);
		return user;
	}

	public static void saveUser(User user)
	{
		ServletActionContext.getRequest().getSession().setAttribute(USER, user);
	}

}
