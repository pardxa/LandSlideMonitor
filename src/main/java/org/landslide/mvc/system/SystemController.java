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
 * ����˵�ҳ����ת
 * @author Administrator
 *
 */
@Controller
public class SystemController {
	/**
	 * �û�����
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
	 * ϵͳ����
	 * @return
	 */
	@RequestMapping(value="/sysconfig*",method=RequestMethod.GET)
	public ModelAndView sysConfig(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/sysConfig");
		return mv;		
	}
	/**
	 * �û���־
	 * @return
	 */
	@RequestMapping(value="/userlog*",method=RequestMethod.GET)
	public ModelAndView userLog(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/admin/userLogList");
		return mv;		
	}
	/**
	 * ����Excel�ļ�
	 * @param beanId IExportExcel�ӿڼ̳����ID
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/exportExcel*",method=RequestMethod.POST)
	public ModelAndView getExportExcel(String beanId,			
			HttpServletRequest request,
			HttpServletResponse response){
		//1.�����ļ�����
		String fileName = request.getParameter("fileName");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName+"_"+df.format(new Date())+ ".xls\"");
		//2.Ϊ���ô�Cntrollerͨ�ã�����ӿڣ�ʹ֮�;���service�ֿ�
		ServletContext scxt=request.getSession().getServletContext();
		WebApplicationContext cxt=WebApplicationContextUtils.getWebApplicationContext(scxt);
		IExportExcel exportExcel= (IExportExcel)cxt.getBean(beanId.trim());
		return exportExcel.getExcelMV(request, response);
	}
}
