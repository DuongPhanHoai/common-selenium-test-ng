package com.david.pfg.test.data;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;

import com.david.test.core.util.LogUtils;

/** Read the Excel file from resources */
public class ExcelReader {
    static ExcelReader excelReader = new ExcelReader();
    protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ExcelReader.class);
    private static XSSFSheet xssfSheet;
    private static XSSFWorkbook xssfSheets;
    private static XSSFCell xssfCell;

    private static XSSFRow xssfRow;

    public static Object[][] readSheetContent(String resourcePath, String sheetName) {

        String[][] tableContent = null;
        try {
            // Access the required test data sheet
            xssfSheets = new XSSFWorkbook(excelReader.getClass().getResourceAsStream(resourcePath));
            xssfSheet = xssfSheets.getSheet(sheetName);

            int startRow = 1;
            int startCol = 1;
            int tableRow, tableColumn;
            int maxRow = xssfSheet.getLastRowNum();

            // you can write a function as well to get Column count
            int maxCol = 2;
            tableContent = new String[maxRow][maxCol];
            tableRow = 0;
            for (int i = startRow; i <= maxRow; i++, tableRow++) {
                tableColumn = 0;
                for (int j = startCol; j <= maxCol; j++, tableColumn++) {
                    tableContent[tableRow][tableColumn] = getCellData(i, j);
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error(LogUtils.getFullStack(e));
        } catch (IOException e) {
            LOG.error(LogUtils.getFullStack(e));
        }
        return (tableContent);
    }

    public static String getCellData(int RowNum, int ColNum) {
        try {
            xssfCell = xssfSheet.getRow(RowNum).getCell(ColNum);
            int cellType = xssfCell.getCellType();
            if (cellType == 3) {
                return "";
            } else {
                String cellValue = xssfCell.getStringCellValue();
                return cellValue;
            }
        } catch (Exception e) {
            LOG.error(LogUtils.getFullStack(e));
        }
        return null;
    }
}
