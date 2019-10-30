package org.landslide.mvc.system;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.landslide.data.security.Resource;
import org.landslide.data.security.User;
import org.landslide.data.system.JsonBizDictionaryItem;
import org.landslide.data.system.SysConfig;
import org.landslide.service.system.SysConfigService;
import org.landslide.service.system.SystemBizDictionaryService;
import org.landslide.service.system.SystemLogService;
import org.landslide.service.system.SystemMenuService;
import org.landslide.service.system.SystemResourceService;
import org.landslide.service.system.SystemRoleService;
import org.landslide.service.system.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 处理Ajax请求
 * @author Administrator
 *
 */
@Controller
public class SystemAjaxRequestHandler {
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemBizDictionaryService systemBizDictionaryService;
	@Autowired
	private SystemRoleService systemRoleService;
	@Autowired
	private SystemResourceService systemResourceService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private SystemMenuService systemMenuService;
	///用户相关
	@RequestMapping(value="/queryUser",method=RequestMethod.POST)
	public @ResponseBody String queryUser(
			int page,//页号，以1开始
			int rows //每页多少行
			){
		return systemUserService.queryUser(page,rows);
	}
	@RequestMapping(value="/queryalluser",method=RequestMethod.POST)
	public @ResponseBody List<User> queryAllUser(){
		return systemUserService.queryAllUser();
	}
	@RequestMapping(value="/adduser*",method=RequestMethod.POST)
	public @ResponseBody String addUser(
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
			String userCompany,
			String type) {		
		try{
			String _userShowName=(userShowName==null)?null:new String(userShowName.getBytes("iso8859-1"),"utf-8");
			String _userName=(userName==null)?null:new String(userName.getBytes("iso8859-1"),"utf-8");
			String _passWord=(passWord==null)?null:new String(passWord.getBytes("iso8859-1"),"utf-8");
			String _userTel=(userTel==null)?null:new String(userTel.getBytes("iso8859-1"),"utf-8");
			String _userCompany=(userCompany==null)?null:new String(userCompany.getBytes("iso8859-1"),"utf-8");
			if(type.equals("add")||userId.trim().equals("")){
				systemUserService.addUser(_userShowName, _userName, _passWord, isEnabled, isActNonLocked, actExpireDate, credentialsExpireDate, _userTel, _userCompany);
			}else if(type.equals("edit")){
				systemUserService.updateUser(userId,userInfoId,_userShowName, _userName, _passWord, isEnabled, isActNonLocked, actExpireDate, credentialsExpireDate, _userTel, _userCompany);
			}else if(type.equals("delete")){
				systemUserService.deleteUser(userId);
			}
			else{
				throw new Exception("can not find type");
			}
		}catch(Exception e){			
			return JsonException(e);
		}
		return "{\"success\":true}";
	}
	@RequestMapping(value="/queryrolesbelong",method=RequestMethod.POST)
	public @ResponseBody List<Integer> queryRolesBelong(String userId)throws Exception{
		return systemUserService.queryRolesBelong(userId);
	}
	@RequestMapping(value="/modifyuserrole",method=RequestMethod.POST)
	public @ResponseBody String modifyUserRole(int userId,@RequestParam(value = "roleIds[]")int[] roleIds){
		try{
			systemUserService.modifyUserRole(userId,roleIds);
		}catch(Exception e){			
			return JsonException(e);
		}
		return "{\"success\":true}";
	}
	@RequestMapping(value="changepassordaction",method=RequestMethod.POST)
	public @ResponseBody String changePassordAction(
			String oldPassword,
			String newPassword,
			HttpServletRequest request){
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if(oldPassword.trim().equals(user.getPassWord())){
			try{				
				systemUserService.updateUserPassword(user.getUserId(), newPassword);
				session.removeAttribute("user");
				user.setPassWord(newPassword);
				session.setAttribute("user",user);
				return "{\"success\":true}";
			}catch(Exception e){
				return JsonException(e);
			}	
		}
		return "{\"success\":false,\"error\":'badpwd'}";
	}
	///字典相关
	@RequestMapping(value="/getdicitems",method=RequestMethod.POST)
	public @ResponseBody List<JsonBizDictionaryItem> getBizDictionaryItems(String dicttypeid,
			HttpServletRequest request){
		ServletContext cxt=	request.getSession().getServletContext();
		Map<String,Map<Integer,String>> bizDicMap=(Map<String,Map<Integer,String>>)cxt.getAttribute("biz_dictionary_object");
		if(bizDicMap==null){
			bizDicMap=systemBizDictionaryService.getBizDictionary();
			cxt.setAttribute("biz_dictionary_object", bizDicMap);
		}
		return systemBizDictionaryService.getBizDictionaryItems(bizDicMap, dicttypeid);
	}
	@RequestMapping(value="/getdictreeitems",method=RequestMethod.POST)
	public @ResponseBody List<JsonBizDictionaryItem> queryBizDictionaryItemTree(String dictTypeId,
			int bizDicItemPid){
		return systemBizDictionaryService.queryBizDictionaryItemTree(dictTypeId, bizDicItemPid);
	}
	///角色相关
	@RequestMapping(value="/queryRole",method=RequestMethod.POST)
	public @ResponseBody String queryRole(
			HttpServletRequest request
			){
		String pageStr=request.getParameter("page");
		String rowsStr=request.getParameter("rows");
		int page=0;
		int rows=0;
		if(pageStr!=null){
			page=Integer.parseInt(pageStr);
			rows=Integer.parseInt(rowsStr);
		}
		return systemRoleService.queryRole(page, rows);
	}
	@RequestMapping(value="/addrole*",method=RequestMethod.POST )
	public @ResponseBody String addRole(String roleId,String roleName,String roleNote,String type){
		try{
			String _roleName=(roleName==null)?null: new String(roleName.getBytes("iso8859-1"),"utf-8");
			String _roleNote=(roleNote==null)?null: new String(roleNote.getBytes("iso8859-1"),"utf-8");;
			if(type.trim().equals("add")){
				systemRoleService.addRole(_roleName, _roleNote);
			}else if(type.trim().equals("edit")){
				systemRoleService.updateRole(roleId, _roleName, _roleNote);
			}else if(type.trim().equals("delete")){
				systemRoleService.deleteRole(roleId);
			}else{
				throw new Exception("can not find type");
			}
		}catch(Exception e){
			return JsonException(e);
		}
		return "{\"success\":true}";
	}
	///资源相关
	@RequestMapping(value="/queryResources",method=RequestMethod.POST)
	public @ResponseBody String queryResources(
			HttpServletRequest request
			){
		int page=0;//页号，以1开始
		int rows=0; //每页多少行
		String pageStr=request.getParameter("page");
		String rowsStr=request.getParameter("rows");
		if(pageStr!=null){
			page=Integer.parseInt(pageStr);
			rows=Integer.parseInt(rowsStr);
		}		
		return systemResourceService.queryResources(page, rows);
	}
	@RequestMapping(value="/querycomboresources",method=RequestMethod.POST)
	public @ResponseBody List<Resource> queryComboResources(
			HttpServletRequest request
			){
		return systemResourceService.queryComboResources();
	}	
	@RequestMapping(value="queryresourcesbelong",method=RequestMethod.POST)
	public @ResponseBody List<Integer> queryResourcesBelong(String roleId) throws Exception{
		return systemResourceService.queryResourcesBelong(roleId);
	}
	@RequestMapping(value="/modifyroleresource")
	public @ResponseBody String modifyRoleResource(int roleId,@RequestParam(value = "rourcesIds[]")int[] rourcesIds){
		try{
			systemResourceService.modifyRoleResource(roleId, rourcesIds);
		}catch(Exception e){			
			return JsonException(e);
		}
		return "{\"success\":true}";
	}
	///系统配置
	@RequestMapping(value="/querysysconfig")
	public @ResponseBody SysConfig querySysConfig(){
		return sysConfigService.querySysConfig();
	}
	@RequestMapping(value="/updatesysconfig*",method=RequestMethod.POST)
	public @ResponseBody String updateSysConfig(String refreshFrequency,String realQueryStart,int bhSitePhotoH,int bhSitePhotoW){
		try{
			long refreshFrequencyL=Long.parseLong(refreshFrequency);
			long realQueryStartL=Long.parseLong(realQueryStart);
			sysConfigService.updateSysConfig(refreshFrequencyL,realQueryStartL, bhSitePhotoH, bhSitePhotoW);
		}catch(Exception e){			
			return JsonException(e);
		}
		return "{\"success\":true}";
	}
	///用户日志
	@RequestMapping(value="/queryuserlog*",method=RequestMethod.POST)
	public @ResponseBody String queryUserLog(
			int page,//页号，以1开始
			int rows, //每页多少行
			HttpServletRequest request
			){
		String userId=request.getParameter("userId");
		String startTimeData=null;
		if(request.getParameter("startTimeData")!=null){
			startTimeData=request.getParameter("startTimeData").equals("")?null:request.getParameter("startTimeData");
		}		
		String endTimeData=null;
		if(request.getParameter("endTimeData")!=null){
			endTimeData=request.getParameter("endTimeData").equals("")?null:request.getParameter("endTimeData");
		}		
		return systemLogService.queryUserLog(page, rows,userId,startTimeData,endTimeData);
	}
	///菜单处理
	@RequestMapping(value="/addmenu*",method=RequestMethod.POST)
	public @ResponseBody String addMenu(HttpServletRequest request){
		String menuPidStr=request.getParameter("menuPid");
		String rcIdStr=request.getParameter("rcId");
		String menuName=request.getParameter("menuName");
		String menuDispName=request.getParameter("menuDispName");
		String menuOrderStr=request.getParameter("menuOrder");
		String statusStr=request.getParameter("status");
		String isLeafStr=request.getParameter("isLeaf");
		int newMenuId=0;
		try{
			newMenuId=systemMenuService.addMenu((menuPidStr==null)?0:Integer.parseInt(menuPidStr),
					(rcIdStr==null)?0:Integer.parseInt(rcIdStr),
					(menuName==null)?"":menuName.trim(),
					(menuDispName==null)?"":menuDispName.trim(),
					(menuOrderStr==null)?0:Integer.parseInt(menuOrderStr),
					(statusStr==null)?0:Integer.parseInt(statusStr),
					(isLeafStr==null)?false:Boolean.parseBoolean(isLeafStr));			
		}catch(Exception e){
			return JsonException(e);
		}
		return "{\"success\":true,\"menuId\":"+newMenuId+"}";
	}
	@RequestMapping(value="/updatemenu*",method=RequestMethod.POST)
	public @ResponseBody String updateMenu(HttpServletRequest request){
		String menuIdStr=request.getParameter("menuId");
		String menuPidStr=request.getParameter("menuPid");
		String rcIdStr=request.getParameter("rcId");
		String menuName=request.getParameter("menuName");
		String menuDispName=request.getParameter("menuDispName");
		String menuOrderStr=request.getParameter("menuOrder");
		String statusStr=request.getParameter("status");
		String isLeafStr=request.getParameter("isLeaf");
		int menuId=(menuIdStr==null)?0:Integer.parseInt(menuIdStr);
		try{
			systemMenuService.updateMenu(menuId, 
					(menuPidStr==null)?0:Integer.parseInt(menuPidStr),
					(rcIdStr==null)?0:Integer.parseInt(rcIdStr),
					(menuName==null)?"":menuName.trim(),
					(menuDispName==null)?"":menuDispName.trim(),
					(menuOrderStr==null)?0:Integer.parseInt(menuOrderStr),
					(statusStr==null)?0:Integer.parseInt(statusStr),
					(isLeafStr==null)?false:((isLeafStr.trim().equals("1"))?true:false));			
		}catch(Exception e){
			return JsonException(e);
		}
		return "{\"success\":true,\"menuId\":"+menuId+"}";
	}
	@RequestMapping(value="/deletemenu*",method=RequestMethod.POST)
	public @ResponseBody String deleteMenu(int menuId){
		try{
			systemMenuService.deleteMenu(menuId);
		}catch(Exception e){
			return JsonException(e);
		}
		return "{\"success\":true,\"menuId\":"+menuId+"}";
	}
	///
	private String JsonException(Exception e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    e.printStackTrace(ps);
	    ps.close();
		return "{\"success\":false,\"message\":\""+baos.toString()+"\"}";		
	}
}
