<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运单详细信息</title>
</head>
<body>
<div class="container-fluid">
		<!-- BEGIN PAGE HEADER-->
		<div class="row-fluid">
			<div class="span12">
				<!-- BEGIN STYLE CUSTOMIZER -->

				<!-- END BEGIN STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<h3 class="page-title"></h3>
				<ul class="breadcrumb" style="margin-bottom: 5px;">
					<li><i class="icon-home"></i> <a href="index.do">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">工作台</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">运单查询</a><i class="icon-angle-right"></i></li>
					<li><a href="#">运单详情</a></li>
				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<!-- END PAGE HEADER-->
	<div class="row-fluid">
			<div class="span12">
			<div class="portlet box" >
				<div class="portlet-title">
					<div class="caption"><i class="icon-link"></i><font style="color:#333;">订单号:${(logisticsOrder.logisticsOrderCode)!'未知单号'}(
					<#assign orderStatus = (logisticsOrder.logisticsOrderStatus)!-1>
					<#if orderStatus == 0>
							 初始状态
						 <#elseif orderStatus ==1>
							 揽货中
						 <#elseif orderStatus ==2>
							 配送中
						 <#elseif orderStatus ==3>
							 已送达
						 <#elseif orderStatus ==4>
							 已取消
						<#else>
							状态错误,状态:#{orderStatus}
					</#if>
					)</font></div>
					<div class="tools">
					<button class="btn blue" type="button" onclick='loadHtml("/waybill/initList_waybill.do?keyword=${keyword!''}")'>
							<i class="m-icon-swapleft"></i>
							返回</button>
					<button class="btn green" type="button" id="refreshOrderDetail" data-url="/waybill/initDetail_waybill.do?logisticsOrderId=#{(logisticsOrder.logisticsOrderId)!0}&keyword=${keyword!''}">
								<i class="icon-refresh"></i> 刷新
							</button>
					</div>
				</div>
			</div>
			</div>
	</div>

	<div class="row-fluid">
	  <div class="span12">
		<div class="tabbable tabbable-custom">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab_1_1">运单信息</a></li>
				<li><a data-toggle="tab" href="#tab_1_2">配送信息</a></li>
			</ul>
			<div class="tab-content">
				<div id="tab_1_1" class="tab-pane active">
					<div class="portlet box">
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<div class="form-horizontal form-view">
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label for="firstName" class="control-label">
											预计取货:
											</label>
											<div class="controls">
											<span class="text" id="orderEstimatedPickingTime">${(logisticsOrder.orderEstimatedPickingTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label for="lastName" class="control-label">
												预计送达:
											</label>
											<div class="controls">
												<span class="text">${(logisticsOrder.orderEstimatedArrivedTime)?string('yyyy-MM-dd HH:mm:ss')} </span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">配送距离(M):</label>
											<div class="controls">
												<span class="text">#{(logisticsOrder.distance)!0} M</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">商品数量(件):</label>
											<div class="controls">
												<span class="text">#{(logisticsOrder.goodsCount)!0} 件</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">应收金额:</label>
											<div class="controls">
												<span class="text">#{(logisticsOrder.orderPrice)!0.00;m2M2}</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">垫付金额:</label>
											<div class="controls">
												<span class="text">#{(logisticsOrder.inPrice)!0.00;m2M2}</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row-fluid">
									<div class="span12 ">
										<div class="control-group">
											<label class="control-label">实收金额:</label>
											<div class="controls">
												<span class="text">#{(logisticsOrder.needPayReceive)!0.00;m2M2}</span>
											</div>
										</div>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span6 ">
										<div class="control-group">
											<label class="control-label">付款方式:</label>
											<div class="controls">
												<span class="text">
												<#assign payMethod = (logisticsOrder.orderPaymentMethod)!0>
												<#if payMethod == 1>
														现金
														<#elseif  payMethod == 2>
														支付宝当面付
														<#else>
														未知,方式:#{payMethod}
												</#if>
												</span>
											</div>
										</div>
									</div>
								</div>
								<!--/row-->
								<div class="row-fluid">
									<!--/span-->
									<div class="span12">
										<div class="control-group">
											<label class="control-label">运单备注:</label>
											<div class="controls">
												<span class="text">${(logisticsOrder.logisticsInfo)!''}</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<h3 class="form-section"></h3>
								<!--/row-->
								<div class="row-fluid">
									<!--/span-->
									<div class="span6">
										<div class="control-group">
											<label class="control-label">商家名称:</label>
											<div class="controls">
												<span class="text">${(logisticsOrder.shopName)!''}</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<!--/span-->
									<div class="span6">
										<div class="control-group">
											<label class="control-label">商家电话:</label>
											<div class="controls">
												<span class="text">${(logisticsOrder.shopMobile)!''}</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row-fluid">
									<!--/span-->
									<div class="span12">
										<div class="control-group">
											<label class="control-label">商家地址:</label>
											<div class="controls">
												<span class="text">${(logisticsOrder.shopAddress)!''}</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row-fluid">
									<!--/span-->
									<div class="span12">
										<div class="control-group">
											<label class="control-label">商家标签:</label>
											<div class="controls">
												<span class="text"></span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row-fluid">
									<!--/span-->
									<div class="span6">
										<div class="control-group">
											<label class="control-label">收货人:</label>
											<div class="controls">
												<span class="text">${(logisticsOrder.goodsReceiver)!''}</span>
											</div>
										</div>
									</div>
									<!--/span-->
									<!--/span-->
									<div class="span6">
										<div class="control-group">
											<label class="control-label">收货电话:</label>
											<div class="controls">
												<span class="text">${(logisticsOrder.receiverMobile)!''}</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row-fluid">
									<!--/span-->
									<div class="span12">
										<div class="control-group">
											<label class="control-label">收货地址:</label>
											<div class="controls">
												<span class="text">${(logisticsOrder.receiverAddress)!''}</span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<!--/row-->
								<div class="row-fluid">
									<!--/span-->
									<div class="span6">
										<div class="control-group">
											<label class="control-label">收货标签:</label>
											<div class="controls">
												<span class="text"></span>
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
								<div class="form-actions">
									<button class="btn red" type="button">
										<i class="icon-pencil"></i> 放弃配送
									</button>
									<button class="btn blue" type="button">多人配送</button>
								</div>
							</div>
							<!-- END FORM-->
						</div>
					</div>
				</div>
				<div id="tab_1_2" class="tab-pane">
					<div class="portlet box">
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<div class="horizontal-form form-view">
								<div class="row-fluid">
									<div class="span3">
										<div class="control-group">
											<label for="firstName" class="control-label">
											处理时间:
											</label>
										<div class="controls">
										<#if statusList??>
											<#list statusList as status>
												<span class="text">${(status.logisticsOrderStatusTime)?string('yyyy-MM-dd HH:mm:ss')}</span>
											</#list>
										</#if>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="span6">
										<div class="control-group">
											<label for="lastName" class="control-label">
												处理信息:
											</label>
											<div class="controls">
												<span class="text">运单生成,进入调度</span>
											<#if orderStatus == 0 >
													<span class="text">运单调度完成,待配送员${(logisticsOrder.dmName)!'未知'}接单</span>
												<#elseif orderStatus==1 >
													<span class="text">运单调度完成,待配送员${(logisticsOrder.dmName)!'未知'}接单</span>
													<span class="text">配送员${(logisticsOrder.dmName)!'未知'}完成接单，前往进行揽货</span>
												<#elseif orderStatus==2>
													<span class="text">运单调度完成,待配送员${(logisticsOrder.dmName)!'未知'}接单</span>
													<span class="text">配送员${(logisticsOrder.dmName)!'未知'}完成接单，前往进行揽货</span>
													<span class="text">配送员${(logisticsOrder.dmName)!'未知'}完成揽货，前往进行配送</span>
												<#elseif orderStatus==3>
													<span class="text">运单调度完成,待配送员${(logisticsOrder.dmName)!'未知'}接单</span>
													<span class="text">配送员${(logisticsOrder.dmName)!'未知'}完成接单，前往进行揽货</span>
													<span class="text">配送员${(logisticsOrder.dmName)!'未知'}完成揽货，前往进行配送</span>
													<span class="text">配送员${(logisticsOrder.dmName)!'未知'}完成配送，运单结束</span>
												<#elseif orderStatus==4>
													订单取消
												<#else>
													订单状态异常:#{orderStatus}
											</#if>
											</div>
										</div>
									</div>
									<!--/span-->
									<!--/span-->
									<div class="span3">
										<div class="control-group">
											<label for="lastName" class="control-label">
												操作人:
											</label>
											<div class="controls">
												<span class="text">系统</span>
												<#if orderStatus == 0 >
														<span class="text">系统</span>
												<#elseif orderStatus==1 >
														<span class="text">系统</span>
														<span class="text">${(logisticsOrder.dmName)!'未知'}</span>
												<#elseif orderStatus==2>
														<span class="text">系统</span>
														<span class="text">${(logisticsOrder.dmName)!'未知'}</span>
														<span class="text">${(logisticsOrder.dmName)!'未知'}</span>
												<#elseif orderStatus==3>
														<span class="text">系统</span>
														<span class="text">${(logisticsOrder.dmName)!'未知'}</span>
														<span class="text">${(logisticsOrder.dmName)!'未知'}</span>
														<span class="text">${(logisticsOrder.dmName)!'未知'}</span>
												<#elseif orderStatus==4>
														<span class="text">系统</span>
												<#else>
													订单状态异常:#{orderStatus}
											</#if>
												
											</div>
										</div>
									</div>
									<!--/span-->
								</div>
							</div>
							<!-- END FORM-->
						</div>
					</div>

				</div>
			  </div>
			</div>
		</div>
	  </div>

</div>

</body>
<script type="text/javascript">
$(function(){
	$("#refreshOrderDetail").die("click").live("click",function(){
		loadHtml($(this).attr("data-url"));		
	})
})
</script>
</html>