package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.RoleMenu;

public interface IRoleMenuService
{
	
	void saveRoleMenu(RoleMenu roleMenu);
	
	void updateRoleMenu(RoleMenu roleMenu);
	
	RoleMenu findById(Long id);
	
	List<RoleMenu> queryList(RoleMenu roleMenu);
	
	Integer queryListCount(RoleMenu roleMenu);
	
	
	
}
