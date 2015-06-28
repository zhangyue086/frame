package com.zline.zlogistics.biz.dal.dto;

import com.zline.zlogistics.biz.dal.base.BaseDto;

/**
 * Description:零号线结算实体
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-11
 * 
 * @version 1.0
 */
public class BalanceLine0Dto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String searchDate;
	private Integer cityId;
	private Long stationId;
	private Float balancedAccount;
	private Long balancedStationId;

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Float getBalancedAccount() {
		return balancedAccount;
	}

	public void setBalancedAccount(Float balancedAccount) {
		this.balancedAccount = balancedAccount;
	}

	public Long getBalancedStationId() {
		return balancedStationId;
	}

	public void setBalancedStationId(Long balancedStationId) {
		this.balancedStationId = balancedStationId;
	}
	

	

}
