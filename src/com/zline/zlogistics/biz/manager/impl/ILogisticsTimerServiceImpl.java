package com.zline.zlogistics.biz.manager.impl;


import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.manager.ILogisticsTimerService;

public class ILogisticsTimerServiceImpl extends BaseService implements ILogisticsTimerService{
	
	public Integer updateOrderSendMethod()
	{
		return getBaseDao().update("logisticsTimerMapper.updateOrderSendMethod");
	}
}
