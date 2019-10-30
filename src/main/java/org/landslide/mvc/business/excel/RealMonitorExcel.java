package org.landslide.mvc.business.excel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.landslide.data.business.RealMonitorDataGrid;
import org.landslide.data.system.IExportExcel;
import org.landslide.service.business.SlideMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

public class RealMonitorExcel implements IExportExcel {
	private SlideMonitorService slideMonitorService;
	public SlideMonitorService getSlideMonitorService() {
		return slideMonitorService;
	}
	public void setSlideMonitorService(SlideMonitorService slideMonitorService) {
		this.slideMonitorService = slideMonitorService;
	}
	//
	@Override
	public ModelAndView getExcelMV(HttpServletRequest request,
			HttpServletResponse response) {
			int bhId=
			(request.getParameter("bhId")==null||request.getParameter("bhId").trim().equals(""))?0:Integer.parseInt(request.getParameter("bhId"));
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("endTime");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<RealMonitorDataGrid> data=new ArrayList<RealMonitorDataGrid>();
			Date st=null;
			Date et=null;
			try {
				if(startTime!=null&&!startTime.trim().equals("")){
					st=df.parse(startTime);
				}
				if(endTime!=null&&!endTime.trim().equals("")){
					df.parse(endTime);
				}
				data = slideMonitorService.queryRealMonitorDataGridExcel(bhId,st, et);
			}catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return new ModelAndView("realMonitorExcelView","rmData",data);
	}

}
