package com.techgenie.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	static XSSFWorkbook wb;
	static XSSFSheet sheet;

	public ExcelUtility(String excelPath, String sheetName) {
		try {
			wb = new XSSFWorkbook(excelPath);
			sheet = wb.getSheet(sheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			e.getCause();
			e.printStackTrace();
		}

	}

	public ExcelUtility(String excelPath) {
		this(excelPath, "Sheet1");
	}

	public static void main(String[] args) {
//		getRowCount();
//		getCellDataString(1,0);
//		getCellDataNumeric(1,1);
	}

	/**
	 * Get No of rows in an sheet
	 */
	public  int getRowCount() {
		int rowCount = 0;
		try {

			 rowCount = sheet.getPhysicalNumberOfRows();
//			System.out.println("No of Rows " + rowCount);
			
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.getMessage();
			exp.getCause();
			exp.printStackTrace();

		}
		return rowCount;
	}

	/**
	 * Get No of cols in an sheet
	 */
	public  int getColCount() {
		int colCount = 0;
		try {

		     colCount = sheet.getRow(0).getPhysicalNumberOfCells();
//			System.out.println("No of Columns " + colCount);
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.getMessage();
			exp.getCause();
			exp.printStackTrace();

		}
		return colCount;
	}	
	/**
	 * Get cell String value
	 * 
	 * @param rowNum , colNum
	 */
	public  String getCellDataString(int rowNum, int colNum) {
		String cellData = null;
		try {
			cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

//			System.out.println(cellData);
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.getMessage();
			exp.getCause();
			exp.printStackTrace();

		}
		return cellData;
	}

	/**
	 * Get Numeric Cell Value
	 * 
	 * @param rowNum
	 * @param colNum
	 */
	public  double getCellDataNumeric(int rowNum, int colNum) {
		double cellData = 0;
		try {
			 cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();

			System.out.println(cellData);
		} catch (Exception exp) {
			// TODO Auto-generated catch block
			exp.getMessage();
			exp.getCause();
			exp.printStackTrace();

		}
		return cellData;
	}

}
