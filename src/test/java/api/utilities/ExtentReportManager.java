package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    String repName;
    public ExtentSparkReporter esr; //ffor look and feel of ui
    public ExtentReports er; // to specify the common info

    public ExtentTest et; //for creating entries of test in report

    // this will execute before executing the test cases.
    public void onStart(ITestContext testContext) {
        String timeStap = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStap + ".html";
        esr = new ExtentSparkReporter(".\\reports\\" + repName);
        esr.config().setDocumentTitle("RestAssuredAutomationProject");
        esr.config().setReportName("User API");
        esr.config().setTheme(Theme.DARK);

        // common info
        er = new ExtentReports();
        er.attachReporter(esr);
        er.setSystemInfo("Application", "User API");
        er.setSystemInfo("Operating System", System.getProperty("os.name"));
        er.flush();
    }


    // for creating fail entry if test is fail
    public void onTestFailure(ITestResult result){

        et= er.createTest(result.getName());
        et.createNode(result.getName());
        et.assignCategory(result.getMethod().getGroups());
        et.log(Status.FAIL,"Test Failed");
        et.log(Status.FAIL, result.getThrowable().getMessage());
    }

    // for creating succes entry if test is pass
    public void onTestSuccess(ITestResult result){

        et= er.createTest(result.getName());
        et.createNode(result.getName());
        et.assignCategory(result.getMethod().getGroups());
        et.log(Status.PASS,"Test Passed");
        er.flush();
    }
//if we will nt call flush method then report will not be generated.
    public void onFinish(ITestResult testContext){
        er.flush();
    }

}
