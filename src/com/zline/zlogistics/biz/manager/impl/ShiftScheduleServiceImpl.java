package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.DateSchedule;
import com.zline.zlogistics.biz.dal.entity.ShiftSchedule;
import com.zline.zlogistics.biz.manager.IShiftScheduleService;

public class ShiftScheduleServiceImpl extends BaseService implements IShiftScheduleService {

	
	public Integer countMemberCount(ShiftSchedule shiftSchedule)
	{
		return getBaseDao().selectOne("ShiftSchedule.countMemberCount", shiftSchedule);
	}
	
	public Integer countMemberSchuduleCount(ShiftSchedule shiftSchedule)
	{
		return getBaseDao().selectOne("ShiftSchedule.countMemberSchuduleCount", shiftSchedule);
	}
	
	
	
	public void saveShiftSchedule(ShiftSchedule shiftSchedule) {
		getBaseDao().insert("ShiftSchedule.save", shiftSchedule);

	}

	
	public void updateShiftSchedule(ShiftSchedule shiftSchedule) {
		getBaseDao().update("ShiftSchedule.update", shiftSchedule);
	}

	
	public ShiftSchedule findById(Long id) {
		return getBaseDao().selectOne("ShiftSchedule.findById",id);
	}

	
	public List<ShiftSchedule> queryList(ShiftSchedule shiftSchedule) {
		return getBaseDao().selectList("ShiftSchedule.queryList",shiftSchedule);
	}

	
	public Integer queryListCount(ShiftSchedule shiftSchedule) {
		return getBaseDao().selectOne("ShiftSchedule.queryListCount", shiftSchedule);
	}

	
	public Integer queryDateCount(DateSchedule schuduleDate)
	{
		 return getBaseDao().selectOne("ShiftSchedule.queryDateCount", schuduleDate);
	}
}
