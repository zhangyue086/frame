package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.dto.BalanceLine0Dto;
import com.zline.zlogistics.biz.dal.dto.BalanceMemberDto;
import com.zline.zlogistics.biz.dal.entity.BalanceMember;
import com.zline.zlogistics.biz.manager.IBalanceService;

public class BalanceServiceImpl extends BaseService implements IBalanceService {

	
	public void saveByBalanceMember(BalanceMember balance) {
		getBaseDao().insert("balanceMemberMapper.insertOne", balance);
	}

	
	public List<BalanceMember> queryLine0Total(BalanceMemberDto balanceMemberDto) {
		return getBaseDao().selectList("balanceMemberMapper.queryForLine0Total", balanceMemberDto);
	}

	
	public void saveLine0ByDto(BalanceLine0Dto paramDto) {
		getBaseDao().insert("balanceLine0Mapper.insertOne", paramDto);
	}

	
	public void updateByMemberDto(BalanceMemberDto balanceMemberDto) {
		getBaseDao().update("balanceMemberMapper.updateByMemberDto", balanceMemberDto);
	}

	
	public Integer queryCountTodayLine0BalanceToday(BalanceLine0Dto balanceLine0Dto) {
		return getBaseDao().selectOne("balanceLine0Mapper.queryCountTodayLine0BalanceToday", balanceLine0Dto);
	}

}
