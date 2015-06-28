package com.zline.zlogistics.biz.util;


/**
 * 
 * 系统常量
 * 
 * @author LuLiLi
 * @version [版本号, 2012-4-13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
/**
 * @author Administrator
 * 
 */
public interface ConstantDef {

	Integer STATION_ALREADY_BALANCED = 1;//站点已经结算
	Integer STATION_NOT_BALANCED = 0;//站点还未结算
	
	Integer SEARCH_BY_NAME = 1; //根据姓名查找
	Integer SEARCH_BY_WORK_NUMBER = 2;//根据工号查找
	
	int LOSS_FILE_COLUMNS = 10; //需要导出的excel文件列数
	int LOSS_CREATE_TIME = 0;//表格第一列:报损时间
	int LOSS_CITY = 1; //表格第二列:报损城市
	int LOSS_STATION_NAME = 2; //表格第三列 :报损站点名称
	int LOSS_DM_WORKNUMBER = 3;//表格第四列  : 报损人员工号
	int LOSS_DM_NAME = 4;//表格第五列:报损人员姓名
	int LOSS_ORDER_CODE = 5;//表格第六列:报损单号
	int LOSS_ORDER_NEED_PAY = 6; //表格第七列:订单应收
	int LOSS_ORDER_NEED_PAY_RECEIVE = 7;//表格第八列:订单实收
	int LOSS_ORDER_LOSS_ACCOUNT = 8;//表格第九列:损失
	int LOSS_DESC = 9;//表格第十列:报损说明
	String[] LOSS_FILE_COLUMNS_NAMES = { "报损时间", "城市", "物流点", "工号", "配送员", "运单号","应收","实收","损失","报损说明" };
	
	
}
