package org.landslide.data.business;

import java.util.Date;

public class RealMonitorData {
	//监控数据ID
	private int monitorId;
	//SIM卡号
	private String dvSimCode;
	//通道编号
	private String clCode;
	//记录时间
	private Date mdRecordTime;
	//位移量
	private double mdDisplaceAlert;
	public int getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(int monitorId) {
		this.monitorId = monitorId;
	}
	public String getDvSimCode() {
		return dvSimCode;
	}
	public void setDvSimCode(String dvSimCode) {
		this.dvSimCode = dvSimCode;
	}
	public String getClCode() {
		return clCode;
	}
	public void setClCode(String clCode) {
		this.clCode = clCode;
	}
	public Date getMdRecordTime() {
		return mdRecordTime;
	}
	public void setMdRecordTime(Date mdRecordTime) {
		this.mdRecordTime = mdRecordTime;
	}
	public double getMdDisplaceAlert() {
		return mdDisplaceAlert;
	}
	public void setMdDisplaceAlert(double mdDisplaceAlert) {
		this.mdDisplaceAlert = mdDisplaceAlert;
	}
}
