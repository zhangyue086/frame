package com.zline.zlogistics.web.action;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.zline.zlogistics.biz.dal.entity.Menu;
import com.zline.zlogistics.biz.manager.IMenuService;
import com.zline.zlogistics.biz.util.Message;

public class MenuAction extends BaseAction
{
	private static final long serialVersionUID = 1L;
	public Logger log = Logger.getLogger(MenuAction.class);
	
	private Message message;
	private IMenuService menuService;
	private List<Menu> menuList;
	private Menu menu;
	
	
	public String initList(){
		menuList = menuService.findAllMenu();
		return "initList";
	}
	
	public String list(){
		menuList = menuService.findAllMenu();
		return "list";
	}
	
	
	public String initEdit(){
		Long id = menu.getMenuId();
		menu = menuService.findById(id);
		return "initEdit";
	}
	
	public String edit(){
		
		message = new Message();
		message.setIsSuccess(true);
		try {
			menu.setLastUpdateTime(new Date());
			menuService.updateMenu(menu);
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("编辑菜单失败"+e.getMessage());
		}
		return "edit";
	}
	
	public String delete(){
		message = new Message();
		message.setIsSuccess(true);
		try {
			/*user.setIsDeleted(1);
			user.setLastUpdateTime(new Date());
			userService.updateUser(user);*/
		} catch (Exception e) {
			message.setIsSuccess(false);
			log.error("删除用户失败"+e.getMessage());
		}
		return "delete";
	}
	
	
	
	
	
	
	
	
	

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}





	
	
}
