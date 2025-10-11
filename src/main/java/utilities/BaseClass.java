package utilities;

import webPages.DashboardPage;
import webPages.LoginPage;

public class BaseClass {

	public WebUtils web=new WebUtils();
	
	public String BROWSER=System.getProperty("browser","chrome");
	public String URL=System.getProperty("url","https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	
	
	//Pages
	public LoginPage lp=new LoginPage();
	public DashboardPage dbp=new DashboardPage();
	
	//WebLibrary
	public void openBrowserAndNavigateToLoginPage(String browser)
	{
		web.openBrowser(browser);
		web.maximizeBrowser();
		web.navigateToURL(URL);
		web.setImplicitWait(10);
		web.verifyDisplayOf(lp.loginTxt());
	}
	
	
}
