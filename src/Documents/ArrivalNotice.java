package Documents;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import BaseClass.BaseTest;
import Utilities.Helper;

public class ArrivalNotice extends BaseTest {

	@Test(priority = 1, description = "Arrival Notice", enabled = false)
	public void arrivalNoticeReport() throws Exception {
		Helper.moduleScreenSelection("Documents_XPath", "ArrivalNotice_XPath");
		String HeaderName = driver.findElement(By.xpath("//h3[text() = 'Arrival Notice']")).getText();
		if (HeaderName.equals("Arrival Notice")) {
			System.out.println("Header Name Is Verified");
			test.log(Status.INFO, "Header Name Is Verified");
		} else {
			System.out.println("Header Name Is Spelling Mistake. Plz check and change it");
			test.log(Status.INFO, "Header Name Is Spelling Mistake. Plz check and change it");
		}
		Helper.listBoxSelectionByXPath("Agency_XPath", "AgencyList_XPath", "MSC MEXICO");
		driver.findElement(By.id(getPropValues("BillOfLadingNumber_ID"))).sendKeys("MEDUBT058261");
		driver.findElement(By.id(getPropValues("RetrieveArrivalNotice_ID"))).click();
		String getTexts = driver.findElement(By.xpath(getPropValues("ErrorMsg_XPath"))).getText();
		System.out.println(getTexts);

		if (getTexts.contains(
				"Either your account is not associated with this bill of lading number or the bill of lading number is invalid.")) {
			System.out.println("BL Number is doesn't associsted with Agency");
			test.log(Status.INFO, "Error");
		} else {
			driver.findElement(By.id(getPropValues("DownloadButton_ID"))).click();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			Helper.readPdfFile("MEDUBT058261");
		}
	}
}
