package com.zline.zlogistics.biz.dal.entity;

/**
 * Description: 订单报损
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-14
 * 
 * @version 1.0
 */
public class OrderLoss extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Long lossId;
	private Long orderId;
	private Integer cityId;
	private Long stationId;
	private Float needPay;
	private Float needPayReceive;
	private Float lossAccount;
	private Long dmWorkNumber;
	private String dmName;
	private String lossDesc;
	private Integer dataVersion;
	
	
	private City city;
	private LogisticsOrder order;
	private DistributionStation station;
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
	public Float getNeedPay() {
		return needPay;
	}
	public void setNeedPay(Float needPay) {
		this.needPay = needPay;
	}
	public Float getNeedPayReceive() {
		return needPayReceive;
	}
	public void setNeedPayReceive(Float needPayReceive) {
		this.needPayReceive = needPayReceive;
	}
	public Float getLossAccount() {
		return lossAccount;
	}
	public void setLossAccount(Float lossAccount) {
		this.lossAccount = lossAccount;
	}
	public Long getDmWorkNumber() {
		return dmWorkNumber;
	}
	public void setDmWorkNumber(Long dmWorkNumber) {
		this.dmWorkNumber = dmWorkNumber;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
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
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public LogisticsOrder getOrder() {
		return order;
	}
	public void setOrder(LogisticsOrder order) {
		this.order = order;
	}
	public DistributionStation getStation() {
		return station;
	}
	public void setStation(DistributionStation station) {
		this.station = station;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
