package Vessel;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Utilities.Helper;

public class CutOffByPort extends BaseTest {
	@Test
	public void searchByPorts() throws Exception {
		Helper.moduleScreenSelection("Vessel_XPath", "CutOffByPort_XPath");
		driver.findElement(By.id(getPropValues("PortOfLoad_ID"))).sendKeys("NASSAU, Bahamas" + Keys.TAB + Keys.TAB);
		driver.findElement(By.id(getPropValues("PortOfDischarge_ID"))).sendKeys("SINGAPORE, Singapore");
		driver.findElement(By.id(getPropValues("Retrieve_ID"))).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
}
