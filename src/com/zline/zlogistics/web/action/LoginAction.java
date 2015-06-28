package com.zline.zlogistics.web.action;

import com.zline.zlogistics.biz.dal.entity.User;
import com.zline.zlogistics.biz.manager.IUserService;
import com.zline.zlogistics.biz.util.UserContext;

public class LoginAction extends BaseAction
{

	private static final long serialVersionUID = -8154517017765993988L;

	private String userName;
	private String passWord;
	private IUserService userService;

	public String userLogin()
	{
		String result = "login";
		if (userName != null && passWord != null)
		{
			User loginUser = new User();
			loginUser.setUserName(userName.trim());
			loginUser.setPassWord(passWord.trim());
			User user = userService.selectUserByUserName(loginUser);
			if (user != null && user.getUserId() != null)
			{
				result = "loginSuccess";
				UserContext.saveUser(user);
			}

		}

		return result;
	}
	
	
	public String loginOut()
	{
		UserContext.saveUser(null);
		return "login";
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassWord()
	{
		return passWord;
	}

	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}

	public IUserService getUserService()
	{
		return userService;
	}

	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}

}
