package com.david.test.core.poi.xssf;

import java.io.*;
import java.util.Objects;

import org.apache.poi.EmptyFileException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkBook {
    XSSFWorkbook workbook;
    Sheet resultSheet;

    public WorkBook(XSSFWorkbook workbook) {
        this.workbook = workbook;
        // Check is result sheet exists
        XSSFSheet resultSheet = workbook.getSheet(Setting.getResultSheetName());
        if (Objects.isNull(resultSheet)) {
            // Create new sheet
            resultSheet = workbook.createSheet(Setting.getResultSheetName());
        }
        this.resultSheet = Sheet.getInstance(resultSheet);
    }

    public Sheet resultSheet() {
        return resultSheet;
    }

    public void save() {
        try {
            FileOutputStream out =
                    new FileOutputStream(new File(Setting.getResultExcelFilePath()), false);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ******** STATIC TO INIT INSTANCE OBJECT AND INIT NEW WORKBOOK ********
    static WorkBook instance = null;

    public static WorkBook createInstance() {
        File wbFile = new File(Setting.getResultExcelFilePath());
        if (wbFile.exists() && !wbFile.isDirectory()) {
            boolean openExitingFailed = true;
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(wbFile));
                // Get Result Sheet or init if need
                instance = new WorkBook(workbook);
                openExitingFailed = false;
            } catch (IOException e) {
                log.info(
                        "Cannot open Excel XSSF {}, error: {}",
                        Setting.getResultExcelFilePath(),
                        e);
            } catch (EmptyFileException e) {
                log.info(
                        "Cannot open Excel XSSF {}, error empty file: {}",
                        Setting.getResultExcelFilePath(),
                        e);
            }
            if (openExitingFailed) { // Cannot open, create new
                Setting.setResultExcelFilePathBackup();
                XSSFWorkbook newWb = new XSSFWorkbook();
                // Create new header row
                instance = new WorkBook(newWb);
            }
        } else {
            XSSFWorkbook newWb = new XSSFWorkbook();
            // Create new header row
            instance = new WorkBook(newWb);
        }
        return instance;
    }

    // STATIC REPORT PROCESS
    public static void startNewResult() {
        if (Objects.nonNull(createInstance())) {
            instance.resultSheet().startNewResulCol();
            instance.save();
        }
    }

    public static void updateTestResult(String testName, String result) {
        if (Objects.nonNull(createInstance())) {
            instance.resultSheet().updateTestRes(testName, result);
            instance.save();
        }
    }
}
