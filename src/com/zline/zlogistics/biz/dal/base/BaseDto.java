package com.zline.zlogistics.biz.dal.base;

import java.io.Serializable;
import java.util.Date;

public class BaseDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7272935786167134826L;
	private Integer curPage = 1;// 当前页面
	private Integer pageRows = 10;// 每页的行数
	private Integer pageAmount = 0;// 总页数
	private Integer totalRows = 0;// 总行数
	private Long createId = 0l;// 创建人
	private Date createTime = new Date();// 创建日期
	private Long lastUpdateId = 0l;// 最后修改人
	private Date lastUpdateTime = new Date();// 最后修改日期
	private Integer isDeleted;// 是否删除
	private Integer firstRow;
	private String sortProperty;// 排序字段
	private String sortOrder = "ASC";// 排序方式ASC升序DESC降序
	private String lastURL = "/index.do";
	private String queryURL;
	private Integer startpage = 1;// 开始页码
	private Integer endpage = 1;// 结束页码
	private Integer showPageAmount = 9;// 页面显示的页数
	private Integer page;
	private Integer rows;
	private Integer firstNum;

	private String startTime;
	private String endTime;

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

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
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

	public String getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getLastURL() {
		return lastURL;
	}

	public void setLastURL(String lastURL) {
		this.lastURL = lastURL;
	}

	public String getQueryURL() {
		return queryURL;
	}

	public void setQueryURL(String queryURL) {
		this.queryURL = queryURL;
	}

	public Integer getStartpage() {
		return startpage;
	}

	public void setStartpage(Integer startpage) {
		this.startpage = startpage;
	}

	public Integer getEndpage() {
		return endpage;
	}

	public void setEndpage(Integer endpage) {
		this.endpage = endpage;
	}

	public Integer getShowPageAmount() {
		return showPageAmount;
	}

	public void setShowPageAmount(Integer showPageAmount) {
		this.showPageAmount = showPageAmount;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(Integer firstNum) {
		this.firstNum = firstNum;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
