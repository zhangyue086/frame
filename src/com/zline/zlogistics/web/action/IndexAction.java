package com.zline.zlogistics.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.zline.zlogistics.biz.dal.entity.Menu;
import com.zline.zlogistics.biz.manager.IMenuService;
import com.zline.zlogistics.biz.util.UserContext;
import com.zline.zlogistics.web.view.MenuView;

public class IndexAction extends BaseAction
{
	private static final long serialVersionUID = 5119144952247192726L;
	private IMenuService menuService;
	private List<MenuView> menuList;
	private String userName;

	public String execute()
	{
		//List<Menu> list = menuService.findAllMenu();
		if (UserContext.getUser() != null)
		{
			userName = UserContext.getUser().getName();
			
			Long roleId = UserContext.getUser().getRoleId();
			List<Menu> list = menuService.selectMenuByUserRoleId(roleId);
			menuList = findMenuView(list);
			return "index";
		}else{
			return "error";
		}
		
	}

	private List<MenuView> findMenuView(List<Menu> list)
	{
		List<MenuView> father = new ArrayList<MenuView>();
		if (list == null)
		{
			return father;
		}
		for (Menu menu : list)
		{
			if (menu.getMenuFather() == null)
			{
				MenuView view = new MenuView();
				view.setFatherMenu(menu);
				father.add(view);
			}

		}
		Collections.sort(father, new Comparator<MenuView>()
		{
			
			public int compare(MenuView o1, MenuView o2)
			{
				return o1.getFatherMenu().getMenuOrder()
						.compareTo(o2.getFatherMenu().getMenuOrder());
			}
		});
		for (MenuView view : father)
		{
			List<Menu> childMenuList = new ArrayList<Menu>();
			for (Menu menu : list)
			{
				if (menu.getMenuFather() != null
						&& menu.getMenuFather().equals(
								view.getFatherMenu().getMenuId()))
				{
					childMenuList.add(menu);
				}

			}

			Collections.sort(childMenuList, new Comparator<Menu>()
			{
				
				public int compare(Menu o1, Menu o2)
				{
					return o1.getMenuOrder().compareTo(o2.getMenuOrder());
				}
			});

			view.setChildMenuList(childMenuList);

		}
		return father;

	}

	public IMenuService getMenuService()
	{
		return menuService;
	}

	public void setMenuService(IMenuService menuService)
	{
		this.menuService = menuService;
	}

	public List<MenuView> getMenuList()
	{
		return menuList;
	}

	public void setMenuList(List<MenuView> menuList)
	{
		this.menuList = menuList;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
