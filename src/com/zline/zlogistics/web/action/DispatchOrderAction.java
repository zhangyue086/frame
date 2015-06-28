package com.zline.zlogistics.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.dto.LogisticsOrderDto;
import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.LogisticsOrder;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.ILogisticsOrderService;
import com.zline.zlogistics.biz.util.Message;

public class DispatchOrderAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(DispatchOrderAction.class);
	private Message message;
	private List<City> cityList;
	private ICityService cityService;
	private ILogisticsOrderService logisticsOrderService;
	private List<LogisticsOrder> logisticsOrderList;
	
	
	/**
	 * 进入系统手动派单页面
	 * @return
	 */
	public String manual(){
		cityList = cityService.queryList();
		return "manual";
	}
	
	/**
	 * 获取待调度订单
	 * @return
	 */
	public String getUnResolvedOrder(){
		String stationIds=getRequest().getParameter("stationIds");
		if(stationIds==null||stationIds.equals("null")){
			return "unresolverorder";
		}
		LogisticsOrderDto logisticsOrderDto=new LogisticsOrderDto();
		logisticsOrderDto.setSendStatus(0);
		logisticsOrderDto.setSendMethod(2);
		String[] idsArr=stationIds.split(",");
		List<Integer> distributionStationIds=new ArrayList<Integer>();
		for(int i=0;i<idsArr.length;i++){
			distributionStationIds.add(Integer.valueOf(idsArr[i]));
		}
		logisticsOrderDto.setDistributionStationIds(distributionStationIds);
		logisticsOrderList=logisticsOrderService.getUnResolvedOrder(logisticsOrderDto);
		
		return "unresolverorder";
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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public ILogisticsOrderService getLogisticsOrderService() {
		return logisticsOrderService;
	}

	public void setLogisticsOrderService(ILogisticsOrderService logisticsOrderService) {
		this.logisticsOrderService = logisticsOrderService;
	}

	public List<LogisticsOrder> getLogisticsOrderList() {
		return logisticsOrderList;
	}

	public void setLogisticsOrderList(List<LogisticsOrder> logisticsOrderList) {
		this.logisticsOrderList = logisticsOrderList;
	}

	
	
	

}
