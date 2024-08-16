package utility;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import loginModule.LoginPage;

    public class ExcelUtility {

    	private static XSSFSheet ExcelWSheet;

		private static XSSFWorkbook ExcelWBook;

		private static XSSFCell Cell;

		private static XSSFRow Row;
		static String CellData;
		 private static final Logger logger = LogManager.getLogger(ExcelUtility.class);

	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

	public static void setExcelFile(String Path,String SheetName) throws Exception {

		try (InputStream ExcelFile = new URL(Path).openStream()) {				

				ExcelWBook = new XSSFWorkbook(ExcelFile);

				ExcelWSheet = ExcelWBook.getSheet(SheetName);

				} catch (Exception e){

					throw (e);

				}

		}

	public static Object[][] getTableArray(String filePath, String sheetName, String sTestCaseName) throws Exception {
		 Object[][] tabArray = null;

		 setExcelFile(filePath,sheetName);
		        XSSFRow headerRow = ExcelWSheet.getRow(0);
		        int totalCols = headerRow.getLastCellNum();
		        

		        int startCol = 1;
		        int ci = 0;

		        // Find the row index for the specified TestCaseName
		        int iTestCaseRow = -1;
		        int totalRows = ExcelUtility.getRowUsed();
		        for (int i = 1; i <= totalRows; i++) {
		            XSSFRow row = ExcelWSheet.getRow(i);
		            String cellValue = getCellData(i, "TestCaseName");
		            if (cellValue.equalsIgnoreCase(sTestCaseName)) {
		                iTestCaseRow = i;
		                break;
		            }
		        }

		        if (iTestCaseRow != -1) {
		        	tabArray = new Object[1][1];

		            Map<String, String> rowData = new HashMap<>();
		            
		           

		            for (int j = startCol; j < totalCols; j++) {
		                XSSFCell headerCell = headerRow.getCell(j);
		              
		                String columnName = headerCell.getStringCellValue();
		            
		                String cellValue = getCellData(iTestCaseRow, columnName);
		                
		                System.out.println("cellValue: "+cellValue);
		                rowData.put(columnName, cellValue);
		               
		            }

		            tabArray[ci] = new Object[]{rowData};
		        }
		        return tabArray;
		    }
	 
	
	public static  Object[][] getTableData(String filePath, String sheetName, int startRow, int endRow) throws Exception {
		  Object[][] tabArray = null;

		  setExcelFile(filePath,sheetName);

		        XSSFRow headerRow = ExcelWSheet.getRow(0);
		        int totalCols = headerRow.getLastCellNum();

		        int numRows = endRow - startRow + 1;

		        tabArray = new Object[numRows][1];

		        for (int i = 0; i < numRows; i++) {
		            Map<String, String> rowData = new HashMap<>();

		            int currentRow = startRow + i;
		            for (int j = 1; j < totalCols; j++) {
		                XSSFCell headerCell = headerRow.getCell(j);
		                String columnName = headerCell.getStringCellValue();
		                String cellValue = getCellData(currentRow, columnName);
		             
		                rowData.put(columnName, cellValue);
		            }

		            tabArray[i][0] = rowData;
		        }

		   
		    return tabArray;
		}
	public static String getCellData(int rowNum, String columnName) throws Exception {
	    try {
	        XSSFRow headerRow = ExcelWSheet.getRow(0);
	        int colNum = -1;
	        for (int i = 0; i <= headerRow.getLastCellNum(); i++) {
	            String headerCellValue = headerRow.getCell(i).getStringCellValue();
	            if (headerCellValue.equalsIgnoreCase(columnName)) {
	                colNum = i;
	                break;
	            }
	        }
	        if (colNum == -1) {
	            System.out.println("Column with name '" + columnName + "' not found.");
	            return "";
	        }

	        Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);

	        if (Cell.getCellType() == CellType.BLANK) {
	            // Return empty value for blank cell
	            return "";
	        }

	        switch (Cell.getCellType()) {
	            case STRING:
	                CellData = Cell.getStringCellValue();
	                break;
	            case NUMERIC:
	                CellData = String.valueOf((long) Cell.getNumericCellValue());
	                break;
	            case BOOLEAN:
	            	 CellData = String.valueOf(Cell.getBooleanCellValue());
	            	 break;
	            case FORMULA:
	                FormulaEvaluator evaluator = ExcelWBook.getCreationHelper().createFormulaEvaluator();
	                CellValue formulaValue = evaluator.evaluate(Cell);
	                if (formulaValue.getCellType() == CellType.NUMERIC) {
	                    // Assuming the formula cell contains a date
	                    double dateValue = formulaValue.getNumberValue();
	                    // Convert the date value to a Java date
	                    Date javaDate = DateUtil.getJavaDate(dateValue);
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	                    CellData = dateFormat.format(javaDate);
	                } else {
	                    CellData = Cell.getStringCellValue();
	                }
	                break;
	            default:
	                break;
	        }

	        return CellData;
	    } catch (Exception e) {
	        return "";
	    }
	}
	

		public static int getRowContains(String sTestCaseName, String colNum) throws Exception{

			int i;

			try {

				int rowCount = ExcelUtility.getRowUsed();

				for ( i=0 ; i<rowCount; i++){

					if  (ExcelUtility.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){

						break;

					}

				}

				return i;

					}catch (Exception e){

				throw(e);

				}

			}

		public static int getRowUsed() throws Exception {

				try{

					int RowCount = ExcelWSheet.getLastRowNum();

					return RowCount;

				}catch (Exception e){

					System.out.println(e.getMessage());

					throw (e);

				}

			}



}
