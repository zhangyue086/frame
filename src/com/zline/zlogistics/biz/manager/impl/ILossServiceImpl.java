package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.dto.OrderLossDto;
import com.zline.zlogistics.biz.dal.entity.OrderLoss;
import com.zline.zlogistics.biz.manager.ILossService;

public class ILossServiceImpl extends BaseService implements ILossService {

	
	public List<OrderLoss> queryListByDto(OrderLossDto orderLossDto) {
		return this.getBaseDao().selectList("orderLossMapper.queryListByDto", orderLossDto);
	}

	
	public Integer queryCount(OrderLossDto orderLossDto) {
		return this.getBaseDao().selectOne("orderLossMapper.queryCount", orderLossDto);
	}

	
	public OrderLoss queryByKey(Long lossId) {
		return this.getBaseDao().selectOne("orderLossMapper.queryByKey", lossId);
	}

	
	public void updateByKey(OrderLossDto orderLossDto) {
		this.getBaseDao().update("orderLossMapper.updateByKey", orderLossDto);
	}

}
