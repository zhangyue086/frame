package com.zline.zlogistics.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.base.ModelDrivenActionSupport;
import com.zline.zlogistics.biz.dal.dto.BalanceMemberDto;
import com.zline.zlogistics.biz.dal.dto.LogisticsOrderDto;
import com.zline.zlogistics.biz.dal.entity.BalanceMember;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.dal.entity.LogisticsOrder;
import com.zline.zlogistics.biz.dal.entity.User;
import com.zline.zlogistics.biz.manager.IBalanceService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;
import com.zline.zlogistics.biz.manager.ILogisticsOrderService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.biz.util.UserContext;
import com.zline.zlogistics.web.common.DataTableReturnObject;

/**
 * Description: 小哥结算action
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-6
 * 
 * @version 1.0
 */
public class BalanceMemberAction extends ModelDrivenActionSupport<BalanceMemberDto> {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(BalanceMemberAction.class);

	private Message message;
	private LogisticsOrder logisticsOrder;
	private LogisticsOrderDto logisticsOrderDto;
	private List<DistributionStation> stationList;
	private List<LogisticsOrder> logisticsOrderList;

	private IBalanceService balanceService;
	private ILogisticsOrderService logisticsOrderService;
	private DataTableReturnObject<LogisticsOrder> returnObject;
	private IDistributionStationService distributionStationService;

	/**
	 * 初始化
	 * 使用
	 * @return
	 */
	public String initBalance() {
		return "init_balance";
	}

	/**
	 * 根据姓名或者工号模糊搜索
	 * 使用
	 * @return
	 */
	public String queryTotalInfo() {
		//初始化
		returnObject = new DataTableReturnObject<LogisticsOrder>();
		logisticsOrderList = new ArrayList<LogisticsOrder>();

		//数据查询
		if (StringUtils.isNotBlank(this.getModel().getKeyword()) && StringUtils.isNotBlank(this.getModel().getSearchDate())) {
			logisticsOrderDto = new LogisticsOrderDto();
			logisticsOrderDto.setKeyword(this.getModel().getKeyword());
			logisticsOrderDto.setSearchDate(this.getModel().getSearchDate());
			logisticsOrder = logisticsOrderService.queryTotalInfoByKeyword(logisticsOrderDto);
			if (logisticsOrder != null) {
				logisticsOrderList.add(logisticsOrder);

			}
		}
		returnObject.setData(logisticsOrderList);
		return "json_order";
	}

	/**
	 * 结算详情
	 * 使用
	 * @return
	 */
	public String initDetail() {
		if(this.getModel().getDmWorkNumber() != null && StringUtils.isNotBlank(this.getModel().getSearchDate())){
			logisticsOrderDto = new LogisticsOrderDto();
			logisticsOrderDto.setDmWorkNumber(this.getModel().getDmWorkNumber());
			logisticsOrderDto.setSearchDate(this.getModel().getSearchDate());
			
			//总数据
			logisticsOrder = logisticsOrderService.queryTotalInfoByKeyword(logisticsOrderDto);
			logisticsOrderList = logisticsOrderService.queryBalanceList(logisticsOrderDto);
		}
		return "init_balance_member_detail";
	}

	/**
	 * 根据id结算
	 * 使用
	 * @return
	 */
	public String balanceById() {
		User user = UserContext.getUser();
		message = new Message();
		message.setIsSuccess(true);
		if(user == null){
			message.setIsSuccess(false);
			message.setInfo("异常:用户未登录");
			return "msg_json";
		}
		try {
			if (this.getModel().getLogisticsOrderId() != null) {
				// 判断订单是否结算 记得加
				logisticsOrder = logisticsOrderService.queryByKey(this.getModel().getLogisticsOrderId());
				if (logisticsOrder == null || logisticsOrder.getBalanceMemberSatus() == 1) {
					message.setIsSuccess(false);
					message.setInfo("异常:订单已结算");
					return "msg_json";
				}
				// 更改结算状态
				logisticsOrderDto = new LogisticsOrderDto();
				logisticsOrderDto.setBalanceMemberStatus(1);
				logisticsOrderDto.setLogisticsOrderId(logisticsOrder.getLogisticsOrderId());
				logisticsOrderDto.setLastUpdateId(user.getUserId());
				logisticsOrderService.balanceByDto(logisticsOrderDto);
				// 添加结算流水
				BalanceMember balance = new BalanceMember();
				balance.setDmWorkNumber(logisticsOrder.getDmWorkNumber());
				balance.setLogisticsOrderId(logisticsOrder.getLogisticsOrderId());
				balance.setBalancedAccount(logisticsOrder.getNeedPay());
				balance.setBalancedStationId(user.getStationId());
				balance.setCreateId(user.getUserId());
				balance.setLastUpdateId(user.getUserId());
				balanceService.saveByBalanceMember(balance);
			} else {
				message.setIsSuccess(false);
				message.setInfo("异常:订单id异常");
			}
		} catch (Exception e) {
			message.setIsSuccess(false);
			message.setInfo("异常:服务器异常,"+e.getCause());
			logger.error(e);
		}
		return "msg_json";
	}

	/**
	 *  结算全部
	 *  使用
	 * @return
	 */
	public String balanceAll() {
		User user = UserContext.getUser();
		message = new Message();
		message.setIsSuccess(true);
		if(user == null){
			message.setIsSuccess(false);
			message.setInfo("异常:用户未登录");
			return "msg_json";
		}
		try {
			logisticsOrderDto = new LogisticsOrderDto();
			if(StringUtils.isNotBlank(this.getModel().getSearchDate()) && this.getModel().getDmWorkNumber() != null){
				logisticsOrderDto.setDmWorkNumber(this.getModel().getDmWorkNumber());
				logisticsOrderDto.setSearchDate(this.getModel().getSearchDate());
			}
			logisticsOrderList = logisticsOrderService.queryBalanceList(logisticsOrderDto);
			if (logisticsOrderList != null && !logisticsOrderList.isEmpty()) {
				LogisticsOrderDto orderDto = null;
				for (LogisticsOrder order : logisticsOrderList) {
					logisticsOrder = logisticsOrderService.queryByKey(order.getLogisticsOrderId());
					if (logisticsOrder == null || logisticsOrder.getBalanceMemberSatus() == 1) {
						continue;
					}
					// 更改结算状态
					orderDto = new LogisticsOrderDto();
					orderDto.setBalanceMemberStatus(1);
					orderDto.setLastUpdateId(user.getUserId());
					orderDto.setLogisticsOrderId(order.getLogisticsOrderId());
					logisticsOrderService.balanceByDto(orderDto);
					// 添加结算流水
					BalanceMember balance = new BalanceMember();
					balance.setDmWorkNumber(logisticsOrder.getDmWorkNumber());
					balance.setLogisticsOrderId(logisticsOrder.getLogisticsOrderId());
					balance.setBalancedAccount(logisticsOrder.getNeedPay());
					balance.setBalancedStationId(user.getStationId());
					balance.setCreateId(user.getUserId());
					balance.setLastUpdateId(user.getUserId());
					balanceService.saveByBalanceMember(balance);
				}
			} else {
				message.setIsSuccess(false);
				message.setInfo("异常:订单已结算完毕");
			}
		} catch (Exception e) {
			message.setIsSuccess(false);
			logger.error(e);
			message.setInfo("异常:服务器异常,"+e.getCause());
		}
		return "msg_json";
	}


	/**
	 * 根据城市查站点
	 * 
	 * @return
	 */
	public String queryStation() {

		String cityId = this.getRequest().getParameter("cityId");
		stationList = new ArrayList<DistributionStation>();
		if (cityId != null) {
			DistributionStation station = new DistributionStation();
			station.setCityId(Long.parseLong(cityId));
			stationList = distributionStationService.queryList(station);
		}
		return "sta_json";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LogisticsOrderDto getLogisticsOrderDto() {
		return logisticsOrderDto;
	}

	public void setLogisticsOrderDto(LogisticsOrderDto logisticsOrderDto) {
		this.logisticsOrderDto = logisticsOrderDto;
	}

	public LogisticsOrder getLogisticsOrder() {
		return logisticsOrder;
	}

	public void setLogisticsOrder(LogisticsOrder logisticsOrder) {
		this.logisticsOrder = logisticsOrder;
	}

	public ILogisticsOrderService getLogisticsOrderService() {
		return logisticsOrderService;
	}

	public void setLogisticsOrderService(ILogisticsOrderService logisticsOrderService) {
		this.logisticsOrderService = logisticsOrderService;
	}


	public DataTableReturnObject<LogisticsOrder> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject<LogisticsOrder> returnObject) {
		this.returnObject = returnObject;
	}

	public List<LogisticsOrder> getLogisticsOrderList() {
		return logisticsOrderList;
	}

	public void setLogisticsOrderList(List<LogisticsOrder> logisticsOrderList) {
		this.logisticsOrderList = logisticsOrderList;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		BalanceMemberAction.logger = logger;
	}

	public IBalanceService getBalanceService() {
		return balanceService;
	}

	public void setBalanceService(IBalanceService balanceService) {
		this.balanceService = balanceService;
	}
	public List<DistributionStation> getStationList() {
		return stationList;
	}

	public void setStationList(List<DistributionStation> stationList) {
		this.stationList = stationList;
	}

	public IDistributionStationService getDistributionStationService() {
		return distributionStationService;
	}

	public void setDistributionStationService(IDistributionStationService distributionStationService) {
		this.distributionStationService = distributionStationService;
	}
}
