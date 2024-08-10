package PlanTestCases;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import customerModule.customerFields;
import loginModule.GenericLogin;
import loginModule.Login_with_ClientUsers;
import loginModule.Webdrivers;
import planModule.DerivedandDisbs;
import planModule.PlanModElements;
import utility.DataReader;
import utility.ExcelConstants;
import utility.ExcelUtility;

public class CreateNewPlan extends Webdrivers {

	private PlanModElements NewPlan;
	private customerFields Customer;
	private DerivedandDisbs Derivied;
	private WebDriverWait wait;
	private boolean  ismbi = true;
	private boolean  issmart= true;
	private DataReader dataReader = new DataReader();
	private static final Logger logger = LogManager.getLogger(CreateNewPlan.class);
	private Map<String, String> warrantyDetailsMap;
	private Map<String, String> disbCaseMap;
	private Object[] disbursements ;
	
	Object[][] testCaseData = null;
	String testScenario = null;
	    
	    @BeforeClass
	    public void setup() {
	        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        NewPlan = new PlanModElements();
	        Customer = new customerFields();
	        Derivied = new DerivedandDisbs();
	    }
	    
	    
	    @BeforeClass
		public void plantestCaseData(ITestContext context) throws Exception {
			String testCaseName = "Plan";

			testCaseData = ExcelUtility.getTableArray(ExcelConstants.File_Path, "TestCases", testCaseName);

			for (Object[] testData : testCaseData) {
				Map<String, String> testCaseMap = (Map<String, String>) testData[0];

				System.out.println("testCaseData: " + testCaseMap);
				testScenario = testCaseMap.get("TestScenario");
				System.out.println("TestScenario: " + testScenario);
				if (testScenario != null) {
					dataReader.setLoginTestCaseName(testScenario, testCaseName);
				} else {
					logger.error("TestScenario is null for test case: " + testCaseName);
				}
			}
		}
	

	@SuppressWarnings("unused")
	public void MMTDetails(String sMake, String sModel, String sclass, String sturbo, String sTransmission,
		String sdrivetype, String sfueltype) throws InterruptedException {
		
		WebElement Make = NewPlan.Make(sMake);
		WebElement Model = NewPlan.Model(sModel);
		WebElement Class = NewPlan.Class(sclass);
		WebElement Turbo = NewPlan.Turbo(sturbo);
		WebElement Transmission = NewPlan.Transmission(sTransmission);
		WebElement Drivetype = NewPlan.DriveType(sdrivetype);
		WebElement Fueltype = NewPlan.FuelType(sfueltype);

	}

	@SuppressWarnings("unused")
	public void MWDetails(String sEngineCC, String sMWSD, String sMWTerm, String sMWMileage, String sMileage,
		String sVSoldDate, String sPolicySoldDate, String sClaimsLimit, String sVIN, String sSellingDealer,
		String sSalesPerson) throws InterruptedException {
		
		WebElement EngineCC = NewPlan.EngineCC(sEngineCC);
		WebElement tb_MWSD = NewPlan.MWSD(sMWSD);
		WebElement dd_MWTerm = NewPlan.MWTerm(sMWTerm);
		WebElement dd_sMWMileage = NewPlan.MWMileage(sMWMileage);
		WebElement tb_sMileage = NewPlan.Mileage(sMileage);
		WebElement tb_sVSoldDate = NewPlan.VSoldDate(sVSoldDate);
		WebElement tb_sPolicySoldDate = NewPlan.PolicySoldDate(sPolicySoldDate);
		WebElement tb_sClaimsLimit = NewPlan.ClaimsLimit(sClaimsLimit);
		WebElement tb_sVIN = NewPlan.Vin(sVIN);
		WebElement dd_sSellingDealer = NewPlan.SellingDealer(sSellingDealer);
		WebElement dd_sSalesPerson = NewPlan.Salesperson(sSalesPerson);
		WebElement showprod = NewPlan.btn_ShowProducts();

	}

	@SuppressWarnings("unused")
	public void MBIProductDetails(String sProduct, String sCoverType, String sPurchaseType, String sWarrantyTerm, String sWarrantyMileage,
			String sClaimLimit) throws InterruptedException {
		
		WebElement ProductCode = NewPlan.ProductCode(sProduct);		  
		WebElement CoverType = NewPlan.CoverType(sCoverType);		
		WebElement WarrantyTerm = NewPlan.WarrantyTerm(sWarrantyTerm);
		WebElement WarrantyMileage = NewPlan.WarrantyMileage(sWarrantyMileage);
	
	}
	@SuppressWarnings("unused")
	public void SmartProductDetails(String smartProduct, String smartCoverType, String smartPurchaseType, String smartWarrantyTerm, String smartWarrantyMileage,
			String smartClaimLimit) throws InterruptedException {					

		WebElement SMAProductCode = NewPlan.SMAProductCode(smartProduct);		  
		WebElement SMACoverType = NewPlan.SMACoverType(smartCoverType);
		WebElement SMAPurchaseType = NewPlan.SMAPurchaseType(smartPurchaseType);
		WebElement SMAWarrantyTerm = NewPlan.SMAWarrantyTerm(smartWarrantyTerm);
		WebElement SMAWarrantyMileage = NewPlan.SMAWarrantyMileage(smartWarrantyMileage);
		WebElement SMAClaimLimit = NewPlan.SMAClaimLimit(smartClaimLimit);	
	}
	@SuppressWarnings("unused")
	public void CustomerDtetails(String stitle,String sFirstName,String sLastName,String sCompanyName,String sAddress1,String sAddress2,String sAddress3,
			String sPostCode,String sEmail,String sCEmail,String sVATCheck,String sVATNum) throws InterruptedException {
		WebElement Title = Customer.Title(stitle);
		WebElement FirstName = Customer.FirstName(sFirstName);
		WebElement LastName = Customer.LastName(sLastName);  
		WebElement CompanyName = Customer.Cname(sCompanyName);
		WebElement Address1 = Customer.Address1(sAddress1);
		WebElement Address2 = Customer.Address2(sAddress2);
		WebElement Address3 = Customer.Address3(sAddress3);
		WebElement PostCode = Customer.PostCode(sPostCode);
		WebElement Email = Customer.Email(sEmail);
		WebElement COnfirmEmail = Customer.CEmail(sCEmail);
		if (sVATCheck.equals(true)) {
		WebElement VATCheck = Customer.VATCheck();
		WebElement VATNum = Customer.VATNum(sVATNum);
		}
	}
	
	public void mbiproductDetails(String Swarranty, String Sdisb) throws Exception {
		Object[][] data = null;
		System.out.println("Swarranty: " + Swarranty);
		String[] SplitSwarranty = Swarranty.split(":");
		int startRow = Integer.parseInt(SplitSwarranty[1]);
		int endRow = Integer.parseInt(SplitSwarranty[1]);
		data = ExcelUtility.getTableData(ExcelConstants.File_Path, SplitSwarranty[0], startRow, endRow);

		for (Object[] prodtestData : data) {
			warrantyDetailsMap = (Map<String, String>) prodtestData[0];

			System.out.println("Warranty testCaseData: " + warrantyDetailsMap);

			String sProduct = warrantyDetailsMap.get("sProduct");
			System.out.println("sProduct: " + sProduct);
			String sCoverType = warrantyDetailsMap.get("sCoverType");
			String sPurchaseType = warrantyDetailsMap.get("sPurchaseType");
			String sWarrantyTerm = warrantyDetailsMap.get("sWarrantyTerm");
			String sWarrantyMileage = warrantyDetailsMap.get("sWarrantyMileage");
			String ClaimLimit = warrantyDetailsMap.get("ClaimLimit");
			
			Object[][] DisbData = null;
		
			System.out.println("Sdisb: " + Sdisb);
			String[] splitDisb = Sdisb.split(":");
			int startRowD = Integer.parseInt(SplitSwarranty[1]);
			int endRowD = Integer.parseInt(SplitSwarranty[1]);
			DisbData = ExcelUtility.getTableData(ExcelConstants.File_Path, splitDisb[0], startRowD, endRowD);
			for (Object[] disbtestData : DisbData) {
				 disbCaseMap = (Map<String, String>) disbtestData[0];
				System.out.println("Warranty disbtestData: " + disbCaseMap);
				
				 disbursements = disbCaseMap.values().toArray();
			
			
				
			}

			MBIProductDetails(sProduct, sCoverType, sPurchaseType, sWarrantyTerm, sWarrantyMileage, ClaimLimit);

		}
	}

	public void smartproductDetails(String Ssmart) throws Exception {
		Object[][] SMAdata = null;
		System.out.println("Ssmart: " + Ssmart);
		String[] SplitSmart = Ssmart.split(":");
		int startRow = Integer.parseInt(SplitSmart[1]);
		int endRow = Integer.parseInt(SplitSmart[1]);
		SMAdata = ExcelUtility.getTableData(ExcelConstants.File_Path, SplitSmart[0], startRow, endRow);

		for (Object[] SMAprodtestData : SMAdata) {
			Map<String, String> testCaseMap = (Map<String, String>) SMAprodtestData[0];
			System.out.println("testCaseData: " + testCaseMap);
			String smartProduct = testCaseMap.get("smartProduct");
			System.out.println("smartProduct: " + smartProduct);
			String smartCoverType = testCaseMap.get("smartCoverType");
			String smartPurchaseType = testCaseMap.get("smartPurchaseType");
			String smartWarrantyTerm = testCaseMap.get("smartWarrantyTerm");
			String smartWarrantyMileage = testCaseMap.get("smartWarrantyMileage");
			String smartClaimLimit = testCaseMap.get("smartClaimLimit");

			SmartProductDetails(smartProduct, smartCoverType, smartPurchaseType, smartWarrantyTerm,
					smartWarrantyMileage, smartClaimLimit);

		}
	}
	
	public void customerDetails(String Scustomer) throws Exception {
		Object[][] data = null;
		System.out.println("Scustomer: " + Scustomer);
		String[] SplitScustomer = Scustomer.split(":");
		int startRow = Integer.parseInt(SplitScustomer[1]);
		int endRow = Integer.parseInt(SplitScustomer[1]);
		data = ExcelUtility.getTableData(ExcelConstants.File_Path, SplitScustomer[0], startRow, endRow);

		for (Object[] custTestData : data) {
			Map<String, String> testCaseMap = (Map<String, String>) custTestData[0];

			System.out.println("testCaseData: " + testCaseMap);

			String stitle = testCaseMap.get("stitle");
			System.out.println("stitle: " + stitle);
			String sFirstName = testCaseMap.get("sFirstName");
			String sLastName = testCaseMap.get("sLastName");
			String sCompanyName = testCaseMap.get("sCompanyName");
			String sAddress1 = testCaseMap.get("sAddress1");
			String sAddress2 = testCaseMap.get("sAddress2");
			String sAddress3 = testCaseMap.get("sAddress3");
			String sPostCode = testCaseMap.get("sPostCode");
			String sEmail = testCaseMap.get("sEmail");
			String sCEmail = testCaseMap.get("sCEmail");
			String sVATCheck = testCaseMap.get("sVATCheck");
			String sVATNum = testCaseMap.get("sVATNum");
			

			CustomerDtetails(stitle, sFirstName, sLastName, sCompanyName, sAddress1, sAddress2, sAddress3, sPostCode,
					sEmail, sCEmail, sVATCheck, sVATNum);
			Customer.CustomerNext();

		}
	}

	@Test(priority = 1, dataProvider = "DataProvider",dataProviderClass = DataReader.class)
	public void createnewPlan(Map<String, Object> testData) throws Exception {
		System.out.println("testData: " + testData);
		String sMake = (String) testData.get("sMake");
		String sModel = (String) testData.get("sModel");
		String sclass = (String) testData.get("sclass");
		String sturbo = (String) testData.get("sturbo");
		String sTransmission = (String) testData.get("sTransmission");
		String sdrivetype = (String) testData.get("sdrivetype");
		String sfueltype = (String) testData.get("sfueltype");
		String sEngineCC = (String) testData.get("sEngineCC");
		String sMWSD = (String) testData.get("sMWSD");
		String sMWTerm = (String) testData.get("sMWTerm");
		String sMWMileage = (String) testData.get("sMWMileage");
		String sMileage = (String) testData.get("sMileage");
		String sVSoldDate = (String) testData.get("sVSoldDate");
		String sPolicySoldDate = (String) testData.get("sPolicySoldDate");
		String sClaimsLimit = (String) testData.get("sClaimsLimit");
		String sVIN = (String) testData.get("sVIN");
		String sSellingDealer = (String) testData.get("sSellingDealer");
		String sSalesPerson = (String) testData.get("sSalesPerson");
		String Swarranty = (String) testData.get("Swarranty");
		String Ssmart = (String)testData.get("Ssmart");
		String Scustomer = (String) testData.get("Scustomer");
		String warranty = (String) testData.get("Warranty");
		boolean warrantyCheck = Boolean.parseBoolean(warranty);
		String smart = (String) testData.get("Smart");
		boolean smartCheck = Boolean.parseBoolean(smart);
		String Sdisb = (String) testData.get("Disbursements");

		NewPlan.EnterNewPlan();
		if (warrantyCheck == false) {
			NewPlan.productMBISel();
			ismbi = false;
		
		}
		if (smartCheck == false) {
			NewPlan.productSMASel();
			 issmart = false;
		}

		NewPlan.productSelnxt();
		MMTDetails(sMake, sModel, sclass, sturbo, sTransmission, sdrivetype, sfueltype);
		MWDetails(sEngineCC, sMWSD, sMWTerm, sMWMileage, sMileage, sVSoldDate, sPolicySoldDate, sClaimsLimit, sVIN,
				sSellingDealer, sSalesPerson);
		
		if(ismbi == true) {
			mbiproductDetails(Swarranty,Sdisb );
		}
		
		if(issmart == true) {
			smartproductDetails(Ssmart);
		}
		
		NewPlan.btn_PlanCreatenext();
		Customer.NewCustomer();
		customerDetails(Scustomer);
		NewPlan.btn_VehPageNext();
		NewPlan.DisbExpand();
		Derivied.Disbursements();
		Derivied.expectedDisbs(disbursements,warrantyDetailsMap);
		Derivied.assertDisbs();
	}



}
