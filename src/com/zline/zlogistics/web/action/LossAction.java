package com.zline.zlogistics.web.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.zline.zlogistics.biz.dal.base.ModelDrivenActionSupport;
import com.zline.zlogistics.biz.dal.dto.OrderLossDto;
import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.OrderLoss;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.ILossService;
import com.zline.zlogistics.biz.util.ConstantDef;
import com.zline.zlogistics.biz.util.ExcelUtils;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.web.common.DataTableReturnObject;
import com.zline.zlogistics.web.util.DateUtils;

/**
 * Description: 报损action
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-6
 * 
 * @version 1.0
 */
public class LossAction extends ModelDrivenActionSupport<OrderLossDto> {

	private static Logger logger =Logger.getLogger(LossAction.class);
	private static final long serialVersionUID = 1L;
	
	private String fileName;
	private Message message;
	private OrderLoss orderLoss;
	private List<City> cityList;
	private ILossService lossService;
	private ICityService cityService;
	private List<OrderLoss> orderLosseList;
	private DataTableReturnObject<OrderLoss> returnObject;
	
	/**
	 * 初始化
	 * @return
	 */
	public String initList() {
		// 差城市
		cityList = cityService.queryList();
		return "init_list";
	}

	/**
	 * 查询
	 * @return
	 */
	public String queryList() {
		returnObject = new DataTableReturnObject<OrderLoss>();
		orderLosseList = new ArrayList<OrderLoss>();
		//先设置一次空list
		returnObject.setData(orderLosseList);
		
		//设置查询条件
		String start = getRequest().getParameter("start");
		String length = getRequest().getParameter("length");
		this.getModel().setFirstRow(Integer.parseInt(start));
		this.getModel().setPageRows(Integer.parseInt(length));
		this.getModel().setStartTime(this.getModel().getSearchDate()+" 00:00:00");
		this.getModel().setEndTime(this.getModel().getSearchDate()+" 23:59:59");
		if(this.getModel().getSearchType() == ConstantDef.SEARCH_BY_NAME){
			this.getModel().setDmName(this.getModel().getKeyword());
		}else if(this.getModel().getSearchType() == ConstantDef.SEARCH_BY_WORK_NUMBER){
			this.getModel().setDmWorkNumber(Long.parseLong(this.getModel().getKeyword()));
		}
		//查询的结果集  带分页
		orderLosseList = lossService.queryListByDto(this.getModel());
		if(orderLosseList != null && !orderLosseList.isEmpty()){
			returnObject.setData(orderLosseList);
		}
		returnObject.setDraw(Integer.parseInt(getRequest().getParameter("draw") == null ? "0"
				: getRequest().getParameter("draw")) + 1);
		//结果集数量 不带分页
		Integer count = lossService.queryCount(this.getModel());
		returnObject.setRecordsTotal(count == null?0:count);
		returnObject.setRecordsFiltered(count == null?0:count);		
		
		return "table_json";
	}
	
	/**
	 * 查看详情
	 * @return
	 */
	public String initLossDetail() {
		Long lossId = this.getModel().getLossId();
		orderLoss = lossService.queryByKey(lossId);
		return "loss_detail";
	}
	/**
	 * 更新报损原因
	 * @return
	 */
	public  String updateById(){
		message = new Message ();
		message.setIsSuccess(true);
		try {
			lossService.updateByKey(this.getModel());
		} catch (Exception e) {
			message.setIsSuccess(false);
			message.setInfo(e.toString());
			logger.error(e);
		}
		return "msg_json";
	}

	/**
	 * 导出报损信息
	 * 
	 * @author：zzt
	 * 
	 */
	public String importLossFile() throws Exception {
		InputStream is = getDownloadFile();
		if (is == null) {
			return null;
		}
		HttpServletResponse response = getResponse();
		response.reset();
		String filedownload = "报损处理.xls";
		response.setContentType("application/x-download");
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(filedownload.getBytes("utf-8"), "ISO8859-1"));
		OutputStream outp = null;
		outp = response.getOutputStream();
		byte[] bs = new byte[1024];
		int i = 0;
		while ((i = is.read(bs)) > 0) {
			outp.write(bs, 0, i);
		}
		outp.flush();
		return null;
	}

	public InputStream getDownloadFile() throws Exception {
		String fileName = ServletActionContext.getServletContext().getRealPath(UUID.randomUUID().toString() + ".xls");
		this.getModel().setCurPage(null);
		this.getModel().setStartTime(this.getModel().getSearchDate()+" 00:00:00");
		this.getModel().setEndTime(this.getModel().getSearchDate()+" 23:59:59");
		if(this.getModel().getSearchType() == ConstantDef.SEARCH_BY_NAME){
			this.getModel().setDmName(this.getModel().getKeyword());
		}else if(this.getModel().getSearchType() == ConstantDef.SEARCH_BY_WORK_NUMBER){
			this.getModel().setDmWorkNumber(Long.parseLong(this.getModel().getKeyword()));
		}
		//查询的结果集  带分页
		orderLosseList = lossService.queryListByDto(this.getModel());
		if (orderLosseList == null || orderLosseList.isEmpty()){
			return null;
		}
		int k = orderLosseList.size();
		Object[][] objs = new Object[k][ConstantDef.LOSS_FILE_COLUMNS];
		for (int j = 0; j < k; j++) {
			orderLoss = orderLosseList.get(j);
			if (orderLoss.getCreateTime() != null) {
				objs[j][ConstantDef.LOSS_CREATE_TIME] = DateUtils.dateToString(orderLoss.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
			}
			if (orderLoss.getCity() != null && StringUtils.isNotBlank(orderLoss.getCity().getCityName())) {
				objs[j][ConstantDef.LOSS_CITY] = orderLoss.getCity().getCityName();
			}
			if (orderLoss.getStation() != null && StringUtils.isNotBlank(orderLoss.getStation().getDistributionStationName())) {
				objs[j][ConstantDef.LOSS_STATION_NAME] = orderLoss.getStation().getDistributionStationName();
			}
			if (orderLoss.getDmWorkNumber() != null) {
				objs[j][ConstantDef.LOSS_DM_WORKNUMBER] = orderLoss.getDmWorkNumber();
			}
			if (StringUtils.isNotBlank(orderLoss.getDmName())) {
				objs[j][ConstantDef.LOSS_DM_NAME] =orderLoss.getDmName();
			}
			if (orderLoss.getOrder() != null && StringUtils.isNotBlank(orderLoss.getOrder().getLogisticsOrderCode())) {
				objs[j][ConstantDef.LOSS_ORDER_CODE] = orderLoss.getOrder().getLogisticsOrderCode();
			}
			if (orderLoss.getNeedPay() != null) {
				objs[j][ConstantDef.LOSS_ORDER_NEED_PAY] = orderLoss.getNeedPay();
			}
			if (orderLoss.getNeedPayReceive() != null) {
				objs[j][ConstantDef.LOSS_ORDER_NEED_PAY_RECEIVE] = orderLoss.getNeedPayReceive();
			}
			if (orderLoss.getLossAccount() != null) {
				objs[j][ConstantDef.LOSS_ORDER_LOSS_ACCOUNT] = orderLoss.getLossAccount();
			}
			if (StringUtils.isNotBlank(orderLoss.getLossDesc())) {
				objs[j][ConstantDef.LOSS_DESC] = orderLoss.getLossDesc();
			}
		}
		ExcelUtils.createExcel(objs,ConstantDef.LOSS_FILE_COLUMNS_NAMES, fileName);
		this.setFileName(fileName);
		return new FileInputStream(fileName);
	}
	
	
	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ILossService getLossService() {
		return lossService;
	}

	public void setLossService(ILossService lossService) {
		this.lossService = lossService;
	}
	public DataTableReturnObject<OrderLoss> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject<OrderLoss> returnObject) {
		this.returnObject = returnObject;
	}

	public OrderLoss getOrderLoss() {
		return orderLoss;
	}

	public void setOrderLoss(OrderLoss orderLoss) {
		this.orderLoss = orderLoss;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		LossAction.logger = logger;
	}

	public List<OrderLoss> getOrderLosseList() {
		return orderLosseList;
	}

	public void setOrderLosseList(List<OrderLoss> orderLosseList) {
		this.orderLosseList = orderLosseList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
