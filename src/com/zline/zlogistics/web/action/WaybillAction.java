package com.zline.zlogistics.web.action;



import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.base.ModelDrivenActionSupport;
import com.zline.zlogistics.biz.dal.dto.LogisticsOrderDto;
import com.zline.zlogistics.biz.dal.entity.LogisticsOrder;
import com.zline.zlogistics.biz.dal.entity.OrderStatus;
import com.zline.zlogistics.biz.manager.ILogisticsOrderService;
import com.zline.zlogistics.biz.manager.IOrderStatusService;
import com.zline.zlogistics.web.common.DataTableReturnObject;

/**
 * Description: 运单action
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-6
 * 
 * @version 1.0
 */
public class WaybillAction extends ModelDrivenActionSupport<LogisticsOrderDto> {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(WaybillAction.class);

	private List<OrderStatus> statusList;
	private LogisticsOrder logisticsOrder;
	private List<LogisticsOrder> orderList;
	private LogisticsOrderDto logisticsOrderDto;
	
	// 运单service
	private ILogisticsOrderService logisticsOrderService;
	private IOrderStatusService orderStatusService;
	
	private DataTableReturnObject<LogisticsOrder> returnObject;

	public String initList() {
		return "init_list";
	}

	public String initDetail() {
		logisticsOrder = logisticsOrderService.queryByKey(this.getModel().getLogisticsOrderId());
		//订单 状态流水表
		//查出个list
		statusList  =  orderStatusService.queryStatusList(logisticsOrder.getLogisticsOrderId());
		
		return "init_detail";
	}

	/**
	 * 根据订单号或者订单号后5位模糊搜索
	 */
	public String queryList() {
		// 初始化
		returnObject = new DataTableReturnObject<LogisticsOrder>();
		orderList = new ArrayList<LogisticsOrder>();
		returnObject.setData(orderList);

		// 根据 ordercode 模糊查询
		this.getModel().setFirstRow(Integer.parseInt(param("start")));
		this.getModel().setPageRows(Integer.parseInt(param("length")));
		orderList = logisticsOrderService.queryListLikeOrderCode(this.getModel());
		Integer orderCount = logisticsOrderService.queryCount(this.getModel());

		// 设置dataTable数据格式
		if (orderList != null && !orderList.isEmpty()) {
			returnObject.setData(orderList);
		}
		returnObject.setDraw(Integer.parseInt(param("draw") == null ? "0" : param("draw")) + 1);
		returnObject.setRecordsTotal(orderCount);
		returnObject.setRecordsFiltered(orderCount);

		return "query_list";
	}

	public ILogisticsOrderService getLogisticsOrderService() {
		return logisticsOrderService;
	}

	public void setLogisticsOrderService(ILogisticsOrderService logisticsOrderService) {
		this.logisticsOrderService = logisticsOrderService;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		WaybillAction.logger = logger;
	}

	public LogisticsOrderDto getLogisticsOrderDto() {
		return logisticsOrderDto;
	}

	public void setLogisticsOrderDto(LogisticsOrderDto logisticsOrderDto) {
		this.logisticsOrderDto = logisticsOrderDto;
	}

	public DataTableReturnObject<LogisticsOrder> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject<LogisticsOrder> returnObject) {
		this.returnObject = returnObject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LogisticsOrder getLogisticsOrder() {
		return logisticsOrder;
	}

	public void setLogisticsOrder(LogisticsOrder logisticsOrder) {
		this.logisticsOrder = logisticsOrder;
	}

	public List<LogisticsOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<LogisticsOrder> orderList) {
		this.orderList = orderList;
	}

	public IOrderStatusService getOrderStatusService() {
		return orderStatusService;
	}

	public void setOrderStatusService(IOrderStatusService orderStatusService) {
		this.orderStatusService = orderStatusService;
	}

	public List<OrderStatus> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<OrderStatus> statusList) {
		this.statusList = statusList;
	}



}
