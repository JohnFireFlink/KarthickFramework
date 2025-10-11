package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import utilities.BaseClass;

public class Module1_Login extends BaseClass {

	@Test(priority = 0)
	public void verifyLoginWithValidData()
	{
		openBrowserAndNavigateToLoginPage(BROWSER);
		web.EnterInputInto(lp.userNameTF(), "Admin");
		web.EnterInputInto(lp.PasswordTF(), "admin123");
		web.clickOn(lp.loginBtn());
		web.verifyDisplayOf(dbp.dashboardTxt());
		
	}
	
	@Test(priority = 1)
	public void verifyLoginWithInvalidData()
	{
		openBrowserAndNavigateToLoginPage(BROWSER);
		web.EnterInputInto(lp.userNameTF(), "Admin");
		web.EnterInputInto(lp.PasswordTF(), "Admin");
		web.clickOn(lp.loginBtn());
		web.verifyDisplayOf(lp.invalidCredentialsMsg());
		
	}
	
	@Test(priority = 2)
	public void verifyLoginWithNoData()
	{
		openBrowserAndNavigateToLoginPage(BROWSER);
		web.clickOn(lp.loginBtn());
		web.verifyDisplayOf(lp.unRequiredMsg());
		web.verifyDisplayOf(lp.pwdRequiredMsg());
	}
	
	@AfterMethod
	public void postCondition()
	{
		web.quitBrowser();
	}
}
