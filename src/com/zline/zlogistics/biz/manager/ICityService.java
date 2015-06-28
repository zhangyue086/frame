package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.City;

public interface ICityService {
	List<City> queryList();

	City queryByKey(Integer cityId);
}
