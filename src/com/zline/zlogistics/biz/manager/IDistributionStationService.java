package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.DistributionStation;

public interface IDistributionStationService {
	void saveStation(DistributionStation station);
	void updateStation(DistributionStation station);
	DistributionStation findById(Long id);
	List<DistributionStation> queryList(DistributionStation station);
	Integer queryListCount(DistributionStation station);
}
