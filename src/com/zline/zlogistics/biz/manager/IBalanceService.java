package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.dto.BalanceLine0Dto;
import com.zline.zlogistics.biz.dal.dto.BalanceMemberDto;
import com.zline.zlogistics.biz.dal.entity.BalanceMember;

public interface IBalanceService {

	public void saveByBalanceMember(BalanceMember balance);

	public List<BalanceMember> queryLine0Total(BalanceMemberDto balanceMemberDto);

	public void saveLine0ByDto(BalanceLine0Dto paramDto);

	public void updateByMemberDto(BalanceMemberDto balanceMemberDto);

	public Integer queryCountTodayLine0BalanceToday(BalanceLine0Dto model);
}
