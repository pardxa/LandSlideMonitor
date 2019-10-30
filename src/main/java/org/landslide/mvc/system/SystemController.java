package org.landslide.mvc.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.landslide.data.system.IExportExcel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
/**
 * 负责菜单页面跳转
 * @author Administrator
 *
 */
@Controller
public class SystemController {
	/**
	 * 用户管理
	 * @return
	 */
	@RequestMapping(value="/usermanage*",method=RequestMethod.GET)
	public ModelAndView usermanage(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/userManagement");
		return mv;
	}
	@RequestMapping(value="/useradd*",method=RequestMethod.GET)
	public ModelAndView useradd(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/userAdd");
		return mv;
	}
	@RequestMapping(value="/roleAdd*",method=RequestMethod.GET)
	public ModelAndView roleadd(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/roleAdd");
		return mv;
	}
	@RequestMapping(value="/changpwd*",method=RequestMethod.GET)
	public ModelAndView changePwd(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/changPassword");
		return mv;		
	}
	/**
	 * 系统配置
	 * @return
	 */
	@RequestMapping(value="/sysconfig*",method=RequestMethod.GET)
	public ModelAndView sysConfig(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/sysConfig");
		return mv;		
	}
	/**
	 * 用户日志
	 * @return
	 */
	@RequestMapping(value="/userlog*",method=RequestMethod.GET)
	public ModelAndView userLog(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/userLogList");
		return mv;		
	}
	/**
	 * 下载Excel文件
	 * @param beanId IExportExcel接口继承类的ID
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/exportExcel*",method=RequestMethod.POST)
	public ModelAndView getExportExcel(String beanId,			
			HttpServletRequest request,
			HttpServletResponse response){
		//1.设置文件名称
		String fileName = request.getParameter("fileName");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName+"_"+df.format(new Date())+ ".xls\"");
		//2.为了让此Cntroller通用，定义接口，使之和具体service分开
		ServletContext scxt=request.getSession().getServletContext();
		WebApplicationContext cxt=WebApplicationContextUtils.getWebApplicationContext(scxt);
		IExportExcel exportExcel= (IExportExcel)cxt.getBean(beanId.trim());
		return exportExcel.getExcelMV(request, response);
	}
}
