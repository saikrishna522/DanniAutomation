package loginModule;



import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import org.testng.asserts.SoftAssert;


//import testCases.NewTest;
import utility.ExcelConstants;

public class Webdrivers {
	public static WebDriver driver;
	protected static final Logger logger = LogManager.getLogger(Webdrivers.class);
	SoftAssert softassert = new SoftAssert();
	 
	@BeforeSuite
	public void setUpWebDriver() throws Exception {
		Configurator.initialize(null, "log4j2.xml");
		if (ExcelConstants.Browser.equals("Edge"))
			edgeBrowser();
		else {
			chromeBrowser();
		}
	}
	
	
	  
	  public void setup() {
		    Configurator.initialize(null, "log4j2.xml");
		    Configurator.setLevel(LogManager.getRootLogger().getName(), Level.INFO); // Change Level.INFO here
		}
	
	public void edgeBrowser() {
		System.setProperty("webdriver.edge.driver", ExcelConstants.EdgeDriverPath);
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver = new EdgeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(ExcelConstants.URL);
		logger.info("Opened Edge Browser");
		waitForTitle(ExcelConstants.Title);
		String expectedTitle = ExcelConstants.Title;
		String originalTitle = driver.getTitle();
		Assert.assertEquals(originalTitle, expectedTitle);
	}

	public void chromeBrowser() {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(ExcelConstants.URL);
		logger.info("Opened Chrome Browser");
		waitForTitle(ExcelConstants.Title);
		String expectedTitle = ExcelConstants.Title;
		String originalTitle = driver.getTitle();
		softassert.assertEquals(originalTitle, expectedTitle, "Title of the Website doesn't match...");
	}
	private void waitForTitle(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleIs(expectedTitle));
        String originalTitle = driver.getTitle();
        softassert.assertEquals(originalTitle, expectedTitle, "Title of the Website doesn't match...");
    }
}