package webPages;

import java.util.ArrayList;

import org.openqa.selenium.By;

public class DashboardPage {

	public ArrayList<By> dashboardTxt()
	{
		ArrayList<By> locators=new ArrayList<By>();
		locators.add(By.xpath("//h6[text()='Dashboard']"));
		
		return locators;
	}
}
