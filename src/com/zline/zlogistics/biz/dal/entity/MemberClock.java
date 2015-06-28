package com.zline.zlogistics.biz.dal.entity;

import java.util.Date;

/**
 * 打卡记录
 * 
 * @author xiaojun
 *
 */
public class MemberClock extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long memberClockId;
	private Long memberId;
	private Long memberScheduleId;
	private Long stationId;
	private Date startTime;
	private Date endTime;
	private Integer clockType;// 正常班 加班
	// //////////////
	private String memberName;
	private String memberWorkNum;
	private String stationName;
	private String cityName;
	private String scheduleName;
	private Integer clockStatus;// 上班 下班
	public Long getMemberClockId()
	{
		return memberClockId;
	}
	public void setMemberClockId(Long memberClockId)
	{
		this.memberClockId = memberClockId;
	}
	public Long getMemberId()
	{
		return memberId;
	}
	public void setMemberId(Long memberId)
	{
		this.memberId = memberId;
	}
	public Long getStationId()
	{
		return stationId;
	}
	public void setStationId(Long stationId)
	{
		this.stationId = stationId;
	}
	public Date getStartTime()
	{
		return startTime;
	}
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}
	public Date getEndTime()
	{
		return endTime;
	}
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
	public Integer getClockType()
	{
		return clockType;
	}
	public void setClockType(Integer clockType)
	{
		this.clockType = clockType;
	}
	public String getMemberName()
	{
		return memberName;
	}
	public void setMemberName(String memberName)
	{
		this.memberName = memberName;
	}
	public String getStationName()
	{
		return stationName;
	}
	public void setStationName(String stationName)
	{
		this.stationName = stationName;
	}
	public String getCityName()
	{
		return cityName;
	}
	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}
	public String getScheduleName()
	{
		return scheduleName;
	}
	public void setScheduleName(String scheduleName)
	{
		this.scheduleName = scheduleName;
	}
	public Integer getClockStatus()
	{
		return clockStatus;
	}
	public void setClockStatus(Integer clockStatus)
	{
		this.clockStatus = clockStatus;
	}
	public Long getMemberScheduleId()
	{
		return memberScheduleId;
	}
	public void setMemberScheduleId(Long memberScheduleId)
	{
		this.memberScheduleId = memberScheduleId;
	}
	public String getMemberWorkNum()
	{
		return memberWorkNum;
	}
	public void setMemberWorkNum(String memberWorkNum)
	{
		this.memberWorkNum = memberWorkNum;
	}
	
}
