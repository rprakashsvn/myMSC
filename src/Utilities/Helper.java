package Utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Reports.ExtentReport;

public class Helper extends ExtentReport {
	protected static String Browser;
	protected static String configFilePath = System.getProperty("user.dir") + "\\src\\Resources\\Config.properties";
	public static WebDriver driver = null;
	protected static String objectFilePath = System.getProperty("user.dir") + "\\src\\Resources\\Object.properties";
	protected static Properties prop;

	public static String captureScreenshot() {
		String timeStamp = Helper.getDataTime();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String destFile = "./Screenshots\\" + timeStamp + ".png";
		File destination = new File(destFile);
		try {
			FileUtils.copyFile(srcFile, destination);
			System.out.println("Screenshot Is Taken.!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}

	public static void changeBackgroundColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}

	public static void clickByJavaScript(String element) {
		JavascriptExecutor js = (JavascriptExecutor) (driver);
		driver.findElement(By.xpath(element));
		js.executeScript("arguments[0].click();", element);
	}

	public static void clickElementByJS(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);
	}

	public static void explictWaitByXPath(WebDriver driver, String XPath, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
	}

	public static void fileUploadUsingAutoIT(String filePath) {
		try {
			Runtime.getRuntime().exec("filePath");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fileUploadUsingSendKeys(String XPath, String filePath) {
		driver.findElement(By.xpath(XPath)).sendKeys(("filePath"));
	}

	public static String getDataTime() {
		String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		System.out.println(timestamp);
		return timestamp;
	}

	public static String getPageInnerText(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	public static String getPropValues(String key) {
		try {
			readPropertiesFileByKey();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			String PropValue = prop.getProperty(key);
			return PropValue;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	public static String getScreenShot(String screenShotDateTime) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String dest = "./Screenshots\\" + screenShotDateTime + ".png";
		File destFile = new File(dest);
		FileUtils.copyFile(srcFile, destFile);
		return dest;
	}

	public static List<WebElement> getTextAllValueXPath(String XPath) {
		List<WebElement> getAllTextValue = driver.findElements(By.xpath(XPath));
		for (WebElement getNames : getAllTextValue) {
			String TextIs = getNames.getText();
			System.out.println(TextIs);
		}
		return getAllTextValue;
	}

	public static String GetTextValueXpath(String Xpath) {
		String textValue = driver.findElement(By.xpath(Xpath)).getText();
		System.out.println(textValue);
		return textValue;
	}

	public static void highlightElement(WebDriver driver, WebElement highLightElement) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border:'2px solid red';", highLightElement);
	}

	public static boolean isAlertPresent(String value) {
		try {
			driver.switchTo().alert().accept();
			return true;
		} catch (NoAlertPresentException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	public static void listBoxSelectionByXPath(String XPath, String ListXPath, String AgencyName) throws Exception {
		driver.findElement(By.xpath(getPropValues(XPath))).click();
		List<WebElement> agencyList = driver.findElements(By.xpath(getPropValues(ListXPath)));
		System.out.println("List Of Values In Dropdown : " + agencyList.size());

		for (WebElement agency : agencyList) {
			String agencyNames = agency.getText().trim().toUpperCase();
			if (agencyNames.equals(AgencyName)) {
				agency.click();
				Thread.sleep(3000);
				break;
			}
		}
	}

	public static void moduleScreenSelection(String Module, String ScreenName) throws Exception {
		Thread.sleep(7000);
		driver.findElement(By.xpath(getPropValues(Module))).click();
		driver.findElement(By.xpath(getPropValues(ScreenName))).click();
	}

	public static void moduleScreenSelection(String Module, String SubModule, String ScreenName) throws Exception {
		Thread.sleep(7000);
		driver.findElement(By.xpath(getPropValues(Module))).click();
		driver.findElement(By.xpath(getPropValues(SubModule))).click();
		driver.findElement(By.xpath(getPropValues(ScreenName))).click();
	}

	public static WebElement mouseHoverActions(WebElement XpathLocator) throws Exception {
		Actions action = new Actions(driver);
		action.moveToElement(XpathLocator).build().perform();
		Thread.sleep(5000);
		action.click();
		Thread.sleep(3000);
		action.sendKeys(Keys.TAB);
		return XpathLocator;
	}

	public static String randomNumberGeneration() {
		String randomString = RandomStringUtils.randomNumeric(3);
		return randomString;
	}

	public static void randomNumberGeneration(int NumberOfDigits) {
		Random randomNumber = new Random();
		int randomNum = randomNumber.nextInt();
		System.out.println("Random Number : " + randomNum);
	}

	public static String randomStringGeneration() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}

	public static String readPdfFile(String pdfFileName) throws Exception {
		File inputFile = null;
		try {
			inputFile = new File(getPropValues("DownloadFilePath") + "\\" + pdfFileName + ".pdf");
			if (!inputFile.exists()) {
				System.out.println("File Not found in the path");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		FileInputStream loadFile = null;
		try {
			loadFile = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		BufferedInputStream readFile = new BufferedInputStream(loadFile);
		PDDocument document = null;
		try {
			document = PDDocument.load(readFile);
		} catch (InvalidPasswordException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		String pdfText = "";
		try {
			pdfText = new PDFTextStripper().getText(document);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(pdfText);
		readFile.close();
		return pdfText;
	}

	public static void readPropertiesFileByKey() throws Exception {
		prop = new Properties();
		FileInputStream configFile = new FileInputStream(configFilePath);
		FileInputStream objectFile = new FileInputStream(objectFilePath);
		prop.load(configFile);
		prop.load(objectFile);
	}

	public static void scrollBy() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
	}

	public static void scrollIntoView(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static void scrollPageDown(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public static void switchToLastWindow() {
		try {
			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
			}
		} catch (Exception e) {
		}
	}

	public static void switchToParentWindow() {
		try {
			Set<String> winHandles = driver.getWindowHandles();
			for (String wHandle : winHandles) {
				driver.switchTo().window(wHandle);
				break;
			}
		} catch (Exception e) {
		}
	}

	public static void tableSelection(String Xpath, String BookingClientName) {
		// Default Popup Select Company Option
		boolean bookingClientInformation = driver.findElement(By.xpath(("//div[@id='winBookingClient']")))
				.isDisplayed();
		if (bookingClientInformation == true) {
			List<WebElement> rows = driver.findElements(By.xpath(Xpath)); // *[@id='gridBookingClient']/div[3]/table/tbody/tr
			int rowcount = rows.size();
			System.out.println("No Of Rows In Table : " + rowcount);

			String BeforeXpath = "//*[@id='gridBookingClient']/div[3]/table/tbody/tr[";
			String AfterXpath = "]/td[2]";

			int i = 1;
			for (i = 1; i <= rowcount; i++) {
				WebElement actualXpath = driver.findElement(By.xpath(BeforeXpath + i + AfterXpath));
				String BookingClient = actualXpath.getText();

				if (BookingClient.equalsIgnoreCase(BookingClientName)) {
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
			// Select Company Option
			WebElement selectCompany = driver.findElement(By.xpath("//button[@id='imgCompanySelect']"));
			selectCompany.click();

			boolean bookingClientInformations = driver.findElement(By.xpath(("//div[@id='winBookingClient']")))
					.isDisplayed();
			if (bookingClientInformations == true) {
				List<WebElement> rows = driver.findElements(By.xpath(Xpath)); // *[@id='gridBookingClient']/div[3]/table/tbody/tr
				int rowcount = rows.size();
				System.out.println("No Of Rows In Table : " + rowcount);

				String BeforeXpath = "//*[@id='gridBookingClient']/div[3]/table/tbody/tr[";
				String AfterXpath = "]/td[2]";

				int i = 1;
				for (i = 1; i <= rowcount; i++) {
					WebElement actualXpath = driver.findElement(By.xpath(BeforeXpath + i + AfterXpath));
					String BookingClient = actualXpath.getText();

					if (BookingClient.equalsIgnoreCase(BookingClientName)) {
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
			}
		}
	}

	public static void takingScreenShot() throws Exception {
		String screenShotsDateTime = getDataTime();
		TakesScreenshot takescreenshot = ((TakesScreenshot) driver);
		File SourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(SourceFile,
				new File("D:\\Studies\\myMSC New UI\\Screenshots\\" + screenShotsDateTime + ".png"));
	}

	public static void verifyBrokenLinks() throws Exception {
		List<WebElement> links = driver.findElements(By.tagName("a"));

		for (WebElement webElement : links) {
			String pageURL = webElement.getAttribute("href");

			URL myURL = new URL(pageURL);
			HttpURLConnection httpConnect = (HttpURLConnection) myURL.openConnection();
			httpConnect.setConnectTimeout(2000);
			httpConnect.connect();
			int responseCode = httpConnect.getResponseCode();

			if (responseCode >= 200) {
				System.out.println("URL : " + pageURL + ":" + httpConnect.getResponseMessage());
			} else if (responseCode >= 400) {
				System.out.println("URL : " + pageURL + ":" + httpConnect.getResponseMessage());
			} else if (responseCode >= 500) {
				System.out.println("URL : " + pageURL + ":" + httpConnect.getResponseMessage());
			} else {
				System.out.println("URL Not Valid");
			}
		}
	}

	public static void verifyPageTitle() {
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		Assert.assertEquals("Home Page Login", pageTitle);
		System.out.println("Title Matched Test Case Passed");
	}

	@Override
	public String getClassName() {
		String className = this.getClass().getSimpleName();
		return className;
	}

	@SuppressWarnings("unused")
	public void saveAsImage(byte[] imageAsByteArray, String name) {
		InputStream in = new ByteArrayInputStream(imageAsByteArray);
		BufferedImage bImageFromConvert = null;
		File file;
		try {
			file = new File("./Images/" + name);
			bImageFromConvert = ImageIO.read(new ByteArrayInputStream(imageAsByteArray));
			ImageIO.write(bImageFromConvert, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void verifyDropDownValuesASCENDING(String XPath, String liXPath) {
		driver.findElement(By.xpath(getPropValues(XPath))).click();
		List<WebElement> dropDownValues = driver.findElements(By.xpath(getPropValues(liXPath)));

		for (WebElement webElement : dropDownValues) {
			String listValues = webElement.getText();
			List actualList = new ArrayList();
			actualList.add(listValues);
			List tempList = new ArrayList();
			tempList.addAll(actualList);
			Collections.sort(tempList);
			Assert.assertEquals(actualList, tempList);
		}
		System.out.println("Test Passed Value is Ascending order");
	}

	public void writeFile(String filePath, String writeText) throws Exception {
		FileWriter fw = new FileWriter(filePath);
		BufferedWriter bufferedWriter = new BufferedWriter(fw);
		String Text = writeText;
		bufferedWriter.write(Text);
		bufferedWriter.close();
	}
}
