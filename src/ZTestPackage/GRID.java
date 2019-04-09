package ZTestPackage;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GRID {

	static WebDriver driver;

	public static void main(String[] args) throws Exception {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);

		ChromeOptions options = new ChromeOptions();
		options.merge(cap);

		String hubUrl = "http://localhost:4444/grid/register";

		URL myHubUrl = new URL(hubUrl);
		driver = new RemoteWebDriver(myHubUrl, cap);

		driver.get("https://test.mymsc.com/myMSC/");
		driver.manage().window().maximize();
		System.out.println("Browser Launched Successfully");
	}
}
