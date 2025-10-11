package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebUtils {

	public WebDriver driver;
	
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
