package planModule;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import loginModule.Login_with_ClientUsers;
import loginModule.Webdrivers;
import utility.ExcelConstants;
import utility.Screenshots;

public class PlanModElements extends Webdrivers {

	 private Map<String, String> derivedDetailsMap;
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	Actions actions = new Actions(driver);
	Screenshots	Screenshot = new Screenshots();	
	private static final Logger logger = LogManager.getLogger(PlanModElements.class);
	
	public WebElement pageLoader() {
		 Configurator.initialize(null, "log4j2.xml");
		WebElement Pageloader = driver.findElement(By.xpath("//div[@class='loading']"));
		
		
		wait.until(ExpectedConditions.invisibilityOf(Pageloader));
	
		return Pageloader;
	}
	
	
	public void  EnterNewPlan() throws InterruptedException {	
		 pageLoader();
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("PlanManager")));
		WebElement planMenu = driver.findElement(By.id("PlanManager"));
		actions.moveToElement(planMenu);
		actions.click().build().perform();
		
		WebElement enterNewPlan = driver.findElement(By.id("newplan"));
		Screenshot.takeScreenshot();
		//Hovering on main menu
		actions.moveToElement(enterNewPlan);
		actions.click().build().perform();
	//return planMenu;
}
	

	
	public  WebElement SearchPlan() throws InterruptedException {	
		 pageLoader();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("PlanManager")));
		WebElement planMenu = driver.findElement(By.id("PlanManager"));
		actions.moveToElement(planMenu);
		actions.click().build().perform();
		
		WebElement searchPlan = driver.findElement(By.id("openpolicy"));
		Screenshot.takeScreenshot();
		//Hovering on main menu
		actions.moveToElement(searchPlan);
		actions.click().build().perform();
		
	return planMenu;
}
	
	public WebElement SearchIDplan() throws InterruptedException {

		pageLoader();
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='S_PlanNumber']")));
		
		WebElement plannum = driver.findElement(By.xpath("//*[@id='S_PlanNumber']"));
		

		return  plannum;
	}
	public WebElement btn_search() throws InterruptedException {

		pageLoader();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='find']")));
		
		WebElement btn_search = driver.findElement(By.xpath("//*[@id='find']"));
		
		
		return  btn_search;
	}
	
	public WebElement openPlan() throws InterruptedException {

		pageLoader();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("i[class*='d-icon plan_icon']")));		
		 WebElement openPlan= driver.findElement(By.cssSelector("i[class*='d-icon plan_icon']")); 
		
		return  openPlan;
	}
	
	public  WebElement drp_amendment() throws InterruptedException {	
		 pageLoader();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("planactions_01")));
		WebElement planactionsMenu = driver.findElement(By.id("planactions_01"));
		actions.moveToElement(planactionsMenu);
		actions.click().build().perform();
		
		WebElement amendment = driver.findElement(By.id("amendment_01"));
		Screenshot.takeScreenshot();
		//Hovering on main menu
		actions.moveToElement(amendment);
		actions.click().build().perform();
		
	return amendment;
}
	
	public WebElement btn_mmtClear() throws InterruptedException {

		pageLoader();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='clearmakemodel']")));
		logger.error("MMT Details Cleared");
		WebElement btn_mmtClear = driver.findElement(By.xpath("//*[@id='clearmakemodel']"));
		
		
		return  btn_mmtClear;
	}
	public WebElement productMBISel() throws InterruptedException{
		pageLoader();	
		
		//WebElement cb_ProdCheckMBI = driver.findElement(By.xpath("//div[@class=\"child-tbl\"]//input[@value=\"MBI\"]"));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MBI']")));
		WebElement productMBISel = driver.findElement(By.xpath("//div[@class=\"child-tbl\"]//input[@value=\"MBI\"]"));
		productMBISel.click();
		return productMBISel;
	}
	public WebElement productSMASel() throws InterruptedException{
		pageLoader();		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SMARTGENERIC']")));
		WebElement productSMASel = driver.findElement(By.xpath("//div[@class=\"child-tbl\"]//input[@value=\"SMARTGENERIC\"]"));
		productSMASel.click();
		return productSMASel;
	
	}
	

	
	public WebElement productSelnxt() throws InterruptedException{
		pageLoader();		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='nextproduct']")));
		WebElement productSelection = driver.findElement(By.xpath("//*[@id='nextproduct']"));
		productSelection.click();
		pageLoader();
		return productSelection;
	}
	
	
public WebElement Make(String makeValue) throws InterruptedException {

		pageLoader();
		WebElement la_Prelim = driver.findElement(By.xpath("//legend[@name=\"Preliminary Details\"]"));
		wait.until(ExpectedConditions.visibilityOf(la_Prelim));
		Assert.assertEquals(la_Prelim.getText(), "Preliminary Details", "Prelimnary Page not displayed");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='V_SMMS_SQL_Make']")));
		//wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='V_SMMS_SQL_Make']"), 0));
		WebElement Make = driver.findElement(By.xpath("//*[@id='V_SMMS_SQL_Make']"));
	
		wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//select[@name=\"V_SMMS_SQL_Make\"]/option")));
		
		
		Select make = new Select(Make);	
		
		make.selectByVisibleText(makeValue);
		
		return  Make;
	}
	
	 public   WebElement Model(String ModelValue) throws InterruptedException
	    {   
		 
		 //Thread.sleep(3000);
		 pageLoader();
		
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='V_SMMS_SQL_Model']")));
		 wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='V_SMMS_SQL_Model']"), 0));
	    	WebElement Model = driver.findElement(By.xpath("//*[@id='V_SMMS_SQL_Model']"));  
	    	Select model = new Select(Model);	
	    	model.selectByVisibleText(ModelValue);
	    	return Model;

	    }
	 public   WebElement Class(String ClasslValue) throws InterruptedException
	    {   
		
		 pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='V_SMMS_SQL_VClass']")));
		 WebElement Class = driver.findElement(By.xpath("//*[@id='V_SMMS_SQL_VClass']"));   
		 Select dd_class = new Select(Class);	
		 dd_class.selectByVisibleText(ClasslValue);
	    	return Class;

	    }
	 public   WebElement Turbo(String TurboValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement Turbo = driver.findElement(By.xpath("//*[@id='V_SMMS_SQL_Turbo']")); 
		 Select dd_Turbo = new Select(Turbo);	
		 dd_Turbo.selectByVisibleText(TurboValue);
	    	return Turbo;

	    }
	 public   WebElement Transmission(String TransmissionValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement Transmission = driver.findElement(By.xpath("//*[@id='V_SMMS_SQL_Transmission']")); 
		 Select dd_Transmission= new Select(Transmission);	
		 dd_Transmission.selectByVisibleText(TransmissionValue);
	    	return Transmission;

	    }
	 public   WebElement DriveType(String DriveTypeValue) throws InterruptedException
	    {   
		 pageLoader();
		 
		 WebElement DriveType = driver.findElement(By.xpath("//*[@id='V_SMMS_SQL_FourWheelDrive\']"));  
		 Select dd_DriveType= new Select(DriveType);	
		 dd_DriveType.selectByVisibleText(DriveTypeValue);
	    	return DriveType;

	    }
	 public   WebElement FuelType(String FuelTypeValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement FuelType = driver.findElement(By.xpath("//*[@id='V_SMMS_SQL_FuelType']"));  
		 Select dd_FuelType= new Select(FuelType);	
		 dd_FuelType.selectByVisibleText(FuelTypeValue);
		 Screenshot.takeScreenshot();
	    	return FuelType;
	    	  }
	 
	 public   WebElement EngineCC(String EngineCCValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement EngineCC = driver.findElement(By.xpath("//*[@id='V_CC']"));  

		 EngineCC.sendKeys(EngineCCValue);
	    	return EngineCC;
	    	  }
	 
	 public   WebElement MWSD(String MWSDValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement MWSD = driver.findElement(By.xpath("//*[@id='V_ManuWarStartDate']"));  
		 MWSD.clear();
		 MWSD.sendKeys(MWSDValue);
	    	return MWSD;
	    	  }
	 
	 public   WebElement MWTerm(String MWTermValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement MWTerm = driver.findElement(By.xpath("//*[@id='V_ManuWarMonths_Select']"));  
		 Select dd_MWTerm= new Select(MWTerm);	
		 dd_MWTerm.selectByVisibleText(MWTermValue);
	    	return MWTerm;
	    	  }
	 
	 public   WebElement MWMileage(String MWMilValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement MWMileage = driver.findElement(By.xpath("//*[@id='V_ManuWarMileage_Select']"));  
		 Select dd_MWMileage= new Select(MWMileage);	
		 dd_MWMileage.selectByVisibleText(MWMilValue);		  
	    	return MWMileage;
	    	  }
	 
	 public   WebElement Mileage(String MileageValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement Mileage = driver.findElement(By.xpath("//*[@id='V_Mileage']"));  
		 Mileage.clear();
		 Mileage.sendKeys(MileageValue);
	    	return Mileage;
	    	  }
	 
	 public   WebElement VSoldDate(String VSoldDateValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement VSoldDate = driver.findElement(By.xpath("//*[@id='V_SoldDate']"));  
		 VSoldDate.clear();
		 VSoldDate.sendKeys(VSoldDateValue);
	    	return VSoldDate;
	    	  }
	 public   WebElement PolicySoldDate(String PSoldDateValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement PolicySoldDate = driver.findElement(By.xpath("//*[@id='P_PolicySoldDate']"));  
		 PolicySoldDate.clear();
		 PolicySoldDate.sendKeys(PSoldDateValue);
	    	return PolicySoldDate;
	    	  }
	 
	 public   WebElement DeliveryDate(String DeliveryDateValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement DeliveryDate = driver.findElement(By.xpath("//*[@id='P_DateExtra1']"));  
		 DeliveryDate.clear();
		 DeliveryDate.sendKeys(DeliveryDateValue);
	    	return DeliveryDate;
	    	  }
	 
	 public   WebElement ClaimsLimit(String ClaimsLimitValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement ClaimsLimit = driver.findElement(By.xpath("//*[@id='V_SoldPrice']"));  
		 ClaimsLimit.clear();
		 ClaimsLimit.sendKeys(ClaimsLimitValue);
	    	return ClaimsLimit;
	    	  }
	
	 public   WebElement LocalCurrency() throws InterruptedException
	    {   
		 pageLoader();
		 WebElement LocalCurrency = driver.findElement(By.xpath("//*[@id='V_SoldPrice']"));  
		 LocalCurrency.clear();
	    	return LocalCurrency;
	    	  }
	 
	 public   WebElement Vin(String vin) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement Vin = driver.findElement(By.xpath("//*[@id='V_VinNo']"));  
		 WebElement VinDup = driver.findElement(By.xpath("//*[@id='V_OverrideVinErrors']"));  
		 actions.moveToElement(VinDup);
		 actions.perform();
		 VinDup.click();
		 Vin.sendKeys(vin);
	    	return Vin;
	    	  }
	 
	 public   WebElement SellingDealer(String SelleingDealerValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement SellingDealer = driver.findElement(By.xpath("//*[@id='O_Salesperson_TS_O_Salesperson_1']"));  
		 Select sd = new Select(SellingDealer);
		 sd.selectByValue(SelleingDealerValue);
		 Screenshot.takeScreenshot();
	    	return SellingDealer;
	    	  }
	 public   WebElement Salesperson(String SalespersonValue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement Salesperson = driver.findElement(By.xpath("//*[@id='O_Salesperson_TS_O_Salesperson_2']")); 
		 Select sp =new Select(Salesperson);
		 sp.selectByValue(SalespersonValue);
		 Screenshot.takeScreenshot();
	    	return Salesperson;
	    	  }
	 
	 public   WebElement btn_ShowProducts() throws InterruptedException
	    {   
		 pageLoader();
		 WebElement ShowProducts = driver.findElement(By.xpath("//*[@id='ShowProducts']"));  
		 ShowProducts.click();
		 Screenshot.takeScreenshot();
	    	return ShowProducts;
	    	  }
	 
	 public   WebElement ProductCode(String ProductCodeValue) throws InterruptedException
	    {   
		 pageLoader();
		 System.out.println("ProductCodeValue : " + ProductCodeValue);
		
		 WebElement ProductCode = driver.findElement(By.xpath("//*[@id='MBI_SchemeID']"));
		 actions.moveToElement(ProductCode);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='MBI_SchemeID']"), 0));
		 Select dd_ProductCode = new Select(ProductCode);
		 dd_ProductCode.selectByVisibleText(ProductCodeValue);
	    	return ProductCode;
	    	  }
	 
	 public   WebElement btn_ProductClear() throws InterruptedException
	    {   
		 pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.name("MBI_Clear")));
		 WebElement btn_ProductClear = driver.findElement(By.xpath("//*[@name='MBI_Clear']")); 
		 btn_ProductClear.click();
		 logger.info("Product Details Cleared:  "+btn_ProductClear);
	    	return btn_ProductClear;
	    	  }
	 
	 public   WebElement CoverType(String CoverTypevalue) throws InterruptedException
	    {   
		 pageLoader();
			//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//select[@name=\"MBI_CoverType\"]/option/.")));
		 WebElement CoverType = driver.findElement(By.xpath("//*[@id='MBI_CoverType']"));  
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='MBI_CoverType']/option[text()='" + CoverTypevalue + "']")));
		
		  Select dd_CoverType = new Select(CoverType);
		  Thread.sleep(1000);
		  dd_CoverType.selectByVisibleText(CoverTypevalue);
	    	return CoverType;
	    	  }
	 
	 public   WebElement PurchaseType(String PurchaseTypevalue) throws InterruptedException
	    {   
		 pageLoader();
		 
		 WebElement PurchaseType = driver.findElement(By.xpath("//*[@id='MBI_PurchaseType']"));  
		 wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='MBI_PurchaseType']"), 0));
		  Select dd_PurchaseType = new Select(PurchaseType);
		  dd_PurchaseType.selectByVisibleText(PurchaseTypevalue);
	    	return PurchaseType;
	    	  }
	 
	 public   WebElement WarrantyTerm(String WarrantyTermvalue) throws InterruptedException
	    {   
		 pageLoader();
		
		 WebElement WarrantyTerm = driver.findElement(By.xpath("//*[@id='MBI_TermMthsBand']"));  
		 wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='MBI_TermMthsBand']"), 0));
		  Select dd_WarrantyTerm = new Select(WarrantyTerm);
		  dd_WarrantyTerm.selectByVisibleText(WarrantyTermvalue);
	    	return WarrantyTerm;
	    	  }
	 
	 public   WebElement WarrantyMileage(String WarrantyMileagevalue) throws InterruptedException
	    {   
		 pageLoader();
		
		 WebElement WarrantyMileage = driver.findElement(By.xpath("//*[@id='MBI_TermMileageBand']"));  
		 wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='MBI_TermMileageBand']"), 0));
		  Select dd_WarrantyMileage = new Select(WarrantyMileage);
		  dd_WarrantyMileage.selectByVisibleText(WarrantyMileagevalue);
	    	return WarrantyMileage;
	    	  }
	 
	 public   WebElement ClaimLimit(String ClaimLimitvalue) throws InterruptedException
	    {   
		 pageLoader();
		 WebElement ClaimLimit = driver.findElement(By.xpath("//*[@id='MBI_ClaimLimitBand']"));  
		 wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//*[@id='MBI_ClaimLimitBand']"), 0));
		 Select dd_ClaimLimit = new Select(ClaimLimit);
		 dd_ClaimLimit.selectByVisibleText(ClaimLimitvalue);
		 Screenshot.takeScreenshot();
	    	return ClaimLimit;
	    	  }
	 public   WebElement SMAProductCode(String SMAProduCodeVal) throws InterruptedException
	    {   
		 pageLoader();
		 
		//String SGGensmart ="//select[@id='SMARTGENERIC_SchemeID']";
		String SmartprodCode ="//select[@id='SMART_SchemeID']"; 
		
		if(ExcelConstants.ClientName == "Generic QBE") {
			SmartprodCode = "//select[@id='SMARTGENERIC_SchemeID']";
	    }
	logger.info("SmartprodCode: "+SmartprodCode);
		
		 WebElement SMAProductCode = driver.findElement(By.xpath(SmartprodCode)); 
		 actions.moveToElement(SMAProductCode);
		 Select dd_SMAProductCode = new Select(SMAProductCode);
		 dd_SMAProductCode.selectByVisibleText(SMAProduCodeVal);
		
	    	return SMAProductCode;
	    	  }
	 
	 public   WebElement btn_SMAProductClear() throws InterruptedException
	    {   
		 pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.name("SMART_Clear")));
		 WebElement btn_ProductClear = driver.findElement(By.xpath("//*[@name='SMART_Clear']")); 
		 btn_ProductClear.click();
		 logger.info("Product Details Cleared:  "+btn_ProductClear);
	    	return btn_ProductClear;
	    	  }
	 
	 public   WebElement SMACoverType(String SMACoverType) throws InterruptedException
	    {   
		 pageLoader();
		 String SGGensmart ="//select[@id='SMARTGENERIC_CoverType']";
			String Smartcovertype ="//*[@id='SMART_CoverType']"; 
			
			if(ExcelConstants.ClientName == "Generic QBE") {
				Smartcovertype = SGGensmart;
		    }
		 WebElement CoverType = driver.findElement(By.xpath(Smartcovertype));  
		 Select dd_CoverType = new Select(CoverType);
		 dd_CoverType.selectByVisibleText(SMACoverType);
		 
	    	return CoverType;
	    	  }
	 
	 public   WebElement SMAPurchaseType(String SMAPurchaseType) throws InterruptedException
	    {   
		 pageLoader();
		 String SGGensmart ="//select[@id='SMARTGENERIC_PurchaseType']";
			String Smartpurtype ="//*[@id='SMART_PurchaseType']"; 
			
			if(ExcelConstants.ClientName == "Generic QBE") {
				Smartpurtype = SGGensmart;
		    }
		 WebElement PurchaseType = driver.findElement(By.xpath(Smartpurtype));  
		 Select dd_PurchaseType = new Select(PurchaseType);
		 dd_PurchaseType.selectByVisibleText(SMAPurchaseType);
		
	    	return PurchaseType;
	    	  }
	 
	 public   WebElement SMAWarrantyTerm(String SMAWarrantyTerm) throws InterruptedException
	    {   
		 pageLoader();
		 String SGGensmart ="//select[@id='SMARTGENERIC_TermMthsBand']";
			String SmartTermMths ="//*[@id='SMART_TermMthsBand']"; 
			
			if(ExcelConstants.ClientName == "Generic QBE") {
				SmartTermMths = SGGensmart;
		    }
		 WebElement WarrantyTerm = driver.findElement(By.xpath(SmartTermMths));  
		 Select dd_WarrantyTerm = new Select(WarrantyTerm);
		 dd_WarrantyTerm.selectByVisibleText(SMAWarrantyTerm);
		 
	    	return WarrantyTerm;
	    	  }
	 
	 public   WebElement SMAWarrantyMileage(String SMAWarrantyMileage) throws InterruptedException
	    {   
		 pageLoader();
		 String SGGensmart ="//select[@id='SMARTGENERIC_TermMileageBand']";
			String SmartTermMileage ="//*[@id='SMART_TermMileageBand']"; 
			
			if(ExcelConstants.ClientName == "Generic QBE") {
				SmartTermMileage = SGGensmart;
		    }
		 WebElement WarrantyMileage = driver.findElement(By.xpath(SmartTermMileage));  
		 Select dd_WarrantyMileage = new Select(WarrantyMileage);
		 dd_WarrantyMileage.selectByVisibleText(SMAWarrantyMileage);
		
	    	return WarrantyMileage;
	    	  }
	 
	 public   WebElement SMAClaimLimit(String SMAClaimLimit) throws InterruptedException
	    {   
		 pageLoader();
		 String SGGensmart ="//select[@id='SMARTGENERIC_ClaimLimitBand']";
			String SmartClaimLimit ="//*[@id='SMART_ClaimLimitBand']"; 
			
			if(ExcelConstants.ClientName == "Generic QBE") {
				SmartClaimLimit = SGGensmart;
		    }
		 WebElement ClaimLimit = driver.findElement(By.xpath(SmartClaimLimit));  
		 Select dd_ClaimLimit = new Select(ClaimLimit);
		 dd_ClaimLimit.selectByVisibleText(SMAClaimLimit);
		 Screenshot.takeScreenshot();
	    	return ClaimLimit;
	    	  }
	 
	 public   WebElement btn_PlanCreatenext() throws InterruptedException
	    {   
		 pageLoader();
		
		 
		 WebElement btn_PlanCreatenext = driver.findElement(By.xpath("//*[@id='Next']"));  
		 actions.moveToElement(btn_PlanCreatenext);
		 actions.perform();
		 Thread.sleep(1000);
		 btn_PlanCreatenext.click();
	    	return btn_PlanCreatenext;
	    	  }
	 
	 public   void btn_NewCustomer() throws InterruptedException
	    {  
		 pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.id("transnewCust")));
		 WebElement btn_NewCustomer = driver.findElement(By.xpath("//*[@id='transnewCust']"));  
		 btn_NewCustomer.click();
	    	
	    	  }
	 
	 public   void btn_VehPageNext() throws InterruptedException
	    {   
		pageLoader();
		String className = "performAction";
		String id = "next";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='next']")));
		 WebElement btn_VehPageNext = driver.findElement(By.xpath("//input[contains(@class, '" + className + "') and @id='" + id + "']")); 
		 Screenshot.takeScreenshot();
		 actions.moveToElement(btn_VehPageNext);
		 
		 actions.perform();
		 btn_VehPageNext.click();
	    	
	    	  }
	 
	 public   void DisbExpand() throws InterruptedException
	    {   
		pageLoader();
		 WebElement la_DisbExpand = driver.findElement(By.id("Disbursements_img_MBI_0_false")); 
		 Screenshot.takeScreenshot();
		 actions.moveToElement(la_DisbExpand);
		 
		 actions.perform();
		 la_DisbExpand.click();
	    	
	    	  }
	 public   WebElement ReasonCode() throws InterruptedException
	    {   
		 pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ReasonCode_Amend']")));
		 WebElement ReasonCode = driver.findElement(By.xpath("//*[@id='ReasonCode_Amend']"));  
			 return ReasonCode;
	    	  }
	 
	 public   WebElement ReasonNotes() throws InterruptedException
	    {   
		pageLoader();
		 WebElement ReasonNotes = driver.findElement(By.xpath("//*[@id='Amend_Notes']"));  
		
		 Screenshot.takeScreenshot();		
	    	return ReasonNotes;
	    	  }
	 
	 public   WebElement btn_ReqAmend() throws InterruptedException
	    {   
		pageLoader();
		 WebElement btn_Notes = driver.findElement(By.xpath("//*[@id='next']"));  
		 btn_Notes.click();
		 Screenshot.takeScreenshot();
		 WebElement btn_reqAmend = driver.findElement(By.xpath("//*[@id='majoramend']"));  
		 btn_reqAmend.click();
		 Screenshot.takeScreenshot();
	    	return btn_Notes;
	    	  }
	 
	 public Map<String, String> derivedDetailsMBI() {
		 
		 
		 pageLoader();
		 logger.info("Executing derivedDetails");

		    Map<String, String> derivedDetailsMap = new HashMap<String, String>();
		    WebElement derivedDetailsIcon = driver.findElement(By.xpath("//em[@data-tabid='active_deriveddetails_MBI' and @id='Derived_img_MBI_0']"));
		    derivedDetailsIcon.click();
		    pageLoader();

		    Map<String, String> dateFieldXpaths = new HashMap<String, String>();
		    String MBIMWEDXpath = "//td[@id='01_active_MBI_ManuWarExpiryDate']";
		   
		    dateFieldXpaths.put("MBIactMWED", MBIMWEDXpath);
		    
		    // Add other desired key-value pairs of field name and XPath here

		    SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy"); // Update the input date format if necessary
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

		    for (Map.Entry<String, String> entry : dateFieldXpaths.entrySet()) {
		        String key = entry.getKey();
		        String xpath = entry.getValue();

		        WebElement dateElement = driver.findElement(By.xpath(xpath));
		        String dateText = dateElement.getText();

		        try {
		            java.util.Date date = inputFormat.parse(dateText);
		            String formattedDate = outputFormat.format(date);
		            derivedDetailsMap.put(key, formattedDate);
		        } catch (ParseException e) {
		            // Handle any potential parsing exceptions
		            e.printStackTrace();
		        }
		    }

		    return derivedDetailsMap;
		}
public Map<String, String> derivedDetailsSMA() {
		 
		 
		 pageLoader();
		 logger.info("Executing derivedDetails");

		    Map<String, String> SMAderivedDetailsMap = new HashMap<String, String>();
		    WebElement SMAderivedDetailsIcon = driver.findElement(By.xpath("//em[@data-tabid='active_deriveddetails_SMART' and @id='Derived_img_SMART_0']"));
		    SMAderivedDetailsIcon.click();
		    pageLoader();

		    Map<String, String> dateFieldXpaths = new HashMap<String, String>();
		    String SMAMWEDXpath = "//td[@id='01_active_SMART_ManuWarExpiryDate']";
		  
		    dateFieldXpaths.put("SMAactMWED", SMAMWEDXpath);
		    // Add other desired key-value pairs of field name and XPath here

		    SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy"); // Update the input date format if necessary
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

		    for (Map.Entry<String, String> entry : dateFieldXpaths.entrySet()) {
		        String key = entry.getKey();
		        String xpath = entry.getValue();

		        WebElement dateElement = driver.findElement(By.xpath(xpath));
		        String dateText = dateElement.getText();

		        try {
		            java.util.Date date = inputFormat.parse(dateText);
		            String formattedDate = outputFormat.format(date);
		            SMAderivedDetailsMap.put(key, formattedDate);
		        } catch (ParseException e) {
		            // Handle any potential parsing exceptions
		            e.printStackTrace();
		        }
		    }

		    return SMAderivedDetailsMap;
		}

	 
	
	
	
	
}
