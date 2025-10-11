package utilities;

import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import webPages.DashboardPage;
import webPages.LoginPage;

public class BaseClass {

	public static ExtentSparkReporter html;
	public static ExtentReports extent;
	public ExtentTest test;
	public Map<String, String> map;
	public WebUtils web=new WebUtils(test);
	
	public String BROWSER=System.getProperty("browser","chrome");
	public String URL=System.getProperty("url","https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	
	public void generateReport()
	{
		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		web=new WebUtils(test);
	}
	
	@BeforeSuite
	public void setUpReport()
	{
		html = new ExtentSparkReporter("./target/Report.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	@AfterSuite
	public void updateReport()
	{
		extent.flush();
	}
	
	@AfterMethod
	public void testOver(ITestResult res) throws InterruptedException
	{
		if(res.getStatus()==ITestResult.FAILURE)
		{
			if (web.driver!=null) 
			{
			TakesScreenshot tss=(TakesScreenshot) web.driver;
			String src = tss.getScreenshotAs(OutputType.BASE64);
			test.addScreenCaptureFromBase64String(src);
			}
		}
		if(res.getStatus()==ITestResult.SUCCESS)
		{
			if (web.driver!=null) 
			{
				web.closeBrowser();
			}
			
		}
		
	}
	
	//Pages
	public LoginPage lp=new LoginPage();
	public DashboardPage dbp=new DashboardPage();
	
	//WebLibrary
	public void openBrowserAndNavigateToLoginPage(String browser) throws InterruptedException
	{
		web.openBrowser(browser);
		web.maximizeBrowser();
		web.navigateToURL(URL);
		web.setImplicitWait(10);
		web.verifyDisplayOf(lp.loginTxt());
	}
	
	
}
