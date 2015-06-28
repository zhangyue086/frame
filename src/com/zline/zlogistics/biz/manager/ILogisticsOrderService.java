package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.dto.LogisticsOrderDto;
import com.zline.zlogistics.biz.dal.entity.LogisticsOrder;

public interface ILogisticsOrderService {

	//查询订单
	public List<LogisticsOrder> queryListLikeOrderCode(LogisticsOrderDto logisticsOrderDto);

	//根据id查询
	public LogisticsOrder queryByKey(Long logisticsOrderId);
	
	//查询待带调度订单
	public List<LogisticsOrder> getUnResolvedOrder(LogisticsOrderDto logisticsOrderDto);
	
	//根据小哥工号/姓名 查询小哥应该 结算金额(总)
	public LogisticsOrder queryTotalInfoByKeyword(LogisticsOrderDto logisticsOrderDto);
	
	public List<LogisticsOrder> queryBalanceList(LogisticsOrderDto logisticsOrderDto);

	public void balanceByDto(LogisticsOrderDto logisticsOrderDto);

	public Integer queryCount(LogisticsOrderDto logisticsOrderDto);


}
