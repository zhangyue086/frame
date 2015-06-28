package com.zline.zlogistics.biz.dal.entity;

import java.util.List;

public class DistributionStation extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long distributionStationId;
	private String distributionStationName;
	private String distributionStationCode;
	private Long distributionAreaId;
	private Double latPoint;
	private Double lngPoint;
	private String distributionStationAddress;
	private String distributionStationMobile;
	private String description;
	private Long cityId;
	private Integer status;
	private String cityName;
	private String stationManagerName;
	private String stationManagerMobile;
	
	private List<Integer> statusList;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getDistributionStationId() {
		return distributionStationId;
	}
	public void setDistributionStationId(Long distributionStationId) {
		this.distributionStationId = distributionStationId;
	}
	public String getDistributionStationName() {
		return distributionStationName;
	}
	public void setDistributionStationName(String distributionStationName) {
		this.distributionStationName = distributionStationName;
	}
	public String getDistributionStationCode() {
		return distributionStationCode;
	}
	public void setDistributionStationCode(String distributionStationCode) {
		this.distributionStationCode = distributionStationCode;
	}
	public Long getDistributionAreaId() {
		return distributionAreaId;
	}
	public void setDistributionAreaId(Long distributionAreaId) {
		this.distributionAreaId = distributionAreaId;
	}
	public Double getLatPoint() {
		return latPoint;
	}
	public void setLatPoint(Double latPoint) {
		this.latPoint = latPoint;
	}
	public Double getLngPoint() {
		return lngPoint;
	}
	public void setLngPoint(Double lngPoint) {
		this.lngPoint = lngPoint;
	}
	public String getDistributionStationAddress() {
		return distributionStationAddress;
	}
	public void setDistributionStationAddress(String distributionStationAddress) {
		this.distributionStationAddress = distributionStationAddress;
	}
	public String getDistributionStationMobile() {
		return distributionStationMobile;
	}
	public void setDistributionStationMobile(String distributionStationMobile) {
		this.distributionStationMobile = distributionStationMobile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Integer> getStatusList()
	{
		return statusList;
	}
	public void setStatusList(List<Integer> statusList)
	{
		this.statusList = statusList;
	}
	public String getStationManagerName()
	{
		return stationManagerName;
	}
	public void setStationManagerName(String stationManagerName)
	{
		this.stationManagerName = stationManagerName;
	}
	public String getStationManagerMobile()
	{
		return stationManagerMobile;
	}
	public void setStationManagerMobile(String stationManagerMobile)
	{
		this.stationManagerMobile = stationManagerMobile;
	}
	
}
