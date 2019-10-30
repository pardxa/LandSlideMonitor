package org.landslide.data.business;

import java.util.Date;

public class Network {
	private int nkId;
	private String nkName;
	private String nkCode;
	private int nkType;
	private int nkStatus;
	private Date nkStartDate;
	private String nkPrimaryIp;
	private String nkBackupIp;
	private int nkPort;
	private int nkBackupPort;
	private double nkDataUploadInv;
	private boolean nkDataEncryption;
	private int region;
	private int city;
	private int province;
	private String addr;
	private String nkContact;
	private String nkTel;
	private String nkNote;

	public int getNkId() {
		return nkId;
	}

	public void setNkId(int nkId) {
		this.nkId = nkId;
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

	public int getNkType() {
		return nkType;
	}

	public void setNkType(int nkType) {
		this.nkType = nkType;
	}

	public int getNkStatus() {
		return nkStatus;
	}

	public void setNkStatus(int nkStatus) {
		this.nkStatus = nkStatus;
	}

	public Date getNkStartDate() {
		return nkStartDate;
	}

	public void setNkStartDate(Date nkStartDate) {
		this.nkStartDate = nkStartDate;
	}

	public String getNkPrimaryIp() {
		return nkPrimaryIp;
	}

	public void setNkPrimaryIp(String nkPrimaryIp) {
		this.nkPrimaryIp = nkPrimaryIp;
	}

	public String getNkBackupIp() {
		return nkBackupIp;
	}

	public void setNkBackupIp(String nkBackupIp) {
		this.nkBackupIp = nkBackupIp;
	}

	public int getNkPort() {
		return nkPort;
	}

	public void setNkPort(int nkPort) {
		this.nkPort = nkPort;
	}

	public int getNkBackupPort() {
		return nkBackupPort;
	}

	public void setNkBackupPort(int nkBackupPort) {
		this.nkBackupPort = nkBackupPort;
	}

	public double getNkDataUploadInv() {
		return nkDataUploadInv;
	}

	public void setNkDataUploadInv(double nkDataUploadInv) {
		this.nkDataUploadInv = nkDataUploadInv;
	}

	public boolean isNkDataEncryption() {
		return nkDataEncryption;
	}

	public void setNkDataEncryption(boolean nkDataEncryption) {
		this.nkDataEncryption = nkDataEncryption;
	}


	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getNkContact() {
		return nkContact;
	}

	public void setNkContact(String nkContact) {
		this.nkContact = nkContact;
	}

	public String getNkTel() {
		return nkTel;
	}

	public void setNkTel(String nkTel) {
		this.nkTel = nkTel;
	}

	public String getNkNote() {
		return nkNote;
	}

	public void setNkNote(String nkNote) {
		this.nkNote = nkNote;
	}
}
