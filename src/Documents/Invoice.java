package Documents;

import org.testng.annotations.Test;

import Utilities.Helper;

public class Invoice {
	@Test
	public void SearchWithBLNumber() throws Exception {
		
		Helper.moduleScreenSelection("Documents_XPath", "Invoice_XPath");
		Helper.listBoxSelectionByXPath("Agency_XPath", "AgencyList_XPath", "MSC BAHAMAS");
		
		
	}
	
	@Test
	public void SearchWithInvoiceNumber() {
	}
	
	@Test
	public void SearcContainerNumber() {
	}
}
