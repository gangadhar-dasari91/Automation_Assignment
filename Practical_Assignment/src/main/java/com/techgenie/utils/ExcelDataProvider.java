package com.techgenie.utils;

import com.techgenie.utils.ExcelUtility;

public class ExcelDataProvider {
public  Object[][]  testData(String excelPath, String sheetName) {
		
		ExcelUtility excel = new ExcelUtility(excelPath);
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		
		Object data[][] = new Object[rowCount-1][colCount];
		for(int i =1;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				String cellData = excel.getCellDataString(i, j);
//				System.out.print(cellData + "|");
				data[i-1][j] = cellData;
			}
//			System.out.println();
		}
		return data;
	}
}
