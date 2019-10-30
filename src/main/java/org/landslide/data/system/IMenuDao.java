package org.landslide.data.system;

import java.util.List;

public interface IMenuDao {
	public List<Menu> queryMenuByUserName(String username);
	public Menu getMenuById(int menuId);
	public void addMenu(Menu menu);
	public void updateMenu(Menu menu);
	public void deleteMenu(Menu menu);
}
