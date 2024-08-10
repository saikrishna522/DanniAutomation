package utility;

import org.testng.annotations.DataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataReader {
    private static final Logger logger = LogManager.getLogger(DataReader.class);
    private static final ThreadLocal<String> loginTestCaseName = new ThreadLocal<>();
    private static final ThreadLocal<String> ExcelsheetName = new ThreadLocal<>();

    public void setLoginTestCaseName(String testScenario, String sheetName) {
        loginTestCaseName.set(testScenario);
        ExcelsheetName.set(sheetName);
        logger.info("LoginTestCaseName Before Class: " + loginTestCaseName);
    }

    public String getLoginTestCaseName() {
        return loginTestCaseName.get();
    }
    
    public String getsheeteName() {
        return ExcelsheetName.get();
    }

    @DataProvider(name = "DataProvider")
    public Object[][] Authentication() throws Exception {
        String testCaseName = loginTestCaseName.get();
        String SheetName = ExcelsheetName.get();
        logger.info("SheetName: " + SheetName);
        logger.info("LoginTestCaseName: " + testCaseName); // Logging the value from ThreadLocal

      

        String[] splitVal1 = testCaseName.split(":");
        logger.info("Split testCaseName: " + splitVal1.length + " parts");

        Object[][] data;
        
        if (splitVal1.length == 1) {
            String TestCaseName = splitVal1[0];
            data = ExcelUtility.getTableArray(ExcelConstants.File_Path, SheetName, TestCaseName);
        } 
        else if (splitVal1.length == 2) {
            if (splitVal1[1].contains("-")) {
                String[] srow = splitVal1[1].split("-");
                if (srow.length == 2) {
                    int startRow = Integer.parseInt(srow[0]);
                    int endRow = Integer.parseInt(srow[1]);
                    data = ExcelUtility.getTableData(ExcelConstants.File_Path, splitVal1[0], startRow, endRow);
                } else {
                    throw new IllegalArgumentException("Invalid row range specified: " + splitVal1[1]);
                }
            } else {
                int row = Integer.parseInt(splitVal1[1]);
                data = ExcelUtility.getTableData(ExcelConstants.File_Path, splitVal1[0], row, row);
            }
        } else {
            throw new IllegalArgumentException("Invalid test case name format: " + testCaseName);
        }

        return data;
    }
}
