package com.zline.zlogistics.biz.dal.entity;

import java.util.List;


public class ShiftSchedule extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long shiftScheduleId;
	private String scheduleDate;
	private String distributionStationName;
	private Long distributionStationId;
	private String scheduleName;
	private Long scheduleId;
	private String distributionMemberNames;
	private List<Long> distributionMemberIds;
	public Long getShiftScheduleId() {
		return shiftScheduleId;
	}
	public void setShiftScheduleId(Long shiftScheduleId) {
		this.shiftScheduleId = shiftScheduleId;
	}
	public String getDistributionStationName() {
		return distributionStationName;
	}
	public void setDistributionStationName(String distributionStationName) {
		this.distributionStationName = distributionStationName;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getDistributionMemberNames() {
		return distributionMemberNames;
	}
	public void setDistributionMemberNames(String distributionMemberNames) {
		this.distributionMemberNames = distributionMemberNames;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public Long getDistributionStationId() {
		return distributionStationId;
	}
	public void setDistributionStationId(Long distributionStationId) {
		this.distributionStationId = distributionStationId;
	}
	public List<Long> getDistributionMemberIds() {
		return distributionMemberIds;
	}
	public void setDistributionMemberIds(List<Long> distributionMemberIds) {
		this.distributionMemberIds = distributionMemberIds;
	}
	
}
