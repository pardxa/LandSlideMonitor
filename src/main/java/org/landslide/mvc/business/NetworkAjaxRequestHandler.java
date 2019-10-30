package org.landslide.mvc.business;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;

import org.landslide.data.business.Network;
import org.landslide.service.business.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NetworkAjaxRequestHandler {
	@Autowired
	private NetworkService networkService;
	@RequestMapping(value="/querynetwork",method=RequestMethod.POST)
	public @ResponseBody String queryNetwork(
			int page,//页号，以1开始
			int rows //每页多少行
			){
		return networkService.queryNetwork(page, rows);
	}
	@RequestMapping(value="/getnetwork",method=RequestMethod.POST)
	public @ResponseBody Network getNetwork(int nkId){
		return networkService.getNetwork(nkId);
	}	
	@RequestMapping(value="/addnetwork*",method=RequestMethod.POST)
	public @ResponseBody String addNetwork(
				HttpServletRequest request 
			){
		try{
			int nkId=(request.getParameter("nkId")==null
					||request.getParameter("nkId").trim().equals(""))?0:Integer.parseInt(request.getParameter("nkId"));
			String nkName=request.getParameter("nkName");
			String nkCode=request.getParameter("nkCode");
			int nkType=(request.getParameter("nkType")==null
					||request.getParameter("nkType").trim().equals(""))?0:Integer.parseInt(request.getParameter("nkType"));
			int nkStatus=(request.getParameter("nkStatus")==null
					||request.getParameter("nkStatus").trim().equals(""))?0:Integer.parseInt(request.getParameter("nkStatus"));
			String nkStartDate=request.getParameter("nkStartDate");
			String nkPrimaryIp=request.getParameter("nkPrimaryIp");
			String nkBackupIp=request.getParameter("nkBackupIp");
			int nkPort=(request.getParameter("nkPort")==null
					||request.getParameter("nkPort").trim().equals(""))?0:Integer.parseInt(request.getParameter("nkPort"));
			int nkBackupPort=(request.getParameter("nkBackupPort")==null
					||request.getParameter("nkBackupPort").trim().equals(""))?0:Integer.parseInt(request.getParameter("nkBackupPort"));
			double nkDataUploadInv=(request.getParameter("nkDataUploadInv")==null
					||request.getParameter("nkDataUploadInv").trim().equals(""))?0:Double.parseDouble(request.getParameter("nkDataUploadInv"));
			boolean nkDataEncryption=(request.getParameter("nkDataEncryption")==null
					||request.getParameter("nkDataEncryption").trim().equals(""))?false:((request.getParameter("nkDataEncryption").trim().equals("1"))?true:false);
			int region=(request.getParameter("region")==null
					||request.getParameter("region").trim().equals(""))?0:Integer.parseInt(request.getParameter("region"));
			int city=(request.getParameter("city")==null
					||request.getParameter("city").trim().equals(""))?0:Integer.parseInt(request.getParameter("city"));
			int province=(request.getParameter("province")==null
					||request.getParameter("province").trim().equals(""))?0:Integer.parseInt(request.getParameter("province"));
			String addr=request.getParameter("addr");
			String nkContact=request.getParameter("nkContact");
			String nkTel=request.getParameter("nkTel");
			String nkNote=request.getParameter("nkNote");
			String type=request.getParameter("type");
			//
			String _nkName=(nkName==null)?null:new String(nkName.getBytes("iso8859-1"),"utf-8");
			String _nkCode=(nkCode==null)?null:new String(nkCode.getBytes("iso8859-1"),"utf-8");
			String _nkStartDate=(nkStartDate==null)?null:new String(nkStartDate.getBytes("iso8859-1"),"utf-8");
			String _nkPrimaryIp=(nkPrimaryIp==null)?null:new String(nkPrimaryIp.getBytes("iso8859-1"),"utf-8");
			String _nkBackupIp=(nkBackupIp==null)?null:new String(nkBackupIp.getBytes("iso8859-1"),"utf-8");
			String _addr=(addr==null)?null:new String(addr.getBytes("iso8859-1"),"utf-8");
			String _nkContact=(nkContact==null)?null:new String(nkContact.getBytes("iso8859-1"),"utf-8");
			String _nkTel=(nkTel==null)?null:new String(nkTel.getBytes("iso8859-1"),"utf-8");
			String _nkNote=(nkNote==null)?null:new String(nkNote.getBytes("iso8859-1"),"utf-8");
			if(type.equals("add")){
				networkService.addNetwork(_nkName, _nkCode, nkType, nkStatus, _nkStartDate, _nkPrimaryIp, _nkBackupIp, nkPort, nkBackupPort, nkDataUploadInv, nkDataEncryption, region, city, province, _addr, _nkContact, _nkTel, _nkNote);
			}else if(type.equals("edit")){
				networkService.updateNetwork(nkId, _nkName, _nkCode, nkType, nkStatus, _nkStartDate, _nkPrimaryIp, _nkBackupIp, nkPort, nkBackupPort, nkDataUploadInv, nkDataEncryption, region, city, province, _addr, _nkContact, _nkTel, _nkNote);
			}else if(type.equals("delete")){
				networkService.deleteNetwork(nkId);
			}else{
				throw new Exception("can not find type");
			}
		}catch(Exception e){
			return JsonException(e);
		}
		return "{\"success\":true}";
	}
	@RequestMapping(value="/updatenkntatus*",method=RequestMethod.POST)
	public @ResponseBody String updateNkStatus(
			int nkId,int nkStatus 
			){
		try{
			if(nkId==0||nkStatus==0){
				throw new Exception("can not find parameter");
			}
			networkService.updateNkStatus(nkId, nkStatus);
		}catch(Exception e){
			return JsonException(e);
		}
		return "{\"success\":true}";
	}
	private String JsonException(Exception e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    e.printStackTrace(ps);
	    ps.close();
		return "{\"success\":false,\"message\":\""+baos.toString()+"\"}";		
	}	
}
