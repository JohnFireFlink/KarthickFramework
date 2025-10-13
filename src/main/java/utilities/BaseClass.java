package utilities;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import configurations.DefaultValues;
import webPages.DashboardPage;
import webPages.LoginPage;

public class BaseClass {

	public static ExtentSparkReporter html;
	public static ExtentReports extent;
	public ExtentTest test;
	public WebUtils web=new WebUtils(test);
	
	public String BROWSER=System.getProperty("browser",DefaultValues.BROWSER);
	public String URL=System.getProperty("url",DefaultValues.URL);
	
	@BeforeSuite
	public void setUpReport()
	{
		suppressWarnings();
		html = new ExtentSparkReporter("./target/Report.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	@AfterSuite
	public void updateReport() throws IOException
	{
		extent.flush();
	}
	
	@AfterMethod
	public void testOver(ITestResult res) throws InterruptedException
	{
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
	public void openBrowserAndNavigateToLoginPage(String browser) throws Exception
	{
		web.openBrowser(browser);
		web.maximizeBrowser();
		web.navigateToURL(URL);
		web.setImplicitWait(10);
		web.verifyDisplayOf(lp.loginTxt());
	}
	
	//configurations
	public void generateReport()
	{
		test=extent.createTest(Reporter.getCurrentTestResult().getName());
		web=new WebUtils(test);
	}
	public static void suppressWarnings() {
        // Suppress Selenium WebSocket warnings
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);

        // Suppress CDP version warnings
        Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.SEVERE);

        // Suppress other Selenium internal logging if needed
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
    }
	

}
