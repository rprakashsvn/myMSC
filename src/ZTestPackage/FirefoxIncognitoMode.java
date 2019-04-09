package ZTestPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxIncognitoMode {

	static WebDriver driver;

	public static void main(String[] args) throws Exception {

		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("-private");
		System.setProperty("webdriver.gecko.driver", "./Drivers\\Geckodriver_V0.23.0.exe");
		WebDriver driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());
	}
}
