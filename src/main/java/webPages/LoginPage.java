package webPages;

import java.util.ArrayList;

import org.openqa.selenium.By;

public class LoginPage {

	public ArrayList<By> loginTxt()
	{
		ArrayList<By> locators=new ArrayList<By>();
		locators.add(By.xpath("//h5[text()='Login']"));
		locators.add(By.tagName("h5"));
		locators.add(By.xpath("//*[contains(@class,'orangehrm-login-title')]"));
		
		return locators;
	}
	
	public ArrayList<By> userNameTF()
	{
		ArrayList<By> locators=new ArrayList<By>();
		locators.add(By.cssSelector("input[placeholder='Username']"));
		locators.add(By.name("username"));
		locators.add(By.xpath("//label[text()='Username']/../..//input"));
		
		return locators;
	}
	
	public ArrayList<By> PasswordTF()
	{
		ArrayList<By> locators=new ArrayList<By>();
		locators.add(By.cssSelector("input[placeholder='Password']"));
		locators.add(By.name("password"));
		locators.add(By.xpath("//label[text()='Password']/../..//input"));
		
		return locators;
	}
	
	public ArrayList<By> loginBtn()
	{
		ArrayList<By> locators=new ArrayList<By>();
		locators.add(By.xpath("//button[normalize-space(.)='Login']"));
		locators.add(By.xpath("//button[contains(@class,'orangehrm-login-button')]"));
		locators.add(By.xpath("//button[@type='submit']"));
		
		return locators;
	}
	
	public ArrayList<By> invalidCredentialsMsg()
	{
		ArrayList<By> locators=new ArrayList<By>();
		locators.add(By.xpath("//p[text()='Invalid credentials']"));
		locators.add(By.xpath("//p[contains(@class,'alert-content-text')]"));
		
		return locators;
	}
	
	public ArrayList<By> unRequiredMsg()
	{
		ArrayList<By> locators=new ArrayList<By>();
		locators.add(By.xpath("//label[text()='Username']/../..//span[text()='Required']"));

		return locators;
	}
	
	public ArrayList<By> pwdRequiredMsg()
	{
		ArrayList<By> locators=new ArrayList<By>();
		locators.add(By.xpath("//label[text()='Password']/../..//span[text()='Required']"));
		
		return locators;
	}
}
