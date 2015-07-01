package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.Menu;
import com.zline.zlogistics.biz.manager.IMenuService;

public class MenuServiceImpl extends BaseService implements IMenuService
{

	@Override
	public List<Menu> findAllMenu()
	{
		return this.getBaseDao().selectList("menu.selectAllMenu");
	}

	@Override
	public Menu findById(Long id) {
		return this.getBaseDao().selectOne("menu.findById",id);
	}

	@Override
	public List<Menu> queryList(Menu menu) {
		return getBaseDao().selectList("menu.queryList", menu);
	}

	@Override
	public Integer queryListCount(Menu menu) {
		return getBaseDao().selectOne("menu.queryListCount", menu);
	}

	@Override
	public void saveMenu(Menu menu) {
		getBaseDao().insert("menu.save", menu);
	}

	@Override
	public void updateMenu(Menu menu) {
		getBaseDao().update("menu.update", menu);
	}

}
