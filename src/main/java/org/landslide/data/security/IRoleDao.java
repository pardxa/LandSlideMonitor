package org.landslide.data.security;

import java.util.List;
import java.util.Map;

public interface IRoleDao {
	public Role getRoleById(int roleId);
	public List<Role> queryRolesByUserId(int userid);
	public List<Role> queryRole(Map<String,Integer> params);
	public int queryRoleTotal();
	public void addRole(Role role);
	public void updateRole(Role role);
	public void deleteRole(Role role);
	public void deleteUserRole(Role role);
	public void deleteRoleResource(Role role);
}
