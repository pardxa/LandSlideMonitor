package org.landslide.mvc.business;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NetworkController {
	@RequestMapping(value="/mnetwedit*",method=RequestMethod.GET)
	public ModelAndView networkList(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/biznetwork/networkList");
		return mv;		
	}
	@RequestMapping(value="/mnetwork*",method=RequestMethod.GET)
	public ModelAndView networkListView(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/biznetwork/networkListView");
		return mv;		
	}
	@RequestMapping(value="/networkadd*",method=RequestMethod.GET)
	public ModelAndView networkAdd(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/biznetwork/networkAdd");
		return mv;
	}
}
