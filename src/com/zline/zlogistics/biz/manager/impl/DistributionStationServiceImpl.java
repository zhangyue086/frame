package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.manager.IDistributionStationService;

public class DistributionStationServiceImpl extends BaseService implements IDistributionStationService {

	
	public void saveStation(DistributionStation station) {
		this.getBaseDao().insert("DistributionStation.save", station);
	}

	
	public void updateStation(DistributionStation station) {
		this.getBaseDao().update("DistributionStation.update", station);
	}

	
	public DistributionStation findById(Long id) {
		return this.getBaseDao().selectOne("DistributionStation.findById", id);
	}

	
	public List<DistributionStation> queryList(DistributionStation station) {
		return this.getBaseDao().selectList("DistributionStation.queryList", station);
	}

	
	public Integer queryListCount(DistributionStation station) {
		return this.getBaseDao().selectOne("DistributionStation.queryListCount", station);
	}

}
