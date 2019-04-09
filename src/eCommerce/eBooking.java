package eCommerce;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import RetryFailedTests.RetryTransformer;
import Utilities.Helper;

@Test(retryAnalyzer = RetryTransformer.class)
public class eBooking extends Helper {

	/*@Test
	public Object[][] getData() throws Exception
	{
		Object[][] XLData = ReadExcelFile.readXLSXFile("");
		return XLData;
	}*/

	@Test
	public void Port_To_Port_Booking() throws Exception {
		Helper.moduleScreenSelection("Documents_XPath", "ArrivalNotice_XPath");
	}

	public static void eBookingCreation() throws Exception 
	{
		driver.findElement(By.xpath(getPropValues("eCommerce_XPath"))).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.linkText(getPropValues("eBooking_XPath"))).click();
	}

	public static void eBookingGeneralTab() throws Exception 
	{
		WebElement GeneralTAB = driver.findElement(By.xpath(getPropValues("GeneralTabName_XPath")));
		String TABName = GeneralTAB.getText();
		System.out.println("Step 1 - TAB Name : " + TABName);

		if (TABName.equals("General")) {
			// Bootstrab Drop Down selection
			driver.findElement(By.xpath(getPropValues("BKOffice_XPath"))).click();
			Thread.sleep(3000);
			List<WebElement> BookingOfifce = driver.findElements(By.xpath(getPropValues("BKOfficeList_XPath")));
			for (WebElement webElement : BookingOfifce) {
				if (webElement.getAttribute("innerHTML").equals("(IQ) Iraq")) {
					webElement.click();
					Thread.sleep(5000);
					break;
				}
			}

			// Web Tab
			boolean bookingClientInformation = driver.findElement(By.xpath(("//div[@id='winBookingClient']")))
					.isDisplayed();
			if (bookingClientInformation == true) {
				List<WebElement> rows = driver
						.findElements(By.xpath("//*[@id='gridBookingClient']/div[3]/table/tbody/tr"));
				int rowcount = rows.size();

				String BeforeXpath = "//*[@id='gridBookingClient']/div[3]/table/tbody/tr[";
				String AfterXpath = "]/td[2]";

				int i = 1;
				for (i = 1; i <= rowcount; i++) {
					WebElement actualXpath = driver.findElement(By.xpath(BeforeXpath + i + AfterXpath));
					String BookingClient = actualXpath.getText();

					if (BookingClient.equalsIgnoreCase("MSC CORK")) {
						System.out.println("Booking Client Is : " + BookingClient);
						String StartsXpath = "//*[@id='gridBookingClient']/div[3]/table/tbody/tr[";
						String EndXpath = "]/td[1]";
						WebElement CellSeletionXpath = driver.findElement(By.xpath(StartsXpath + i + EndXpath));
						Actions action = new Actions(driver);
						action.moveToElement(CellSeletionXpath).build().perform();
						CellSeletionXpath.click();
						break;
					}
				}
			} else {
				WebElement selectCompany = driver.findElement(By.xpath("//button[@id='imgCompanySelect']"));
				selectCompany.click();
				Helper.tableSelection("//div[@id='winBookingClient']", "MSC CORK");
			}

			System.out.println("Booking Office Has Been Selected Successfully");

			// Phone Number:
			WebElement PhoneNumber = driver.findElement(By.xpath("//input[@name='Reference.Phone']"));
			PhoneNumber.clear();
			PhoneNumber.sendKeys("98654752129865475");

			// Reference_Fax
			driver.findElement(By.id("Reference_Fax")).sendKeys("45786568457865684");

			// Booking Reference:
			driver.findElement(By.id("Reference_BookingRefNumber")).sendKeys("Port to Port");

			// Eamil Reference:
			WebElement Email = driver.findElement(By.id("Reference_Email"));
			Email.clear();
			Email.sendKeys("prakashpandian.rajaram@msc.com");
			Email.sendKeys(Keys.TAB);

			driver.findElement(By.xpath("//span[@aria-owns='Reference_ShipmentTermsId_listbox']")).click();
			List<WebElement> ShippingTerms = driver
					.findElements(By.xpath("//ul[@id='Reference_ShipmentTermsId_listbox']/li"));

			for (WebElement webelement : ShippingTerms) {
				String ShippingNames = webelement.getAttribute("innerHTML");
				System.out.println("Shipping Terms : " + ShippingNames);
				Thread.sleep(5000);
				if (ShippingNames.equalsIgnoreCase(("Port to Port"))) {
					Thread.sleep(5000);
					webelement.click();
					// webelement.sendKeys(Keys.TAB);
					break;
				}
			}
		}

		Thread.sleep(3000);
		// Port Of Load
		WebElement PordOfLoad = driver.findElement(By.xpath("//input[@name='POLId_input']"));
		PordOfLoad.clear();
		PordOfLoad.sendKeys("JEBEL ALI, United Arab Emirates");
		Thread.sleep(3000);
		PordOfLoad.sendKeys(Keys.ENTER);

		// Port Of Discharge
		WebElement PordOfDischarge = driver.findElement(By.xpath("//input[@name='PODId_input']"));
		PordOfDischarge.clear();
		PordOfDischarge.sendKeys("SINGAPORE, Singapore");
		Thread.sleep(3000);
		PordOfDischarge.sendKeys(Keys.ENTER);

		// Customer Comments
		driver.findElement(By.id("textareaId")).sendKeys("Port To Port Booking");

		// Next Button
		driver.findElement(By.id("btnNext")).click();

		System.out.println("General TAB Completed");
		System.out.println();
	}

	public static void containerCargoTab() throws Exception {
		Thread.sleep(3000);
		boolean addContainerPopup = driver.findElement(By.xpath("//div[@id='containerWindow']")).isDisplayed();
		System.out.println("Add Container Popup Shown : " + addContainerPopup);

		if (addContainerPopup == true) {
			driver.findElement(By.xpath("//span[@aria-owns='EquipmentTypes_listbox']")).click();
			Thread.sleep(3000);
			List<WebElement> equipmentTypes = driver.findElements(By.xpath("//ul[@id='EquipmentTypes_listbox']/li"));

			// String ContainerElementVisible =
			// driver.findElement(By.xpath("//ul[@id='EquipmentTypes_listbox']")).getText();

			Thread.sleep(3000);

			for (WebElement webElement : equipmentTypes) {
				String equipmentList = webElement.getText();
				System.out.println("Equipment Types : " + equipmentList);

				if (equipmentList.equalsIgnoreCase(("20' DRY VAN"))) {
					Thread.sleep(3000);
					webElement.click();
					break;
				}
			}
		}

		else if (driver.findElement(By.id("EquipmentTypes_validationMessage")).getText()
				.equals(" Equipment Type is required!")) {
			driver.findElement(By.xpath("//span[@aria-owns='EquipmentTypes_listbox']")).click();
			List<WebElement> equipmentTypes = driver.findElements(By.xpath("//ul[@id='EquipmentTypes_listbox']/li"));

			String ContainerElementVisible = driver.findElement(By.xpath("//ul[@id='EquipmentTypes_listbox']"))
					.getText();
			Thread.sleep(3000);

			for (WebElement webElement : equipmentTypes) {
				String equipmentList = webElement.getText();
				System.out.println("Equipment Types : " + equipmentList);

				if (equipmentList.equalsIgnoreCase(("20' DRY VAN"))) {
					WebDriverWait wait = new WebDriverWait(driver, 100);
					wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(ContainerElementVisible))));
					break;
				}
			}
		}

		WebElement ContainerNumber = driver.findElement(By.id("Number"));
		ContainerNumber.sendKeys("MEDU1876789");
		ContainerNumber.sendKeys(Keys.TAB);

		WebElement contVefrication = driver.findElement(By.id("searchContainer"));
		contVefrication.click();

		String contStatus = driver.findElement(By.id("lblContainerStatus")).getText();
		if (contStatus.equals("Verified Container!")) {
			System.out.println("Conatiner Number Has been Verified");
		} else if (contStatus.equals("Invalid Container!")) {
			WebElement ReEnterContainer = driver.findElement(By.id("Number"));
			ReEnterContainer.sendKeys("FBIU0218367");
			ReEnterContainer.sendKeys(Keys.TAB);
		}

		// Cargo Weight
		driver.findElement(By.name("FirstCargo.WeightPerUnit")).sendKeys("2500");

		// Cargo Weight Type
		driver.findElement(By.xpath("//span[@aria-owns='FirstCargo_IsWeightInLbs_listbox']")).click();
		List<WebElement> cargoWeightType = driver
				.findElements(By.xpath("//ul[@id='FirstCargo_IsWeightInLbs_listbox']/li"));

		for (WebElement webElement : cargoWeightType) {
			if (webElement.getText().equals("Kgs")) {
				Helper.highlightElement(driver, webElement);
				webElement.click();
			}
		}

		driver.findElement(By.name("FirstCargo.Volume")).sendKeys("100");
		driver.findElement(By.id("FirstCargo_NumberOfPackages")).sendKeys("10");

		// Package Type
		driver.findElement(By.xpath("//span[@aria-owns='FirstCargo_PackageType_listbox']")).click();
		List<WebElement> PackageType = driver.findElements(By.xpath("//ul[@id='FirstCargo_PackageType_listbox']/li"));

		for (WebElement webElement : PackageType) {
			if (webElement.getText().equals("BOX")) {
				Helper.highlightElement(driver, webElement);
				webElement.click();
				webElement.sendKeys(Keys.TAB);
			}
		}

		WebElement HarmonizedCode = driver.findElement(By.id("FirstCargo_HarmonizedCode"));
		HarmonizedCode.sendKeys("15");

		List<WebElement> CountOfHSCode = driver
				.findElements(By.xpath("//ul[@id='FirstCargo_HarmonizedCode_listbox']//child::li"));
		int counts = CountOfHSCode.size();

		if (counts > 0) {
			for (int count = 1; count <= CountOfHSCode.size(); count++) {
				String BeforeXpath = "//ul[@id='FirstCargo_HarmonizedCode_listbox']//child::li[";
				String AfterXpath = "]";
				WebElement HSXpath = driver.findElement(By.xpath(BeforeXpath + count + AfterXpath));
				String HSValue = HSXpath.getText();
				if (HSValue.equals(HSValue)) {
					HSXpath.click();
					HSXpath.sendKeys(Keys.TAB);
					Thread.sleep(3000);
				}
			}
		} else {
			System.out.println("Count Should be greater than Zero (> 0) ");
		}

		Thread.sleep(3000);

		boolean saveButton = driver
				.findElement(By.xpath("//button[@type='button' and @value='Next' and @id='addContainerOkBtn']"))
				.isDisplayed();
		WebElement saveCont = driver
				.findElement(By.xpath("//button[@type='button' and @value='Next' and @id='addContainerOkBtn']"));
		if (saveButton == true) {
			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 10);
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * ("//button[@type='button' and @value='Next' and @id='addContainerOkBtn']"))))
			 * ;
			 */
			Thread.sleep(3000);
			saveCont.click();
		}

		WebElement GeneralTAB = driver.findElement(By.xpath("//div[@class='icon-info-outline font24']"));
		String TABName = GeneralTAB.getText();
		System.out.println("Step 2 - TAB Name : " + TABName);
	}
}
