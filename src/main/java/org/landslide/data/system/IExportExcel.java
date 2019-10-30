package org.landslide.data.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
/**
 * 抽象接口，继承类完成一个具体下载功能：
 * 1.查询参数读取，
 * 2.调用具体Service类对象进行查询，
 * 3.返回 ModelAndView
 * @author Administrator
 *
 */
public interface IExportExcel {
	public ModelAndView getExcelMV(HttpServletRequest request,
			HttpServletResponse response);
}
