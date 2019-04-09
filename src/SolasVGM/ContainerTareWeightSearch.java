package SolasVGM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import RetryFailedTests.RetryTransformer;
import Utilities.Helper;

@Test(retryAnalyzer = RetryTransformer.class)
public class ContainerTareWeightSearch extends Helper {

	@Test
	public void SingleContainerTareWeightSearch() throws Exception 
	{
		Helper.moduleScreenSelection("SolasVGM_XPath", "ContainerWeightSearch_XPath");
		driver.findElement(By.id(getPropValues("ContainerNumber_ID"))).sendKeys("MEDU123456");
		driver.findElement(By.id(getPropValues("Retrieve_ID"))).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,100)");
		
		boolean containerError = driver.findElement(By.id(getPropValues("ContainerError_ID"))).isDisplayed();
		if(containerError == true)
		{
			WebElement Table = driver.findElement(By.xpath("//*[@id='TareWeightErrors']"));
			List<WebElement> TableRows = Table.findElements(By.tagName("tr"));
			System.out.println("Total No Of Rows : " + TableRows.size());
			List<WebElement> rows = driver.findElements(By.tagName("td"));
			for (WebElement rowValues : rows) 
			{
				String Values = rowValues.getText().trim();
				System.out.println(Values);
				if(Values.contains("we cannot find this container in our database"))
				{
					System.out.println("Container Is Not Avaiable in Database");
				}
			}
		}
	}
}
