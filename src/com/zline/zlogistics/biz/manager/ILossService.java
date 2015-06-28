package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.dto.OrderLossDto;
import com.zline.zlogistics.biz.dal.entity.OrderLoss;



public interface ILossService {

	public List<OrderLoss> queryListByDto(OrderLossDto orderLossDto);

	public Integer queryCount(OrderLossDto orderLossDto);

	public OrderLoss queryByKey(Long lossId);

	public void updateByKey(OrderLossDto orderLossDto);

}
