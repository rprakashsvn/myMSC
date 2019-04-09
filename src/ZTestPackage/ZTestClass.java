package ZTestPackage;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import BaseClass.BaseTest;

public class ZTestClass extends BaseTest {

	/*@Test(groups = { "Sanity" })
	public void TestMethods1() {
		test.assignCategory("Sanity Testing");
		System.out.println("Test Method 1");
		test.log(Status.INFO, "Test Case 1");
	}*/

	@Test(groups = { "Smoke" })
	public void TestMethods2() {
		System.out.println("Test Method 2");
		test.log(Status.INFO, "Test Case 2");
	}

	@Test(groups = { "Sanity" })
	public void TestMethods3() {
		test.assignCategory("Sanity Testing");
		System.out.println("Test Method 3");
		test.log(Status.INFO, "Test Case 3");
	}

	@Test(groups = { "Sanity" })
	public void TestMethods4() {
		test.assignCategory("Sanity Testing");
		System.out.println("Test Method 4");
		test.log(Status.INFO, "Test Case 4");
	}

	@Test(groups = { "Regression" })
	public void TestMethods5() {
		test.assignCategory("Regression Testing");
		System.out.println("Test Method 5");
		test.log(Status.INFO, "Test Case 5");
	}

	@Test(groups = { "Regression" })
	public void TestMethods6() {
		test.assignCategory("Regression Testing");
		System.out.println("Test Method 6");
		test.log(Status.INFO, "Test Case 6");
	}

	@Test(groups = { "Functional" })
	public void TestMethods7() {
		test.assignCategory("Functional Testing");
		System.out.println("Test Method 7");
		test.log(Status.INFO, "Test Case 7");
	}

}
