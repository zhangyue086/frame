package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.Schedule;

public interface IDistributionMemberService {
	void saveMember(DistributionMember member);
	void updateMember(DistributionMember member);
	DistributionMember findById(Long id);
	List<DistributionMember> queryList(DistributionMember member);
	Integer queryListCount(DistributionMember member);
	String createWorkNum();
	public List<DistributionMember> selectScheduleMember(Schedule schudule);
	public List<DistributionMember> selectRepeatMember(Schedule schudule);
	public List<DistributionMember> selectAllMember(Schedule schudule);
	public DistributionMember findByWorkNubmer(Long dmWorkNumber);
}
