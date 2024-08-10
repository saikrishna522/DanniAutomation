package loginModule;


import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import loginTestCases.LoginTestCase;

import loginModule.LoginPage;
import utility.ExcelConstants;
import utility.ExcelUtility;


public class Login_with_ClientUsers extends Webdrivers {
   
    private LoginPage LoginClass; // LoginClass is declared but not initialized
    private SwitchClientMod SwitchClient;

    private static final Logger logger = LogManager.getLogger(Login_with_ClientUsers.class);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public void setup() {
    	
        LoginClass = new LoginPage(); // Initialize the LoginClass object
        
        SwitchClient = new SwitchClientMod();
        Configurator.initialize(null, "log4j2.xml");
    }
    
	
	public void userNameData(String sUserName) throws InterruptedException {
		
		WebElement userID = LoginClass.userName();
		userID.sendKeys(sUserName);
		 logger.error("UserName entered: " + sUserName);
		
		WebElement next = driver.findElement(By.id("validatelogin"));
		next.click();
	}
	

	
	public void passwordData(String sPassword) throws InterruptedException {
		
		WebElement Password = LoginClass.password();
		Password.sendKeys(sPassword);
		
		logger.error("Password Entered: "+sPassword);		
		
		WebElement login =driver.findElement(By.xpath("//input[@id='relogin']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);
		Thread.sleep(1000); 
		wait.until(ExpectedConditions.elementToBeClickable(login)).click();
		
	}

	

	
	public void LoginData(Map<String, String> testData) throws InterruptedException {
	
		setup();
		String sUserName = testData.get("sUserName");
		logger.info("UserName Entered: ", sUserName);
	    String sPassword = testData.get("sPassword");
	    logger.info("Password Entered: ", sPassword);
	    userNameData(sUserName);
	    passwordData(sPassword);
	    try {
	        LoginClass.oopsError(false);
	        userNameData(sUserName);
	        passwordData(sPassword);
	    } catch (Exception e) {
	      
	    }
	    LoginClass.pwdError();
	    LoginClass.closePopup();
	    SwitchClient.switchClient();
	}
	

	 

	@DataProvider(name = "LoginDataProvider")
    public Object[][] Authentication(ITestContext context) throws Exception {
        // Retrieve the loginTestCaseName from LoginTestCase class
        String loginTestCaseName = LoginTestCase.getLoginTestCaseName();

        logger.info("LoginTestCaseName: " + loginTestCaseName);
        // Now use loginTestCaseName to fetch data from Excel or perform other operations

        return ExcelUtility.getTableArray(ExcelConstants.File_Path, "Login", loginTestCaseName);
    }
	
}


