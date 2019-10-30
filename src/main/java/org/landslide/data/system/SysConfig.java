package org.landslide.data.system;

public class SysConfig {
	private int sysConfigId;
	//页面默认刷新周期
	private long refreshFrequency;
	//默认实时查询起点 
	private long realQueryStart;
	//桩点图片高度
	private int bhSitePhotoH;
	//桩点图片宽度
	private int bhSitePhotoW;
	public int getSysConfigId() {
		return sysConfigId;
	}
	public void setSysConfigId(int sysConfigId) {
		this.sysConfigId = sysConfigId;
	}
	public long getRefreshFrequency() {
		return refreshFrequency;
	}
	public void setRefreshFrequency(long refreshFrequency) {
		this.refreshFrequency = refreshFrequency;
	}
	public long getRealQueryStart() {
		return realQueryStart;
	}
	public void setRealQueryStart(long realQueryStart) {
		this.realQueryStart = realQueryStart;
	}
	public int getBhSitePhotoH() {
		return bhSitePhotoH;
	}
	public void setBhSitePhotoH(int bhSitePhotoH) {
		this.bhSitePhotoH = bhSitePhotoH;
	}
	public int getBhSitePhotoW() {
		return bhSitePhotoW;
	}
	public void setBhSitePhotoW(int bhSitePhotoW) {
		this.bhSitePhotoW = bhSitePhotoW;
	}


}
