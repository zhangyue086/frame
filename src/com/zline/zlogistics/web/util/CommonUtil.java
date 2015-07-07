package com.zline.zlogistics.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.zline.zlogistics.biz.dal.entity.Menu;
import com.zline.zlogistics.web.view.MenuView;

public class CommonUtil {
	
	
	public static  List<MenuView> findMenuView(List<Menu> list)
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
	
	
}
