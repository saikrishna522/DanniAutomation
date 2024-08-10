package loginModule;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.*;
import loginTestCases.LoginTestCase;
import utility.DataReader;
import utility.ExcelConstants;
import utility.ExcelUtility;

public class GenericLogin extends Webdrivers {

    private static final Logger logger = LogManager.getLogger(GenericLogin.class);
    private LoginPage LoginClass;
    private SwitchClientMod SwitchClient;
    private WebDriverWait wait;
    private DataReader dataReader = new DataReader();    
    Object[][] testCaseData = null;
    String testScenario = null;
    public boolean oops = false; 

    @BeforeClass
    public void setup()  {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        LoginClass = new LoginPage();
        SwitchClient = new SwitchClientMod();
        Configurator.initialize(null, "log4j2.xml");
    }

    @AfterSuite
    public void logout() {
    	//LoginClass.Logout();
    }

    @BeforeClass
    public void testCaseData(ITestContext context) throws Exception {
        String testCaseName = "Login";
        testCaseData = ExcelUtility.getTableArray(ExcelConstants.File_Path, "TestCases", testCaseName);
        for (Object[] testData : testCaseData) {
            Map<String, String> testCaseMap = (Map<String, String>) testData[0];
            
            System.out.println("testCaseData: " + testCaseMap);
            testScenario = testCaseMap.get("TestScenario");
            System.out.println("TestScenario: " + testScenario);
            if (testScenario != null) {
                dataReader.setLoginTestCaseName(testScenario,testCaseName);
            } else {
                logger.error("TestScenario is null for test case: " + testCaseName);
            }
        }
    }
    
    public void loginData(String sUserName, String sPassword) throws InterruptedException {
        WebElement userID = LoginClass.userName();
        userID.sendKeys(sUserName);
        logger.error("UserName entered: " + sUserName);
        LoginClass.bt_loginNext().click();

        WebElement Password = LoginClass.password();
        Password.sendKeys(sPassword);
        logger.error("Password Entered: " + sPassword);

        wait.until(ExpectedConditions.elementToBeClickable(LoginClass.bt_loginSubmit())).click();
        logger.info("Clicked Submit button");
    }

    
    @Test(priority = 1, dataProvider = "DataProvider", dataProviderClass = DataReader.class)
    public void LoginData(Map<String, String> loginData) throws Exception  {
        System.out.println("testData: " + loginData);
        String sUserName = loginData.get("sUserName");
        logger.info("UserName Entered: " + sUserName);

        String sPassword = loginData.get("sPassword");
        logger.info("Password Entered: " + sPassword);
        String sLogOut = loginData.get("sLogOut");
        logger.info("is sLogOut : " + sLogOut);

        loginData(sUserName, sPassword);

        try {
            LoginClass.oopsError(oops);
           
            if (oops=true) {
            	 System.out.println("oops: "+oops);
            loginData(sUserName, sPassword);
            }
        } catch (Exception e) {
            // Handle exception
        }

        LoginClass.pwdError();
        LoginClass.closePopup();
        SwitchClient.switchClient();

        if ("Yes".equals(sLogOut)) {
            System.out.println("came to logout");
            LoginClass.Logout();
        }
    }
}
