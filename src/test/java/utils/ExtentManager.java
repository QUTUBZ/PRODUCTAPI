package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/extent-report.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project Name", "Product API Tests");
            extent.setSystemInfo("Tester", "Qutub Z");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
