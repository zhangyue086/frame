package com.zline.zlogistics.biz.manager.impl;

import java.util.List;

import com.zline.zlogistics.biz.dal.base.BaseService;
import com.zline.zlogistics.biz.dal.entity.Role;
import com.zline.zlogistics.biz.manager.IRoleService;

public class RoleServiceImpl extends BaseService implements IRoleService
{

	public Role selectRoleByRoleName(Role loginRole)
	{
		return this.getBaseDao().selectOne("role.selectRoleByRoleName",loginRole);
	}

	public Role findById(Long id) {
		return this.getBaseDao().selectOne("role.findById",id);
	}

	public List<Role> queryList(Role role) {
		return getBaseDao().selectList("role.queryList",role);
	}

	public Integer queryListCount(Role role) {
		return getBaseDao().selectOne("role.queryListCount", role);
	}

	public void saveRole(Role role) {
		getBaseDao().insert("role.save", role);
	}

	public void updateRole(Role role) {
		getBaseDao().update("role.update", role);
	}

}
