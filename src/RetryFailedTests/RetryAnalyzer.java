package RetryFailedTests;

import org.testng.ITestResult;

public class RetryAnalyzer {

	public int minRetryCount = 0, maxRetryCount = 3;

	public boolean retryTestCases(ITestResult iTestResult) {
		if (minRetryCount < maxRetryCount) {
			minRetryCount++;
			return true;
		} else {
			return false;
		}
	}
}
