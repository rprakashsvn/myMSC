package Documents;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Utilities.Helper;

public class NonNegotiableBillOfLading extends BaseTest {

	@Test(priority = 1)
	public void SearchBLNumber() throws Exception {
		Helper.moduleScreenSelection("Documents_XPath", "NonNegotiableBillOfLading_XPath");
		Helper.listBoxSelectionByXPath("MSCAgency_XPath", "MSCAgency_List_XPath", "MSC BAHAMAS");
		driver.findElement(By.xpath(getPropValues("BillOfLadingNumber_XPath"))).sendKeys("MSCUNU234398");
		driver.findElement(By.xpath(getPropValues("RetrieveButton_XPath"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String getTexts = driver.findElement(By.xpath(getPropValues("VerifyBLNumebr_XPath"))).getText();
		System.out.println(getTexts);
		driver.findElement(By.id(getPropValues("DownloadPDF_ID"))).click();
		//Runtime.getRuntime().exec("C:\\Program Files\\sw\\FIleDownload.au3");
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/*@Test(priority = 2)
	public void resetButton() throws Exception {
		driver.findElement(By.xpath(getPropValues("BLResetButton_XPath"))).click();
	}

	@Test(priority = 3)
	public void SearchBookingNumber() throws Exception {
		Thread.sleep(3000);
		Helper.listBoxSelectionByXPath("MSCAgency_XPath", "MSCAgency_List_XPath", "MSC BAHAMAS");
		driver.findElement(By.xpath(getPropValues("BLBookingNumber_XPath"))).sendKeys("904BS0006217");
		driver.findElement(By.xpath(getPropValues("RetrieveButton_XPath"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String getTexts = driver.findElement(By.xpath(getPropValues("VerifyBLNumebr_XPath"))).getText();
		System.out.println(getTexts);
		driver.findElement(By.id(getPropValues("DownloadPDF_ID"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}*/
}
