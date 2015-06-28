package com.zline.zlogistics.biz.dal.entity;

import java.util.Date;

public class LogisticsOrder extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Long logisticsOrderId; // 运单id
	private String logisticsOrderCode;// 运单编号
	private String oldOrderCode;// 订单编号
	private Integer sendStatus;// 派单状态 0：未派单1：已派单
	private Integer sendMethod;// 派单方式 1:自动派单 2:手动派单
	private Integer logisticsOrderStatus;// 运单状态 0:初始状态 1：揽货 2：配送 3：送达 4:取消
	private Integer payMethod;
	private Float needPay;
	private Float needPayReceive;
	private Date orderEstimatedPickingTime;
	private Date orderEstimatedArrivedTime;
	private Integer orderPaymentMethod;
	private Integer orderPaymentStatus;
	private Integer orderPaymentMethodActual;
	private Integer orderPaymentStatusActual;
	private Float actualPay;
	private Float orderPrice;
	private Integer receiverId;
	private String receiverCityCode;
	private String logisticsInfo;
	private Float inPrice;
	private Integer goodsCount;
	private Float goodsWeight;
	private String receiverProvince;
	private String receiverCity;
	private String receiverCounty;
	private String receiverStreet;
	private String receiverAddress;
	private String receiverMobile;
	private String receiverPhone;
	private String goodsReceiver;
	private String userRemark;
	private Long shopId;
	private String shopName;
	private String shopMobile;
	private String shopAddress;
	private String deliveryAreaCode;
	private String deliveryStationCode;
	private Double shopLng;
	private Double shopLat;
	private Double receiveLng;
	private Double receiveLat;
	private Double distance;
	private Integer orderSouce;
	private Integer balanceLine0Status;
	private Integer balanceMemberSatus;
	private Long dmWorkNumber;
	private String dmPhone;
	private String dmName;
	private Date controlledTime;
	private Date gettingTime;
	private Date shippingTime;
	private Date arrivedTime;
	private Date requirePickUpTime;
	private Date requireArrivedTime;
	private Integer companyKey;
	private Integer carryOrder;
	private Integer dateVersion;
	private DistributionStation distributionStation;
	

	/***** 结算 ****/
	private String searchDate;
	private String balanceDate;
	private Float needPayTotal;//应收(总)
	private Float inPriceTotal;//垫付(总)
	private Float waitPayTotal;//待结算金额(总)
	private Float needPayReceiveTotal;//实收(总)
	private DistributionMember member;
	private Integer balanceOrderCount;//待结算单数
	public DistributionStation getDistributionStation() {
		return distributionStation;
	}
	public void setDistributionStation(DistributionStation distributionStation) {
		this.distributionStation = distributionStation;
	}
	public Long getLogisticsOrderId() {
		return logisticsOrderId;
	}
	public void setLogisticsOrderId(Long logisticsOrderId) {
		this.logisticsOrderId = logisticsOrderId;
	}
	public String getLogisticsOrderCode() {
		return logisticsOrderCode;
	}
	public void setLogisticsOrderCode(String logisticsOrderCode) {
		this.logisticsOrderCode = logisticsOrderCode;
	}
	public String getOldOrderCode() {
		return oldOrderCode;
	}
	public void setOldOrderCode(String oldOrderCode) {
		this.oldOrderCode = oldOrderCode;
	}
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	public Integer getSendMethod() {
		return sendMethod;
	}
	public void setSendMethod(Integer sendMethod) {
		this.sendMethod = sendMethod;
	}
	public Integer getLogisticsOrderStatus() {
		return logisticsOrderStatus;
	}
	public void setLogisticsOrderStatus(Integer logisticsOrderStatus) {
		this.logisticsOrderStatus = logisticsOrderStatus;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public Float getNeedPay() {
		return needPay;
	}
	public void setNeedPay(Float needPay) {
		this.needPay = needPay;
	}
	public Float getNeedPayReceive() {
		return needPayReceive;
	}
	public void setNeedPayReceive(Float needPayReceive) {
		this.needPayReceive = needPayReceive;
	}
	public Date getOrderEstimatedPickingTime() {
		return orderEstimatedPickingTime;
	}
	public void setOrderEstimatedPickingTime(Date orderEstimatedPickingTime) {
		this.orderEstimatedPickingTime = orderEstimatedPickingTime;
	}
	public Date getOrderEstimatedArrivedTime() {
		return orderEstimatedArrivedTime;
	}
	public void setOrderEstimatedArrivedTime(Date orderEstimatedArrivedTime) {
		this.orderEstimatedArrivedTime = orderEstimatedArrivedTime;
	}
	public Integer getOrderPaymentMethod() {
		return orderPaymentMethod;
	}
	public void setOrderPaymentMethod(Integer orderPaymentMethod) {
		this.orderPaymentMethod = orderPaymentMethod;
	}
	public Integer getOrderPaymentStatus() {
		return orderPaymentStatus;
	}
	public void setOrderPaymentStatus(Integer orderPaymentStatus) {
		this.orderPaymentStatus = orderPaymentStatus;
	}
	public Integer getOrderPaymentMethodActual() {
		return orderPaymentMethodActual;
	}
	public void setOrderPaymentMethodActual(Integer orderPaymentMethodActual) {
		this.orderPaymentMethodActual = orderPaymentMethodActual;
	}
	public Integer getOrderPaymentStatusActual() {
		return orderPaymentStatusActual;
	}
	public void setOrderPaymentStatusActual(Integer orderPaymentStatusActual) {
		this.orderPaymentStatusActual = orderPaymentStatusActual;
	}
	public Float getActualPay() {
		return actualPay;
	}
	public void setActualPay(Float actualPay) {
		this.actualPay = actualPay;
	}
	public Float getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Float orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverCityCode() {
		return receiverCityCode;
	}
	public void setReceiverCityCode(String receiverCityCode) {
		this.receiverCityCode = receiverCityCode;
	}
	public String getLogisticsInfo() {
		return logisticsInfo;
	}
	public void setLogisticsInfo(String logisticsInfo) {
		this.logisticsInfo = logisticsInfo;
	}
	public Float getInPrice() {
		return inPrice;
	}
	public void setInPrice(Float inPrice) {
		this.inPrice = inPrice;
	}
	public Integer getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}
	public Float getGoodsWeight() {
		return goodsWeight;
	}
	public void setGoodsWeight(Float goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	public String getReceiverProvince() {
		return receiverProvince;
	}
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverCounty() {
		return receiverCounty;
	}
	public void setReceiverCounty(String receiverCounty) {
		this.receiverCounty = receiverCounty;
	}
	public String getReceiverStreet() {
		return receiverStreet;
	}
	public void setReceiverStreet(String receiverStreet) {
		this.receiverStreet = receiverStreet;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getGoodsReceiver() {
		return goodsReceiver;
	}
	public void setGoodsReceiver(String goodsReceiver) {
		this.goodsReceiver = goodsReceiver;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopMobile() {
		return shopMobile;
	}
	public void setShopMobile(String shopMobile) {
		this.shopMobile = shopMobile;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getDeliveryAreaCode() {
		return deliveryAreaCode;
	}
	public void setDeliveryAreaCode(String deliveryAreaCode) {
		this.deliveryAreaCode = deliveryAreaCode;
	}
	public String getDeliveryStationCode() {
		return deliveryStationCode;
	}
	public void setDeliveryStationCode(String deliveryStationCode) {
		this.deliveryStationCode = deliveryStationCode;
	}
	public Double getShopLng() {
		return shopLng;
	}
	public void setShopLng(Double shopLng) {
		this.shopLng = shopLng;
	}
	public Double getShopLat() {
		return shopLat;
	}
	public void setShopLat(Double shopLat) {
		this.shopLat = shopLat;
	}
	public Double getReceiveLng() {
		return receiveLng;
	}
	public void setReceiveLng(Double receiveLng) {
		this.receiveLng = receiveLng;
	}
	public Double getReceiveLat() {
		return receiveLat;
	}
	public void setReceiveLat(Double receiveLat) {
		this.receiveLat = receiveLat;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Integer getOrderSouce() {
		return orderSouce;
	}
	public void setOrderSouce(Integer orderSouce) {
		this.orderSouce = orderSouce;
	}
	public Integer getBalanceLine0Status() {
		return balanceLine0Status;
	}
	public void setBalanceLine0Status(Integer balanceLine0Status) {
		this.balanceLine0Status = balanceLine0Status;
	}
	public Integer getBalanceMemberSatus() {
		return balanceMemberSatus;
	}
	public void setBalanceMemberSatus(Integer balanceMemberSatus) {
		this.balanceMemberSatus = balanceMemberSatus;
	}
	public Long getDmWorkNumber() {
		return dmWorkNumber;
	}
	public void setDmWorkNumber(Long dmWorkNumber) {
		this.dmWorkNumber = dmWorkNumber;
	}
	public String getDmPhone() {
		return dmPhone;
	}
	public void setDmPhone(String dmPhone) {
		this.dmPhone = dmPhone;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	public Date getControlledTime() {
		return controlledTime;
	}
	public void setControlledTime(Date controlledTime) {
		this.controlledTime = controlledTime;
	}
	public Date getGettingTime() {
		return gettingTime;
	}
	public void setGettingTime(Date gettingTime) {
		this.gettingTime = gettingTime;
	}
	public Date getShippingTime() {
		return shippingTime;
	}
	public void setShippingTime(Date shippingTime) {
		this.shippingTime = shippingTime;
	}
	public Date getArrivedTime() {
		return arrivedTime;
	}
	public void setArrivedTime(Date arrivedTime) {
		this.arrivedTime = arrivedTime;
	}
	public Date getRequirePickUpTime() {
		return requirePickUpTime;
	}
	public void setRequirePickUpTime(Date requirePickUpTime) {
		this.requirePickUpTime = requirePickUpTime;
	}
	public Date getRequireArrivedTime() {
		return requireArrivedTime;
	}
	public void setRequireArrivedTime(Date requireArrivedTime) {
		this.requireArrivedTime = requireArrivedTime;
	}
	public Integer getCompanyKey() {
		return companyKey;
	}
	public void setCompanyKey(Integer companyKey) {
		this.companyKey = companyKey;
	}
	public Integer getCarryOrder() {
		return carryOrder;
	}
	public void setCarryOrder(Integer carryOrder) {
		this.carryOrder = carryOrder;
	}
	public Integer getDateVersion() {
		return dateVersion;
	}
	public void setDateVersion(Integer dateVersion) {
		this.dateVersion = dateVersion;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	public Float getNeedPayTotal() {
		return needPayTotal;
	}
	public void setNeedPayTotal(Float needPayTotal) {
		this.needPayTotal = needPayTotal;
	}
	public Float getInPriceTotal() {
		return inPriceTotal;
	}
	public void setInPriceTotal(Float inPriceTotal) {
		this.inPriceTotal = inPriceTotal;
	}
	public Float getWaitPayTotal() {
		return waitPayTotal;
	}
	public void setWaitPayTotal(Float waitPayTotal) {
		this.waitPayTotal = waitPayTotal;
	}
	public Float getNeedPayReceiveTotal() {
		return needPayReceiveTotal;
	}
	public void setNeedPayReceiveTotal(Float needPayReceiveTotal) {
		this.needPayReceiveTotal = needPayReceiveTotal;
	}
	public DistributionMember getMember() {
		return member;
	}
	public void setMember(DistributionMember member) {
		this.member = member;
	}
	public Integer getBalanceOrderCount() {
		return balanceOrderCount;
	}
	public void setBalanceOrderCount(Integer balanceOrderCount) {
		this.balanceOrderCount = balanceOrderCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/***** end ****/


}
