package com.zline.zlogistics.biz.dal.dto;

import com.zline.zlogistics.biz.dal.base.BaseDto;

/**
 * Description: 订单报损
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-14
 * 
 * @version 1.0
 */
public class OrderLossDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private Long lossId;
	private Long orderId;	
	private Integer cityId;
	private Long stationId;
	private Long dmWorkNumber;
	private String dmName;
	private String lossDesc;
	private Integer dataVersion;
	
	
	/****页面参数*****/
	private String searchDate;
	private Integer searchType;
	private String keyword;
	/*****end******/
	public Long getLossId() {
		return lossId;
	}
	public void setLossId(Long lossId) {
		this.lossId = lossId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getDmWorkNumber() {
		return dmWorkNumber;
	}
	public void setDmWorkNumber(Long dmWorkNumber) {
		this.dmWorkNumber = dmWorkNumber;
	}
	public String getLossDesc() {
		return lossDesc;
	}
	public void setLossDesc(String lossDesc) {
		this.lossDesc = lossDesc;
	}
	public Integer getDataVersion() {
		return dataVersion;
	}
	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	public Integer getSearchType() {
		return searchType;
	}
	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
