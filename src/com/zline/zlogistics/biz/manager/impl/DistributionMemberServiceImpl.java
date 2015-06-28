package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.Schedule;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;

public class DistributionMemberServiceImpl extends BaseService implements
		IDistributionMemberService
{

	
	public void saveMember(DistributionMember member)
	{
		getBaseDao().insert("DistributionMember.save", member);
		DistributionMember memberNew = new DistributionMember();
		memberNew.setDistributionMemberId(member.getDistributionMemberId());
		memberNew.setWorkNumber(member.getDistributionMemberId()+"");
		getBaseDao().update("DistributionMember.updateWorkNumber", memberNew);

	}

	
	public void updateMember(DistributionMember member)
	{
		getBaseDao().update("DistributionMember.update", member);

	}

	
	public DistributionMember findById(Long id)
	{
		return getBaseDao().selectOne("DistributionMember.findById", id);
	}

	
	public List<DistributionMember> queryList(DistributionMember member)
	{
		return getBaseDao().selectList("DistributionMember.queryList", member);
	}

	
	public Integer queryListCount(DistributionMember member)
	{
		return getBaseDao().selectOne("DistributionMember.queryListCount",
				member);
	}

	
	public String createWorkNum()
	{
		Integer id = getBaseDao().selectOne("DistributionMember.findMaxId");
		id++;
		return id.toString();
	}

	
	public List<DistributionMember> selectScheduleMember(Schedule schudule)
	{
		return getBaseDao().selectList("DistributionMember.selectScheduleMember",schudule);
	}

	
	public List<DistributionMember> selectRepeatMember(Schedule schudule)
	{
		return getBaseDao().selectList("DistributionMember.selectRepeatMember",schudule);

	}

	
	public List<DistributionMember> selectAllMember(Schedule schudule)
	{
		return getBaseDao().selectList("DistributionMember.selectAllMember",schudule);

	}

	
	public DistributionMember findByWorkNubmer(Long dmWorkNumber) {
		return getBaseDao().selectOne("DistributionMember.findByWorkNubmer", dmWorkNumber);
	}

}
