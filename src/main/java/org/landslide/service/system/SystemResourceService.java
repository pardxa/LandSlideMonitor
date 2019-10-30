package org.landslide.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.landslide.data.security.IResourceDao;
import org.landslide.data.security.Resource;
import org.landslide.data.security.Role;
import org.landslide.data.security.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SystemResourceService {
	@Autowired
	private IResourceDao resourceDao;
	public String queryResources(
			int page,//页号，以1开始
			int rows //每页多少行
			){
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		Map<String,Integer> params=new HashMap<String,Integer>();
		if(page>0){
			int total=resourceDao.queryResourcesTotal();
			ont.put("total", total);
			params.put("rows", rows);
			int offset=(page-1)*rows;
			params.put("offset", offset);			
		}else{
			params.put("nopaging", 0);
		}
		List<Resource> rcs=resourceDao.queryResources(params);
		ArrayNode resourceArray=om.valueToTree(rcs);		
		ont.put("rows", resourceArray);
		return ont.toString();
	}
	public List<Resource> queryComboResources(){
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("nopaging", 0);
		return resourceDao.queryResources(params);
	}
	public List<Integer> queryResourcesBelong(String roleId)throws Exception{
		Role role=new Role();
		role.setRoleId(Integer.parseInt(roleId));
		return resourceDao.queryRoleResource(role);
	}
	public void modifyRoleResource(int roleId,int[] rourcesIds) throws Exception{
		RoleResource roleRes=new RoleResource();
		roleRes.setRoleId(roleId);
		resourceDao.deleteRoleResource(roleRes);
		for(int rourcesId:rourcesIds){
			RoleResource rrs=new RoleResource();
			rrs.setRoleId(roleId);
			rrs.setRcId(rourcesId);
			resourceDao.addRoleResource(rrs);
		}
	}
}
