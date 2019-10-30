package org.landslide.data.system;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private int menuId;
	private int menuPid;
	private int rcId;
	private String rcUrl="";
	private String menuName="";
	private String menuDispName="";
	private int menuOrder;
	private int status;
	private boolean isLeaf;
	private List<Menu> children=new ArrayList<Menu>();
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getMenuPid() {
		return menuPid;
	}
	public void setMenuPid(int menuPid) {
		this.menuPid = menuPid;
	}
	public int getRcId() {
		return rcId;
	}
	public void setRcId(int rcId) {
		this.rcId = rcId;
	}
	public String getRcUrl() {
		if(!rcUrl.trim().isEmpty()){
			String tempRcUrl=rcUrl.substring(1);
			return tempRcUrl+".do";			
		}
		return rcUrl;
	}
	public void setRcUrl(String rcUrl) {
		this.rcUrl = rcUrl;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuDispName() {
		return menuDispName;
	}
	public void setMenuDispName(String menuDispName) {
		this.menuDispName = menuDispName;
	}
	public int getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void addChildren(Menu child) {
		children.add(child);
	}
}
