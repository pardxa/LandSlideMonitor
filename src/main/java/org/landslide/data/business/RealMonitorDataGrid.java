package org.landslide.data.business;

import java.util.Date;

public class RealMonitorDataGrid {
	//�������ID
	private int monitorId;
	//SIM����
	private String dvSimCode;
	//ͨ�����
	private String clCode;
	//��¼ʱ��
	private Date mdRecordTime;
	//λ����
	private double mdDisplaceAlert;
	//���ID
	private int bhId;
	//�����ID
	private int nkId;
	//�������
	private String bhName;
	//�豸���
	private String dvCode;
	//�豸����
	private String dvName;
	//���������
	private String nkName;
	//��������
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
