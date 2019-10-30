package org.landslide.service.system;

import java.util.HashMap;
import java.util.Map;

import org.landslide.data.system.IPrimaryKeyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemPremaryKeyService {
	@Autowired
	private IPrimaryKeyDao primaryKeyDao;
	public int getPrimaryKey(String tableName,String pkName){
		Map<String,String> params=new HashMap<String,String>();
		params.put("tablename", tableName);
		params.put("pkname", pkName);
		int pk=primaryKeyDao.getPrimaryKey(params);
		int newpk=pk+1;
		params.put("newpk", Integer.toString(newpk));
		primaryKeyDao.updatePrimaryKey(params);
		return pk;
	}
}
