package org.landslide.data.security;

import java.util.Date;

public class User {
	private int userId;
	private String userName="";
	private String passWord="";
	private boolean isEnabled;
	private boolean isActNonLocked;
	private Date actExpireDate;
	private Date credentialsExpireDate;
	//
	private int userInfoId;
	private String userShowName="";
	private String userCompany="";
	private String userTel="";
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public boolean isActNonLocked() {
		return isActNonLocked;
	}
	public void setActNonLocked(boolean isActNonLocked) {
		this.isActNonLocked = isActNonLocked;
	}
	public Date getActExpireDate() {
		return actExpireDate;
	}
	public void setActExpireDate(Date actExpireDate) {
		this.actExpireDate = actExpireDate;
	}
	public Date getCredentialsExpireDate() {
		return credentialsExpireDate;
	}
	public void setCredentialsExpireDate(Date credentialsExpireDate) {
		this.credentialsExpireDate = credentialsExpireDate;
	}
	public int getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getUserShowName() {
		return userShowName;
	}
	public void setUserShowName(String userShowName) {
		this.userShowName = userShowName;
	}
	public String getUserCompany() {
		return userCompany;
	}
	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

}
