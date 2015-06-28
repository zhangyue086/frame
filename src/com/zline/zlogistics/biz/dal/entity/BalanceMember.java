package com.zline.zlogistics.biz.dal.entity;


/**
 * Description: 
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-11
 * 
 * @version 1.0
 */
public class BalanceMember extends BaseEntity{
	
	
	private static final long serialVersionUID = 1L;
	private  Long balanceId;
	private  Long dmWorkNumber;
	private  Long logisticsOrderId;
	private  Float balancedAccount;
	private  Long balancedStationId;
	private Integer stationBalanceStatus;
	private  Integer dataVersion;
	
	/*******结算页面******/
	private  String balanceDate;
	private DistributionStation station;
	/******end*******/
	
	public Long getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}
	public Long getDmWorkNumber() {
		return dmWorkNumber;
	}
	public void setDmWorkNumber(Long dmWorkNumber) {
		this.dmWorkNumber = dmWorkNumber;
	}
	public Long getLogisticsOrderId() {
		return logisticsOrderId;
	}
	public void setLogisticsOrderId(Long logisticsOrderId) {
		this.logisticsOrderId = logisticsOrderId;
	}
	public Float getBalancedAccount() {
		return balancedAccount;
	}
	public void setBalancedAccount(Float balancedAccount) {
		this.balancedAccount = balancedAccount;
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
	public Long getBalancedStationId() {
		return balancedStationId;
	}
	public void setBalancedStationId(Long balancedStationId) {
		this.balancedStationId = balancedStationId;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	public DistributionStation getStation() {
		return station;
	}
	public void setStation(DistributionStation station) {
		this.station = station;
	}
	public Integer getStationBalanceStatus() {
		return stationBalanceStatus;
	}
	public void setStationBalanceStatus(Integer stationBalanceStatus) {
		this.stationBalanceStatus = stationBalanceStatus;
	}		

}
