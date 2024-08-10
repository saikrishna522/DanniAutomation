package loginTestCases;

import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import utility.ExcelConstants;
import utility.ExcelUtility;

public class TestSuiteValues {

	


    public String loginTestCase(Map<String, String> suitetestData) {
    	 String sloginTestCase = suitetestData.get("LoginTestCase");
    	 System.out.println("sloginTestCase is: "+sloginTestCase);
        return sloginTestCase;
    }

    public String planTestCase(Map<String, String> suitetestData) {
    	String splanCreationTestCase = suitetestData.get("PlanCreationTestCase");
        return splanCreationTestCase;
    }

    public String custTestCase(Map<String, String> suitetestData) {
    	String scustTestCase = suitetestData.get("CustomerTestCase");
        return scustTestCase;
    }
    
    
public void suiteTestCases(Map<String, String> suitetestData) {
	loginTestCase(suitetestData);
	planTestCase(suitetestData);
	custTestCase(suitetestData);
}



@DataProvider(name = "SuiteDataProvider")
public Object[][] SuiteDataProvider(ITestContext context) throws Exception {
    String suitTestcaseName = context.getCurrentXmlTest().getParameter("LoginTC");
    System.out.println(suitTestcaseName);
    return ExcelUtility.getTableArray(ExcelConstants.File_Path, "Login", suitTestcaseName);
}
}
