package org.landslide.data.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
/**
 * ����ӿڣ��̳������һ���������ع��ܣ�
 * 1.��ѯ������ȡ��
 * 2.���þ���Service�������в�ѯ��
 * 3.���� ModelAndView
 * @author Administrator
 *
 */
public interface IExportExcel {
	public ModelAndView getExcelMV(HttpServletRequest request,
			HttpServletResponse response);
}
