package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.MemberClock;

public interface IMemberClockService
{
	/**
	 * 根据物流点和人员id返回当天的可以打卡的正常班信息
	 * 
	 * @param memberClock
	 * @return
	 */
	public List<MemberClock> listClock(MemberClock memberClock);
	/**
	 * 正常班上班打卡
	 * @param memberClock
	 */
	public void addMemberClock(MemberClock memberClock);
	/**
	 * 下班卡
	 * @param memberClock
	 */
	public void updateMemberClock(MemberClock memberClock);
	/**
	 * 加班
	 * @param memberClock
	 * @return
	 */
	public List<MemberClock> listAddClock(MemberClock memberClock);
	/**
	 * 加班上班
	 * @param memberClock
	 */
	public void addMemberAddClock(MemberClock memberClock);
	
	
	
}
