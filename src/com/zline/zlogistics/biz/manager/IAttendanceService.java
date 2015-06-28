package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.Attendance;
import com.zline.zlogistics.biz.dal.entity.AttendanceInfo;

public interface IAttendanceService {
	void saveAttendance(Attendance attendance);
	void updateAttendancee(Attendance attendance);
	Attendance findById(Long id);
	List<Attendance> queryList(Attendance attendance);
	Integer queryListCount(Attendance attendance);
	List<AttendanceInfo> queryAttendanceInfo(AttendanceInfo info);
	Integer queryAttendanceInfoCount(AttendanceInfo info);
}
