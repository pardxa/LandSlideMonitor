package org.landslide.mvc.business;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MonitorThdController {
	@RequestMapping(value="/aset*",method=RequestMethod.GET)
	public ModelAndView MonitorThdBoleHoleView(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/bizthreshold/monitorThdEdit");
		return mv;		
	}
	@RequestMapping(value="/addThd*",method=RequestMethod.GET)
	public ModelAndView AddThresholdView(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/bizthreshold/monitorThdAdd");
		return mv;		
	}	
}
