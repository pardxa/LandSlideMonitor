package org.landslide.mvc.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;

import org.landslide.data.business.Borehole;
import org.landslide.data.business.Network;
import org.landslide.service.business.BoreholeService;
import org.landslide.service.business.SlideMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SlideMonitorAjaxRequestHandler {
	@Autowired
	private SlideMonitorService slideMonitorService;
	@Autowired
	private BoreholeService boreholeService;
	@RequestMapping (value="/comboquerynetworklist",method=RequestMethod.POST)
	public @ResponseBody List<Network> queryNetworkList(){
		return slideMonitorService.queryNetworkList();
	}
	@RequestMapping (value="/comboqueryboreholeslist",method=RequestMethod.POST)
	public @ResponseBody List<Borehole> queryBoreholes(int nkId){
		return slideMonitorService.queryBoreholes(nkId);
	}
	@RequestMapping(value="/queryrealmonitordata",method=RequestMethod.POST)
	public @ResponseBody String queryRealMonitorData(int bhId,String startTime,String endTime,long realStart,
			HttpServletResponse response){
		//根据选择ID获取钻孔
		Borehole bh=boreholeService.getBorehole(bhId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result="";
		String data="";
		try {
			Date sDate=null;
			Date eDate=null;
			//查询
			if(!startTime.trim().equals("")&&!endTime.trim().equals("")){
				data = slideMonitorService.queryRealMonitorData(bh.getDvSimCode(), 
						df.parse(startTime), df.parse(endTime));
				sDate=df.parse(startTime);
				eDate=df.parse(endTime);
			}else{
				//实时
				//计算起止时间
				Date current=new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(current);
				long mills=calendar.getTimeInMillis();
				mills=mills-realStart;
				Date start=new Date(mills);
				data = slideMonitorService.queryRealMonitorData(bh.getDvSimCode(), 
						start, null);
				sDate=start;
				eDate=current;				
			}
			String timeformat="any";
			if(isSameDay(sDate,eDate)){				
				timeformat="day";				
			}else if(isSameMonth(sDate,eDate)){
				timeformat="month";	
			}else if(isSameYear(sDate,eDate)){
				timeformat="year";	
			}
			result="{\"result\":"+data+",\"timeformat\":\""+timeformat+"\"}";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value="/queryrealmonitorgrid",method=RequestMethod.POST)
	public @ResponseBody String queryRealMonitorDataGrid(
			int page,//页号，以1开始
			int rows, //每页多少行
			int bhId,
			String startTime,
			String endTime,
			HttpServletResponse response){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data="";
		Date st=null;
		Date et=null;
		try {
			if(startTime!=null&&!startTime.trim().equals("")){
				st=df.parse(startTime);
			}
			if(endTime!=null&&!endTime.trim().equals("")){
				df.parse(endTime);
			}
			data = slideMonitorService.queryRealMonitorDataGrid(
						page,rows,bhId,st, et);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}	
	private boolean isSameDay(Date d1,Date d2){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(d1).equals(fmt.format(d2));
	}
	private boolean isSameMonth(Date d1,Date d2){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
		return fmt.format(d1).equals(fmt.format(d2));
	}
	private boolean isSameYear(Date d1,Date d2){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
		return fmt.format(d1).equals(fmt.format(d2));
	}
}
