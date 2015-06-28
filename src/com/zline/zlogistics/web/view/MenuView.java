package com.zline.zlogistics.web.view;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.Menu;

public class MenuView
{
	private Menu fatherMenu;
	private List<Menu> childMenuList;

	public Menu getFatherMenu()
	{
		return fatherMenu;
	}

	public void setFatherMenu(Menu fatherMenu)
	{
		this.fatherMenu = fatherMenu;
	}

	public List<Menu> getChildMenuList()
	{
		return childMenuList;
	}

	public void setChildMenuList(List<Menu> childMenuList)
	{
		this.childMenuList = childMenuList;
	}

}
