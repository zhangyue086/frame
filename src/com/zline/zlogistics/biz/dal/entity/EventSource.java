package com.zline.zlogistics.biz.dal.entity;

/** 
* @author 作者: 徐宇鹏
* @version 创建时间：2015年3月23日 下午3:16:51 
* 类说明 
*/ 
public class EventSource extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private String title;
	private String start;
	private String backgroundColor;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
