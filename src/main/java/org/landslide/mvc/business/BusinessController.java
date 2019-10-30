package org.landslide.mvc.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.landslide.data.system.Menu;
import org.landslide.service.system.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BusinessController {
	@Autowired
	SystemMenuService systemMenuService;
	@RequestMapping(value="/welcome*",method=RequestMethod.GET)
	public ModelAndView welcome(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/frame/welcome");
		return mv;
	}
	@RequestMapping(value="/getMenu",method=RequestMethod.POST)
	public @ResponseBody List<Menu> getMenu(HttpServletRequest request){
		//获取当前用户
		SecurityContextImpl scl=(SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if(scl==null){
			return new ArrayList<Menu>();
		}
		String userName=scl.getAuthentication().getName();
		List<Menu> menus=systemMenuService.getMenu(userName);
		ObjectMapper om=new ObjectMapper();
		try {
			om.writeValue(System.out, menus);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menus;
	}
}
