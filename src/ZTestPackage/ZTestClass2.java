package ZTestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ZTestClass2 {

	@Test(groups = "orderBo")
	public void testFindOrder() {
		System.out.println("testFindOrder");
	}

	@Test(groups = { "orderBo", "save" })
	public void testMakeEmptyOrder() {
		System.out.println("testMakeEmptyOrder");
	}

	@Test(groups = { "orderBo", "save" })
	public void testMakeOrder() {
		System.out.println("testMakeOrder");
	}

	@Test(groups = "orderBo")
	public void testUpdateOrder() {
		System.out.println("testUpdateOrder");
	}

	@Test(description = "Testing")
	public void dependsOnMethodTest() {
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods = "dependsOnMethodTest", alwaysRun = true)
	public void dependsOnMethod() {
		System.out.println("dependsOnMethodTest Always RUN");
	}
}
