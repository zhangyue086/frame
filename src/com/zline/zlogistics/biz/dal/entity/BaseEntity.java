package com.zline.zlogistics.biz.dal.entity;

import java.io.Serializable;
import java.util.Date;

/** 
* @author 作者: 徐宇鹏
* @version 创建时间：2015年2月27日 上午10:28:58 
*/ 
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer curPage;// 当前页面
	private Integer pageRows;// 每页的行数
	private Integer pageAmount;// 总页数
	private Long createId;// 创建人
	private Date createTime;// 创建日期
	private Long lastUpdateId;// 最后修改人
	private Date lastUpdateTime;// 最后修改日期
	private Integer isDeleted;// 是否删除
	private Integer firstRow;// 取记录的第一条
	private Date startDate;//开始时间
	private Date endDate;//结束时间
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageRows() {
		return pageRows;
	}
	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}
	public Integer getPageAmount() {
		return pageAmount;
	}
	public void setPageAmount(Integer pageAmount) {
		this.pageAmount = pageAmount;
	}
	public Long getCreateId() {
		return createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getLastUpdateId() {
		return lastUpdateId;
	}
	public void setLastUpdateId(Long lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}
	
}
