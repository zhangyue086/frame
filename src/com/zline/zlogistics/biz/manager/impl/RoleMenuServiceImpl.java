package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.RoleMenu;
import com.zline.zlogistics.biz.manager.IRoleMenuService;

public class RoleMenuServiceImpl extends BaseService implements IRoleMenuService
{

	@Override
	public RoleMenu findById(Long id) {
		return getBaseDao().selectOne("roleMenu.findById", id);
	}

	@Override
	public List<RoleMenu> queryList(RoleMenu roleMenu) {
		return getBaseDao().selectList("roleMenu.queryList",roleMenu);
	}

	@Override
	public Integer queryListCount(RoleMenu roleMenu) {
		return getBaseDao().selectOne("roleMenu.queryListCount", roleMenu);
	}

	@Override
	public void saveRoleMenu(RoleMenu roleMenu) {
		getBaseDao().insert("roleMenu.save", roleMenu);
	}

	@Override
	public void updateRoleMenu(RoleMenu roleMenu) {
		getBaseDao().update("roleMenu.update", roleMenu);
	}

}
