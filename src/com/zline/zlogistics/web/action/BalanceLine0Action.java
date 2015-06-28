package com.zline.zlogistics.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.base.ModelDrivenActionSupport;
import com.zline.zlogistics.biz.dal.dto.BalanceLine0Dto;
import com.zline.zlogistics.biz.dal.dto.BalanceMemberDto;
import com.zline.zlogistics.biz.dal.entity.BalanceLine0;
import com.zline.zlogistics.biz.dal.entity.BalanceMember;
import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.dal.entity.User;
import com.zline.zlogistics.biz.manager.IBalanceService;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;
import com.zline.zlogistics.biz.util.ConstantDef;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.biz.util.UserContext;
import com.zline.zlogistics.web.common.DataTableReturnObject;

/**
 * Description: 零号线结算action
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-6
 * 
 * @version 1.0
 */
public class BalanceLine0Action extends ModelDrivenActionSupport<BalanceLine0Dto> {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(BalanceLine0Action.class);

	private Message message;
	private List<City> cityList;
	private BalanceMemberDto balanceMemberDto;
	private List<DistributionStation> stationList;

	private ICityService cityService;
	private IBalanceService balanceService;
	private DataTableReturnObject<BalanceLine0> returnObject;
	private IDistributionStationService distributionStationService;

	public String initBalance() {
		// 差城市
		cityList = cityService.queryList();
		return "init_balance";
	}

	public String balanceLine0() {
		User user = UserContext.getUser();
		message = new Message();
		message.setIsSuccess(false);
		if (user == null) {
			// 转登录页面
			message.setInfo("异常:用户还没有登录!");
			return "msg_json";
		}
		BalanceMemberDto balanceMemDto = new BalanceMemberDto();
		balanceMemDto.setSearchDate(this.getModel().getSearchDate());
		balanceMemDto.setBalancedStationId(this.getModel().getStationId());
		List<BalanceMember> balances = balanceService.queryLine0Total(balanceMemDto);
		if (balances != null && !balances.isEmpty()) {
			Float waitBalanceTotal = 0f;
			for (BalanceMember balanceMember : balances) {
				waitBalanceTotal += balanceMember.getBalancedAccount();
			}
			try {
				// 增加一条 站点结算流水
				BalanceLine0Dto paramDto = new BalanceLine0Dto();
				paramDto.setBalancedAccount(waitBalanceTotal);
				paramDto.setCityId(this.getModel().getCityId());
				paramDto.setStationId(this.getModel().getStationId());
				paramDto.setCreateId(user.getUserId());
				paramDto.setLastUpdateId(user.getUserId());
				balanceService.saveLine0ByDto(paramDto);
				// 更改 每条小哥结算流水的 站点结算状态
				BalanceMemberDto balanceMemberDto = null;
				for (BalanceMember balanceMember : balances) {
					if (balanceMember.getStationBalanceStatus() == ConstantDef.STATION_NOT_BALANCED) {
						continue;
					}
					balanceMemberDto = new BalanceMemberDto();
					balanceMemberDto.setBalanceId(balanceMember.getBalanceId());
					balanceMemberDto.setStationBalanceStatus(ConstantDef.STATION_ALREADY_BALANCED);
					balanceService.updateByMemberDto(balanceMemberDto);
				}
				message.setIsSuccess(true);
			} catch (Exception e) {
				logger.error(e);
				message.setInfo("异常:" + e);
			}
		} else {
			message.setInfo("异常:无数据");
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

	/**
	 * 零号线结算 页面搜索
	 * 
	 * @return
	 */
	public String queryStationBalance() {
		// 初始化 结果容器
		returnObject = new DataTableReturnObject<BalanceLine0>();
		List<BalanceLine0> list = new ArrayList<BalanceLine0>();
		balanceMemberDto = new BalanceMemberDto();
		balanceMemberDto.setSearchDate(this.getModel().getSearchDate());
		balanceMemberDto.setBalancedStationId(this.getModel().getBalancedStationId());
		// 可以包含 已经结算给站点的 小哥流水
		List<BalanceMember> balanceMembers = balanceService.queryLine0Total(balanceMemberDto);
		City city = cityService.queryByKey(this.getModel().getCityId());
		DistributionStation station = distributionStationService.findById(this.getModel().getBalancedStationId());
		Float waitBalanceTotal = 0f;
		if (balanceMembers != null && !balanceMembers.isEmpty()) {
			for (BalanceMember balanceMember : balanceMembers) {
				waitBalanceTotal += balanceMember.getBalancedAccount();
			}
			BalanceLine0 balanceLine0 = new BalanceLine0();
			balanceLine0.setWaitBalanceTotal(waitBalanceTotal);
			balanceLine0.setCity(city);
			balanceLine0.setStation(station);
			balanceLine0.setBalanceDate(this.getModel().getSearchDate());
			Integer count = balanceService.queryCountTodayLine0BalanceToday(this.getModel());
			if (count != null && count > 0) {
				balanceLine0.setHasRecordToday(true);
			} else {
				balanceLine0.setHasRecordToday(false);
			}
			list.add(balanceLine0);
		}
		returnObject.setData(list);
		return "dt_json";

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		BalanceLine0Action.logger = logger;
	}

	public IBalanceService getBalanceService() {
		return balanceService;
	}

	public void setBalanceService(IBalanceService balanceService) {
		this.balanceService = balanceService;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
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

	public BalanceMemberDto getBalanceMemberDto() {
		return balanceMemberDto;
	}

	public void setBalanceMemberDto(BalanceMemberDto balanceMemberDto) {
		this.balanceMemberDto = balanceMemberDto;
	}

	public DataTableReturnObject<BalanceLine0> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject<BalanceLine0> returnObject) {
		this.returnObject = returnObject;
	}

}
