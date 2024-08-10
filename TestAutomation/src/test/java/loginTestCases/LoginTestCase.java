package loginTestCases;

import java.util.Map;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import customerModule.Customer_Creation_AllFields;
import loginModule.Logout;
import loginModule.Login_with_ClientUsers;
import loginModule.Webdrivers;
import utility.ExcelConstants;
import utility.ExcelUtility;
//import newPlanMod.CreatePlan_NewCust_CCSG;
import utility.ExcelWriter;

public class LoginTestCase extends Webdrivers{
	 String suiteName;
	    private Login_with_ClientUsers loginTestCasse;
	    private static String loginTestCaseName;
	    /*private CreatePlan_NewCust_CCSG planClass;
	    private Customer_Creation_AllFields customerClass; */
	   
	    ExcelWriter excelWriter = new ExcelWriter();
	   
	    
	 
	    @BeforeClass
	    public void setup() {
	        
	      
	        loginTestCasse = new Login_with_ClientUsers();
	       
	        /*planClass = new CreatePlan_NewCust_CCSG();
	        customerClass = new Customer_Creation_AllFields();
	        
	        planClass.setup();
	        customerClass.setup();*/
	        
	       
	    }
	    
	

	    @Test(priority = 1, dataProvider = "LoginDataProvider", dataProviderClass = Login_with_ClientUsers.class,invocationCount = 1)
	    public void loginTest(ITestContext context, Map<String, String> testData) throws InterruptedException {
	        try {
	            loginTestCasse.LoginData(testData);
	            
	            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	            excelWriter.writeResultsToExcel(context.getCurrentXmlTest().getName(), methodName,"", "Pass");
	        } catch (Exception e) {
	            e.printStackTrace();
	            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	            excelWriter.writeResultsToExcel(context.getCurrentXmlTest().getName(), methodName, "", "Fail");
	        }
	    }

	   /* @Test(priority = 2, dataProvider = "PlanDataProvider", dataProviderClass = CreatePlan_NewCust_CCSG.class, dependsOnMethods = "loginTest")
	    public void createPlanTest(ITestContext context, Map<String, String> testData) throws InterruptedException {
	        try {
	            planClass.CreatePlan(planClass, testData);
	            Map<String, String> parameters = context.getCurrentXmlTest().getLocalParameters();
	            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	            excelWriter.writeResultsToExcel(context.getCurrentXmlTest().getName(), methodName,parameters.get("LoginTC"), "Pass");
	    	    
	        } catch (Exception e) {
	            e.printStackTrace();
	            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	            excelWriter.writeResultsToExcel(context.getCurrentXmlTest().getName(), methodName, "", "Fail");
	        }
	    }

	    @Test(priority = 3, dataProvider = "CustomerDataProvider", dataProviderClass = Customer_Creation_AllFields.class, dependsOnMethods = "createPlanTest")
	    public void CreateCustomer(ITestContext context, Map<String, String> testData) throws InterruptedException {
	        try {
	            customerClass.CreateCustomer(customerClass, testData);
	            Map<String, String> parameters = context.getCurrentXmlTest().getLocalParameters();
	            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	            excelWriter.writeResultsToExcel(context.getCurrentXmlTest().getName(), methodName,parameters.get("LoginTC"), "Pass");
	    	    
	           } catch (Exception e) {
	            e.printStackTrace();
	            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
	            excelWriter.writeResultsToExcel(context.getCurrentXmlTest().getName(), methodName, "", "Fail");
	        }
	    }

	    @Test(priority = 4, dataProvider = "PlanDataProvider", dataProviderClass = CreatePlan_NewCust_CCSG.class, dependsOnMethods = "CreateCustomer")
	    public void derivedDetailsMain(ITestContext context, Map<String, String> testData) throws InterruptedException {
	        try {
	            planClass.deriveDetailsData(planClass, testData);
	            Map<String, String> parameters = context.getCurrentXmlTest().getLocalParameters();
	            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();	 
	            excelWriter.writeResultsToExcel(context.getCurrentXmlTest().getName(), methodName,parameters.get("LoginTC"), "Pass");
	            } catch (Exception e) {
	            e.printStackTrace();
	            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();	            
	            excelWriter.writeResultsToExcel(context.getCurrentXmlTest().getName(), methodName, "", "Fail");
	        }
	    }

	    @Test(priority = 5, dependsOnMethods = "derivedDetailsMain")
	    public void logoutTest() throws InterruptedException { 
	    	Logout logout = new Logout();
	    	logout.logout();
	    } */


	    @AfterSuite
	    public void tearDown(ITestContext context) throws InterruptedException {
	        Map<String, String> parameters = context.getCurrentXmlTest().getLocalParameters();
	        excelWriter.saveToFile(parameters.get("outputDirectory"));
	        Logout logout = new Logout();
	    	logout.logout();
	    	logger.info("Logging Out");
	        driver.quit();
	        logger.info("Browser closed");
	    }
	    
	    @DataProvider(name = "TestCases")
	    public Object[][] readTestcase() throws Exception {
	        return ExcelUtility.getTableArray(ExcelConstants.File_Path, "TestCases","N/A");
	    }

	    @Test(priority = 0, dataProvider = "TestCases")
	    public void testMethod(String testCaseName, String loginTestCase, String planCreationTestCase) throws Exception {
	        System.out.println("TestCaseName: " + testCaseName);
	        System.out.println("LoginTestCase: " + loginTestCase);
	        System.out.println("PlanCreationTestCase: " + planCreationTestCase);

	        // Store the loginTestCase value
	        loginTestCaseName = loginTestCase;
	    }

	    // Getter method for loginTestCaseName
	    public static String getLoginTestCaseName() {
	        return loginTestCaseName;
	    }
}
