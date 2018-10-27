package com.kingdee.abc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class ExecelReader {
	
	
	@Value("classpath:web-info.xls")
	private Resource resource;
	
	
	
	@PostConstruct
	private void init() throws Exception {
		InputStream inputStream = resource.getInputStream();
		File tmpFile = File.createTempFile("web-info", ".xls");
		FileUtils.copyInputStreamToFile(inputStream, tmpFile);
		IOUtils.closeQuietly(inputStream);
		HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(tmpFile));
	    HSSFSheet sheet = book.getSheetAt(0);

	    for(int i=0; i<=sheet.getLastRowNum(); i++) {
	        HSSFRow row = sheet.getRow(i);
	        String name = row.getCell(0).getStringCellValue(); //名称
	        double number = row.getCell(1).getNumericCellValue(); //url

	    }
		this.createExcel("F:\\excel.xls");
	}
	
	public void createExcel(String excelName) throws Exception {
		 
		//创建工作簿
		XSSFWorkbook wb = new XSSFWorkbook();
		//创建一个sheet
		XSSFSheet sheet = wb.createSheet();
 
		// 创建单元格样式
		XSSFCellStyle style =  wb.createCellStyle();	
		style.setFillForegroundColor((short)4); //设置要添加表格北京颜色
		style.setFillPattern(FillPatternType.NO_FILL); //solid 填充
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER); //文字水平居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//文字垂直居中
		style.setBorderBottom(BorderStyle.THIN); //底边框加黑
		style.setBorderLeft(BorderStyle.THIN);  //左边框加黑
		style.setBorderRight(BorderStyle.THIN); // 有边框加黑
		style.setBorderTop(BorderStyle.THIN); //上边框加黑
		//为单元格添加背景样式
		for (int i = 0; i < 6; i++) { //需要6行表格
		    Row row = sheet.createRow(i); //创建行
			for (int j = 0; j < 6; j++) {//需要6列
				row.createCell(j).setCellStyle(style);
			}
		}
		
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格，cellRangAddress四个参数，第一个起始行，第二终止行，第三个起始列，第四个终止列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 5));
		
		//tian入数据
		XSSFRow row = sheet.getRow(0); //获取第一行
		row.getCell(1).setCellValue("2018期末考试"); //在第一行中创建一个单元格并赋值
		XSSFRow row1 = sheet.getRow(1); //获取第二行，为每一列添加字段
		row1.getCell(1).setCellValue("语文");
		row1.getCell(2).setCellValue("数学");
		row1.getCell(3).setCellValue("英语");
		row1.getCell(4).setCellValue("物理");
		row1.getCell(5).setCellValue("化学");
		XSSFRow row2 = sheet.getRow(2); //获取第三行
		row2.getCell(0).setCellValue("张三");
		XSSFRow row3 = sheet.getRow(3); //获取第四行
		row3.getCell(0).setCellValue("张三");
		XSSFRow row4 = sheet.getRow(4); //获取第五行
		row4.getCell(0).setCellValue("张三");
		XSSFRow row5 = sheet.getRow(5); //获取第五行
		row5.getCell(0).setCellValue("张三");
		//将数据写入文件
		FileOutputStream out = new FileOutputStream(excelName);
		wb.write(out);
 
	}


}
