package basefunctions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	// File open
	// Workbook
	// Sheets
	// Rows, columns
	FileInputStream fis;
	XSSFWorkbook workbook;

	public XSSFSheet readData(String filePath, String sheetName)  
	{
		try {
/*get the excel sheet file location*/ 
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
/*get the total row count in the excel sheet*/ 
			XSSFSheet sheet = workbook.getSheet(sheetName);
			return sheet;
		}catch (Exception e) {
			// TODO: handle exception
			 return null;
		}		
	}
	public String readDataBasedOnRowAndCell(String filePath, String sheetName, int row, int cell)  
	{
		try {
			
			XSSFSheet sheet = readData(filePath, sheetName);
			XSSFRow xssfRow = sheet.getRow(row);
			Cell cellData = xssfRow.getCell(cell);
			System.out.println(cellData);
			return cellData.getStringCellValue();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Object[][] readAllData(String filePath, String sheetName)  
	{
		try {
			XSSFSheet sheet = readData(filePath, sheetName);
			int rows = sheet.getLastRowNum() + 1;
//			int colums=sheet.getRow(0).getLastCellNum();
			int colums=sheet.getRow(1).getLastCellNum();
			Object data[][] = new Object[rows-1][colums];
			for (int i = 1; i < rows; i++) {
				XSSFRow xssfRow = sheet.getRow(i);
				//int colums = xssfRow.getLastCellNum();
				for (int j = 0; j < colums; j++) {
					Cell cell = xssfRow.getCell(j);
					System.out.print(cell + "    ");
					try {
						data[i-1][j]=cell.getStringCellValue();
					}catch (Exception e) {
						// TODO: handle exception
					}					
				}
				System.out.println();
			}
			return data;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void writeDataBasedOnRowAndCell(String filePath, String sheetName, int row, int cell, String cellDataValue)  
	{
		try {
			XSSFSheet sheet = readData(filePath, sheetName);
			XSSFRow xssfRow = sheet.getRow(row);
			Cell cellData1 =null;
			try {
				cellData1 = xssfRow.createCell(cell);
			}catch (Exception e) {
				cellData1 = xssfRow.getCell(cell);
			}
			cellData1.setCellValue(cellDataValue);
	
			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			workbook.close();
			fos.close();
		//fis.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}