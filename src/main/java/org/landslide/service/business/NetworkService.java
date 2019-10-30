package org.landslide.service.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.landslide.data.business.INetworkDao;
import org.landslide.data.business.Network;
import org.landslide.service.system.SystemPremaryKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetworkService {
	@Autowired
	INetworkDao networkDao;
	@Autowired
	private SystemPremaryKeyService pkService;
	/**
	 * 分页显示
	 * @param page
	 * @param rows
	 * @return
	 */
	public String queryNetwork(
			int page,//页号，以1开始
			int rows //每页多少行
			){
		ObjectMapper om=new ObjectMapper();
		ObjectNode ont=om.createObjectNode();
		int total=networkDao.queryNetworkTotal();
		ont.put("total", total);
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("rows", rows);
		int offset=(page-1)*rows;
		params.put("offset", offset);
		List<Network> networks=networkDao.queryNetwork(params);
		ArrayNode networkArray=om.valueToTree(networks);		
		ont.put("rows", networkArray);
		return ont.toString();
	}
	public void addNetwork(
			//int nkId,
			String nkName,
			String nkCode,
			int nkType,
			int nkStatus,
			String nkStartDate,
			String nkPrimaryIp,
			String nkBackupIp,
			int nkPort,
			int nkBackupPort,
			double nkDataUploadInv,
			boolean nkDataEncryption,
			int region,
			int city,
			int province,
			String addr,
			String nkContact,
			String nkTel,
			String nkNote
			) throws Exception{
		Network nw=new Network();
		int nkId=pkService.getPrimaryKey("M_NETWORK", "NK_ID");
		nw.setNkId(nkId);
		nw.setNkName(nkName);
		nw.setNkCode(nkCode);
		nw.setNkType(nkType);
		//nw.setNkStatus(nkStatus);
		nw.setNkStatus(1);
		if(nkStartDate!=null){
			Date nkStartDateD=new SimpleDateFormat("yyyy-MM-dd").parse(nkStartDate);
			nw.setNkStartDate(nkStartDateD);
		}
		nw.setNkPrimaryIp(nkPrimaryIp);
		nw.setNkBackupIp(nkBackupIp);
		nw.setNkPort(nkPort);
		nw.setNkBackupPort(nkBackupPort);
		nw.setNkDataUploadInv(nkDataUploadInv);
		nw.setNkDataEncryption(nkDataEncryption);
		nw.setRegion(region);
		nw.setCity(city);
		nw.setProvince(province);
		nw.setAddr(addr);
		nw.setNkContact(nkContact);
		nw.setNkTel(nkTel);
		nw.setNkNote(nkNote);
		networkDao.addNetwork(nw);
	}
	public void updateNetwork(
			int nkId,
			String nkName,
			String nkCode,
			int nkType,
			int nkStatus,
			String nkStartDate,
			String nkPrimaryIp,
			String nkBackupIp,
			int nkPort,
			int nkBackupPort,
			double nkDataUploadInv,
			boolean nkDataEncryption,
			int region,
			int city,
			int province,
			String addr,
			String nkContact,
			String nkTel,
			String nkNote
			)throws Exception{
		Network nw=new Network();
		nw.setNkId(nkId);
		nw.setNkName(nkName);
		nw.setNkCode(nkCode);
		nw.setNkType(nkType);
		nw.setNkStatus(nkStatus);
		if(nkStartDate!=null){
			Date nkStartDateD=new SimpleDateFormat("yyyy-MM-dd").parse(nkStartDate);
			nw.setNkStartDate(nkStartDateD);
		}
		nw.setNkPrimaryIp(nkPrimaryIp);
		nw.setNkBackupIp(nkBackupIp);
		nw.setNkPort(nkPort);
		nw.setNkBackupPort(nkBackupPort);
		nw.setNkDataUploadInv(nkDataUploadInv);
		nw.setNkDataEncryption(nkDataEncryption);
		nw.setRegion(region);
		nw.setCity(city);
		nw.setProvince(province);
		nw.setAddr(addr);
		nw.setNkContact(nkContact);
		nw.setNkTel(nkTel);
		nw.setNkNote(nkNote);
		networkDao.updateNetwork(nw);
	}
	public void deleteNetwork(int nkId) throws Exception{
		Network nw=new Network();
		nw.setNkId(nkId);
		networkDao.deleteNetwork(nw);
	}
	public Network getNetwork(int nkId){
		Map<String,Integer> param=new HashMap<String,Integer>();
		param.put("nkId", nkId);
		return networkDao.getNetwork(param);
	}
	public void updateNkStatus(int nkId,int nkStatus){
		Network nw=new Network();
		nw.setNkId(nkId);
		nw.setNkStatus(nkStatus);
		networkDao.updateNkStatus(nw);
	}
}
