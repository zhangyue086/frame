package com.zline.zlogistics.web.action;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.dal.entity.Role;
import com.zline.zlogistics.biz.dal.entity.User;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;
import com.zline.zlogistics.biz.manager.IRoleService;
import com.zline.zlogistics.biz.manager.IUserService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.web.common.DataTableReturnObject;

public class UserAction extends BaseAction
{

	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(DistributionMemberAction.class);

	private List<City> cityList;
	
	private DistributionMember member;
	private DataTableReturnObject<User> returnObject;
	private String queryKeyWord;

	private String memberStatus;
	
	private List<DistributionStation> stationList;
	private Message message;

	
	private User user;

	
	private List<Role> roleList;
	
	@Autowired
	IDistributionStationService distributionStationService;
	@Autowired
	IDistributionMemberService distributionMemberService;
	@Autowired
	IUserService userService;
	@Autowired
	IRoleService roleService;
	@Autowired
	ICityService cityService;
	
	
	public String initList()
	{
		return "initList";
	}

	public String list(){
		if(null == user){
			user = new User();
		}
		String start = getRequest().getParameter("start");
		String length = getRequest().getParameter("length");
		user.setFirstRow(Integer.parseInt(start));
		user.setPageRows(Integer.parseInt(length));
		
		if(queryKeyWord!=null&&queryKeyWord.length()>0)
		{
			user.setUserName(queryKeyWord);
			
		}
		
		Integer count = userService.queryListCount(user);
		List<User> list = userService.queryList(user);
		returnObject = new DataTableReturnObject<User>();
		returnObject.setData(list);
		returnObject.setDraw(Integer.parseInt(getRequest().getParameter("draw") == null ? "0"
				: getRequest().getParameter("draw")) + 1);
		returnObject.setRecordsTotal(count);
		returnObject.setRecordsFiltered(count);
		
		return "list";
	}
	
	
	public String initAdd(){
		
		roleList = roleService.queryList(new Role());
		cityList = cityService.queryList();
		stationList = distributionStationService.queryList(new DistributionStation());
		return "initAdd";
	}
	
	public String add(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			if(user.getPassWord()!=null&&user.getPassWord().length()>0&& !user.getPassWord().matches("[0-9a-zA-Z]+")){
				message.setIsSuccess(false);
				return "add";
			}
			userService.saveUser(user);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("添加用户失败"+e.getMessage());
		}
		return "add";
	}
	
	
	public String initEdit(){
		Long id = user.getUserId();
		user = userService.findById(id);
		roleList = roleService.queryList(new Role());
		cityList = cityService.queryList();
		DistributionStation dis = new DistributionStation();
		user.setCityId(user.getCityId());
		stationList = distributionStationService.queryList(dis);
		return "initEdit";
	}
	
	public String edit(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			if(user.getPassWord()!=null&&user.getPassWord().length()>0&& !user.getPassWord().matches("[0-9a-zA-Z]+")){
				message.setIsSuccess(false);
				return "edit";
			}
			userService.updateUser(user);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("编辑用户失败"+e.getMessage());
		}
		return "edit";
	}
	
	public String initDelete(){
		Long id = user.getUserId();
		user = userService.findById(id);
		return "initDelete";
	}
	
	public String delete(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			user.setIsDeleted(1);
			user.setLastUpdateTime(new Date());
			userService.updateUser(user);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("删除用户失败"+e.getMessage());
		}
		return "delete";
	}
	
	
	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}



	public String getQueryKeyWord() {
		return queryKeyWord;
	}


	public void setQueryKeyWord(String queryKeyWord) {
		this.queryKeyWord = queryKeyWord;
	}


	public DistributionMember getMember() {
		return member;
	}


	public void setMember(DistributionMember member) {
		this.member = member;
	}



	public List<City> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<City> cityList)
	{
		this.cityList = cityList;
	}


	public List<DistributionStation> getStationList() {
		return stationList;
	}


	public void setStationList(List<DistributionStation> stationList) {
		this.stationList = stationList;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DataTableReturnObject<User> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject<User> returnObject) {
		this.returnObject = returnObject;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}


	
	
	
}
