package org.landslide.mvc.security;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.landslide.data.security.IResourceDao;
import org.landslide.data.security.Resource;
import org.landslide.data.security.User;
import org.landslide.data.security.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SecurityController {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/index**", method = RequestMethod.GET)
	public ModelAndView enterMainFrame() {
		//resourceDao.queryResource()
		//User user=userDao.getUserByName("admin");
		//System.out.println(user.getUserName());
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/frame/mainFrame");
		return mv;
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="error",required=false) String error){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/logins/login");
		return mv;
	}
	/*
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		//ModelAndView mv=new ModelAndView();
		//mv.setViewName("/logins/login");
		return "redirect:login.do";
	}*/
	@RequestMapping(value = "/403",method = RequestMethod.POST)
	public ModelAndView accessDenied(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/logins/403");
		return mv;
	}
	@RequestMapping(value = "/nf",method = RequestMethod.GET)
	public ModelAndView otherPage(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/frame/nf");
		return mv;
	}
}
