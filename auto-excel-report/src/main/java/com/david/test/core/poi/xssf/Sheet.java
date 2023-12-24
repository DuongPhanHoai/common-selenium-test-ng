package com.david.test.core.poi.xssf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.david.test.core.util.string.StringUtils;

public class Sheet {

    // STATIC TO INIT INSTANCE OBJECT AND INIT NEW WORKBOOK
    static Sheet instance = null;

    public static Sheet getInstance(XSSFSheet sheet) {
        instance = new Sheet(sheet);
        return instance;
    }

    // STATIC UPDATE RESULT
    static final int MAX_BLANK = 5;
    static SimpleDateFormat COL_START_FORMAT = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");

    public static void startNewResulCol() {
        // Insert column
        if (Objects.nonNull(instance)) {
            instance.insertResultCol(Setting.getTestResultCol());
            instance.setCellValue(
                    COL_START_FORMAT.format(new Date()),
                    Setting.getTestNameStartRow() - 2,
                    Setting.getTestResultCol());
        }
    }

    public static void updateTestResult(String testName, String result) {
        if (Objects.nonNull(instance)) {
            instance.updateTestRes(testName, result);
        }
    }

    // SHEET WORKING
    XSSFSheet sheet;

    public Sheet(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public void insertResultCol(int col) {
        // Find all the current test and add 1 column for row before shift
        int lastRowIndex = sheet.getLastRowNum();
        int blankCount = 0;
        for (int i = Setting.getTestNameStartRow() - 2; i <= lastRowIndex; i++) {
            // shift left all value from col index
            XSSFRow row = sheet.getRow(i);
            if (Objects.nonNull(row)) {
                // Shift all value
                int maxCol = getLastColHasValue(row);
                XSSFCell prevCell = row.createCell(maxCol + 1);
                XSSFCell cell = prevCell;
                for (int j = maxCol; j >= col; j--) {
                    cell = row.getCell(j);
                    if (Objects.nonNull(cell)) prevCell.setCellValue(cell.getStringCellValue());
                    else cell = row.createCell(j);
                    prevCell = cell;
                }
                // Clear the cell value after shift
                cell.setCellValue("");
                blankCount = 0;
            } else {
                blankCount++;
                if (blankCount > MAX_BLANK) break;
                row = sheet.createRow(i);
                row.createCell(col);
            }
        }
    }

    int getLastColHasValue(XSSFRow row) {
        int lastColHasValue = Setting.getTestResultCol();
        if (Objects.nonNull(row)) {
            int blankCount = 0;
            for (int i = Setting.getTestResultCol(); i < row.getLastCellNum(); i++) {
                XSSFCell cell = row.getCell(i);
                if (Objects.isNull(cell) || StringUtils.isEmpty(cell.getStringCellValue())) {
                    blankCount++;
                    if (blankCount > MAX_BLANK) break;
                } else {
                    lastColHasValue = i;
                    blankCount = 0;
                }
            }
        }
        return lastColHasValue;
    }

    public void updateTestRes(String testName, String result) {
        int lastRowIndex = sheet.getLastRowNum();
        int blankCount = 0;
        int last_value_index = Setting.getTestNameStartRow() - 2;
        for (int i = Setting.getTestNameStartRow(); i <= lastRowIndex; i++) {
            String scanName = getCellValue(i, Setting.getTestNameCol());
            if (StringUtils.isEmpty(scanName)) blankCount++;
            else {
                blankCount = 0;
                last_value_index = i;
                if (testName.equalsIgnoreCase(scanName)) {
                    // Check if current result is not set
                    String currentResult = getCellValue(i, Setting.getTestResultCol());
                    if (StringUtils.isEmpty(currentResult)) {
                        setCellValue(result, i, Setting.getTestResultCol());
                        return;
                    }
                }
            }
            if (blankCount > MAX_BLANK) break;
        }

        // Not found matching result > add new column
        setCellValue(testName, last_value_index + 2, Setting.getTestNameCol());
        setCellValue(result, last_value_index + 2, Setting.getTestResultCol());
    }

    public String getCellValue(int row, int col) {
        if (Objects.nonNull(sheet.getRow(row))) {
            if (Objects.nonNull(sheet.getRow(row).getCell(col)))
                return sheet.getRow(row).getCell(col).getStringCellValue();
            else sheet.getRow(row).createCell(col);
        }
        return null;
    }

    public void setCellValue(String value, int row, int col) {
        // Make sure row exists
        XSSFRow rowInstance = sheet.getRow(row);
        if (Objects.isNull(rowInstance)) {
            rowInstance = sheet.createRow(row);
        }
        // Check Cell exists
        XSSFCell cell = rowInstance.getCell(col);
        if (Objects.isNull(cell)) {
            cell = sheet.getRow(row).createCell(col);
        }
        cell.setCellValue(value);
    }
}
