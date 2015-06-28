package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.OrderStatus;
import com.zline.zlogistics.biz.manager.IOrderStatusService;

public class OrderStatusServiceImpl extends BaseService implements IOrderStatusService {

	
	public List<OrderStatus> queryStatusList(Long logisticsOrderId) {
		return this.getBaseDao().selectList("logisticsOrderStatusMapper.queryStatusList",logisticsOrderId);
	}


}
