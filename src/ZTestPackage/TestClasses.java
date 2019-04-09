package ZTestPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utilities.Helper;

public class TestClasses extends Helper {

	//static WebDriver driver;

	public void SetUpDriver() throws Exception {
		System.out.println("Chrome Browser Launched");
		System.setProperty("webdriver.chrome.driver", "./Drivers\\Chromedriver_V2.44.exe");
		driver = new ChromeDriver();
		driver.get("https://www.gettyimages.in/");
		getText();
	}

	@SuppressWarnings("resource")
	public static void getText() throws Exception {
		// Print Text
		System.out.println("Printing Outside Tag Text Name");
		String getNameText1 = driver.findElement(By.xpath(
				"(//div[@class='square-grid-container']//following::div[@class='square-grid-item__contributor-name'])[1]"))
				.getText();
		String getNameText2 = driver.findElement(By.xpath(
				"(//div[@class='square-grid-container']//following::div[@class='square-grid-item__contributor-name'])[1]"))
				.getAttribute("innerHTML");
		System.out.println("Text Names Is  :  " + getNameText1);
		System.out.println("Text Names Is  :  " + getNameText2);

		// Print All The Text
		System.out.println();
		System.out.println("Print Outside Tag All Text Names");
		List<WebElement> getNameTextList = driver.findElements(By.xpath(
				"//div[@class='square-grid-container']//following::div[@class='square-grid-item__contributor-name']"));
		System.out.println("Count Of Texts " + getNameTextList.size());
		System.out.println();
		FileWriter fileWriter = new FileWriter("./log\\getTexts.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		for (WebElement getName : getNameTextList) {
			String textName = getName.getText();
			//System.out.println(textName);
			bufferedWriter.write(textName+"\n");
		}
		bufferedWriter.close();
		System.out.println("File Writted successfully");
		
		File fileName = new File("./log\\getTexts.txt");
		FileReader readFile = new FileReader(fileName);
		BufferedReader bufferRead = new BufferedReader(readFile);
		
		if(fileName.exists())
		{ 
			String st; 
			while((st = bufferRead.readLine()) != null)
			{
				System.out.println(st);
			}
			System.out.println("File Readed successfully");
		}
	}

	/*public static void main(String[] args) throws Exception {

		System.out.println("Chrome Browser Launched");
		System.setProperty("webdriver.chrome.driver", "./Drivers\\Chromedriver_V2.44.exe");
		driver = new ChromeDriver();
		driver.get("https://www.gettyimages.in/");
		getText();
	}*/
	
	@SuppressWarnings("deprecation")
	@Parameters ("browser")
	@Test
	public static WebDriver launchBrowsers(String browser) throws Exception {

		if (browser.equalsIgnoreCase("Chrome")) 
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
		else if (browser.equalsIgnoreCase("FireFox")) 
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
		else if (browser.equalsIgnoreCase("IE")) 
		{
			System.out.println("IE Browser Launched");
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			System.setProperty("webdriver.ie.driver", getPropValues("IEDriver"));
			driver = new InternetExplorerDriver(ieOptions);
		} 
		else if (browser.equalsIgnoreCase("Edge")) 
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
}
