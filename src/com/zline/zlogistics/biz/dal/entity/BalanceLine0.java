package com.zline.zlogistics.biz.dal.entity;


/**
 * Description:零号线结算实体
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-11
 * 
 * @version 1.0
 */
public class BalanceLine0 extends BaseEntity {                                     
                                                                                   
	private static final long serialVersionUID = 1L;                               
	private Long balanceId;                                                        
	private Long stationId;                                                        
	private Integer cityId;
	private Float balancedAccount;
	private Integer dataVersion;  
	
	
	/****零号线结算展示******/
	private City city;
	private DistributionStation station;
	private Float waitBalanceTotal;
	private String balanceDate;
	private Boolean hasRecordToday;
	/*****end***********/
                                                                                   
	public Long getBalanceId() {                                                   
		return balanceId;                                                          
	}                                                                              
                                                                                   
	public void setBalanceId(Long balanceId) {                                     
		this.balanceId = balanceId;                                                
	}                                                                              
                                                                                   
	public Long getStationId() {                                                   
		return stationId;                                                          
	}                                                                              
                                                                                   
	public void setStationId(Long stationId) {                                     
		this.stationId = stationId;                                                
	}                                                                              
                                                                                   
	public Integer getCityId() {                                                   
		return cityId;                                                             
	}                                                                              
                                                                                   
	public void setCityId(Integer cityId) {                                        
		this.cityId = cityId;                                                      
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public DistributionStation getStation() {
		return station;
	}

	public void setStation(DistributionStation station) {
		this.station = station;
	}

	public Float getWaitBalanceTotal() {
		return waitBalanceTotal;
	}

	public void setWaitBalanceTotal(Float waitBalanceTotal) {
		this.waitBalanceTotal = waitBalanceTotal;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public Float getBalancedAccount() {
		return balancedAccount;
	}

	public void setBalancedAccount(Float balancedAccount) {
		this.balancedAccount = balancedAccount;
	}

	public Boolean getHasRecordToday() {
		return hasRecordToday;
	}

	public void setHasRecordToday(Boolean hasRecordToday) {
		this.hasRecordToday = hasRecordToday;
	}

}
