package org.landslide.data.security;

public class Resource {
	private int rcId;
	private String rcName;
	private String roleName;
	private String resourceUrl;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public int getRcId() {
		return rcId;
	}
	public void setRcId(int rcId) {
		this.rcId = rcId;
	}
	public String getRcName() {
		return rcName;
	}
	public void setRcName(String rcName) {
		this.rcName = rcName;
	}
}
