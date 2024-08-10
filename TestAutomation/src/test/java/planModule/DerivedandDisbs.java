package planModule;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PlanTestCases.CreateNewPlan;
import loginModule.Webdrivers;

public class DerivedandDisbs extends Webdrivers {
	
	private static final Logger logger = LogManager.getLogger(DerivedandDisbs.class);
	private 	Map<String, String> disbursementMap = new HashMap<>();
	private 	Map<String, String> expectedMap = new HashMap<>();
	Map<String, String> sortedExpectedDisbursementMap = new TreeMap<>();
    Map<String, String> sortedDisbursementMap = new TreeMap<>();
	public void Disbursements() {

		// Locate the table
		WebElement table = driver.findElement(By.xpath("//div[@id='active_disbursments_MBI_false']"));

		// Get headers and map them to column indices
		List<WebElement> headers = table.findElements(By.xpath(".//thead/tr[2]/th"));
		Map<String, Integer> headerMap = new HashMap<>();
		for (int i = 0; i < headers.size(); i++) {
			
			String headerText = headers.get(i).getText();
			headerMap.put(headerText, i);
			
		}

		// Identify column indices for 'Disbursement' and 'Original Amt'
		int disbursementIndex = headerMap.get("Disbursement");
		int originalAmtIndex = headerMap.get("Original Amt");

		// Get all rows from the table body
		List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));	

		// Iterate over each row to get 'Disbursement' and 'Original Amt' values
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String disbursement = cells.get(disbursementIndex).getText();
			String originalAmt = cells.get(originalAmtIndex).getText();
			disbursementMap.put(disbursement, originalAmt);

		}
		
		System.out.println("disbursementMap: "+disbursementMap);
		 // Create a TreeMap to sort the disbursementMap by keys
	   sortedDisbursementMap = new TreeMap<>(disbursementMap);

	    // Print the sorted disbursementMap
	    System.out.println("sortedDisbursementMap: " + sortedDisbursementMap);
		
	
	}
	
	public void expectedDisbs(Object[] disbursements, Map<String, String> warrantyDetailsMap) {
		System.out.println("disbursements.length: "+disbursements.length);
		//Map<String, String> expectedDisbsMap = new HashMap<>();
		for (int i =0; i< disbursements.length;i++) {
			System.out.println("(String) disbursements[i] "+(String) disbursements[i]);
			String disbursementKey = (String) disbursements[i];
			
			String disbursementValue = warrantyDetailsMap.get(disbursementKey);
			
			expectedMap.put(disbursementKey, disbursementValue);
			
			
		}
		 System.out.println("expectedMap: "+expectedMap);
		  sortedExpectedDisbursementMap = new TreeMap<>(expectedMap);
		 System.out.println("sortedExpectedDisbursementMap: "+sortedExpectedDisbursementMap);
		 System.out.println("Expectedsize: "+ sortedExpectedDisbursementMap.size()+ "Actualsize: "+sortedDisbursementMap.size());
	}

	public void assertDisbs() {
	if (sortedExpectedDisbursementMap.size() == sortedDisbursementMap.size()) {		
		
	     for (String entry : sortedExpectedDisbursementMap.keySet()) {
	    	 String key = entry;
	    	 String value1 = sortedExpectedDisbursementMap.get(entry);
	    			// entry.getValue();
	            String value2 = sortedDisbursementMap.get(key);
	            assertNotNull(value2, "Value for key " + key + " is not present in both maps");
	            assertEquals(value1, value2, "Values for key " + key + " are not equal");
	            logger.info("Value : "+key +" Expected : "+value1 +" Actual : "+value2);
	}
	
	}else {
		logger.error("Given Disb's and Actual Disb's count is not matching");
		logger.error("Given Disb's count: "+sortedExpectedDisbursementMap.size() );
		logger.error("Actual  Disb's count: "+sortedDisbursementMap.size() );
	}
}

	
}
