package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.MemberClock;
import com.zline.zlogistics.biz.manager.IMemberClockService;

public class MemberClockServiceImpl extends BaseService implements
		IMemberClockService
{

	
	public List<MemberClock> listClock(MemberClock memberClock)
	{
		if (memberClock.getMemberId() == null
				|| memberClock.getStationId() == null)
			return null;
		return this.getBaseDao().selectList("memberClock.listClock",
				memberClock);
	}

	
	public void addMemberClock(MemberClock memberClock)
	{
		// 判断是否已经是上班状态
		DistributionMember myMember = getBaseDao().selectOne(
				"DistributionMember.findById", memberClock.getMemberId());
		if (myMember.getClockStatus() == 1)
		{
			throw new RuntimeException("小哥已经是上班状态，不能打上班卡");
		}
		// 插入打卡
		memberClock.setStationId(myMember.getDistributionStationId());
		this.getBaseDao().insert("memberClock.addMemberClock", memberClock);
		// 更新小哥为已经上班状态
		DistributionMember member = new DistributionMember();
		member.setDistributionMemberId(memberClock.getMemberId());
		member.setClockStatus(1);
		getBaseDao().update("DistributionMember.update", member);

	}

	
	public void updateMemberClock(MemberClock memberClock)
	{
		this.getBaseDao().update("memberClock.updateMemberClock", memberClock);
		DistributionMember member = new DistributionMember();
		member.setDistributionMemberId(memberClock.getMemberId());
		member.setClockStatus(2);
		getBaseDao().update("DistributionMember.update", member);
	}

	
	public List<MemberClock> listAddClock(MemberClock memberClock)
	{
		List<MemberClock> list = this.getBaseDao().selectList(
				"memberClock.listAddClock", memberClock);
		if (list == null || list.isEmpty())
		{
			// 新插入一条记录可以用于打卡上班
			list = this.getBaseDao().selectList(
					"memberClock.createOneAddClock", memberClock);
		}
		return list;
	}

	
	public void addMemberAddClock(MemberClock memberClock)
	{
		// 判断是否已经是上班状态
		DistributionMember myMember = getBaseDao().selectOne(
				"DistributionMember.findById", memberClock.getMemberId());
		if (myMember.getClockStatus() == 1)
		{
			throw new RuntimeException("小哥已经是上班状态，不能打上班卡");
		}
		// 插入打卡
		memberClock.setStationId(myMember.getDistributionStationId());
		this.getBaseDao().insert("memberClock.addMemberAddClock", memberClock);
		// 更新小哥为已经上班状态
		DistributionMember member = new DistributionMember();
		member.setDistributionMemberId(memberClock.getMemberId());
		member.setClockStatus(1);
		getBaseDao().update("DistributionMember.update", member);
	}

}
