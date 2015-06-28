package com.zline.zlogistics.biz.dal.dto;

import com.zline.zlogistics.biz.dal.base.BaseDto;


/**
 * Description: 
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-11
 * 
 * @version 1.0
 */
public class BalanceMemberDto extends BaseDto{
	
	
	private static final long serialVersionUID = 1L;
	
	private String searchDate;
	private String keyword;
	private Long dmWorkNumber;
	
	private Integer cityId;
	private  Long balanceId;
	
	private  Float balancedAccount;
	private  Long balancedStationId;
	private Integer stationBalanceStatus;
	
	private Long logisticsOrderId;
	
	
	public Long getLogisticsOrderId() {
		return logisticsOrderId;
	}
	public void setLogisticsOrderId(Long logisticsOrderId) {
		this.logisticsOrderId = logisticsOrderId;
	}
	public Long getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getStationBalanceStatus() {
		return stationBalanceStatus;
	}
	public void setStationBalanceStatus(Integer stationBalanceStatus) {
		this.stationBalanceStatus = stationBalanceStatus;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getDmWorkNumber() {
		return dmWorkNumber;
	}
	public void setDmWorkNumber(Long dmWorkNumber) {
		this.dmWorkNumber = dmWorkNumber;
	}
	
	

}
