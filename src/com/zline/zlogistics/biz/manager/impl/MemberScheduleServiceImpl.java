package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.MemberSchedule;
import com.zline.zlogistics.biz.manager.IMemberScheduleService;

public class MemberScheduleServiceImpl extends BaseService implements IMemberScheduleService {

	
	public void saveMemberSchedule(MemberSchedule mSchedule) {
		getBaseDao().insert("MemberSchedule.save", mSchedule);

	}

	
	public void updateSchedule(MemberSchedule mSchedule) {
		getBaseDao().update("MemberSchedule.update", mSchedule);

	}

	
	public MemberSchedule findById(Long id) {
		return getBaseDao().selectOne("MemberSchedule.findById", id);
	}

	
	public List<MemberSchedule> queryList(MemberSchedule mSchedule) {
		return getBaseDao().selectList("MemberSchedule.queryList", mSchedule);
	}

	
	public Integer queryListCount(MemberSchedule mSchedule) {
		return getBaseDao().selectOne("MemberSchedule.queryListCount", mSchedule);
	}

	
	public void saveMemberScheduleList(List<MemberSchedule> mScheduleList) {
		for(MemberSchedule mSchedule : mScheduleList){
			this.saveMemberSchedule(mSchedule);
		}
	}

	
	public String queryHaveShifts(MemberSchedule mSchedule) {
		return getBaseDao().selectOne("MemberSchedule.queryHaveShifts", mSchedule);
	}

	
	public Integer selectMemberScheduleCount(MemberSchedule mSchedule)
	{
		return getBaseDao().selectOne("MemberSchedule.selectMemberScheduleCount", mSchedule);
	}

}
