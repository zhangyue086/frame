package com.zline.zlogistics.biz.dal.entity;

public class Schedule extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long scheduleId;
	private Long distributionStationId;
	private String scheduleName;
	private String scheduleStart;
	private String scheduleEnd;
	private String scheduleDate;//查询条件 ，实体无此字段
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getDistributionStationId() {
		return distributionStationId;
	}
	public void setDistributionStationId(Long distributionStationId) {
		this.distributionStationId = distributionStationId;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public String getScheduleStart() {
		return scheduleStart;
	}
	public void setScheduleStart(String scheduleStart) {
		this.scheduleStart = scheduleStart;
	}
	public String getScheduleEnd() {
		return scheduleEnd;
	}
	public void setScheduleEnd(String scheduleEnd) {
		this.scheduleEnd = scheduleEnd;
	}
	public String getScheduleDate()
	{
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate)
	{
		this.scheduleDate = scheduleDate;
	}
	
}
