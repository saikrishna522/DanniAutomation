package customerModule;



import loginModule.LoginPage;
import loginModule.Webdrivers;

import utility.Screenshots;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


public class customerFields extends Webdrivers {
	
	
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions actions = new Actions(driver);
	
	LoginPage Login =new LoginPage();
	Screenshots	Screenshot = new Screenshots();	
	
	
	public  WebElement EnterNewCust() throws InterruptedException {	
		
		Login.pageLoader();
			//wait.until(ExpectedConditions.elementToBeClickable(By.id("CustomerManager")));
			WebElement mainMenu = driver.findElement(By.id("CustomerManager"));
			actions.moveToElement(mainMenu);
			actions.click().build().perform();
			
			WebElement newcustomer = driver.findElement(By.id("newcustomer"));
			Reporter.log("Clicked on New Customer..");
			//Hovering on main menu
			actions.moveToElement(newcustomer);
			actions.click().build().perform();
		return mainMenu;
	}
	
	public void NewCustomer() {
		Login.pageLoader();
		WebElement newCustomer = driver.findElement(By.id("transnewCust"));
		newCustomer.click();
		Login.pageLoader();
	}
	 public   WebElement VATCheck() throws InterruptedException
	    {   
		 Login.pageLoader();
		 WebElement VATCheckT = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@class=\"radio-inline\"]/input[@value=\"true\"]"))); 
		 actions.moveToElement(VATCheckT);
		 VATCheckT.click();
			
	    	return VATCheckT;
	    }
	 public WebElement CustType() throws InterruptedException {
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='C_Type_Select']")));
			//(driver.findElement(By.xpath("//*[@id='C_Title_Select']")));
			WebElement CustType = driver.findElement(By.xpath("//*[@id='C_Type_Select']"));
			
			return CustType;
		}
	
	public WebElement Title(String titleValue) throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='C_Title_Select']")));
		//(driver.findElement(By.xpath("//*[@id='C_Title_Select']")));
		WebElement title = driver.findElement(By.xpath("//*[@id='C_Title_Select']"));
		Screenshot.takeScreenshot();
		Select  dd_title = new Select(title);
		dd_title.selectByValue(titleValue);
		return title;
	}
	
	 public   WebElement FirstName(String FnameValue) throws InterruptedException
	    {   
	    	WebElement FirstName = driver.findElement(By.xpath("//*[@id='C_FirstName']"));  
	    	FirstName.sendKeys(FnameValue);
	    	return FirstName;

	    }
	 public   WebElement LastName(String LnameValue) throws InterruptedException
	    {   
		 WebElement LastName = driver.findElement(By.xpath("//*[@id='C_LastName']"));
		 LastName.sendKeys(LnameValue);
	    	return LastName;

	    }
	 public   WebElement Cname(String CnameValue) throws InterruptedException
	    {   
		 WebElement Cname = driver.findElement(By.xpath("//*[@id='C_CompanyName']"));    
		 Cname.sendKeys(CnameValue);
	    	return Cname;

	    }
	 public   WebElement Address1(String Add1Value) throws InterruptedException
	    {   
		 WebElement Address1 = driver.findElement(By.xpath("//*[@id='C_Address1']"));      
		 Address1.sendKeys(Add1Value);
	    	return Address1;

	    }
	 public   WebElement Address2(String Add2Value) throws InterruptedException
	    {   
		 WebElement Address2 = driver.findElement(By.xpath("//*[@id='C_Address2']"));
		 Address2.sendKeys(Add2Value);
	    	return Address2;

	    }
	 public   WebElement Address3(String Add3Value) throws InterruptedException
	    {   
		 WebElement Address3 = driver.findElement(By.xpath("//*[@id='C_Address3']"));  
		 Address3.sendKeys(Add3Value);
	    	return Address3;
	    	  }
	 
	 public   WebElement State() throws InterruptedException
	    {   
		 WebElement State = driver.findElement(By.xpath("//*[@id='C_Address4']"));  
	    	return State;
	    }
	 public   WebElement Address5() throws InterruptedException
	    {   
		 WebElement Address5 = driver.findElement(By.xpath("//*[@id='C_Address5']"));     
	    	return Address5;
	    }
	 
	 public   WebElement Address6() throws InterruptedException
	    {   
		 WebElement Address6 = driver.findElement(By.xpath("//*[@id='C_Address6']"));     
	    	return Address6;
	    }
	 
	 public   WebElement PostCode(String PostcodeValue) throws InterruptedException
	    {   
		 WebElement PostCode = driver.findElement(By.xpath("//*[@id='C_PostZip']"));   
		 PostCode.sendKeys(PostcodeValue);
	    	return PostCode;
	    }
	 
	 public   WebElement NID() throws InterruptedException
	    {   
		 WebElement NID = driver.findElement(By.xpath("//*[@id='C_Extra4']"));        
	    	return NID;
	    }
	 
	 public   WebElement Email(String EmailValue) throws InterruptedException
	    {   
		 WebElement Email = driver.findElement(By.xpath("//*[@id='C_Email']")); 
		 Email.sendKeys(EmailValue);
	    	return Email;
	    }
	 
	 public   WebElement CEmail(String CEmailValue) throws InterruptedException
	    {   
		 WebElement CEmail = driver.findElement(By.xpath("//*[@id='C_CEmail']"));
		 CEmail.sendKeys(CEmailValue);
	    	return CEmail;
	    }
	
	 public   WebElement VATNum(String VatValue) throws InterruptedException
	    {   
		 Login.pageLoader();
		 WebElement VATNum = driver.findElement(By.xpath("//*[@id='C_Extra1']"));  
		 Screenshot.takeScreenshot();
		 VATNum.sendKeys(VatValue);
	    	return VATNum;
	    }
	 public   WebElement regNum() throws InterruptedException
	    {   
		 Login.pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='V_RegNo']")));
		 WebElement regNum = driver.findElement(By.xpath("//*[@id='V_RegNo']"));  
		 Screenshot.takeScreenshot();
	    	return regNum;
	    }
	 public   void CustomerNext() throws InterruptedException
	    {   
		 Login.pageLoader();
		 WebElement Next = driver.findElement(By.xpath("//*[@id='next']"));  
		 actions.moveToElement(Next);
		 actions.perform();
		 Screenshot.takeScreenshot();
	    	Next.click(); 
	    	
	    	
	    }
	 public   WebElement Save() throws InterruptedException
	    {   
		 WebElement save = driver.findElement(By.xpath("//*[@id='savecust']"));        
		 save.click();      
	    	return save;
	    }
	 public   WebElement Savedcustomer() throws InterruptedException
	    {   
		 Login.pageLoader();
			JavascriptExecutor js = (JavascriptExecutor) driver;
	    	WebElement Savedcustomer = driver.findElement(By.xpath("//*[@id='savedcustomer']")); 
	    	js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	    	Savedcustomer.click();     
	    	return Savedcustomer;
	    }
	 
	 public   WebElement btn_VehPageNext() throws InterruptedException
	    {   
		 Login.pageLoader();
		 WebElement btn_VehPageNext = driver.findElement(By.xpath("//*[@id='next']"));  
		 Screenshot.takeScreenshot();
		 btn_VehPageNext.click();
		
	    	return btn_VehPageNext;
	    	  }
	 
	 public   WebElement RegisterPlan() throws InterruptedException
	    {   
		 Login.pageLoader();
		 WebElement RegisterPlan = driver.findElement(By.xpath("//*[@id='saveplan']"));  
		 js.executeScript("arguments[0].scrollIntoView();", RegisterPlan);
		 Screenshot.takeScreenshot();
		 RegisterPlan.click();
		
	    	return RegisterPlan;
	    	  }
	 
	 public   WebElement PlanNum() throws InterruptedException
	    {   
		 Login.pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.id("pnumber")));
		 WebElement PlanNum = driver.findElement(By.xpath("//*[@id='pnumber']"));  
		 Screenshot.takeScreenshot();
		 PlanNum.click();
		
		 Thread.sleep(1000);
		 Screenshot.takeScreenshot();
	    	return PlanNum;
	    	  }
	 
	 public   WebElement EmailCust() throws InterruptedException
	    {   
		 Login.pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.id("okay")));
		 WebElement emailcust = driver.findElement(By.xpath("//*[@id='okay']"));  
		
		 emailcust.click();
		 Login.pageLoader();
		 wait.until(ExpectedConditions.elementToBeClickable(By.id("pnumber")));
		 WebElement PlanNum = driver.findElement(By.xpath("//*[@id='pnumber']"));  
		 Screenshot.takeScreenshot();
		 PlanNum.click();

		 Screenshot.takeScreenshot();
	    	return emailcust;
	    	  }
  }
