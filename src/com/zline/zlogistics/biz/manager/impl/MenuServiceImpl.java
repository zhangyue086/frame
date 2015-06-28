package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.Menu;
import com.zline.zlogistics.biz.manager.IMenuService;

public class MenuServiceImpl extends BaseService implements IMenuService
{

	
	public List<Menu> findAllMenu()
	{
		return this.getBaseDao().selectList("menu.selectAllMenu");
	}

}
