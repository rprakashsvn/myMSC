package SolasVGM;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import RetryFailedTests.RetryTransformer;
import Utilities.Helper;

@Test(retryAnalyzer = RetryTransformer.class)
public class SubmitVGM extends Helper {

	@Test
	public void SubmitSolasVGM() throws Exception {
		driver.findElement(By.xpath(getPropValues("SolasVGM_XPath"))).click();
		driver.findElement(By.xpath(getPropValues("SubmitVGM_XPath"))).click();
	}
}
