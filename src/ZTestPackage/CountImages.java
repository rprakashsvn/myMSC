package ZTestPackage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CountImages {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "./Drivers\\Chromedriver_V2.44.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://en.wikipedia.org/wiki/Flower");
		List<WebElement> listImages = driver.findElements(By.tagName("img"));
		System.out.println(listImages.size());

		int count = 0;

		for (WebElement images : listImages) {
			if (images.isDisplayed()) {
				count++;
				System.out.println(count);
				String[] names = images.getAttribute("src").split("/");
				System.out.println(names);
				String name = names[names.length - 1];
				System.out.println(name);
				URL myURL = new URL(images.getAttribute("src"));
				File files = new File("C:\\downloadedPictures\\" + System.currentTimeMillis() + "." + name);
				FileUtils.copyURLToFile(myURL, files);
			}
		}
		System.out.println("No. of total displable images: " + count);
		driver.quit();
	}
}
