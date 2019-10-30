package org.landslide.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.landslide.data.security.IRoleDao;
import org.landslide.data.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemRoleService {
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private SystemPremaryKeyService pkService;
	public String queryRole(
			int page,//页号，以1开始
			int rows //每页多少行
			){
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		Map<String,Integer> params=new HashMap<String,Integer>();
		if(page>0){
			int total=roleDao.queryRoleTotal();
			ont.put("total", total);
			params.put("rows", rows);
			int offset=(page-1)*rows;
			params.put("offset", offset);
		}else{
			params.put("nopaging", 0);
		}
		List<Role> roles=roleDao.queryRole(params);
		ArrayNode userArray=om.valueToTree(roles);		
		ont.put("rows", userArray);
		return ont.toString();
	}
	public void addRole(String roleName,String roleNote) throws Exception{
		Role role=new Role();
		role.setRoleName(roleName);
		role.setRoleNote(roleNote);
		int roleId=pkService.getPrimaryKey("ROLE", "ROLE_ID");
		role.setRoleId(roleId);
		roleDao.addRole(role);
	}
	public void updateRole(String roleId,String roleName,String roleNote) throws Exception{
		Role roleForUpdate=new Role();
		roleForUpdate.setRoleId(Integer.parseInt(roleId));
		roleForUpdate.setRoleName(roleName);
		roleForUpdate.setRoleNote(roleNote);
		roleDao.updateRole(roleForUpdate);
	}
	public void deleteRole(String roleId)throws Exception{
		Role role=new Role();
		role.setRoleId(Integer.parseInt(roleId));
		roleDao.deleteRoleResource(role);
		roleDao.deleteUserRole(role);
		roleDao.deleteRole(role);
	}
}
