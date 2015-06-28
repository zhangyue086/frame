package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.MemberSchedule;
import com.zline.zlogistics.biz.dal.entity.Schedule;
import com.zline.zlogistics.biz.dal.entity.ShiftSchedule;

public interface IScheduleService {
	void saveSchedule(Schedule schedule);
	void updateSchedule(Schedule schedule);
	Schedule findById(Long id);
	List<Schedule> queryList(Schedule schedule);
	Integer queryListCount(Schedule schedule);
	void saveMemberAndShift(List<MemberSchedule> list,ShiftSchedule shiftSchedule);
	void deleteMemberAndShift(Long id);
	List<Schedule> queryScheduleForMember(MemberSchedule mSchedule);
}
