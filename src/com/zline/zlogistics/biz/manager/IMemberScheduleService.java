package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.MemberSchedule;

public interface IMemberScheduleService {
	void saveMemberSchedule(MemberSchedule mSchedule);
	void updateSchedule(MemberSchedule mSchedule);
	MemberSchedule findById(Long id);
	List<MemberSchedule> queryList(MemberSchedule mSchedule);
	Integer queryListCount(MemberSchedule mSchedule);
	void saveMemberScheduleList(List<MemberSchedule> mScheduleList);
	String queryHaveShifts(MemberSchedule mSchedule);
	/**
	 * 
	 * @param mSchedule
	 * @return
	 */
	public Integer selectMemberScheduleCount(MemberSchedule mSchedule);
}
