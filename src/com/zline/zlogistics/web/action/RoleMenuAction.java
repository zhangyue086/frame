package com.zline.zlogistics.web.action;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.entity.City;
import com.zline.zlogistics.biz.dal.entity.DistributionMember;
import com.zline.zlogistics.biz.dal.entity.Menu;
import com.zline.zlogistics.biz.dal.entity.Role;
import com.zline.zlogistics.biz.dal.entity.RoleMenu;
import com.zline.zlogistics.biz.manager.ICityService;
import com.zline.zlogistics.biz.manager.IDistributionMemberService;
import com.zline.zlogistics.biz.manager.IMenuService;
import com.zline.zlogistics.biz.manager.IRoleMenuService;
import com.zline.zlogistics.biz.manager.IRoleService;
import com.zline.zlogistics.biz.util.Message;
import com.zline.zlogistics.web.common.DataTableReturnObject;
import com.zline.zlogistics.web.util.CommonUtil;
import com.zline.zlogistics.web.view.MenuView;

public class RoleMenuAction extends BaseAction
{

	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(DistributionMemberAction.class);
	private ICityService cityService;
	private List<City> cityList;
	
	private DistributionMember member;
	private DataTableReturnObject<RoleMenu> returnObject;
	private String queryKeyWord;
	private IDistributionMemberService distributionMemberService;
	private String memberStatus;
	
	private Message message;
	
	private RoleMenu roleMenu;
	private IRoleMenuService roleMenuService;
	private List<MenuView> menuViewList;
	private IMenuService menuService;
	
	private List<Role> roleList;
	private IRoleService roleService;
	
	private List<Menu> menuList;
	
	
	public String initList()
	{
		cityList = cityService.queryList();
		return "initList";
	}

	public String list(){
		if(null == roleMenu){
			roleMenu = new RoleMenu();
		}
		String start = getRequest().getParameter("start");
		String length = getRequest().getParameter("length");
		roleMenu.setFirstRow(Integer.parseInt(start));
		roleMenu.setPageRows(Integer.parseInt(length));
		
		Integer count = roleMenuService.queryListCount(roleMenu);
		List<RoleMenu> list = roleMenuService.queryList(roleMenu);
		returnObject = new DataTableReturnObject<RoleMenu>();
		returnObject.setData(list);
		returnObject.setDraw(Integer.parseInt(getRequest().getParameter("draw") == null ? "0"
				: getRequest().getParameter("draw")) + 1);
		returnObject.setRecordsTotal(count);
		returnObject.setRecordsFiltered(count);
		
		return "list";
	}
	
	
	public String initAdd(){
		roleList = roleService.queryList(new Role());
		menuList = menuService.findAllMenu();
		
		menuViewList = CommonUtil.findMenuView(menuList);
		
		cityList = cityService.queryList();
		
		return "initAdd";
	}
	
	public String add(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			roleMenuService.saveRoleMenu(roleMenu);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("添加角色失败"+e.getMessage());
		}
		return "add";
	}
	
	
	public String initEdit(){
		
		roleList = roleService.queryList(new Role());
		menuList = menuService.findAllMenu();
		menuViewList = CommonUtil.findMenuView(menuList);
		Long id = roleMenu.getId();
		roleMenu = roleMenuService.findById(id);
		return "initEdit";
	}
	
	public String edit(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			roleMenuService.updateRoleMenu(roleMenu);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("编辑角色失败"+e.getMessage());
		}
		return "edit";
	}
	
	public String initDelete(){
		Long id = roleMenu.getId();
		roleMenu = roleMenuService.findById(id);
		return "initDelete";
	}
	
	public String delete(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			roleMenu.setIsDeleted(1);
			roleMenu.setLastUpdateTime(new Date());
			roleMenuService.updateRoleMenu(roleMenu);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("删除角色失败"+e.getMessage());
		}
		return "delete";
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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public DataTableReturnObject<RoleMenu> getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(DataTableReturnObject<RoleMenu> returnObject) {
		this.returnObject = returnObject;
	}

	public RoleMenu getRoleMenu() {
		return roleMenu;
	}

	public void setRoleMenu(RoleMenu roleMenu) {
		this.roleMenu = roleMenu;
	}

	public IRoleMenuService getRoleMenuService() {
		return roleMenuService;
	}

	public void setRoleMenuService(IRoleMenuService roleMenuService) {
		this.roleMenuService = roleMenuService;
	}

	public List<MenuView> getMenuViewList() {
		return menuViewList;
	}

	public void setMenuViewList(List<MenuView> menuViewList) {
		this.menuViewList = menuViewList;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	

	
	
	
}
