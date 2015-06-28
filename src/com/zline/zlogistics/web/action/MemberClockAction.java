package com.zline.zlogistics.web.action;

import java.util.ArrayList;
import java.util.List;

import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.LeaveInfo;
import com.zline.zlogistics.biz.dal.entity.MemberClock;
import com.zline.zlogistics.biz.dal.entity.MemberSchedule;
import com.zline.zlogistics.biz.dal.entity.User;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.ILeaveInfoService;
import com.zline.zlogistics.biz.manager.IMemberClockService;
import com.zline.zlogistics.biz.manager.IMemberScheduleService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.biz.util.UserContext;
import com.zline.zlogistics.web.common.DataTableReturnObject;

/**
 * 人员打卡
 * 
 * @author xiaojun
 *
 */
public class MemberClockAction extends BaseAction
{

	private static final long serialVersionUID = 1L;
	private IMemberClockService memberClockService;
	private IMemberScheduleService memberScheduleService;
	private IDistributionMemberService distributionMemberService;
	private DataTableReturnObject returnObject;
	private Long workNum;
	private Integer clockStatus;
	private Long memberScheduleId;
	private Long memberClockId;
	private Long memberId;
	// /请假信息
	private Long firstName;// 请假人工号
	private String leaveDate;// 请假日期
	private Integer leaveTime;// 请假时长
	private ILeaveInfoService leaveInfoService;
	private Message message;

	public String initList()
	{
		return "initList";
	}

	/**
	 * 返回正常排班打卡
	 * 
	 * @return
	 */
	public String listClock()
	{
		returnObject = new DataTableReturnObject<MemberClock>();
		returnObject.setData(new ArrayList<String>());
		User currUser = UserContext.getUser();
		if (currUser == null || currUser.getStationId() == null)
		{
			return "listClock";
		}
		DistributionMember member = distributionMemberService
				.findByWorkNubmer(workNum);
		if (member == null
				|| !member.getDistributionStationId().equals(
						currUser.getStationId()))
		{
			return "listClock";
		}
		MemberClock memberClock = new MemberClock();
		memberClock.setMemberId(member.getDistributionMemberId());
		memberClock.setStationId(member.getDistributionStationId());
		List<MemberClock> list = memberClockService.listClock(memberClock);
		if (list != null)
		{
			returnObject.setData(list);
		}
		returnObject.setDraw(Integer
				.parseInt(getRequest().getParameter("draw") == null ? "0"
						: getRequest().getParameter("draw")) + 1);
		return "listClock";
	}

	/**
	 * 返回加班排班打卡
	 * 
	 * @return
	 */
	public String listAddClock()
	{
		returnObject = new DataTableReturnObject<MemberClock>();
		returnObject.setData(new ArrayList<String>());
		User currUser = UserContext.getUser();
		if (currUser == null || currUser.getStationId() == null)
		{
			return "listClock";
		}
		DistributionMember member = distributionMemberService
				.findByWorkNubmer(workNum);
		if (member == null
				|| !member.getDistributionStationId().equals(
						currUser.getStationId()))
		{
			return "listClock";
		}
		MemberClock memberClock = new MemberClock();
		memberClock.setMemberId(member.getDistributionMemberId());
		memberClock.setStationId(member.getDistributionStationId());
		List<MemberClock> list = memberClockService.listAddClock(memberClock);
		if (list != null)
		{
			returnObject.setData(list);
		}
		returnObject.setDraw(Integer
				.parseInt(getRequest().getParameter("draw") == null ? "0"
						: getRequest().getParameter("draw")) + 1);
		return "listClock";
	}

	/**
	 * 正常班打卡操作
	 * 
	 * @return
	 */
	public String memberClock()
	{
		MemberClock memberClock = new MemberClock();
		memberClock.setMemberScheduleId(memberScheduleId);
		memberClock.setMemberClockId(memberClockId);
		memberClock.setMemberId(memberId);
		if (clockStatus == 1)
		{
			memberClockService.addMemberClock(memberClock);
		} else if (clockStatus == 2)
		{
			// 下班
			memberClockService.updateMemberClock(memberClock);
		}
		return null;
	}

	/**
	 * 加班打卡操作
	 * 
	 * @return
	 */
	public String memberAddClock()
	{
		MemberClock memberClock = new MemberClock();
		memberClock.setMemberScheduleId(memberScheduleId);
		memberClock.setMemberClockId(memberClockId);
		memberClock.setMemberId(memberId);
		if (clockStatus == 1)
		{
			memberClockService.addMemberAddClock(memberClock);
		} else if (clockStatus == 2)
		{
			// 下班
			memberClockService.updateMemberClock(memberClock);
		}
		return null;
	}

	/**
	 * 添加请假信息
	 * 
	 * @return
	 */
	public String addMemberLeave()
	{
		message = new Message();
		message.setIsSuccess(true);
		try
		{
			DistributionMember member = distributionMemberService
					.findByWorkNubmer(firstName);
			User currUser = UserContext.getUser();
			if (currUser == null || currUser.getStationId() == null||!currUser.getStationId().equals(member.getDistributionStationId()))
			{
				message.setIsSuccess(false);
				return "add";
			}
			// 验证当天有排班 
			MemberSchedule mSchedule = new MemberSchedule();
			mSchedule.setDistributionMemberId(member.getDistributionMemberId());
			mSchedule.setScheduleDate(leaveDate);
			Integer count = memberScheduleService
					.selectMemberScheduleCount(mSchedule);
			if (count > 0)
			{
				// 添加请假信息
				LeaveInfo leaveInfo = new LeaveInfo();
				leaveInfo.setLeaveInfoDate(leaveDate);
				leaveInfo.setLeaveInfoTime(leaveTime);
				leaveInfo.setDistributionMemberId(member
						.getDistributionMemberId());
				leaveInfoService.saveLeaveInfo(leaveInfo);
			}
			else
			{
				message.setIsSuccess(false);
			}
		} catch (Exception e)
		{
			message.setIsSuccess(false);
		}
		return "add";
	}

	public DataTableReturnObject getReturnObject()
	{
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject returnObject)
	{
		this.returnObject = returnObject;
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

	public Long getWorkNum()
	{
		return workNum;
	}

	public void setWorkNum(Long workNum)
	{
		this.workNum = workNum;
	}

	public IMemberClockService getMemberClockService()
	{
		return memberClockService;
	}

	public void setMemberClockService(IMemberClockService memberClockService)
	{
		this.memberClockService = memberClockService;
	}

	public IDistributionMemberService getDistributionMemberService()
	{
		return distributionMemberService;
	}

	public void setDistributionMemberService(
			IDistributionMemberService distributionMemberService)
	{
		this.distributionMemberService = distributionMemberService;
	}

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

	public Long getFirstName()
	{
		return firstName;
	}

	public void setFirstName(Long firstName)
	{
		this.firstName = firstName;
	}

	public String getLeaveDate()
	{
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate)
	{
		this.leaveDate = leaveDate;
	}

	public Integer getLeaveTime()
	{
		return leaveTime;
	}

	public void setLeaveTime(Integer leaveTime)
	{
		this.leaveTime = leaveTime;
	}

	public IMemberScheduleService getMemberScheduleService()
	{
		return memberScheduleService;
	}

	public void setMemberScheduleService(
			IMemberScheduleService memberScheduleService)
	{
		this.memberScheduleService = memberScheduleService;
	}

	public ILeaveInfoService getLeaveInfoService()
	{
		return leaveInfoService;
	}

	public void setLeaveInfoService(ILeaveInfoService leaveInfoService)
	{
		this.leaveInfoService = leaveInfoService;
	}

	public Message getMessage()
	{
		return message;
	}

	public void setMessage(Message message)
	{
		this.message = message;
	}

}
