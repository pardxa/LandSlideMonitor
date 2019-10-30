package org.landslide.service.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.landslide.data.business.IMonitorThd;
import org.landslide.data.business.MonitorThd;
import org.landslide.service.system.SystemPremaryKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorThdService {
	@Autowired
	private IMonitorThd iMonitorThd;
	@Autowired
	private SystemPremaryKeyService pkService;
	public String queryMonitorThd(
			int bhId,
			int page,//页号，以1开始
			int rows //每页多少行
			){
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("bhId", bhId);
		params.put("rows", rows);
		int offset=(page-1)*rows;
		params.put("offset", offset);
		int total=iMonitorThd.queryMonitorThdTotal(params);
		ont.put("total", total);
		List<MonitorThd> mts=iMonitorThd.queryMonitorThd(params);
		ArrayNode networkArray=om.valueToTree(mts);		
		ont.put("rows", networkArray);
		return ont.toString();
	}
	public void addMonitorThd(
			int bhId,//钻孔ID
			String clCode,//通道编号	
			double thd //阀值
			)throws Exception{
		MonitorThd mt=new MonitorThd();
		mt.setBhId(bhId);
		mt.setClCode(clCode);
		mt.setThd(thd);
		int thdId=pkService.getPrimaryKey("MONITOR_THD", "THD_ID");
		mt.setThdId(thdId);
		iMonitorThd.addMonitorThd(mt);
	}
	public void updateMonitorThd(
			int thdId,
			int bhId,//钻孔ID
			String clCode,//通道编号	
			double thd //阀值
			)throws Exception{
		MonitorThd mt=new MonitorThd();
		mt.setBhId(bhId);
		mt.setClCode(clCode);
		mt.setThd(thd);
		mt.setThdId(thdId);	
		iMonitorThd.updateMonitorThd(mt);
	}
	public void deleteMonitorThd(int bhId)throws Exception{
		MonitorThd mt=new MonitorThd();
		mt.setBhId(bhId);
		iMonitorThd.deleteMonitorThd(mt);
	}
}
