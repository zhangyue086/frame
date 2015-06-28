package com.zline.zlogistics.biz.dal.entity;

import java.util.Date;

public class OrderStatus extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long logisticsOrderStatusId;
	private Long logisticsOrderId;
	private Integer logisticsOrderStatusCode;
	private Date logisticsOrderStatusTime;
	private String dmMobile;
	private String dmName;
	private Long dmWorkNumber;
	private Double dmLat;
	private Double dmLng;
	private Integer dateVersion;
	
	public Long getLogisticsOrderStatusId() {
		return logisticsOrderStatusId;
	}
	public void setLogisticsOrderStatusId(Long logisticsOrderStatusId) {
		this.logisticsOrderStatusId = logisticsOrderStatusId;
	}
	public Long getLogisticsOrderId() {
		return logisticsOrderId;
	}
	public void setLogisticsOrderId(Long logisticsOrderId) {
		this.logisticsOrderId = logisticsOrderId;
	}
	public Integer getLogisticsOrderStatusCode() {
		return logisticsOrderStatusCode;
	}
	public void setLogisticsOrderStatusCode(Integer logisticsOrderStatusCode) {
		this.logisticsOrderStatusCode = logisticsOrderStatusCode;
	}
	public Date getLogisticsOrderStatusTime() {
		return logisticsOrderStatusTime;
	}
	public void setLogisticsOrderStatusTime(Date logisticsOrderStatusTime) {
		this.logisticsOrderStatusTime = logisticsOrderStatusTime;
	}
	public String getDmMobile() {
		return dmMobile;
	}
	public void setDmMobile(String dmMobile) {
		this.dmMobile = dmMobile;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	public Long getDmWorkNumber() {
		return dmWorkNumber;
	}
	public void setDmWorkNumber(Long dmWorkNumber) {
		this.dmWorkNumber = dmWorkNumber;
	}
	public Double getDmLat() {
		return dmLat;
	}
	public void setDmLat(Double dmLat) {
		this.dmLat = dmLat;
	}
	public Double getDmLng() {
		return dmLng;
	}
	public void setDmLng(Double dmLng) {
		this.dmLng = dmLng;
	}
	public Integer getDateVersion() {
		return dateVersion;
	}
	public void setDateVersion(Integer dateVersion) {
		this.dateVersion = dateVersion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
