package com.zline.zlogistics.biz.dal.entity;

public class Attendance extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long attendanceId;
	private Long distributionMemberId;
	private Long scheduleId;//班次id -1代表加班
	private String attendanceDate;
	private String startWorkTime;
	private String endWorkTime;
	public Long getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}
	public Long getDistributionMemberId() {
		return distributionMemberId;
	}
	public void setDistributionMemberId(Long distributionMemberId) {
		this.distributionMemberId = distributionMemberId;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
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
