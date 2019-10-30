package org.landslide.data.security;

import java.util.List;
import java.util.Map;

public interface IUserDao {
	public User getUserByName(String userName);
	//
	public List<User> queryUser(Map<String,Integer> params);
	public int queryUserTotal();
	public void addUser(User user);
	public void addUserInfo(User user);
	public void updateUser(User user);
	public void updateUserInfo(User user);
	public void updateUserPassword(User user);
	public void deleteUser(User user);
	public void deleteUserInfo(User user);
	public void deleteUserRole(User user);
	public List<Integer> queryUserRole(User user);
	public void addUserRole(UserRole userRole);
}
