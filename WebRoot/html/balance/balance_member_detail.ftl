<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/workplace/balance_member_detail.js"></script>
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
					<li><a href="#">结算(配送员)</a><i class="icon-angle-right"></i></li>
					<li><a href="#">结算详情</a></li>
				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<!-- END PAGE HEADER-->
	<div class="row-fluid">
			<div class="span12">
			<div class="portlet box" >
				<div class="portlet-title">
					<div class="caption"><i class="icon-plane"></i><font style="color:#333;">配送员:${(logisticsOrder.member.distributionMemberName)!'未知配送员'}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结算日期:${(logisticsOrder.balanceDate)!''}</font></div>
					<div class="tools">
					<button class="btn blue" type="button" id="balance_refresh" onclick='loadHtml("/balance/initDetail_memberBalance.do?dmWorkNumber=${(logisticsOrder.member.workNumber)}&searchDate=${(logisticsOrder.balanceDate)!''}")'>
										<i class="icon-refresh"></i> 刷新
					</button>
					<button class="btn blue" type="button" 
					onclick="loadHtml('/balance/initBalance_memberBalance.do')">
							<i class="m-icon-swapleft"></i>
							返回</button>
					</div>

				</div>
			</div>
			</div>
	</div>

	<div class="row-fluid">
	  <div class="span12">

				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box green">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-globe"></i>待结算
						</div>
						<div class="actions">
							<div class="btn-group">
							</div>
						</div>

					</div>
					<div class="portlet-body">
							<table id="balance_member_detail_list_table1"
								class="table table-striped table-bordered table-hover table-full-width">
								<thead>
										<th >待结算单数</th>
										<th >应收(总)</th>
										<th >实收(总)</th>
										<th>垫付(总)</th>
										<th >待结算金额(总)</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody >
									<tr>
										<td>#{(logisticsOrder.balanceOrderCount)!0}</td>
										<td>#{((logisticsOrder.needPayTotal)!0);m2M2}</td>
										<td>#{((logisticsOrder.needPayReceiveTotal)!0);m2M2}</td>
										<td>#{((logisticsOrder.inPriceTotal)!0);m2M2}</td>
										<td>#{((logisticsOrder.waitPayTotal)!0);m2M2}</td>
										<td>
												<a data-url="/balance/balanceAll_memberBalance.do?dmWorkNumber=${(logisticsOrder.member.workNumber)}&searchDate=${(logisticsOrder.balanceDate)!''}"  id='balance_member_detail_list_balance_all' class='btn green btn-large'>确认结算</a>
										</td>
									</tr>
								</tbody>
							</table>
					</div>

				</div>

				<!-- END EXAMPLE TABLE PORTLET-->

				<!-- BEGIN EXAMPLE TABLE PORTLET-->

				<div class="portlet box blue">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-globe"></i>结算明细
						</div>
						<div class="actions">
							<div class="btn-group">
							</div>
						</div>
					</div>
					<div class="portlet-body">
							<table id="balance_member_detail_list_table2"
								class="table table-striped table-bordered table-hover table-full-width">
								<thead>
									<tr>
										<th>运单号</th>
										<th>应收</th>
										<th >实收</th>
										<th>付款方式</th>
										<th >垫付金额</th>
										<th>待结算金额</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<#if logisticsOrderList?? && (logisticsOrderList?size > 0) >
										<#list logisticsOrderList as lo>
											<tr>
											<td class="">${(lo.logisticsOrderCode)!'未知单号'}</td>
												<td class="">#{((lo.needPay)!0);m2M2}</td>
												<td class="">#{((lo.needPayReceive)!0);m2M2}</td>
											<td class="">
													<#if lo.orderPaymentMethodActual == 1>	
														现金
														<#elseif lo.orderPaymentMethodActual == 2>
														支付宝当面付
														<else>
														#{(lo.orderPaymentMethodActual)}
													</#if>
												</td>
												<td class="">#{((lo.inPrice)!0);m2M2}</td>
												<td class="">#{((lo.needPay - lo.inPrice)!0);m2M2}</td>
												<td class="">
														<a data-url="/balance/balanceById_memberBalance.do?logisticsOrderId=#{(lo.logisticsOrderId)}"  id='balance_member_detail_list_balance_one' class='btn blue btn-small'>结算</a>
												</td>
											</tr>
										</#list>
									</#if>
								</tbody>
							</table>
					</div>

				</div>

				<!-- END EXAMPLE TABLE PORTLET-->

			</div>
	  </div>

</div>
</body>
<script type="text/javascript">
</script>
</html>