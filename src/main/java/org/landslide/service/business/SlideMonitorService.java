package org.landslide.service.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.landslide.data.business.Borehole;
import org.landslide.data.business.IBoreholeDao;
import org.landslide.data.business.INetworkDao;
import org.landslide.data.business.IRealMonitorData;
import org.landslide.data.business.Network;
import org.landslide.data.business.RealMonitorData;
import org.landslide.data.business.RealMonitorDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideMonitorService {
	@Autowired
	private INetworkDao networkDao;
	@Autowired
	private IBoreholeDao boreholeDao;
	@Autowired
	private IRealMonitorData realMonitorData;
	public List<Network> queryNetworkList(){
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("nopaging", 1);
		return networkDao.queryNetwork(params);
	}
	public List<Borehole> queryBoreholes(int nkId){
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("nkId", nkId);
		return boreholeDao.queryBoreholes(params);
	}
	public String queryRealMonitorData(String dvSimCode,Date startRecordTime,Date endRecordTime){
		int channel=4;
		String[] colorStr=new String[]{"#000000","#6600ff","#99ff33","#3333ff"};
		ObjectMapper mapper=new ObjectMapper();
		Map<String,String> params=new HashMap<String,String>();
		params.put("dvSimCode", dvSimCode);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("startRecordTime", df.format(startRecordTime));
		if(endRecordTime!=null){
			params.put("endRecordTime", df.format(endRecordTime));
		}		
		JsonNode allData=mapper.createArrayNode();
		for(int i=0;i<channel;i++){
			params.remove("clCode");
			params.put("clCode", Integer.toString(i+1));
			List<RealMonitorData> data=realMonitorData.queryRealMonitorData(params);			
			ArrayNode  pairs=mapper.createArrayNode();
			for(RealMonitorData dt:data){
				JsonNode  dl=mapper.createArrayNode();
				((ArrayNode)dl).add(dt.getMdRecordTime().getTime());
				((ArrayNode)dl).add(dt.getMdDisplaceAlert());
				pairs.add(dl);
			}
			ObjectNode oneChannelData=createFlotLine(mapper,
					pairs,
					colorStr[i],
					(data.size()<200),//一行小于200个点，显示数据点
					Integer.toString(i+1));//第几个channel
			((ArrayNode)allData).add(oneChannelData);
		}
		ObjectNode ont=mapper.createObjectNode();
		ont.put("data",allData);
		return ont.toString();
	}
	/**
	 * 生成Flot数据格式：
	 * {
	 * 		data: d1,
	 *		lines: { show: true},
	 *		points: { show: true,symbol: "diamond"},
	 *		color: ["#FFFFFF"]
	 *	}
	 * @param mapper
	 * @param data
	 * @param colorStr
	 * @param isShowPoint 是否显示点
	 * @return
	 */
	private ObjectNode createFlotLine(ObjectMapper mapper,
			JsonNode data,
			String colorStr,
			boolean isShowPoint,String label){
		ObjectNode objectNode=mapper.createObjectNode();
		//
		objectNode.put("data", data);
		objectNode.put("label", label);
		//
		ObjectNode lines=mapper.createObjectNode();
		lines.put("show", true);
		objectNode.put("lines", lines);
		//
		ObjectNode points=mapper.createObjectNode();
		points.put("show", true);
		points.put("symbol", "diamond");
		if(isShowPoint){
			objectNode.put("points",points);
		}
		//
		ArrayNode color=mapper.createArrayNode();
		color.add(colorStr);
		objectNode.put("color", color);
		return objectNode;
	}
	public String queryRealMonitorDataGrid(
			int page,//页号，以1开始
			int rows, //每页多少行
			int bhId,
			Date startRecordTime,
			Date endRecordTime){
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		Map<String,String> params=new HashMap<String,String>();
		if(bhId!=0){
			params.put("bhId", Integer.toString(bhId));
		}		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(startRecordTime!=null){
			params.put("startRecordTime", df.format(startRecordTime));
		}		
		if(endRecordTime!=null){
			params.put("endRecordTime", df.format(endRecordTime));
		}
		int total=realMonitorData.queryRealMonitorDataGridTotal(params);
		ont.put("total", total);
		params.put("rows", Integer.toString(rows));
		int offset=(page-1)*rows;
		params.put("offset", Integer.toString(offset));
		List<RealMonitorDataGrid> rd=realMonitorData.queryRealMonitorDataGrid(params);
		ArrayNode networkArray=om.valueToTree(rd);		
		ont.put("rows", networkArray);
		return ont.toString();
	}
	public List<RealMonitorDataGrid> queryRealMonitorDataGridExcel(int bhId,
			Date startRecordTime,
			Date endRecordTime){
		Map<String,String> params=new HashMap<String,String>();
		if(bhId!=0){
			params.put("bhId", Integer.toString(bhId));
		}		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(startRecordTime!=null){
			params.put("startRecordTime", df.format(startRecordTime));
		}		
		if(endRecordTime!=null){
			params.put("endRecordTime", df.format(endRecordTime));
		}
		return realMonitorData.queryRealMonitorDataGrid(params);
	}
}
