package loginModule;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import utility.Screenshots;

public class LoginPage extends Webdrivers {
	private static final Logger logger = LogManager.getLogger(LoginPage.class);
	Actions action = new Actions(driver);
	public boolean oopspage;

	@BeforeClass
	public void setup() {
		Configurator.initialize(null, "log4j2.xml");
		Configurator.setLevel(LogManager.getRootLogger().getName(), Level.INFO); // Change Level.INFO here
	}

	Screenshots Screenshot = new Screenshots();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	public WebElement pageLoader() {

		WebElement Pageloader = driver.findElement(By.xpath("//div[@class='loading']"));
		logger.info("Waiting for loader");
		wait.until(ExpectedConditions.invisibilityOf(Pageloader));

		return Pageloader;
	}

	public WebElement userName() throws InterruptedException {
		logger.info("Waiting for  Element User Name");
		pageLoader();
		Screenshot.takeScreenshot();
		WebElement la_loginPage = driver.findElement(By.xpath("//section[@class=\"container\"]//h3"));
		wait.until(ExpectedConditions.textToBePresentInElement(la_loginPage, "Authorised User Log In"));
		Assert.assertEquals(la_loginPage.getText(), "Authorised User Log In", "Login Page Not found");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("j_user_name")));
		WebElement userID = driver.findElement(By.id("j_user_name"));
		logger.info("Found Element User Name");
		Screenshot.takeScreenshot();
		return userID;
	}

	public WebElement bt_loginNext() throws InterruptedException {
		WebElement next = driver.findElement(By.id("validatelogin"));
		return next;
	}

	public WebElement bt_loginSubmit() throws InterruptedException {

		WebElement login = driver.findElement(By.xpath("//input[@id='relogin']"));
		action.moveToElement(login);
		action.perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);
		return login;
	}

	public WebElement password() throws InterruptedException {
		logger.info("Waiting for  Element Password");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("j_password")));
		WebElement Password = driver.findElement(By.id("j_password"));
		logger.info("Found Element Password");
		Screenshot.takeScreenshot();
		return Password;
	}

	public  boolean oopsError(boolean oops) throws InterruptedException {
		try {
			logger.info("Waiting for  Element Oops error");
			pageLoader();

			WebElement oopsError = driver.findElement((By.xpath("//div[@id=\"pagetemplate\"]/h3")));
			wait.until(ExpectedConditions.visibilityOf(oopsError));
			
			if (oopsError.isDisplayed()) {
				oopspage = true;
				Screenshot.takeScreenshot();
				logger.info("Found Oops error and relogin ");
				driver.navigate().refresh();
				pageLoader();
				Thread.sleep(1000); 
			}				
			return oops;
		} catch (Exception ignored) {
			logger.info("OOPS error Not found");
		
		}
		return oops;
	}
	

	public void pwdError() throws InterruptedException {
		try {
			logger.info("Waiting for Password notification");
			pageLoader();

			WebElement PWDcont = driver.findElement((By.xpath("//*[@id=\"continue\"]")));
			Screenshot.takeScreenshot();
			pageLoader();
			PWDcont.click();
			logger.info("Found Force Change Password Screen clicked continue..");
		} catch (Exception ignored) {
			logger.info("Not Found Force Change Password Screen");
		}

	}

	public void closePopup() throws InterruptedException {
		try {
			logger.info("Waiting for Popup");
			pageLoader();
			Thread.sleep(1000);

			WebElement closePopup = driver
					.findElement(By.xpath("//div[@id=\"popupModal\"]//button[@class=\"close\"]/span[text()=\"×\"]/.."));
			Screenshot.takeScreenshot();

			wait.until(ExpectedConditions.elementToBeClickable(closePopup)).click();
			logger.info("Found Pop up and Closed Pop Up");
		} catch (Exception ignored) {
			logger.info("No  Pop up Found");
		}

	}

	
	public void Logout() {
		logger.info("Waiting for Logout Button");
		pageLoader();
		Screenshot.takeFullScreenshot();
		
		WebElement la_home = driver.findElement(By.id("home"));
		action.moveToElement(la_home);
		action.perform();
		la_home.click();
		pageLoader();
		try {
			closePopup();
		} catch (Exception ignored) {
			
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"logout\"]")));
		WebElement logout = driver.findElement(By.id("logout"));
		logout.click();
		logger.info("Logged out successfully");
		Screenshot.takeFullScreenshot();
		// driver.quit();
	}
}
