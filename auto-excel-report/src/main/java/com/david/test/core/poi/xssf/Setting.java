package com.david.test.core.poi.xssf;

public class Setting {

    // Report file
    static String resultExcelFilePath = "TestNGResult.xlsx";
    static String resultExcelFilePathBackup = "newTestNGResult.xlsx";
    static String resultSheetName = "TestResult";

    public static String getResultExcelFilePath() {
        return resultExcelFilePath;
    }

    public static String getResultSheetName() {
        return resultSheetName;
    }

    public static void setResultExcelFilePath(String resultExcelFilePath) {
        Setting.resultExcelFilePath = resultExcelFilePath;
    }

    public static void setResultExcelFilePathBackup() {
        Setting.resultExcelFilePath = resultExcelFilePathBackup;
    }

    public static void setResultSheetName(String resultSheetName) {
        Setting.resultSheetName = resultSheetName;
    }

    // Report row and column

    static int testNameCol = 2;
    static int testResultCol = 3;

    public static void setTestCols(int testNameCol, int testResultCol) {
        Setting.testNameCol = testNameCol;
        Setting.testResultCol = testResultCol;
    }

    public static int getTestNameCol() {
        return testNameCol;
    }

    public static int getTestResultCol() {
        return testResultCol;
    }

    static int testNameStartRow = 12;

    public static int getTestNameStartRow() {
        return testNameStartRow;
    }

    public static void setTestNameStartRow(int testNameStartRow) {
        Setting.testNameStartRow = testNameStartRow;
    }
}
