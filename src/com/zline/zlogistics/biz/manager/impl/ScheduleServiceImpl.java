package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.dal.entity.MemberSchedule;
import com.zline.zlogistics.biz.dal.entity.Schedule;
import com.zline.zlogistics.biz.dal.entity.ShiftSchedule;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;
import com.zline.zlogistics.biz.manager.IMemberScheduleService;
import com.zline.zlogistics.biz.manager.IScheduleService;
import com.zline.zlogistics.biz.manager.IShiftScheduleService;

public class ScheduleServiceImpl extends BaseService implements
		IScheduleService
{
	private IMemberScheduleService memberScheduleService;
	private IShiftScheduleService shiftScheduleService;
	private IDistributionStationService distributionStationService;
	private IDistributionMemberService memberService;

	
	public void saveSchedule(Schedule schedule)
	{
		getBaseDao().insert("Schedule.save", schedule);

	}

	
	public void updateSchedule(Schedule schedule)
	{
		MemberSchedule memberSchedule = new MemberSchedule();
		memberSchedule.setScheduleId(schedule.getScheduleId());
		memberSchedule.setScheduleName(schedule.getScheduleName());
		ShiftSchedule shiftSchedule = new ShiftSchedule();
		shiftSchedule.setScheduleId(schedule.getScheduleId());
		shiftSchedule.setScheduleName(schedule.getScheduleName());
		if (schedule.getIsDeleted() != null && schedule.getIsDeleted() == 1)
		{
			memberSchedule.setIsDeleted(1);
			shiftSchedule.setIsDeleted(1);
			getBaseDao().update("MemberSchedule.deleteByScheduleId",
					memberSchedule);
			getBaseDao().update("ShiftSchedule.deleteByScheduleId",
					shiftSchedule);

		} else
		{
			getBaseDao().update("MemberSchedule.updateByScheduleId",
					memberSchedule);
			getBaseDao().update("ShiftSchedule.updateByScheduleId",
					shiftSchedule);

		}
		getBaseDao().update("Schedule.update", schedule);
	}

	
	public Schedule findById(Long id)
	{
		return getBaseDao().selectOne("Schedule.findById", id);
	}

	
	public List<Schedule> queryList(Schedule schedule)
	{
		return getBaseDao().selectList("Schedule.queryList", schedule);
	}

	
	public Integer queryListCount(Schedule schedule)
	{
		return getBaseDao().selectOne("Schedule.queryListCount", schedule);
	}

	
	public void saveMemberAndShift(List<MemberSchedule> list,
			ShiftSchedule shiftSchedule)
	{
		String distributionMemberNames = "";
		Schedule schedule = findById(shiftSchedule.getScheduleId());
		DistributionStation station = distributionStationService
				.findById(shiftSchedule.getDistributionStationId());

		for (MemberSchedule memberSchedule : list)
		{
			DistributionMember member = memberService.findById(memberSchedule
					.getDistributionMemberId());
			distributionMemberNames += "," + member.getDistributionMemberName();
			memberSchedule.setScheduleDate(shiftSchedule.getScheduleDate());
			memberSchedule.setScheduleName(schedule.getScheduleName());
		}
		if (distributionMemberNames.length() > 0)
		{
			distributionMemberNames = distributionMemberNames.substring(1);
		}
		shiftSchedule.setDistributionStationName(station
				.getDistributionStationName());
		shiftSchedule.setScheduleName(schedule.getScheduleName());
		shiftSchedule.setScheduleDate(shiftSchedule.getScheduleDate());
		shiftSchedule.setDistributionMemberNames(distributionMemberNames);
		if (shiftSchedule.getShiftScheduleId() != null)
		{
			deleteMemberAndShift(shiftSchedule.getShiftScheduleId());
		}
		memberScheduleService.saveMemberScheduleList(list);
		shiftScheduleService.saveShiftSchedule(shiftSchedule);
	}

	
	public void deleteMemberAndShift(Long id)
	{
		ShiftSchedule shift = shiftScheduleService.findById(id);
		shift.setIsDeleted(1);
		MemberSchedule mSchedule = new MemberSchedule();
		mSchedule.setScheduleDate(shift.getScheduleDate());
		mSchedule.setScheduleId(shift.getScheduleId());
		mSchedule.setDistributionStationId(shift.getDistributionStationId());
		List<MemberSchedule> list = memberScheduleService.queryList(mSchedule);
		shiftScheduleService.updateShiftSchedule(shift);
		for (MemberSchedule ms : list)
		{
			ms.setIsDeleted(1);
			memberScheduleService.updateSchedule(ms);
		}
	}

	
	public List<Schedule> queryScheduleForMember(MemberSchedule mSchedule)
	{
		return getBaseDao().selectList("Schedule.queryScheduleForMember",
				mSchedule);
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

	public IShiftScheduleService getShiftScheduleService()
	{
		return shiftScheduleService;
	}

	public void setShiftScheduleService(
			IShiftScheduleService shiftScheduleService)
	{
		this.shiftScheduleService = shiftScheduleService;
	}

	public IDistributionStationService getDistributionStationService()
	{
		return distributionStationService;
	}

	public void setDistributionStationService(
			IDistributionStationService distributionStationService)
	{
		this.distributionStationService = distributionStationService;
	}

	public IDistributionMemberService getMemberService()
	{
		return memberService;
	}

	public void setMemberService(IDistributionMemberService memberService)
	{
		this.memberService = memberService;
	}

}
