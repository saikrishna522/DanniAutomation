package loginModule;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import utility.Screenshots;

public class Logout extends Webdrivers {
	
	private static final Logger logger = LogManager.getLogger(Logout.class);
	Screenshots	Screenshot = new Screenshots();
	LoginPage Login =new LoginPage();
	
	@AfterSuite
    public void logout() throws InterruptedException {
        // Perform login and navigate to the desired page before calling logoutbtn()
		Configurator.initialize(null, "log4j2.xml");
        logoutbtn();
    }
	

    public void logoutbtn() throws InterruptedException {
    
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	  Login.pageLoader();
          Screenshot.takeFullScreenshot();
          
          WebElement logout = driver.findElement(By.xpath("//*[@id=\"logout\"]"));
          wait.until(ExpectedConditions.elementToBeClickable(logout)).click();

          Login.pageLoader();
          Screenshot.takeFullScreenshot();

        // You can perform additional actions or assertions here if needed


        // You can also clear the browser cookies to ensure a fresh session
        driver.manage().deleteAllCookies();
    }
}