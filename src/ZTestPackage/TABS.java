package ZTestPackage;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TABS {

	static WebDriver driver = null;
	static Actions action = null;

	public static void main(String args[]) throws Exception {
		System.out.println("Chrome Browser Launched");
		System.setProperty("webdriver.chrome.driver", "./Drivers\\Chromedriver_V2.44.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hdfcbank.com/assets/popuppages/netbanking.htm");
		WebElement clickImage = driver.findElement(By.xpath("(//img[@class='vDesc'])[1]"));
		action = new Actions(driver);
		action.moveToElement(clickImage).build().perform();
		action.click();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs);
		Runtime.getRuntime().exec("TASKKILL /IM Chromedriver_V2.44.exe /F");
		Runtime.getRuntime().exec("TASKKILL /IM Chromedriver.exe /F");
		Runtime.getRuntime().exec("TASKKILL /IM Geckodriver_V0.23.0.exe /F");
		Runtime.getRuntime().exec("TASKKILL /IM IEDriverServer_V3.14.0.exe /F");
	}
}
