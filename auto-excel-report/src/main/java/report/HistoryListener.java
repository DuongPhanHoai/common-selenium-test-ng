package report;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.david.test.core.poi.xssf.Setting;
import com.david.test.core.poi.xssf.Sheet;
import com.david.test.core.poi.xssf.WorkBook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HistoryListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        WorkBook.updateTestResult(iTestResult.getName(), "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {}

    @Override
    public void onTestSkipped(ITestResult tr) {
        Sheet.updateTestResult(tr.getName(), "SKIP");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    @Override
    public void onStart(ITestContext iTestContext) {
        // Update Setting
        Setting.setResultExcelFilePath(
                getAttributeFromSuite(iTestContext, "ex.report.path", "TestNGResult.xlsx"));
        Setting.setResultSheetName(
                getAttributeFromSuite(iTestContext, "ex.report.sheet", "TestResult"));
        // Get the run sheet
        WorkBook.startNewResult();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {}

    static String getAttributeFromSuite(
            ITestContext testContext, String attName, String defaultValue) {
        String strResult = defaultValue;
        String strTemp = testContext.getSuite().getParameter(attName);
        if (strTemp != null && strTemp.length() > 0) strResult = strTemp;
        return strResult;
    }
}
