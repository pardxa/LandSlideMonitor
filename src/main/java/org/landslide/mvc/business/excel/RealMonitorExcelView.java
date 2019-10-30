package org.landslide.mvc.business.excel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.landslide.data.business.RealMonitorDataGrid;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class RealMonitorExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//读取数据
		List<RealMonitorDataGrid> rmData = (List<RealMonitorDataGrid>) model
				.get("rmData");
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("数据");
		sheet.setDefaultColumnWidth(30);

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		// create header row
		HSSFRow header = sheet.createRow(0);

		header.createCell(0).setCellValue("记录时间");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("通道编号");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("位移量");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("钻孔名称");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("设备编号");
		header.getCell(4).setCellStyle(style);
		//
		header.createCell(5).setCellValue("设备名称");
		header.getCell(5).setCellStyle(style);
		
		header.createCell(6).setCellValue("SIM卡号");
		header.getCell(6).setCellStyle(style);
		
		header.createCell(7).setCellValue("监测网名称");
		header.getCell(7).setCellStyle(style);
		
		header.createCell(8).setCellValue("监测网编号");
		header.getCell(8).setCellStyle(style);
		// create data rows
		int rowCount = 1;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (RealMonitorDataGrid rmdg : rmData) {
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(df.format(rmdg.getMdRecordTime()));
			aRow.createCell(1).setCellValue(rmdg.getClCode());
			aRow.createCell(2).setCellValue(rmdg.getMdDisplaceAlert());
			aRow.createCell(3).setCellValue(rmdg.getBhName());
			aRow.createCell(4).setCellValue(rmdg.getDvCode());
			aRow.createCell(5).setCellValue(rmdg.getDvName());
			aRow.createCell(6).setCellValue(rmdg.getDvSimCode());
			aRow.createCell(7).setCellValue(rmdg.getNkName());
			aRow.createCell(8).setCellValue(rmdg.getNkCode());
		}
	}

}
