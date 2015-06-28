package com.zline.zlogistics.biz.dal.entity;

/**
 * Description: 角色
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-12
 * 
 * @version 1.0
 */
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long roleId;
	private String roleName;
	private String roleDescription;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
