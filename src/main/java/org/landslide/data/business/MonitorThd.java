package org.landslide.data.business;

public class MonitorThd {
	private int thdId;
	//���ID
	private int bhId;
	//ͨ�����
	private String clCode;
	//��ֵ
	private double thd;
	public int getThdId() {
		return thdId;
	}
	public void setThdId(int thdId) {
		this.thdId = thdId;
	}
	public int getBhId() {
		return bhId;
	}
	public void setBhId(int bhId) {
		this.bhId = bhId;
	}
	public String getClCode() {
		return clCode;
	}
	public void setClCode(String clCode) {
		this.clCode = clCode;
	}
	public double getThd() {
		return thd;
	}
	public void setThd(double thd) {
		this.thd = thd;
	}
}
