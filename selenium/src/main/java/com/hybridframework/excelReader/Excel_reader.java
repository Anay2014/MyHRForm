package com.hybridframework.excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_reader {

	public static final Logger logger = Logger.getLogger(Excel_reader.class.getName());
	
	public String[][] getExcelData(String excellocation ,String sheetName){
		
		try {
			logger.info("creating excel object:"+excellocation);
			String dataSets[][] = null;
			
			FileInputStream file = new FileInputStream(new File(excellocation));
			
			//create workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			//get first sheet
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			//count number of active rows
			int totalRow = sheet.getLastRowNum() +1;
			//count number of active rows
			int totalColumn = sheet.getRow(0).getLastCellNum();
			
			//Initialize array of rows and column
			dataSets = new String[totalRow-1][totalColumn];
			
			//iterate through one by one 
			Iterator<Row> rowIterator = sheet.iterator();
			 
			int i = 0;
			int t = 0;
			 
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if(i++!= 0) {
					int k = t; 
					t++ ;
					Iterator<Cell> cellIterator = row.cellIterator();
					int j = 0;
					while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
				//	check the cell type and format accordingly
					switch(cell.getCellType()) {
				case Cell. CELL_TYPE_NUMERIC:
				dataSets[k][j++] = cell.getStringCellValue();
				System.out.println(cell.getNumericCellValue());
				break;
				
				case Cell.CELL_TYPE_STRING:
					dataSets[k][j++] = cell.getStringCellValue();
					System.out.println(cell.getStringCellValue());
					break;
					
				case Cell.CELL_TYPE_BOOLEAN:
					dataSets[k][j++] = cell.getStringCellValue();
					System.out.println(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					dataSets[k][j++] = cell.getStringCellValue();
					System.out.println(cell.getStringCellValue());
				    break; 
				
					}
						
					}
					System.out.println(" ");	
					}
				}
			file.close();
	
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	public static void main(String[] args) {
		String excellocation = "\\src\\main\\java\\com\\hybridframework\\data.xlsx";
				String sheetName = "loginTestData";
				Excel_reader excel= new Excel_reader();
		excel.getExcelData(excellocation, sheetName);
		
		
	}
}
