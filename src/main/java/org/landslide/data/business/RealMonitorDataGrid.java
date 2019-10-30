package org.landslide.data.business;

import java.util.Date;

public class RealMonitorDataGrid {
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
	//钻孔ID
	private int bhId;
	//监测网ID
	private int nkId;
	//钻孔名称
	private String bhName;
	//设备编号
	private String dvCode;
	//设备名称
	private String dvName;
	//监测网名称
	private String nkName;
	//监测网编号
	private String nkCode;
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
	public int getBhId() {
		return bhId;
	}
	public void setBhId(int bhId) {
		this.bhId = bhId;
	}
	public int getNkId() {
		return nkId;
	}
	public void setNkId(int nkId) {
		this.nkId = nkId;
	}
	public String getBhName() {
		return bhName;
	}
	public void setBhName(String bhName) {
		this.bhName = bhName;
	}
	public String getDvCode() {
		return dvCode;
	}
	public void setDvCode(String dvCode) {
		this.dvCode = dvCode;
	}
	public String getDvName() {
		return dvName;
	}
	public void setDvName(String dvName) {
		this.dvName = dvName;
	}
	public String getNkName() {
		return nkName;
	}
	public void setNkName(String nkName) {
		this.nkName = nkName;
	}
	public String getNkCode() {
		return nkCode;
	}
	public void setNkCode(String nkCode) {
		this.nkCode = nkCode;
	}
}
