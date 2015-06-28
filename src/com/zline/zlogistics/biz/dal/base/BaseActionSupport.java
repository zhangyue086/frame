package com.zline.zlogistics.biz.dal.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author LuLiLi
 * @version [版本号, 2012-2-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseActionSupport extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -1232609418286925518L;

	public static final String LIST = "list";

	public static final String SAVE = "save";

	public static final String NORESULT = "noresult";

	private String priMsg;

	ActionContext context = ActionContext.getContext();

	HttpServletRequest request;

	HttpServletResponse response;

	@SuppressWarnings("rawtypes")
	SessionMap session;

	// 取参数方便些。
	public String param(String paramName) {
		return request.getParameter(paramName);
	}

	@SuppressWarnings("rawtypes")
	public void setSession(Map map) {
		this.session = (SessionMap) map;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@SuppressWarnings("rawtypes")
	public SessionMap getSession() {
		return session;
	}

	public String getPriMsg() {
		return priMsg;
	}

	public void setPriMsg(String priMsg) {
		this.priMsg = priMsg;
	}

}