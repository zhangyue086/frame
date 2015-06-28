package com.zline.zlogistics.biz.dal.entity;

public class MemberSchedule extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long memberScheduleId;
	private Long scheduleId;
	private Long distributionMemberId;
	private String scheduleDate;
	private String scheduleName;
	private Long distributionStationId;
	public Long getMemberScheduleId() {
		return memberScheduleId;
	}
	public void setMemberScheduleId(Long memberScheduleId) {
		this.memberScheduleId = memberScheduleId;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getDistributionMemberId() {
		return distributionMemberId;
	}
	public void setDistributionMemberId(Long distributionMemberId) {
		this.distributionMemberId = distributionMemberId;
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
	public String getScheduleName()
	{
		return scheduleName;
	}
	public void setScheduleName(String scheduleName)
	{
		this.scheduleName = scheduleName;
	}
	
}
