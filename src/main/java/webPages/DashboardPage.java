package webPages;

import java.util.ArrayList;

import org.openqa.selenium.By;

public class DashboardPage {

	public ArrayList<Object> dashboardTxt()
	{
		ArrayList<Object> locators=new ArrayList<Object>();
		locators.add("Dashboard Text");
		locators.add(By.xpath("//h6[text()='Dashboard']"));
		
		return locators;
	}
}
