package com.swaglabs.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtiles {
	
	String projectpath = System.getProperty("user.dir");

	public static Object[][] getdata(String excelpath, String sheetname) throws IOException {
		File file1 = new File(excelpath);
		System.out.println("file path is:" + file1);
		FileInputStream fs = new FileInputStream(file1);
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet worksheet = workbook.getSheet(sheetname);
		int rowcount = worksheet.getPhysicalNumberOfRows();
		int colcount = worksheet.getRow(0).getPhysicalNumberOfCells();
		String[][] data = new String[rowcount][colcount];
		System.out.println("rows:" + rowcount);

		for (int i = 0; i < rowcount; i++) {
	        for (int j = 0; j < colcount; j++) {
	            data[i][j] = worksheet.getRow(i).getCell(j).getStringCellValue();
	        }
	    }

		return data;

	}

}
