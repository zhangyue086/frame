package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.manager.ICityService;

public class CityServiceImpl extends BaseService implements ICityService{

	
	public List<City> queryList() {
		return getBaseDao().selectList("city.queryList");
	}

	
	public City queryByKey(Integer cityId) {
		return getBaseDao().selectOne("city.queryByKey",cityId);
	}

}
