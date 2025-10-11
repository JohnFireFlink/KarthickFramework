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

public class WebUtils {

	public WebDriver driver;
	public String ENV=System.getProperty("env");

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

	public void openBrowser(String browserName)
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
				driver=new ChromeDriver(options); }
			else if (browserName.contains("fire")) {
				fopt.addArguments("--headless");
				fopt.addArguments("--disable-gpu"); // Optional, usually harmless
				fopt.addArguments("--no-sandbox"); // Needed on Linux CI
				fopt.addArguments("--disable-dev-shm-usage"); // Prevent /dev/shm issues
				fopt.addArguments("--profile");
				fopt.addArguments("/tmp/firefox-profile-" + System.currentTimeMillis()); 
				driver=new FirefoxDriver(); }
			else if (browserName.contains("edge")) {
				eopt.addArguments("--headless=new");
				eopt.addArguments("--no-sandbox");
				eopt.addArguments("--disable-dev-shm-usage");
				eopt.addArguments("--disable-gpu");
				eopt.addArguments("--disable-extensions");
				eopt.addArguments("--remote-allow-origins=*");
				eopt.addArguments("--user-data-dir=/tmp/edge-" + System.currentTimeMillis());
				driver=new EdgeDriver(eopt); }
			else {
				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-extensions");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());
				driver=new ChromeDriver(options); } 
		}  else if(ENV.contains("local"))
		{
			browserName=browserName.toLowerCase();
			if (browserName.contains("chrome")) 
				driver=new ChromeDriver(); 
			else if (browserName.contains("fire"))
				driver=new FirefoxDriver(); 
			else if (browserName.contains("edge"))
				driver=new EdgeDriver();
			else 
				driver=new ChromeDriver();
		}

	}

	public void maximizeBrowser()
	{
		driver.manage().window().maximize();
	}

	public void navigateToURL(String url)
	{
		driver.get(url);
	}

	public void setImplicitWait(int seconds)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	public void EnterInputInto(List<By> loc, String input)
	{
		locateElement(loc).sendKeys(input);
	}

	public void clickOn(List<By> loc)
	{
		locateElement(loc).click();
	}

	public void verifyDisplayOf(List<By> loc)
	{
		locateElement(loc).isDisplayed();
	}

	public void closeBrowser()
	{
		driver.close();
	}

	public void quitBrowser()
	{
		driver.quit();
	}
}
