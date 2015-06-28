package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.Menu;

public interface IMenuService
{
	/**
	 * 返回所有菜单
	 * 
	 * @return
	 */
	public List<Menu> findAllMenu();
}
