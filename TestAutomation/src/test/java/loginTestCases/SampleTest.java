package loginTestCases;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import utility.ExcelConstants;


public class SampleTest {
	
	public static WebDriver driver;
  @Test
  public void setUpWebDriver() throws Exception {
	 
	  System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\Drivers\\ChromeDriver\\chromedriver.exe");

      // Create a new instance of ChromeDriver
      WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(ExcelConstants.URL);
		
	}
 
}
