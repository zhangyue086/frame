package com.zline.zlogistics.biz.dal.entity;

public class Menu extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long menuId;
	private String menuName;
	private String menuUrl;
	private Long menuFather;
	private Integer menuOrder;
	private Integer menuVersion;
	private String selected;//1表示已经是当前角色的菜单，0表示不是的。
	public Long getMenuId()
	{
		return menuId;
	}

	public void setMenuId(Long menuId)
	{
		this.menuId = menuId;
	}

	public String getMenuName()
	{
		return menuName;
	}

	public void setMenuName(String menuName)
	{
		this.menuName = menuName;
	}

	public String getMenuUrl()
	{
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl)
	{
		this.menuUrl = menuUrl;
	}

	public Long getMenuFather()
	{
		return menuFather;
	}

	public void setMenuFather(Long menuFather)
	{
		this.menuFather = menuFather;
	}

	public Integer getMenuVersion()
	{
		return menuVersion;
	}

	public void setMenuVersion(Integer menuVersion)
	{
		this.menuVersion = menuVersion;
	}

	public Integer getMenuOrder()
	{
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder)
	{
		this.menuOrder = menuOrder;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	
	
}
