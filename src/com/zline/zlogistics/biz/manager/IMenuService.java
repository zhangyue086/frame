package com.zline.zlogistics.biz.manager;

import java.util.List;
import java.util.Map;

import com.zline.zlogistics.biz.dal.entity.Menu;

public interface IMenuService
{
	/**
	 * 返回所有菜单
	 * 
	 * @return
	 */
	public List<Menu> findAllMenu();

	public Menu findById(Long id);
	
	void saveMenu(Menu menu);
	
	void updateMenu(Menu menu);
	
	List<Menu> queryList(Menu menu);
	
	Integer queryListCount(Menu menu);
	
	List<Menu> selectMenuByMap(Map<String,Object> map);
	
	
}
