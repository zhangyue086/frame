package com.zline.zlogistics.biz.dal.entity;

import java.util.List;

public class DistributionMember extends BaseEntity {
	private static final long serialVersionUID = 1L;
	// 配送员位置纬度
	private Double latPoint;
	// 配送员位置经度
	private Double lonPoint;
	private Long distributionMemberId;
	private String workNumber;
	private String cityName;
	private Long cityId;
	private String distributionStationName;
	private Long distributionStationId;
	private String distributionMemberName;
	private String mobile;
	private Integer status;
	private String password;
	private Integer clockStatus;
	private String haveShifts;// 已排班信息
	private List<Integer> statusList;
	private Integer OrderCount; // 当前备单量
	private List<Integer> distributionStationIds;
	
	public Double getLatPoint() {
		return latPoint;
	}

	public void setLatPoint(Double latPoint) {
		this.latPoint = latPoint;
	}

	public Double getLonPoint() {
		return lonPoint;
	}

	public void setLonPoint(Double lonPoint) {
		this.lonPoint = lonPoint;
	}

	public List<Integer> getDistributionStationIds() {
		return distributionStationIds;
	}

	public void setDistributionStationIds(List<Integer> distributionStationIds) {
		this.distributionStationIds = distributionStationIds;
	}

	public Integer getOrderCount() {
		return OrderCount;
	}

	public void setOrderCount(Integer orderCount) {
		OrderCount = orderCount;
	}

	public Long getDistributionMemberId() {
		return distributionMemberId;
	}

	public Integer getClockStatus() {
		return clockStatus;
	}

	public void setClockStatus(Integer clockStatus) {
		this.clockStatus = clockStatus;
	}

	public void setDistributionMemberId(Long distributionMemberId) {
		this.distributionMemberId = distributionMemberId;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getDistributionStationName() {
		return distributionStationName;
	}

	public void setDistributionStationName(String distributionStationName) {
		this.distributionStationName = distributionStationName;
	}

	public Long getDistributionStationId() {
		return distributionStationId;
	}

	public void setDistributionStationId(Long distributionStationId) {
		this.distributionStationId = distributionStationId;
	}

	public String getDistributionMemberName() {
		return distributionMemberName;
	}

	public void setDistributionMemberName(String distributionMemberName) {
		this.distributionMemberName = distributionMemberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHaveShifts() {
		return haveShifts;
	}

	public void setHaveShifts(String haveShifts) {
		this.haveShifts = haveShifts;
	}

	public List<Integer> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}

}
