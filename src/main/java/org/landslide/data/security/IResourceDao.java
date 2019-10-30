package org.landslide.data.security;

import java.util.List;
import java.util.Map;

public interface IResourceDao {
	public List<Resource> queryResource();
	public List<Resource> queryResources(Map<String,Integer> params);
	public int queryResourcesTotal();
	public List<Integer> queryRoleResource(Role role);
	public void addRoleResource(RoleResource roleRes);
	public void deleteRoleResource(RoleResource roleRes);
}
