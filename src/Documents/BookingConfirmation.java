package Documents;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import BaseClass.BaseTest;
import Utilities.Helper;

public class BookingConfirmation extends BaseTest {

	@Test
	public void BookingConfirmReport() throws Exception {
		Logger log = Logger.getLogger(BookingConfirmation.class);
		Helper.moduleScreenSelection("Documents_XPath", "BookingConfirmation_XPath");
		log.info("Module & Screen is Navigated");
		driver.findElement(By.id(getPropValues("Booking_ID"))).sendKeys("EBKGAT00016599");
		log.info("Booking Number is Entered");
		driver.findElement(By.id(getPropValues("RetrieveBK_ID"))).click();
		log.info("Booking is retrieved");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id(getPropValues("DownloadButton_ID"))).click();
		log.info("Booking Pdf file is downloaded");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String textPDF = Helper.readPdfFile("EBKGAT00016599");
		System.out.println(textPDF);
		if (textPDF.contains("EBKGAT00016599")) {
			System.out.println("Pdf File is verifed Test Case Passed");
			log.info("Pdf File is verifed Test Case Passed");
		}
		String timeStamps = getDataTime();
		getScreenShot(timeStamps);
	}
}
