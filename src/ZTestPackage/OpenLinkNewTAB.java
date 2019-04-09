package ZTestPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class OpenLinkNewTAB {

	static int a = 100;
	int b = 100;

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		testStaticMethod();
		System.setProperty("webdriver.chrome.driver", "./Drivers\\Chromedriver_V2.44.exe");
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		WebDriver driver = new ChromeDriver(capability);
		driver.get("http://www.google.com");
		WebElement link = driver.findElement(By.name("q"));
		link.sendKeys("Testing");
		Thread.sleep(6000);
		link.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		link.sendKeys(Keys.BACK_SPACE);
		driver.quit();
	}

	public static void testStaticMethod() {
		System.out.println(a);
	}

	public void testNonStaticMethod() {
		System.out.println(a);
		System.out.println(b);
	}
}