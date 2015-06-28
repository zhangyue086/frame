package com.zline.zlogistics.biz.manager.impl;


import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.dto.LogisticsOrderDto;
import com.zline.zlogistics.biz.dal.entity.LogisticsOrder;
import com.zline.zlogistics.biz.manager.ILogisticsOrderService;

public class LogisticsOrderServiceImpl extends BaseService implements ILogisticsOrderService{

	
	public List<LogisticsOrder> queryListLikeOrderCode(LogisticsOrderDto logisticsOrderDto) {
		return getBaseDao().selectList("logisticsOrderMapper.queryListLikeOrderCode", logisticsOrderDto);
	}

	
	public LogisticsOrder queryByKey(Long logisticsOrderId) {
		return getBaseDao().selectOne("logisticsOrderMapper.queryByKey", logisticsOrderId);
	}

	
	public List<LogisticsOrder> getUnResolvedOrder(LogisticsOrderDto logisticsOrderDto) {
		return getBaseDao().selectList("logisticsOrderMapper.getUnResolvedOrder", logisticsOrderDto);
	}

	
	public LogisticsOrder queryTotalInfoByKeyword(LogisticsOrderDto logisticsOrderDto) {
		return getBaseDao().selectOne("logisticsOrderMapper.queryTotalInfo", logisticsOrderDto);
	}

	
	public List<LogisticsOrder> queryBalanceList(LogisticsOrderDto logisticsOrderDto) {
		return getBaseDao().selectList("logisticsOrderMapper.queryBalanceList", logisticsOrderDto);
	}

	
	public void balanceByDto(LogisticsOrderDto logisticsOrderDto) {
		this.getBaseDao().update("logisticsOrderMapper.balanceByDto", logisticsOrderDto);
	}

	
	public Integer queryCount(LogisticsOrderDto logisticsOrderDto) {
		return getBaseDao().selectOne("logisticsOrderMapper.queryCount", logisticsOrderDto);
	}
}
