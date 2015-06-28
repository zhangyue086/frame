package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.Attendance;
import com.zline.zlogistics.biz.dal.entity.AttendanceInfo;
import com.zline.zlogistics.biz.manager.IAttendanceService;

public class AttendanceServiceImpl extends BaseService implements IAttendanceService {

	
	public void saveAttendance(Attendance attendance) {
		getBaseDao().insert("Attendance.save", attendance);

	}

	
	public void updateAttendancee(Attendance attendance) {
		getBaseDao().update("Attendance.update", attendance);
	}

	
	public Attendance findById(Long id) {
		return getBaseDao().selectOne("Attendance.findById", id);
	}

	
	public List<Attendance> queryList(Attendance attendance) {
		return getBaseDao().selectList("Attendance.queryList", attendance);
	}

	
	public Integer queryListCount(Attendance attendance) {
		return getBaseDao().selectOne("Attendance.queryListCount", attendance);
	}

	
	public List<AttendanceInfo> queryAttendanceInfo(AttendanceInfo info) {
		return getBaseDao().selectList("Attendance.queryAttendanceInfo", info);
	}

	
	public Integer queryAttendanceInfoCount(AttendanceInfo info) {
		return getBaseDao().selectOne("Attendance.queryAttendanceInfoCount", info);
	}

}
