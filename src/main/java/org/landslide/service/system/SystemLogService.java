package org.landslide.service.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.landslide.data.system.ISysLog;
import org.landslide.data.system.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogService {
	@Autowired
	private ISysLog iSysLog;
	public String queryUserLog(
			int page,//页号，以1开始
			int rows, //每页多少行
			String userId,
			String startTimeData,
			String endTimeData
			){
		Map<String,String> params=new HashMap<String,String>();
		params.put("rows", Integer.toString(rows));
		int offset=(page-1)*rows;
		params.put("offset", Integer.toString(offset));
		if(userId!=null){
			params.put("userId",userId);
		}
		if(startTimeData!=null){
			params.put("soperTime",startTimeData);
		}
		if(endTimeData!=null){
			params.put("eoperTime",endTimeData);
		}
		List<UserLog> ul=iSysLog.queryUserLog(params);
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		int total=iSysLog.queryAllUserLog(params);
		ont.put("total", total);
		ArrayNode userLogArray=om.valueToTree(ul);		
		ont.put("rows", userLogArray);
		return ont.toString();
	}
}
