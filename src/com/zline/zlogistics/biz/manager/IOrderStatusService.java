package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.OrderStatus;

public interface IOrderStatusService {

	List<OrderStatus> queryStatusList(Long logisticsOrderId);
}
