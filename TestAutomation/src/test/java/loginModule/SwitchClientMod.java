package loginModule;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import utility.ExcelConstants;

public class SwitchClientMod  extends Webdrivers{
	
	
	private static final Logger logger = LogManager.getLogger(SwitchClientMod.class);
	 Actions actions = new Actions(driver);
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 
	 
	 public WebElement pageLoader() {
			
			WebElement Pageloader = driver.findElement(By.xpath("//div[@class='loading']"));
			
			
			wait.until(ExpectedConditions.invisibilityOf(Pageloader));
			
			return Pageloader;
		}
	public void switchClient() throws InterruptedException{
		
	
		try {
			
			pageLoader();
			 logger.info("waing for client selection");
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"popupModal\"]/div/div/div[1]/button/span")));
		    WebElement loginPopup = driver.findElement(By.xpath("//*[@id=\"popupModal\"]/div/div/div[1]/button/span"));
		    loginPopup.click();
		
		    Thread.sleep(2000);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
		    pageLoader();
		} catch (Exception ignored) {
		    
		}

		try {
			pageLoader();
			
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
		    String clientName = ExcelConstants.ClientName;
		    WebElement mainMenu = driver.findElement(By.id("client"));
		    actions.moveToElement(mainMenu);
		    actions.click().build().perform();
		   
		    String xpath = String.format("//li/a[@class='switchclient' and @title='%s']", clientName);
		    WebElement selectclient = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		    pageLoader();
		    actions.moveToElement(selectclient);
		   logger.info("found clientname: " + selectclient);
		    actions.click().build().perform();
		    logger.info("switched client to " + ExcelConstants.ClientName);
		} catch (Exception ignored) {
			
			logger.info("Default client has been set");
			
		}

}
}
