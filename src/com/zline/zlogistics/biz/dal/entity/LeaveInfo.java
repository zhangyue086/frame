package com.zline.zlogistics.biz.dal.entity;

public class LeaveInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long leaveInfoId;
	private String leaveInfoDate;
	private String leaveInfoMask;
	private Long distributionMemberId;
	private Integer leaveInfoTime;
	public Long getLeaveInfoId() {
		return leaveInfoId;
	}
	public void setLeaveInfoId(Long leaveInfoId) {
		this.leaveInfoId = leaveInfoId;
	}
	public String getLeaveInfoDate() {
		return leaveInfoDate;
	}
	public void setLeaveInfoDate(String leaveInfoDate) {
		this.leaveInfoDate = leaveInfoDate;
	}
	public String getLeaveInfoMask() {
		return leaveInfoMask;
	}
	public void setLeaveInfoMask(String leaveInfoMask) {
		this.leaveInfoMask = leaveInfoMask;
	}
	public Long getDistributionMemberId() {
		return distributionMemberId;
	}
	public void setDistributionMemberId(Long distributionMemberId) {
		this.distributionMemberId = distributionMemberId;
	}
	public Integer getLeaveInfoTime()
	{
		return leaveInfoTime;
	}
	public void setLeaveInfoTime(Integer leaveInfoTime)
	{
		this.leaveInfoTime = leaveInfoTime;
	}
	
}
