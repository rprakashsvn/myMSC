package Vessel;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Utilities.Helper;

public class CutOffByBooking extends BaseTest {

	@Test(priority = 1)
	public void ScreenSelection() throws Exception {
		Helper.moduleScreenSelection("Vessel_XPath", "CutOffByBooking_XPath");
	}

	@Test(priority = 2)
	public void searchByBookingNumber() throws Exception {
		driver.findElement(By.xpath(getPropValues("BookingNumber_XPath"))).clear();
		driver.findElement(By.xpath(getPropValues("BookingNumber_XPath"))).sendKeys("172KL0000021");
		driver.findElement(By.id(getPropValues("Retrieve_ID"))).click();
		Thread.sleep(6000);
	}

	@Test(priority = 3)
	public void searchByBookingNumbers() throws Exception {
		driver.findElement(By.xpath(getPropValues("BookingNumber_XPath"))).clear();
		driver.findElement(By.xpath(getPropValues("BookingNumber_XPath"))).sendKeys("038NYNC5645555555");
		driver.findElement(By.id(getPropValues("Retrieve_ID"))).click();
		Thread.sleep(5000);
	}
	
	
	public void resetButton()
	{
		/*Actions action = new Actions(driver);
		WebElement resetButton = driver.findElement(By.xpath(getPropValues("ResetButton_XPath")));
		action.moveToElement(resetButton).build().perform();
		action.click();*/
		
		WebElement resetButton = driver.findElement(By.xpath(getPropValues("ResetButton_XPath")));
		Point p= resetButton.getLocation();
		Actions actions = new Actions(driver);
		actions.moveToElement(resetButton).moveByOffset(p.x,p.y).click().perform();
	}
}
