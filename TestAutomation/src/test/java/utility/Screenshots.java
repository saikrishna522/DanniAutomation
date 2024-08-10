package utility;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import loginModule.Webdrivers;

import javax.imageio.ImageIO;


//import loginModule.Webdrivers;


public class Screenshots extends Webdrivers {
	private static final Logger logger = LogManager.getLogger(Screenshots.class);
    public void takeFullScreenshot() {
    	 Configurator.initialize(null, "log4j2.xml");
        try {
            // Create an instance of Robot class
            Robot robot = new Robot();

            // Get the screen size
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            // Capture the screenshot using Robot class
            BufferedImage screenshotImage = robot.createScreenCapture(screenRect);

            // Define the destination directory
            String destinationDirectory = "D:\\Selenium\\Screenshots\\";

            // Create a timestamp for the filename
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = formatter.format(new Date());

            // Create the destination file with a unique filename
            String destinationFilePath = destinationDirectory + "screenshot_" + timestamp + ".jpeg";

            // Save the screenshot to the destination file path
            ImageIO.write(screenshotImage, "jpeg", new File(destinationFilePath));

            //logger.info("Screenshot captured: " + destinationFilePath);
        } catch (AWTException e) {
        	 logger.error("Failed to capture screenshot: " + e.getMessage());
        } catch (Exception e) {
        	 logger.error("Failed to capture screenshot: " + e.getMessage());
        }
    }
    
    public void takeScreenshot() {
    	try {
    		TakesScreenshot screenshot = (TakesScreenshot) driver;

            File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

            String destinationDirectory = "D:\\Selenium\\Screenshots\\";
            File destDir = new File(destinationDirectory);
            destDir.mkdirs();

           
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = formatter.format(new Date());

            String destinationFilePath = destinationDirectory + "screenshot_" + timestamp + ".jpeg";

            FileUtils.copyFile(screenshotFile, new File(destinationFilePath));
  
            logger.info("Screenshot saved successfully.");
        } catch (Exception e) {
        	logger.error("Failed to take a screenshot: " + e.getMessage());
        }

    }
}