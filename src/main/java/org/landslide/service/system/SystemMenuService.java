package org.landslide.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.landslide.data.system.IMenuDao;
import org.landslide.data.system.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SystemMenuService {
	private static final Logger logger = LoggerFactory.getLogger(SystemMenuService.class);
	@Autowired 
	private IMenuDao menuDao;
	@Autowired
	private SystemPremaryKeyService pkService;
	private HashMap<Integer,Menu> hm;
	public List<Menu> getMenu(String userName){
		logger.debug("current user is:"+userName);
		getAllMenu(userName);
		return getMenus();
	}
	private void getAllMenu(String userName){
		hm=new HashMap<Integer,Menu>();
		List<Menu> mlist=menuDao.queryMenuByUserName(userName);
		for(Menu menu:mlist){
			hm.put(menu.getMenuId(), menu);
			if(menu.getMenuPid()!=0){
				getParent(menu.getMenuPid());
			}
		}
	}
	private void getParent(int id){
		Menu menu=menuDao.getMenuById(id);
		if(!hm.keySet().contains(id)){
			hm.put(id, menu);
		}		
		if(menu.getMenuPid()!=0){
			getParent(menu.getMenuPid());
		}
	}
	private List<Menu> getMenus(){
		List<Menu> menus=new ArrayList<Menu>();
		Set<Integer> keys=hm.keySet();
		Iterator<Integer> itor= keys.iterator();
		while(itor.hasNext()){
			Integer key=itor.next();
			Menu m=hm.get(key);
			if(m.getMenuPid()==0){
				getChildren(m);
				menus.add(m);
			}
		}
		return menus;
	}
	private void getChildren(Menu parent){
		Integer parentId=parent.getMenuId();
		Set<Integer> keys=hm.keySet();
		Iterator<Integer> itor= keys.iterator();
		while(itor.hasNext()){
			Integer key=itor.next();
			Menu m=hm.get(key);
			if(m.getMenuPid()==parentId){
				getChildren(m);
				parent.addChildren(m);
			}
		}
	}
	public int addMenu(int menuPid,
			int rcId,
			String menuName,
			String menuDispName,
			int menuOrder,
			int status,
			boolean isLeaf){
		Menu menu=new Menu();
		int newMenuId=pkService.getPrimaryKey("MENU", "MENU_ID");
		menu.setMenuId(newMenuId);
		menu.setMenuPid(menuPid);
		menu.setRcId(rcId);
		menu.setMenuName(menuName);
		menu.setMenuDispName(menuDispName);
		menu.setMenuOrder(menuOrder);
		menu.setStatus(status);
		menu.setLeaf(isLeaf);
		menuDao.addMenu(menu);
		return newMenuId;
	}
	public void updateMenu(int menuId,int menuPid,
			int rcId,
			String menuName,
			String menuDispName,
			int menuOrder,
			int status,
			boolean isLeaf){
		Menu menu=new Menu();
		menu.setMenuId(menuId);
		menu.setMenuPid(menuPid);
		menu.setRcId(rcId);
		menu.setMenuName(menuName);
		menu.setMenuDispName(menuDispName);
		menu.setMenuOrder(menuOrder);
		menu.setStatus(status);
		menu.setLeaf(isLeaf);
		menuDao.updateMenu(menu);
	}
	public void deleteMenu(int menuId){
		Menu menu=new Menu();
		menu.setMenuId(menuId);
		menuDao.deleteMenu(menu);
	}
}
