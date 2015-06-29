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

	public final static String LoginEntity = "loginEntity";

	public static User getUser()
	{
		User loginEntity = (User) ServletActionContext.getRequest().getSession()
				.getAttribute(LoginEntity);
		return loginEntity;
	}

	public static void saveUser(User loginEntity)
	{
		ServletActionContext.getRequest().getSession().setAttribute(LoginEntity, loginEntity);
	}

}
