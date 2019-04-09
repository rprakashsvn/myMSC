package BaseClass;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Utilities.Helper;
import atu.testrecorder.ATUTestRecorder;

public class BaseTest extends Helper {

	public static Logger log;
	public static ATUTestRecorder recorder;

	public static boolean AcceptCookiePolicy() {
		try {
			boolean AcceptCookie = driver.findElement(By.xpath(getPropValues("Accept_Cookies_Xpath"))).isDisplayed();
			if (AcceptCookie == true) {
				WebElement AcceptCookies = driver.findElement(By.xpath(getPropValues("Accept_Cookies_Xpath")));
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Actions action = new Actions(driver);
				action.moveToElement(AcceptCookies).build().perform();
				AcceptCookies.click();
				return true;
			} else {
				return false;
			}
		} catch (NoSuchWindowException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public static WebDriver launchBrowser() throws Exception {

		// Recording Videos for Scripts
		String timeStamp = getDataTime();
		recorder = new ATUTestRecorder("./Videos\\", timeStamp, false);
		recorder.start();

		// Log4j Configurations
		log = Logger.getLogger("myMSC");
		PropertyConfigurator.configure("Log4j.properties");

		Browser = getPropValues("BrowserName");
		System.out.println("Test Case Executing Browser Name Is : " + Browser);

		if (prop.getProperty("BrowserName").equalsIgnoreCase("Chrome")) 
		{
			System.out.println("Chrome Browser Launched");
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.directory_upgrade", true);
			prefs.put("safebrowsing.enabled", true);
			prefs.put("download.default_directory", getPropValues("DownloadFilePath"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("disalbe-geolocation");
			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", getPropValues("ChromeDriver"));
			driver = new ChromeDriver(cap);
		} 
		else if (prop.getProperty("BrowserName").equalsIgnoreCase("FireFox")) 
		{
			System.out.println("Firefox Browser Launched");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			profile.setPreference("browser.download.dir", getPropValues("DownloadFilePath"));
			profile.setPreference("dom.webnotifications.enabled", false);
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);
			System.setProperty("webdriver.gecko.driver", getPropValues("FirefoxDriver"));
		} 
		else if (prop.getProperty("BrowserName").equalsIgnoreCase("IE")) 
		{
			System.out.println("IE Browser Launched");
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			System.setProperty("webdriver.ie.driver", getPropValues("IEDriver"));
			driver = new InternetExplorerDriver(ieOptions);
		} 
		else if (prop.getProperty("BrowserName").equalsIgnoreCase("Edge")) 
		{
			System.out.println("IE Browser Launched");
			System.setProperty("webdriver.ie.driver", getPropValues("EdgeDriver"));
			driver = new EdgeDriver();
		} 
		else 
		{
			System.out.println("Browser Name Is Not Macthed");
		}

		driver.get(getPropValues("myMSCURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath(getPropValues("Accept_Cookies_Xpath"))).click();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return driver;
	}
	
	@SuppressWarnings("deprecation")
	@Parameters({ "BrowserName" })
	//@BeforeSuite
	public static WebDriver launchBrowsers(String BrowserName) throws Exception {

		// Recording Videos for Scripts
		String timeStamp = getDataTime();
		recorder = new ATUTestRecorder("./Videos\\", timeStamp, false);
		recorder.start();

		// Log4j Configurations
		log = Logger.getLogger("myMSC");
		PropertyConfigurator.configure("Log4j.properties");

		if (BrowserName.equalsIgnoreCase("Chrome")) 
		{
			System.out.println("Chrome Browser Launched");
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			//prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.directory_upgrade", true);
			prefs.put("safebrowsing.enabled", true);
			prefs.put("download.default_directory", getPropValues("DownloadFilePath"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("disalbe-geolocation");
			options.setExperimentalOption("prefs", prefs);
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", getPropValues("ChromeDriver"));
			driver = new ChromeDriver(cap);
		} 
		else if (BrowserName.equalsIgnoreCase("FireFox")) 
		{
			System.out.println("Firefox Browser Launched");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
			profile.setPreference("browser.download.dir", getPropValues("DownloadFilePath"));
			profile.setPreference("dom.webnotifications.enabled", false);
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);
			System.setProperty("webdriver.gecko.driver", getPropValues("FirefoxDriver"));
		} 
		else if (BrowserName.equalsIgnoreCase("IE")) 
		{
			System.out.println("IE Browser Launched");
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			System.setProperty("webdriver.ie.driver", getPropValues("IEDriver"));
			driver = new InternetExplorerDriver(ieOptions);
		} 
		else if (BrowserName.equalsIgnoreCase("Edge")) 
		{
			System.out.println("IE Browser Launched");
			System.setProperty("webdriver.ie.driver", getPropValues("EdgeDriver"));
			driver = new EdgeDriver();
		} 
		else 
		{
			System.out.println("Browser Name Is Not Macthed");
		}

		driver.get(getPropValues("myMSCURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath(getPropValues("Accept_Cookies_Xpath"))).click();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		return driver;
	}

	@BeforeTest
	public static void loginApplication() throws Exception {
		driver.findElement(By.id(getPropValues("Username_ID"))).sendKeys(getPropValues("username"));
		driver.findElement(By.id(getPropValues("Password_ID"))).sendKeys(getPropValues("password"));
		driver.findElement(By.xpath(getPropValues("RememberMe_XPath"))).click();
		driver.findElement(By.xpath(getPropValues("Login_XPath"))).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @AfterSuite
	public void closeBrowser() throws Exception {
		if (driver != null) {
			System.out.println(getPropValues("BrowserName") + " Browser Closed");
			driver.quit();
			Runtime.getRuntime().exec("TASKKILL /IM Chromedriver_V2.44.exe /F");
			Runtime.getRuntime().exec("TASKKILL /IM Chromedriver.exe /F");
			Runtime.getRuntime().exec("TASKKILL /IM Geckodriver_V0.23.0.exe /F");
			Runtime.getRuntime().exec("TASKKILL /IM IEDriverServer_V3.14.0.exe /F");
			recorder.stop();
		}
	}
}
