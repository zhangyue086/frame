package com.zline.zlogistics.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.DistributionStation;
import com.zline.zlogistics.biz.dal.entity.Menu;
import com.zline.zlogistics.biz.dal.entity.Role;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.IDistributionStationService;
import com.zline.zlogistics.biz.manager.IMenuService;
import com.zline.zlogistics.biz.manager.IRoleService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.web.common.DataTableReturnObject;
import com.zline.zlogistics.web.util.CommonUtil;
import com.zline.zlogistics.web.view.MenuView;

public class RoleAction extends BaseAction
{

	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(DistributionMemberAction.class);
	private ICityService cityService;
	private List<City> cityList;
	
	private DistributionMember member;
	private DataTableReturnObject<Role> returnObject;
	private String queryKeyWord;
	private IDistributionMemberService distributionMemberService;
	private String memberStatus;
	
	private List<DistributionStation> stationList;
	private Message message;
	private IDistributionStationService distributionStationService;
	
	private Role role;
	private IRoleService roleService;
	
	private IMenuService menuService;
	private List<Menu> menuList;
	private List<Role> roleList;
	private List<MenuView> menuViewList;
	
	public String initList()
	{
		cityList = cityService.queryList();
		return "initList";
	}

	public String list(){
		if(null == role){
			role = new Role();
		}
		String start = getRequest().getParameter("start");
		String length = getRequest().getParameter("length");
		role.setFirstRow(Integer.parseInt(start));
		role.setPageRows(Integer.parseInt(length));
		
		if(queryKeyWord!=null&&queryKeyWord.length()>0)
		{
			role.setRoleName(queryKeyWord);
			
		}
		
		Integer count = roleService.queryListCount(role);
		List<Role> list = roleService.queryList(role);
		returnObject = new DataTableReturnObject<Role>();
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
			roleService.saveRole(role);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("添加角色失败"+e.getMessage());
		}
		return "add";
	}
	
	
	public String initEdit(){
		Long id = role.getRoleId();
		role = roleService.findById(id);
		return "initEdit";
	}
	
	public String edit(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			roleService.updateRole(role);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("编辑角色失败"+e.getMessage());
		}
		return "edit";
	}
	
	public String initDelete(){
		Long id = role.getRoleId();
		role = roleService.findById(id);
		return "initDelete";
	}
	
	public String delete(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			role.setIsDeleted(1);
			role.setLastUpdateTime(new Date());
			roleService.updateRole(role);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("删除角色失败"+e.getMessage());
		}
		return "delete";
	}
	
	public String initAddResourse(){
		Long id = role.getRoleId();
		role = roleService.findById(id);
		menuList = menuService.findAllMenu();
		roleList = roleService.queryList(new Role());
		menuViewList = CommonUtil.findMenuView(menuList);
		return "initAddResourse";
	}
	
	
	public String getMeunByFatherIdAjax(){
		String menuFather = request.getParameter("parentId");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("menuFather", menuFather);
		menuList = menuService.selectMenuByMap(map);
		return "getMeunByFatherIdAjax";
	}
	
	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}


	public IDistributionMemberService getDistributionMemberService() {
		return distributionMemberService;
	}


	public void setDistributionMemberService(
			IDistributionMemberService distributionMemberService) {
		this.distributionMemberService = distributionMemberService;
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


	public ICityService getCityService()
	{
		return cityService;
	}

	public void setCityService(ICityService cityService)
	{
		this.cityService = cityService;
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


	public IDistributionStationService getDistributionStationService() {
		return distributionStationService;
	}


	public void setDistributionStationService(
			IDistributionStationService distributionStationService) {
		this.distributionStationService = distributionStationService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}



	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public DataTableReturnObject<Role> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject<Role> returnObject) {
		this.returnObject = returnObject;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<MenuView> getMenuViewList() {
		return menuViewList;
	}

	public void setMenuViewList(List<MenuView> menuViewList) {
		this.menuViewList = menuViewList;
	}
	



	
	
	
}
