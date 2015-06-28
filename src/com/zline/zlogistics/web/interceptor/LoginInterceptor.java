package com.zline.zlogistics.web.interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zline.zlogistics.biz.util.UserContext;

public class LoginInterceptor extends AbstractInterceptor
{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger(LoginInterceptor.class);
	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
		String result = "login";
		try
		{
			if(UserContext.getUser()!=null)
			{
			   result = invocation.invoke();
			}
		} catch (Exception e)
		{
			result = "exception";
			logger.error(e);
		}

		return result;

	}

}
