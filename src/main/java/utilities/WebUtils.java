package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class WebUtils {

	public WebDriver driver;
	int delay;
	ExtentTest test;
	public String ENV=System.getProperty("env","local");
	
	public WebUtils(ExtentTest test)
	{
		this.test=test;
	}
	
	public void setDelayBtwnSteps(int seconds)
	{
		delay=seconds*1000;
	}

	public WebElement locateElement(List<By> locators)
	{
		Duration implicit = driver.manage().timeouts().getImplicitWaitTimeout();
		WebElement ele=null;
		for (By  loc : locators) {
			try {
				ele=driver.findElement(loc);
				break;
			} catch (Exception e) {
				driver.manage().timeouts().implicitlyWait(Duration.ZERO);
			}
		}
		driver.manage().timeouts().implicitlyWait(implicit);
		return ele;
	}

	public void openBrowser(String browserName) throws InterruptedException
	{
		ENV=ENV.toLowerCase();
		if (ENV.contains("headless")) {
			ChromeOptions options=new ChromeOptions();
			FirefoxOptions fopt=new FirefoxOptions();
			EdgeOptions eopt=new EdgeOptions();
			browserName=browserName.toLowerCase();
			if (browserName.contains("chrome")) {
				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-extensions");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());
				driver=new ChromeDriver(options);
				Reporter.log("Launched Chrome in headless mode",true);
				test.log(Status.PASS, "Launched Chrome in headless mode");}
			else if (browserName.contains("fire")) {
				fopt.addArguments("--headless");
				fopt.addArguments("--disable-gpu"); // Optional, usually harmless
				fopt.addArguments("--no-sandbox"); // Needed on Linux CI
				fopt.addArguments("--disable-dev-shm-usage"); // Prevent /dev/shm issues
				fopt.addArguments("--profile");
				fopt.addArguments("/tmp/firefox-profile-" + System.currentTimeMillis()); 
				driver=new FirefoxDriver();
				Reporter.log("Launched Firefox in headless mode",true);
				test.log(Status.PASS, "Launched Firefox in headless mode");}
			else if (browserName.contains("edge")) {
				eopt.addArguments("--headless=new");
				eopt.addArguments("--no-sandbox");
				eopt.addArguments("--disable-dev-shm-usage");
				eopt.addArguments("--disable-gpu");
				eopt.addArguments("--disable-extensions");
				eopt.addArguments("--remote-allow-origins=*");
				eopt.addArguments("--user-data-dir=/tmp/edge-" + System.currentTimeMillis());
				driver=new EdgeDriver(eopt); 
				Reporter.log("Launched Edge in headless mode",true);
				test.log(Status.PASS, "Launched Edge in headless mode");}
			else {
				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-extensions");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());
				driver=new ChromeDriver(options);
				Reporter.log("Launched Chrome in headless mode",true);
				test.log(Status.PASS, "Launched Chrome in headless mode");} 
		}  else if(ENV.contains("local"))
		{
			browserName=browserName.toLowerCase();
			if (browserName.contains("chrome")) 
				{driver=new ChromeDriver(); 
				Reporter.log("Launched Chrome browser",true);
				test.log(Status.PASS, "Launched Chrome browser");}
			else if (browserName.contains("fire"))
				{driver=new FirefoxDriver(); 
				Reporter.log("Launched FireFox browser",true);
				test.log(Status.PASS, "Launched FireFox browser");}
			else if (browserName.contains("edge"))
				{driver=new EdgeDriver();
				Reporter.log("Launched Edge browser",true);
				test.log(Status.PASS, "Launched Edge browser");}
			else 
				{driver=new ChromeDriver();
				Reporter.log("Launched Chrome browser",true);
				test.log(Status.PASS, "Launched Chrome browser");}
		}
		Thread.sleep(delay);
	}

	public void maximizeBrowser() throws InterruptedException
	{
		driver.manage().window().maximize();
		Reporter.log("Browser Maximized",true);
		test.log(Status.PASS, "Browser Maximized");
		Thread.sleep(delay);
	}

	public void navigateToURL(String url) throws InterruptedException
	{
		driver.get(url);
		Reporter.log("Navigated to "+url,true);
		test.log(Status.PASS, "Navigated to "+url);
		Thread.sleep(delay);
	}

	public void setImplicitWait(int seconds) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		Reporter.log("Implicit wait set to: "+seconds+" seconds",true);
		test.log(Status.PASS, "Implicit wait set to: "+seconds+" seconds");
		Thread.sleep(delay);
	}

	public void EnterInputInto(List<By> loc, String input) throws InterruptedException
	{
		locateElement(loc).sendKeys(input);
		Reporter.log("Entered "+input,true);
		test.log(Status.PASS, "Entered "+input);
		Thread.sleep(delay);
	}

	public void clickOn(List<By> loc) throws InterruptedException
	{
		locateElement(loc).click();
		Reporter.log("Clicked on",true);
		test.log(Status.PASS, "Clicked on");
		Thread.sleep(delay);
	}

	public void verifyDisplayOf(List<By> loc) throws InterruptedException
	{
		locateElement(loc).isDisplayed();
		Reporter.log("Element display is verified",true);
		test.log(Status.PASS, "Element display is verified");
		Thread.sleep(delay);
	}

	public void closeBrowser() throws InterruptedException
	{
		driver.close();
		Reporter.log("Closed browser",true);
		test.log(Status.PASS, "Closed browser");
		Thread.sleep(delay);
	}

	public void quitBrowser() throws InterruptedException
	{
		driver.quit();
		Reporter.log("Quited browser",true);
		test.log(Status.PASS, "Quited browser");
		Thread.sleep(delay);
	}
}
