package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.LeaveInfo;
import com.zline.zlogistics.biz.manager.ILeaveInfoService;

public class LeaveInfoServiceImpl extends BaseService implements ILeaveInfoService {

	
	public void saveLeaveInfo(LeaveInfo leaveInfo) {
		getBaseDao().insert("LeaveInfo.save", leaveInfo);
	}

	
	public void updateLeaveInfo(LeaveInfo leaveInfo) {
		getBaseDao().update("LeaveInfo.update", leaveInfo);
	}

	
	public LeaveInfo findById(Long id) {
		return getBaseDao().selectOne("LeaveInfo.findById", id);
	}

	
	public List<LeaveInfo> queryList(LeaveInfo leaveInfo) {
		return getBaseDao().selectList("LeaveInfo.queryList", leaveInfo);
	}

	
	public Integer queryListCount(LeaveInfo leaveInfo) {
		return getBaseDao().selectOne("LeaveInfo.queryListCount", leaveInfo);
	}

}
