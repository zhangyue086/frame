package com.zline.zlogistics.biz.manager;

import java.util.List;

import com.zline.zlogistics.biz.dal.entity.Role;

public interface IRoleService
{
	Role selectRoleByRoleName(Role roleName);
	
	void saveRole(Role role);
	
	void updateRole(Role role);
	
	Role findById(Long id);
	
	List<Role> queryList(Role role);
	
	Integer queryListCount(Role role);
	
	
	
}
