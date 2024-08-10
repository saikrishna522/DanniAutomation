package utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterSuite;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExcelWriter {
    private Workbook workbook;
    private Sheet sheet;

    public ExcelWriter() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("TestResults");
        createHeaderRow();
    }

    private void createHeaderRow() {
        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("TestName");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("MethodName");
        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("TestCasename");
        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Results");
    }

    public void writeResultsToExcel(String testName, String methodName, String parameterValue, String result) {
        Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
        Cell dataCell1 = dataRow.createCell(0);
        dataCell1.setCellValue(testName);

        Cell dataCell2 = dataRow.createCell(1);
        dataCell2.setCellValue(methodName);

        Cell dataCell3 = dataRow.createCell(2);
        dataCell3.setCellValue(parameterValue);

        Cell dataCell4 = dataRow.createCell(3);
        dataCell4.setCellValue(result);
    }


    public void saveToFile(String directoryPath) {
        try {
        	LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = now.format(formatter);
            String fileName = "TestResults_" + timestamp + ".xlsx";
            String filePath = "D:\\Selenium\\Results" + "\\" + fileName;

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();

            System.out.println("Test results written to Excel successfully.");
            System.out.println("File path: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
}
