package org.landslide.data.system;

import java.util.Date;

public class UserLog {
	//USER_ID numeric(10,0)
	private int userId;
	//OPER_TIME            timestamp
	private Date operTime;
	//OPER_TYPE            numeric(10,0)
	private int operType;
	//OPER_DETAIL          varchar(200)
	private String operDetail;
	//ÕËºÅ
	private String userName;
	//ÖÐÎÄÃû
	private String userShowName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public int getOperType() {
		return operType;
	}
	public void setOperType(int operType) {
		this.operType = operType;
	}
	public String getOperDetail() {
		return operDetail;
	}
	public void setOperDetail(String operDetail) {
		this.operDetail = operDetail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserShowName() {
		return userShowName;
	}
	public void setUserShowName(String userShowName) {
		this.userShowName = userShowName;
	}
}
