package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.LeaveInfo;


public interface ILeaveInfoService {
	void saveLeaveInfo(LeaveInfo leaveInfo);
	void updateLeaveInfo(LeaveInfo leaveInfo);
	LeaveInfo findById(Long id);
	List<LeaveInfo> queryList(LeaveInfo leaveInfo);
	Integer queryListCount(LeaveInfo leaveInfo);
}
