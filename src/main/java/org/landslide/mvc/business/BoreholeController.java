package org.landslide.mvc.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.landslide.data.business.Borehole;
import org.landslide.service.business.BoreholeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoreholeController {
	@Autowired
	private BoreholeService boreholeService;
	@RequestMapping(value="/boreholemanage*",method=RequestMethod.GET)
	public ModelAndView usermanage(int nkId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		request.setAttribute("nkId", nkId);
		mv.setViewName("/bizborehole/boreholeList");
		return mv;
	}
	@RequestMapping(value="/boreholeadd*",method=RequestMethod.GET)
	public ModelAndView boreholeAdd(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/bizborehole/boreholeAdd");
		return mv;
	}
	@RequestMapping(value="/boreholefileadd*",method=RequestMethod.GET)
	public ModelAndView boreholeFileAdd(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/bizborehole/boreholeFileAdd");
		return mv;
	}
	@RequestMapping(value="/boreholeview")
	public ModelAndView boreholeView(int nkId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		request.setAttribute("bhs", boreholeService.queryBoreholes(nkId));
		mv.setViewName("/bizborehole/boreholeListView");
		return mv;
	}
}
