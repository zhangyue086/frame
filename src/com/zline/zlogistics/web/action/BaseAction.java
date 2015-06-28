package com.zline.zlogistics.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware,
		ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;

	ActionContext context = ActionContext.getContext();

	HttpServletRequest request;

	HttpServletResponse response;

	@SuppressWarnings("rawtypes")
	SessionMap session;

	public Object getRequestParam(String key){
		return getRequest().getParameter(key);
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

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap) session;
	}

}
