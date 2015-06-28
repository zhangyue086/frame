package com.zline.zlogistics.web.timer;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.manager.ILogisticsTimerService;

/**
 * 自动派单超过一定时间以后派单到手动
 * 
 * @author xiaojun
 *
 */
public class ChangeOrderSendMethodTimer
{
	private static Logger logger = Logger
			.getLogger(ChangeOrderSendMethodTimer.class);
	private ILogisticsTimerService logisticsTimerService;

	public void changeSendMethod()
	{
		try
		{
			logger.info("ChangeOrderSendMethodTimer...........");
			logisticsTimerService.updateOrderSendMethod();
		} catch (Exception e)
		{
			logger.error(e);
		}
	}

	public ILogisticsTimerService getLogisticsTimerService()
	{
		return logisticsTimerService;
	}

	public void setLogisticsTimerService(
			ILogisticsTimerService logisticsTimerService)
	{
		this.logisticsTimerService = logisticsTimerService;
	}

}
