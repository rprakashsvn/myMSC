package ZTestPackage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CountOfImages {

	static WebDriver driver = null;

	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver", "./Drivers\\Chromedriver_V2.44.exe");
		driver = new ChromeDriver();
		driver.get("https://en.wikipedia.org/wiki/New_York_City");

		List<WebElement> totalImages = driver.findElements(By.tagName("img"));
		System.out.println("Count Of Total Images in Webpage : " + totalImages.size());

		int count = 0;
		for (WebElement images : totalImages) {

			if (images.isDisplayed()) {
				count++;
				System.out.println(count);
				String[] imageName = images.getAttribute("src").split("/");
				String name = imageName[imageName.length - 1];
				System.out.println(name);
				URL myURL = new URL(images.getAttribute("src"));
				File destFile = new File("./DownloadImages\\" + System.currentTimeMillis() + name);
				FileUtils.copyURLToFile(myURL, destFile);
			}
		}
		driver.quit();
	}
}
