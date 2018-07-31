package com.reward.now.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NowData {

	public static List<Map<String,Object>> NOW_DATA = new ArrayList<>(); 
	public static String EXCLEFILE ;
	
	//读取配置文件
	static {
		Properties properties = new Properties();
		InputStream in = NowData.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		EXCLEFILE = properties.getProperty("exclefile");
	}
	
	public static void refresh() throws Exception {
		 NOW_DATA = new ArrayList<>(); 
		 File file = new File(EXCLEFILE);
         InputStream is = new FileInputStream(file);
         Workbook wb = new XSSFWorkbook(is);
         Sheet sheet = wb.getSheetAt(1);
         
         Integer totalRows = sheet.getPhysicalNumberOfRows();
         /** 循环Excel的行 */
         for (int r = 2; r < totalRows; r++) {
             Row row = sheet.getRow(r);
             if (row == null) {
                 continue;
             }
             
             //期数
             Map<String,Object> rowData = new HashMap<>();
             Cell c1 = row.getCell(0);
             if(c1 != null) {
            	 if(c1.getCellType() == HSSFCell.CELL_TYPE_STRING ){
            		 rowData.put("qs", c1.getStringCellValue());
	             }else {
	            	 rowData.put("qs", (int)c1.getNumericCellValue());
	             }
             }else {
            	 rowData.put("qs", "");
             }
             
			 //和值
             Cell c2 = row.getCell(1);
             if(c2 != null) {
            	 if(c2.getCellType() == HSSFCell.CELL_TYPE_STRING ){
            		 rowData.put("hz", c2.getStringCellValue());
	             }else {
	            	 rowData.put("hz", (int)c2.getNumericCellValue());
	             }
             }else {
            	 rowData.put("hz", "");
             }
             
			 //奇偶和
             List<String> joh = new ArrayList<>();
             Cell c3 = row.getCell(4);
             if(c3 != null && c3.getCellType() == HSSFCell.CELL_TYPE_STRING ){
            	 joh.add(c3.getStringCellValue());
             }else {
            	 joh.add("");
             }
             
             Cell c4 = row.getCell(5);
             if(c4 != null && c4.getCellType() == HSSFCell.CELL_TYPE_STRING ){
            	 joh.add(c4.getStringCellValue());
             }else {
            	 joh.add("");
             }
             
             Cell c5 = row.getCell(6);
             if(c5 != null) {
	             if(c5.getCellType() == HSSFCell.CELL_TYPE_STRING ){
	            	 joh.add(c5.getStringCellValue()+"盘");
	             }else {
	            	 joh.add(""+(int)c5.getNumericCellValue());
	             }
             }else {
            	 joh.add("");
             }
             
             Cell c6 = row.getCell(2);
             if(c6!=null) {
            	 if(c6.getCellType() == HSSFCell.CELL_TYPE_STRING ){
            		 joh.add(c6.getStringCellValue());
	             }else {
	            	 joh.add(""+(int)c6.getNumericCellValue());
	             }
             }else {
            	 joh.add("");
             }
             
             Cell c7 = row.getCell(3);
             if(c7 != null ) {
            	 if(c7.getCellType() == HSSFCell.CELL_TYPE_STRING ){
            		 joh.add(c7.getStringCellValue());
	             }else {
	            	 joh.add(""+(int)c7.getNumericCellValue());
	             }
             }else {
            	 joh.add("");
             }
             rowData.put("joh", joh);
             
			 //上下中
             List<String> sxz = new ArrayList<>();
             Cell c8 = row.getCell(9);
             if(c8 != null && c8.getCellType() == HSSFCell.CELL_TYPE_STRING ){
            	 sxz.add(c8.getStringCellValue());
             }else {
            	 sxz.add("");
             }
             
             Cell c9 = row.getCell(10);
             if(c9 != null && c9.getCellType() == HSSFCell.CELL_TYPE_STRING ){
            	 sxz.add(c9.getStringCellValue());
             }else {
            	 sxz.add("");
             }
             
             Cell c10 = row.getCell(11);
             if(c10 != null){
	             if(c10.getCellType() == HSSFCell.CELL_TYPE_STRING ){
	            	 sxz.add(c10.getStringCellValue());
	             }else {
	            	 sxz.add(""+(int)c10.getNumericCellValue());
	             }
             }else {
            	 sxz.add("");
             }
             
             Cell c11 = row.getCell(7);
             if(c11 != null) {
	             if(c11.getCellType() == HSSFCell.CELL_TYPE_STRING ){
	            	 sxz.add(c11.getStringCellValue());
	             }else {
	            	 sxz.add(""+(int)c11.getNumericCellValue());
	             }
             }else {
            	 sxz.add("");
             }
             
             Cell c12 = row.getCell(8);
             if(c12 != null) {
	             if(c12.getCellType() == HSSFCell.CELL_TYPE_STRING ){
	            	 sxz.add(c12.getStringCellValue());
	             }else {
	            	 sxz.add(""+(int)c12.getNumericCellValue());
	             }
             }else {
            	 sxz.add("");
             }
             
             rowData.put("sxz", sxz);
             	
			
             NOW_DATA.add(rowData);
         }
         wb.close();
         is.close();
	}
	
	
	
	
}

