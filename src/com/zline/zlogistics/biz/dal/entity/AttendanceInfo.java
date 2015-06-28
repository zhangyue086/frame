package com.zline.zlogistics.biz.dal.entity;

public class AttendanceInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long distributionMemberId;
	private String distributionMemberName;
	private String attendanceDate;
	private Long distributionStationId;
	private String distributionStationName;
	private Integer lateTime;//迟到	
	private Integer earlyTime;//早退
	private String leaveInfoMask;//请假
	private Integer overTime;//加班
	private String scheduleName;//班次名称
	private String startWorkTime;//上班打卡时间
	private String endWorkTime;//下班打卡时间
	public Long getDistributionMemberId() {
		return distributionMemberId;
	}
	public void setDistributionMemberId(Long distributionMemberId) {
		this.distributionMemberId = distributionMemberId;
	}
	public String getDistributionMemberName() {
		return distributionMemberName;
	}
	public void setDistributionMemberName(String distributionMemberName) {
		this.distributionMemberName = distributionMemberName;
	}
	public String getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
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
	public Integer getLateTime() {
		return lateTime;
	}
	public void setLateTime(Integer lateTime) {
		this.lateTime = lateTime;
	}
	public Integer getEarlyTime() {
		return earlyTime;
	}
	public void setEarlyTime(Integer earlyTime) {
		this.earlyTime = earlyTime;
	}
	public String getLeaveInfoMask() {
		return leaveInfoMask;
	}
	public void setLeaveInfoMask(String leaveInfoMask) {
		this.leaveInfoMask = leaveInfoMask;
	}
	public Integer getOverTime() {
		return overTime;
	}
	public void setOverTime(Integer overTime) {
		this.overTime = overTime;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public String getStartWorkTime() {
		return startWorkTime;
	}
	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}
	public String getEndWorkTime() {
		return endWorkTime;
	}
	public void setEndWorkTime(String endWorkTime) {
		this.endWorkTime = endWorkTime;
	}


}
