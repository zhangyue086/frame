package com.zline.zlogistics.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.web.common.DataTableReturnObject;

public class DistributionMemberAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(DistributionMemberAction.class);
	private DataTableReturnObject<DistributionMember> returnObject;
	private Message message;
	private IDistributionStationService distributionStationService;
	private ICityService cityService;
	private DistributionMember member;
	private List<City> cityList;
	private List<DistributionStation> stationList;
	private IDistributionMemberService distributionMemberService;
	private String queryKeyWord;
	private String memberStatus;
	public String initList(){
		cityList = cityService.queryList();
		stationList = distributionStationService.queryList(new DistributionStation());
		return "initlist";
	}
	
	public String list(){
		if(null == member){
			member = new DistributionMember();
		}
		String start = getRequest().getParameter("start");
		String length = getRequest().getParameter("length");
		member.setFirstRow(Integer.parseInt(start));
		member.setPageRows(Integer.parseInt(length));
		if(queryKeyWord!=null&&queryKeyWord.length()>0)
		{
			if(queryKeyWord.matches("\\d+"))
			{
				member.setWorkNumber(queryKeyWord);
			}
			else
			{
				member.setDistributionMemberName(queryKeyWord);
			}
		}
		if(!memberStatus.equals("1,2,3"))
		{
			List<Integer> l = new ArrayList<Integer>();
			l.add(Integer.parseInt(memberStatus.split(",")[0]));
			l.add(Integer.parseInt(memberStatus.split(",")[1]));
			l.add(Integer.parseInt(memberStatus.split(",")[2]));
			member.setStatusList(l);
		}
		
		
		
		Integer count = distributionMemberService.queryListCount(member);
		List<DistributionMember> list = distributionMemberService.queryList(member);
		returnObject = new DataTableReturnObject<DistributionMember>();
		returnObject.setData(list);
		returnObject.setDraw(Integer.parseInt(getRequest().getParameter("draw") == null ? "0"
				: getRequest().getParameter("draw")) + 1);
		returnObject.setRecordsTotal(count);
		returnObject.setRecordsFiltered(count);
		return "list";
	}
	
	public String initAdd(){
		cityList = cityService.queryList();
		stationList = distributionStationService.queryList(new DistributionStation());
		return "initAdd";
	}
	
	public String add(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			if(member.getPassword()!=null&&member.getPassword().length()>0&& !member.getPassword().matches("[0-9a-zA-Z]+")){
				message.setIsSuccess(false);
				return "add";
			}
			
			member.setWorkNumber(distributionMemberService.createWorkNum());
			distributionMemberService.saveMember(member);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("添加配送员失败"+e.getMessage());
		}
		return "add";
	}
	
	public String initEdit(){
		Long id = member.getDistributionMemberId();
		member = distributionMemberService.findById(id);
		cityList = cityService.queryList();
		DistributionStation dis = new DistributionStation();
		dis.setCityId(member.getCityId());
		stationList = distributionStationService.queryList(dis);
		return "initEdit";
	}
	
	public String edit(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			if(member.getPassword()!=null&&member.getPassword().length()>0&& !member.getPassword().matches("[0-9a-zA-Z]+")){
				message.setIsSuccess(false);
				return "edit";
			}
			distributionMemberService.updateMember(member);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("编辑配送员失败"+e.getMessage());
		}
		return "edit";
	}
	
	public String initDelete(){
		Long id = member.getDistributionMemberId();
		member = distributionMemberService.findById(id);
		return "initDelete";
	}
	
	public String delete(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			member.setIsDeleted(1);
			member.setLastUpdateTime(new Date());
			distributionMemberService.updateMember(member);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("删除配送员失败"+e.getMessage());
		}
		return "delete";
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public IDistributionStationService getDistributionStationService() {
		return distributionStationService;
	}
	public void setDistributionStationService(IDistributionStationService distributionStationService) {
		this.distributionStationService = distributionStationService;
	}
	public ICityService getCityService() {
		return cityService;
	}
	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}
	public DistributionMember getMember() {
		return member;
	}
	public void setMember(DistributionMember member) {
		this.member = member;
	}
	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	public List<DistributionStation> getStationList() {
		return stationList;
	}
	public void setStationList(List<DistributionStation> stationList) {
		this.stationList = stationList;
	}

	public DataTableReturnObject<DistributionMember> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject<DistributionMember> returnObject) {
		this.returnObject = returnObject;
	}

	public IDistributionMemberService getDistributionMemberService() {
		return distributionMemberService;
	}

	public void setDistributionMemberService(IDistributionMemberService distributionMemberService) {
		this.distributionMemberService = distributionMemberService;
	}

	public String getQueryKeyWord()
	{
		return queryKeyWord;
	}

	public void setQueryKeyWord(String queryKeyWord)
	{
		this.queryKeyWord = queryKeyWord;
	}

	public String getMemberStatus()
	{
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus)
	{
		this.memberStatus = memberStatus;
	}

	
}
