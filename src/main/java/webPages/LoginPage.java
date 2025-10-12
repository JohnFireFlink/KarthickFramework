package webPages;

import java.util.ArrayList;

import org.openqa.selenium.By;

public class LoginPage {

	public ArrayList<Object> loginTxt()
	{
		ArrayList<Object> locators=new ArrayList<Object>();
		locators.add("Login Text");
		locators.add(By.xpath("//h5[text()='Login']"));
		locators.add(By.tagName("h5"));
		locators.add(By.xpath("//*[contains(@class,'orangehrm-login-title')]"));
		
		return locators;
	}
	
	public ArrayList<Object> userNameTF()
	{
		ArrayList<Object> locators=new ArrayList<Object>();
		locators.add("Username Text Field");
		locators.add(By.cssSelector("input[placeholder='Username']"));
		locators.add(By.name("username"));
		locators.add(By.xpath("//label[text()='Username']/../..//input"));
		
		return locators;
	}
	
	public ArrayList<Object> PasswordTF()
	{
		ArrayList<Object> locators=new ArrayList<Object>();
		locators.add("Password Text Field");
		locators.add(By.cssSelector("input[placeholder='Password']"));
		locators.add(By.name("password"));
		locators.add(By.xpath("//label[text()='Password']/../..//input"));
		
		return locators;
	}
	
	public ArrayList<Object> loginBtn()
	{
		ArrayList<Object> locators=new ArrayList<Object>();
		locators.add("Login Button");
		locators.add(By.xpath("//button[normalize-space(.)='Login']"));
		locators.add(By.xpath("//button[contains(@class,'orangehrm-login-button')]"));
		locators.add(By.xpath("//button[@type='submit']"));
		
		return locators;
	}
	
	public ArrayList<Object> invalidCredentialsMsg()
	{
		ArrayList<Object> locators=new ArrayList<Object>();
		locators.add("Invalid Credential Text");
		locators.add(By.xpath("//p[text()='Invalid credentials']"));
		locators.add(By.xpath("//p[contains(@class,'alert-content-text')]"));
		
		return locators;
	}
	
	public ArrayList<Object> unRequiredMsg()
	{
		ArrayList<Object> locators=new ArrayList<Object>();
		locators.add("Username Required Text");
		locators.add(By.xpath("//label[text()='Username']/../..//span[text()='Required']"));

		return locators;
	}
	
	public ArrayList<Object> pwdRequiredMsg()
	{
		ArrayList<Object> locators=new ArrayList<Object>();
		locators.add("Password Required Text");
		locators.add(By.xpath("//label[text()='Password']/../..//span[text()='Required']"));
		
		return locators;
	}
}
