package org.landslide.service.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.landslide.data.security.IRoleDao;
import org.landslide.data.security.IUserDao;
import org.landslide.data.security.User;
import org.landslide.data.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService {
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private SystemPremaryKeyService pkService;
	public String queryUser(
			int page,//页号，以1开始
			int rows //每页多少行
			){
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		int total=userDao.queryUserTotal();
		ont.put("total", total);
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("rows", rows);
		int offset=(page-1)*rows;
		params.put("offset", offset);
		List<User> users=userDao.queryUser(params);
		ArrayNode userArray=om.valueToTree(users);		
		ont.put("rows", userArray);
		return ont.toString();
	}
	public List<User> queryAllUser(){
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("nopaging", 0);
		return userDao.queryUser(params);		
	}
	public void addUser(String userShowName,
			String userName,
			String passWord,
			boolean isEnabled,
			boolean isActNonLocked,
			String actExpireDate,
			String credentialsExpireDate,
			String userTel,
			String userCompany)throws Exception{
		User newUser=new User();
		newUser.setUserShowName(userShowName);
		newUser.setUserName(userName);
		newUser.setPassWord(passWord);
		newUser.setEnabled(isEnabled);
		newUser.setActNonLocked(isActNonLocked);
		if(credentialsExpireDate!=null&&!credentialsExpireDate.trim().equals("")){
			Date ced=new SimpleDateFormat("yyyy-MM-dd").parse(credentialsExpireDate);
			newUser.setCredentialsExpireDate(ced);
		}
		if(actExpireDate!=null&&!actExpireDate.trim().equals("")){
			Date aed=new SimpleDateFormat("yyyy-MM-dd").parse(actExpireDate);
			newUser.setActExpireDate(aed);
		}
		newUser.setUserTel(userTel);
		newUser.setUserCompany(userCompany);
		int userId=pkService.getPrimaryKey("USER", "USER_ID");
		int userInfoId=pkService.getPrimaryKey("USER_INFO", "USER_INFO_ID");
		newUser.setUserId(userId);
		newUser.setUserInfoId(userInfoId);
		userDao.addUser(newUser);
		userDao.addUserInfo(newUser);
	}
	public void updateUser(
			String userId,
			String userInfoId,
			String userShowName,
			String userName,
			String passWord,
			boolean isEnabled,
			boolean isActNonLocked,
			String actExpireDate,
			String credentialsExpireDate,
			String userTel,
			String userCompany)throws Exception{
		User userForUpdate=new User();
		userForUpdate.setUserId(Integer.parseInt(userId));
		userForUpdate.setUserShowName(userShowName);
		userForUpdate.setUserName(userName);
		userForUpdate.setPassWord(passWord);
		userForUpdate.setEnabled(isEnabled);
		userForUpdate.setActNonLocked(isActNonLocked);
		if(credentialsExpireDate!=null&&!credentialsExpireDate.trim().equals("")){
			Date ced=new SimpleDateFormat("yyyy-MM-dd").parse(credentialsExpireDate);
			userForUpdate.setCredentialsExpireDate(ced);
		}
		if(actExpireDate!=null&&!actExpireDate.trim().equals("")){
			Date aed=new SimpleDateFormat("yyyy-MM-dd").parse(actExpireDate);
			userForUpdate.setActExpireDate(aed);
		}
		userForUpdate.setUserTel(userTel);
		userForUpdate.setUserCompany(userCompany);
		if(userInfoId.trim().equals("")||userInfoId.trim().equals("0")){
			userForUpdate.setUserInfoId(pkService.getPrimaryKey("USER_INFO", "USER_INFO_ID"));
			userDao.addUserInfo(userForUpdate);
		}else{
			userForUpdate.setUserInfoId(Integer.parseInt(userInfoId));
			userDao.updateUserInfo(userForUpdate);
		}
		userDao.updateUser(userForUpdate);
	}
	public void updateUserPassword(int userId,String passWord)throws Exception{
		User userForUpdate=new User();
		userForUpdate.setUserId(userId);
		userForUpdate.setPassWord(passWord);
		userDao.updateUserPassword(userForUpdate);
	}
	public void deleteUser(String userId)throws Exception{
		User userForDelete=new User();
		userForDelete.setUserId(Integer.parseInt(userId));
		//注意顺序，由于有外键连接，先删除UserInfo、USER_ROLE
		userDao.deleteUserInfo(userForDelete);
		userDao.deleteUserRole(userForDelete);
		userDao.deleteUser(userForDelete);		
	}
	public List<Integer> queryRolesBelong(String userId)throws Exception{
		User user=new User();
		user.setUserId(Integer.parseInt(userId));
		return userDao.queryUserRole(user);
	}
	public void modifyUserRole(int userId,int[] roleIds) throws Exception{
		User user=new User();
		user.setUserId(userId);
		userDao.deleteUserRole(user);
		for(int roleId:roleIds){
			UserRole ur=new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			userDao.addUserRole(ur);
		}
	}
}
