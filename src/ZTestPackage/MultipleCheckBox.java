package ZTestPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleCheckBox {
	public static void main(String[] args) {

		System.out.println("Chrome Browser Launched");
		System.setProperty("webdriver.chrome.driver", "./Drivers\\Chromedriver_V2.44.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.gsmarena.com/samsung-phones-9.php");
		
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='Checkbox']"));
		for (int i = 0; i < checkBoxes.size(); i = i + 2) 
		{
			checkBoxes.get(i).click();
		}
		
		int checkedCount = 0, uncheckedCount = 0;
		
		for (int i = 0; i < checkBoxes.size(); i++) 
		{
			System.out.println(i + " checkbox is selected " + checkBoxes.get(i).isSelected());
			if (checkBoxes.get(i).isSelected()) 
			{
				checkedCount++;
			} 
			else {
				uncheckedCount++;
			}
		}
		System.out.println("number of selected checkbox: " + checkedCount);
		System.out.println("number of unselected checkbox: " + uncheckedCount);
	}
}
