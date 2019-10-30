package org.landslide.mvc.business;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.landslide.service.system.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SlideMonitorController {
	@Autowired
	private SysConfigService sysConfService;
	@RequestMapping(value="/rmonitor*",method=RequestMethod.GET)
	public ModelAndView slideMonitorView(HttpServletRequest request ){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/bizmonitor/slideMonitorView");
		long freq=sysConfService.querySysConfig().getRefreshFrequency();
		long realStart=sysConfService.querySysConfig().getRealQueryStart();
		request.setAttribute("freq", freq);
		request.setAttribute("realStart", realStart);
		return mv;
	}
	@RequestMapping(value="/gmonitor*",method=RequestMethod.GET)
	public ModelAndView slideMonitorGrid(HttpServletRequest request ){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/bizmonitor/slideMonitorGrid");
		return mv;
	}
}
