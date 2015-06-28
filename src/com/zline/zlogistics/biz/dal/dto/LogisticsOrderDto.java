package com.zline.zlogistics.biz.dal.dto;

import java.util.Date;
import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseDto;

public class LogisticsOrderDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private Long logisticsOrderId; // 运单id
	private String logisticsOrderCode;// 运单编号
	private Long dmWorkNumber; //小哥工号
	private Integer sendStatus;// 派单状态 0：未派单1：已派单
	private Integer sendMethod;// 派单方式 1:自动派单 2:手动派单
	private List<Integer> distributionStationIds;//物流点id
	private Integer dataVersion;//版本号
	private String dmPhone;//小哥电话
	private String dmName;
	private Date controlledTime;
	private Date lastUpdateTime;
	
	/***** 小哥结算 ******/
	// 0:空 1:姓名:2:工号
	private String keyword;
	private String searchDate;
	private Integer balanceMemberStatus;
	/****** end *******/
	
	
	
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public Integer getBalanceMemberStatus() {
		return balanceMemberStatus;
	}

	public void setBalanceMemberStatus(Integer balanceMemberStatus) {
		this.balanceMemberStatus = balanceMemberStatus;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getDmPhone() {
		return dmPhone;
	}

	public void setDmPhone(String dmPhone) {
		this.dmPhone = dmPhone;
	}

	public String getDmName() {
		return dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	public Date getControlledTime() {
		return controlledTime;
	}

	public void setControlledTime(Date controlledTime) {
		this.controlledTime = controlledTime;
	}

	public Integer getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}

	public Long getLogisticsOrderId() {
		return logisticsOrderId;
	}

	public void setLogisticsOrderId(Long logisticsOrderId) {
		this.logisticsOrderId = logisticsOrderId;
	}

	public String getLogisticsOrderCode() {
		return logisticsOrderCode;
	}

	public void setLogisticsOrderCode(String logisticsOrderCode) {
		this.logisticsOrderCode = logisticsOrderCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Integer getSendMethod() {
		return sendMethod;
	}

	public void setSendMethod(Integer sendMethod) {
		this.sendMethod = sendMethod;
	}

	public List<Integer> getDistributionStationIds() {
		return distributionStationIds;
	}

	public void setDistributionStationIds(List<Integer> distributionStationIds) {
		this.distributionStationIds = distributionStationIds;
	}

	public Long getDmWorkNumber() {
		return dmWorkNumber;
	}

	public void setDmWorkNumber(Long dmWorkNumber) {
		this.dmWorkNumber = dmWorkNumber;
	}


	
	

}
