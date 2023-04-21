package base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

	public static WebDriver driver;
	public ExtentSparkReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeTest
	public void preRequisite() {
		String reportPath = System.getProperty("user.dir") + "\\reports\\AutomationReport.html";
		reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setEncoding("utf-8");
		reporter.config().setDocumentTitle("KWG Automation");
		reporter.config().setReportName("Regression 12 Report");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test Platform", "Window 11");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Automation Engineer", "Niteen S.");
	}

	@BeforeMethod
	@Parameters(value = { "browser" })
	public void startBrowser(String browser, Method testMethod) {
		logger = extent.createTest(testMethod.getName());
		String projectPath = System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("Chrome")) {
			//We dont need to set this property as Selenium Manager is integral part of latest selenium versions
			//System.setProperty("webdriver.chrome.driver", projectPath + "\\browsers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browsers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browsers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void stopBrowser(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "";
		Markup m;
		if (result.getStatus() == ITestResult.SUCCESS) {
			logText = "Test Case: " + methodName + " Passed.";
			m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logText = "Test Case: " + methodName + " Failed.";
			m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
		} else if (result.getStatus() == ITestResult.SKIP) {
			logText = "Test Case: " + methodName + " Skipped.";
			m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			logger.log(Status.SKIP, m);
		}
		driver.quit();
	}

	@AfterTest
	public void postTestMethod() {
		extent.flush();
	}

}
