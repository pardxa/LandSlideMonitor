package org.landslide.mvc.business;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.landslide.data.business.Borehole;
import org.landslide.data.system.SysConfig;
import org.landslide.service.business.BoreholeService;
import org.landslide.service.system.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class BoreholeAjaxRequestHandler {
	@Autowired
	private BoreholeService boreholeService;
	@Autowired
	private SysConfigService sysConfigService;
	@RequestMapping(value="/queryborehole",method=RequestMethod.POST)
	public @ResponseBody String queryBorehole(
			int page,//页号，以1开始
			int rows, //每页多少行
			int nkId
			){
		//int nkId=(request.getParameter("nkId")==null||request.getParameter("nkId").trim().equals(""))?0:Integer.parseInt(request.getParameter("nkId"));
		return boreholeService.queryBorehole(page, rows,nkId);
	}
	@RequestMapping(value="/querynwborehole",method=RequestMethod.POST)
	public @ResponseBody String queryBoreholeNetwork(
			HttpServletRequest request
			){
		int nkId=(request.getParameter("nkId")==null||request.getParameter("nkId").trim().equals(""))?0:Integer.parseInt(request.getParameter("nkId"));
		int page=(request.getParameter("page")==null||request.getParameter("page").trim().equals(""))?0:Integer.parseInt(request.getParameter("page"));
		int rows=(request.getParameter("rows")==null||request.getParameter("rows").trim().equals(""))?0:Integer.parseInt(request.getParameter("rows"));
		int bhId=(request.getParameter("bhId")==null||request.getParameter("bhId").trim().equals(""))?0:Integer.parseInt(request.getParameter("bhId"));
		return boreholeService.queryBoreholeNetwork(page, rows,bhId,nkId);
	}
	@RequestMapping(value="/getborehole",method=RequestMethod.POST)
	public @ResponseBody Borehole getBorehole(int bhId){
		return boreholeService.getBorehole(bhId);
	}
	@RequestMapping(value="/addborehole*",method=RequestMethod.POST)
	public @ResponseBody String addBorehole(
				HttpServletRequest request 
			){
		try{
			int bhId=(request.getParameter("bhId")==null||request.getParameter("bhId").trim().equals(""))?0:Integer.parseInt(request.getParameter("bhId"));
			int nkId=(request.getParameter("nkId")==null||request.getParameter("nkId").trim().equals(""))?0:Integer.parseInt(request.getParameter("nkId"));
			String bhName=request.getParameter("bhName");
			String _bhName=(bhName==null)?null:new String(bhName.getBytes("iso8859-1"),"utf-8");
			String dvCode=request.getParameter("dvCode");
			String _dvCode=(dvCode==null)?null:new String(dvCode.getBytes("iso8859-1"),"utf-8");
			String dvName=request.getParameter("dvName");
			String _dvName=(dvName==null)?null:new String(dvName.getBytes("iso8859-1"),"utf-8");
			String dvSimCode=request.getParameter("dvSimCode");
			String _dvSimCode=(dvSimCode==null)?null:new String(dvSimCode.getBytes("iso8859-1"),"utf-8");
			String type=request.getParameter("type");
			double bhIntCoordDe=(request.getParameter("bhIntCoordDe")==null||request.getParameter("bhIntCoordDe").trim().equals(""))?0:Double.parseDouble(request.getParameter("bhIntCoordDe"));
			double bhIntCoordDn=(request.getParameter("bhIntCoordDn")==null||request.getParameter("bhIntCoordDn").trim().equals(""))?0:Double.parseDouble(request.getParameter("bhIntCoordDn"));
			double bhIntCoordDu=(request.getParameter("bhIntCoordDu")==null||request.getParameter("bhIntCoordDu").trim().equals(""))?0:Double.parseDouble(request.getParameter("bhIntCoordDu"));
			String bhContact=request.getParameter("bhContact");
			String _bhContact=(bhContact==null)?null:new String(bhContact.getBytes("iso8859-1"),"utf-8");
			String bhTel=request.getParameter("bhTel");
			String _bhTel=(bhTel==null)?null:new String(bhTel.getBytes("iso8859-1"),"utf-8");
			String bhNote=request.getParameter("bhNote");
			String _bhNote=(bhNote==null)?null:new String(bhNote.getBytes("iso8859-1"),"utf-8");
			double bhLg=(request.getParameter("bhLg")==null||request.getParameter("bhLg").trim().equals(""))?0:Double.parseDouble(request.getParameter("bhLg"));
			double bhLt=(request.getParameter("bhLt")==null||request.getParameter("bhLt").trim().equals(""))?0:Double.parseDouble(request.getParameter("bhLt"));
			int bhDisplaceRange=(request.getParameter("bhDisplaceRange")==null||request.getParameter("bhDisplaceRange").trim().equals(""))?0:Integer.parseInt(request.getParameter("bhDisplaceRange"));
			if(type.equals("add")){
				boreholeService.addBorehole(nkId, _bhName, _dvCode, _dvName, _dvSimCode, bhIntCoordDe, bhIntCoordDn, bhIntCoordDu, _bhContact, _bhTel, _bhNote, bhLg, bhLt, bhDisplaceRange);
			}else if(type.equals("edit")){
				boreholeService.updateBorehole(bhId, _bhName, _dvCode, _dvName, _dvSimCode, bhIntCoordDe, bhIntCoordDn, bhIntCoordDu, _bhContact, _bhTel, _bhNote, bhLg, bhLt, bhDisplaceRange);
			}else if(type.equals("delete")){
				boreholeService.deleteBorehole(bhId);
			}else{
				throw new Exception("can not find type");
			}
		}catch(Exception e){
			return JsonException(e);
		}
		return "{\"success\":true}";
	}
	@RequestMapping(value="/uploadboreholefile*",method=RequestMethod.POST)
	public @ResponseBody String uploadboreholeFile(@RequestParam("bhId")int bhId,
			@RequestParam("bhPhoto")MultipartFile bhPhoto){
		if(!bhPhoto.isEmpty()){
			try{
				BufferedImage image = ImageIO.read(bhPhoto.getInputStream());
				int bhPhotoHeight=image.getHeight();
				int bhPhotoWidth=image.getWidth();
				SysConfig sysconf=sysConfigService.querySysConfig();
				if(bhPhotoHeight<=sysconf.getBhSitePhotoH()
						&&bhPhotoWidth<=sysconf.getBhSitePhotoW())
				{
					boreholeService.uploadBhPhoto(bhId, bhPhoto,bhPhotoHeight,bhPhotoWidth);
				}else{
					return "{\"success\":false,\"height\":\""+sysconf.getBhSitePhotoH()+"\""
							+ ",\"width\":\""+sysconf.getBhSitePhotoW()+"\"}";
				}
				
			}catch(Exception e){
				return JsonException(e);
			}
			return "{\"success\":true}";
		}
		return "{\"success\":false,\"message\":\"UploadFile is Empty\"}";
	}
	private String JsonException(Exception e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    e.printStackTrace(ps);
	    ps.close();
		return "{\"success\":false,\"message\":\""+baos.toString()+"\"}";		
	}
}
