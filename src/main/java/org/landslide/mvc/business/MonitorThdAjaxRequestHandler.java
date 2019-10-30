package org.landslide.mvc.business;

import javax.servlet.http.HttpServletRequest;

import org.landslide.service.business.MonitorThdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonitorThdAjaxRequestHandler {
	@Autowired
	private MonitorThdService monitorThdService;
	@RequestMapping(value="/thdQuery*",method=RequestMethod.POST)
	public @ResponseBody String queryMonitorThd(int bhId){
		return monitorThdService.queryMonitorThd(bhId, 0, 0);
	}
	@RequestMapping(value="/thdAdd*",method=RequestMethod.POST)
	public @ResponseBody String addMonitorThd(HttpServletRequest request){
		String type=request.getParameter("type");
		String bhIdStr=request.getParameter("bhId");
		if(bhIdStr==null){
			return "{\"success\":false}";
		}
		try{
			int bhId=Integer.parseInt(bhIdStr);
			double C1=Double.parseDouble(request.getParameter("C1"));
			double C2=Double.parseDouble(request.getParameter("C2"));
			double C3=Double.parseDouble(request.getParameter("C3"));
			double C4=Double.parseDouble(request.getParameter("C4"));
			if(type!=null&&type.equals("add")){
				monitorThdService.addMonitorThd(bhId, "1", C1);
				monitorThdService.addMonitorThd(bhId, "2", C2);
				monitorThdService.addMonitorThd(bhId, "3", C3);
				monitorThdService.addMonitorThd(bhId, "4", C4);
			}else if(type!=null&&type.equals("update")){
				int thdId1=Integer.parseInt(request.getParameter("thdId1"));
				int thdId2=Integer.parseInt(request.getParameter("thdId2"));
				int thdId3=Integer.parseInt(request.getParameter("thdId3"));
				int thdId4=Integer.parseInt(request.getParameter("thdId4"));
				String clCode1=request.getParameter("clCode1");
				String clCode2=request.getParameter("clCode2");
				String clCode3=request.getParameter("clCode3");
				String clCode4=request.getParameter("clCode4");
				monitorThdService.updateMonitorThd(thdId1, bhId, clCode1, C1);
				monitorThdService.updateMonitorThd(thdId2, bhId, clCode2, C2);
				monitorThdService.updateMonitorThd(thdId3, bhId, clCode3, C3);
				monitorThdService.updateMonitorThd(thdId4, bhId, clCode4, C4);
			}
		}catch(Exception e){
			return "{\"success\":false}";
		}
		return "{\"success\":true}";
	}
	@RequestMapping(value="/thdDel*",method=RequestMethod.POST)
	public @ResponseBody String deleteMonitorThd(int bhId){
		try{
			monitorThdService.deleteMonitorThd(bhId);
		}catch(Exception e){
			return "{\"success\":false}";
		}		
		return "{\"success\":true}";
	}
}
