package com.ReadData;

import java.io.IOException;

public class ExcelClass {
	
	
	public void excelCall() throws IOException {
		
	
	String currentDirectory = System.getProperty("user.dir");
	System.out.println(currentDirectory);
	String datafile = currentDirectory + "\\src\\test\\resources\\utils\\Amazone.xlsx";
	System.out.println("Current Directory of test data: " + datafile);
	String sheetname = "LoginTest";
	Object[][] myTestData = ReadExcel.readTestData(datafile, sheetname);
	}
	
}
