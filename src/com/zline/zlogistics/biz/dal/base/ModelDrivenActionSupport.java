package com.zline.zlogistics.biz.dal.base;

import com.opensymphony.xwork2.interceptor.ScopedModelDriven;

public class ModelDrivenActionSupport<T> extends BaseActionSupport implements ScopedModelDriven<T> {

	private static final long serialVersionUID = -184464174403337789L;

	private T model;

	private String scope;

	/**
	 * @param model
	 *           set the model 
	 */
	public void setModel(T model) {
		this.model = model;
	}

	public T getModel() {
		return model;
	}

	public String getScopeKey() {
		return this.scope;
	}

	public void setScopeKey(String key) {
		this.scope = key;
	}

}