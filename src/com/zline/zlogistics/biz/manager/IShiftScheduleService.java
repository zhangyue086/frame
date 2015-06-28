package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.DateSchedule;
import com.zline.zlogistics.biz.dal.entity.ShiftSchedule;

public interface IShiftScheduleService {
	void saveShiftSchedule(ShiftSchedule shiftSchedule);
	void updateShiftSchedule(ShiftSchedule shiftSchedule);
	ShiftSchedule findById(Long id);
	List<ShiftSchedule> queryList(ShiftSchedule shiftSchedule);
	Integer queryListCount(ShiftSchedule shiftSchedule);
	Integer queryDateCount(DateSchedule schuduleDate);
	public Integer countMemberCount(ShiftSchedule shiftSchedule);
	public Integer countMemberSchuduleCount(ShiftSchedule shiftSchedule);
}
