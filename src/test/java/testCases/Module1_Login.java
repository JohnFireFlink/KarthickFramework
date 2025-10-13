package testCases;

import org.testng.annotations.Test;

import utilities.BaseClass;

public class Module1_Login extends BaseClass {

	@Test(priority = 0)
	public void Verify_Login_With_Valid_Data() throws Exception
	{
		generateReport();
		web.setDelayBtwnSteps(1);
		
		openBrowserAndNavigateToLoginPage(BROWSER);
		web.EnterInputInto(lp.userNameTF(), "Admin");
		web.EnterInputInto(lp.PasswordTF(), "admin123");
		web.clickOn(lp.loginBtn());
		web.verifyDisplayOf(dbp.dashboardTxt());
		
	}
	
	@Test(priority = 1)
	public void Verify_Login_With_Invalid_Data() throws Exception
	{
		generateReport();
		web.setDelayBtwnSteps(1);
		
		openBrowserAndNavigateToLoginPage(BROWSER);
		web.EnterInputInto(lp.userNameTF(), "Admin");
		web.EnterInputInto(lp.PasswordTF(), "Admin");
		web.clickOn(lp.loginBtn());
		web.verifyDisplayOf(lp.invalidCredentialsMsg());
		
	}
	
	@Test(priority = 2)
	public void Verify_Login_With_NoData() throws Exception
	{
		generateReport();
		web.setDelayBtwnSteps(1);
		
		openBrowserAndNavigateToLoginPage(BROWSER);
		web.clickOn(lp.loginBtn());
		web.verifyDisplayOf(lp.unRequiredMsg());
		web.verifyDisplayOf(lp.pwdRequiredMsg());
	}
	
}
