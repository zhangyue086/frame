package com.zline.zlogistics.web.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;

public class ScheduleNewAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(ScheduleNewAction.class);
	private IDistributionStationService distributionStationService;
	private ICityService cityService;
	private DistributionStation station;
	private List<City> cityList;
	private List<DistributionStation> stationList;
	private String today;
	public String initList()
	{
		// 查询登录用户所在物流点
		cityList = cityService.queryList();
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		today = format.format(calendar.getTime());
		return "initlist";
	}

	public IDistributionStationService getDistributionStationService()
	{
		return distributionStationService;
	}

	public void setDistributionStationService(
			IDistributionStationService distributionStationService)
	{
		this.distributionStationService = distributionStationService;
	}

	public ICityService getCityService()
	{
		return cityService;
	}

	public void setCityService(ICityService cityService)
	{
		this.cityService = cityService;
	}

	public DistributionStation getStation()
	{
		return station;
	}

	public void setStation(DistributionStation station)
	{
		this.station = station;
	}

	public List<City> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<City> cityList)
	{
		this.cityList = cityList;
	}

	public List<DistributionStation> getStationList()
	{
		return stationList;
	}

	public void setStationList(List<DistributionStation> stationList)
	{
		this.stationList = stationList;
	}

	public String getToday()
	{
		return today;
	}

	public void setToday(String today)
	{
		this.today = today;
	}

}
