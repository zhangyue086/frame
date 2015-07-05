package com.zline.zlogistics.biz.dal.entity;
/**
 * 角色菜单关联表
 * @author zhangyue
 *
 */
public class RoleMenu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long roleId;
	private Long menuId;
	
	private String roleName;
	private String menuName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
}
