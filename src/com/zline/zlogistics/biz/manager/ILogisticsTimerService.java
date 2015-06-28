package com.zline.zlogistics.biz.manager;

public interface ILogisticsTimerService
{
	/**
	 * 更新10分钟自动派单为完成的为手动派单
	 * 
	 * @return
	 */
	public Integer updateOrderSendMethod();

}
