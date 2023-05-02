package com.inetBanking.utilities;

import java.io.*;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;


public class XLUtils {
	
	public static FileInputStream fileip;
	public static FileOutputStream fileop;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell col;
	
	
	public static int getRowCount(String xlFile, String xlSheet) throws IOException {
		fileip = new FileInputStream(xlFile);
		wb=new XSSFWorkbook(fileip);
		sheet = wb.getSheet(xlSheet);
		int rCount = sheet.getLastRowNum();
		wb.close();
		fileip.close();
	
		 return rCount;
	}
	
	public static int getColCount(String xlFile, String xlSheet , int rownum) throws IOException {
		fileip = new FileInputStream(xlFile);
		wb=new XSSFWorkbook(fileip);
		sheet = wb.getSheet(xlSheet);
		row=sheet.getRow(rownum);
		int cCount = row.getLastCellNum();
		wb.close();
		fileip.close();
	
		 return cCount;
	}
	
	public static String getCellData(String xlFile, String xlSheet , int rownum, int colnum) throws IOException {
		fileip = new FileInputStream(xlFile);
		wb=new XSSFWorkbook(fileip);
		sheet = wb.getSheet(xlSheet);
		row=sheet.getRow(rownum);
		col = row.getCell(colnum);
		String data;
		try{
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(col);
			return cellData;
		} 
		catch(Exception e) {
			data="";
		}
		wb.close();
		fileip.close();
		return data;
	}
	
//	public static void setCellData(String xlFile, String xlSheet , int rownum, int colnum, String data) throws IOException {
//		fileip = new FileInputStream(xlFile);
//		wb=new XSSFWorkbook(fileip);
//		sheet = wb.getSheet(xlSheet);
//		row=sheet.getRow(rownum);
//		col = row.createCell(colnum);
//		col.setCellValue(data);
//		fileop= new FileOutputStream(xlFile);
//		wb.write(fileop);
//		wb.close();
//		fileip.close();
//		fileop.close();
//	}
	
	
	

}
