package com.zline.zlogistics.web.action;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.manager.ICityService;

public class UserAction extends BaseAction
{

	private static final long serialVersionUID = 1L;

	private ICityService cityService;
	private List<City> cityList;

	public String initList()
	{
		cityList = cityService.queryList();
		return "initList";
	}

	public ICityService getCityService()
	{
		return cityService;
	}

	public void setCityService(ICityService cityService)
	{
		this.cityService = cityService;
	}

	public List<City> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<City> cityList)
	{
		this.cityList = cityList;
	}

}
