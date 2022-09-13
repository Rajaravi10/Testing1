package testcases;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import basefunctions.BrowserActionsHpp;
import basefunctions.BrowserFactory;
import basefunctions.ExcelUtils;
import basefunctions.RefNumber;
import repository.HPPpage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class O25PSSWC {
	WebDriver driver ;
	ExcelUtils excelUtils = new ExcelUtils();
	static int resultRowNum;
	RefNumber RetrefNumber = new RefNumber();
	String ref1;
/*Launch browser method*/ 
		@BeforeMethod
	public void beforeMethod() 
		{
		BrowserFactory factory1 = new BrowserFactory();
		driver = factory1.launchBrowser();
		
		BrowserActionsHpp actions = new BrowserActionsHpp();
		actions.navigateUrl(driver);
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	}
	 @DataProvider
	    public Object[][] dataProvi(){
	    	
	    	int i=1;
	    	String marchantId = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,0);
			String MerchantKey = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,1);
			String Url = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,2);
			String Amount = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,3);
			String UserID = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,4);
			String CardName = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,5);
			String CardNumber = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,6);
			String ExpMonth = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,7);
			String ExpYear = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,8);
			String cvv = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,9);
			String password = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",i,10);
			String referenceNum = RetrefNumber.refNumber;
			
//			String ref1 = excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\CardsTesting.xlsx","Merchants",i,11 ref1);
			Object data[][]={{marchantId,MerchantKey,Url,referenceNum,Amount,UserID,CardName,CardNumber,ExpMonth,ExpYear,cvv,password}};
			return data;
	    }
//	@DataProvider
//	 public Object[][] readAllData()
//	 {
//		 return excelUtils.readAllData(".\\IsandoData\\CardsTesting.xlsx","Merchants");
//	    	
//	    }	 
	@Test (dataProvider = "dataProvi")
	public void PSSWCcnp(String marchantId,String MerchantKey,String Url,String referenceNum,String Amount,String UserID,String CardName,
				String CardNumber,String ExpMonth,String ExpYear,String cvv,String password) throws InterruptedException 
//	String CardNumber,String ExpMonth,String ExpYear,String cvv,String password, @Optional("Result") String result) throws InterruptedException		
	{
			BrowserActionsHpp actions = new BrowserActionsHpp();
			actions.clearAndEnter(driver, HPPpage.txt_MerchantGUID, marchantId,"MerchantGUID");
			actions.clearAndEnter(driver, HPPpage.txt_MerchantKey, MerchantKey,"MerchantKey");
			actions.clearAndEnter(driver, HPPpage.txt_Url, Url,"Url");
			actions.clearAndEnter(driver, HPPpage.txt_Amount, Amount,"Amount");
			actions.clearAndEnter(driver, HPPpage.txt_UserID, UserID,"UserID");
			Thread.sleep(2000);
			actions.Clickjs(driver, HPPpage.chkbx_GenRuntime,"Gen Runtime checkbox");
			actions.AutoRefNumbers(driver, HPPpage.txt_MerReference, referenceNum,"MerReference");
			actions.Click1(driver, HPPpage.btn_submit,"Submit");
			 ref1= actions.getElementText(driver, HPPpage.RefNumoftran,"Reference number in tran");
			System.out.println("Reference of CNP transaction: "+ref1);
			actions.Click1(driver, HPPpage.radbtn_cnp,"CNPPayment");
			Thread.sleep(2000);
			actions.Click1(driver, HPPpage.btn_continue,"ContinuePAY");
//			actions.Click1(driver, HPPpage.btn_PaywithnewCard,"PAY with new Card");
			actions.clearAndEnter(driver, HPPpage.txt_CardName, CardName,"CardName");
			actions.clearAndEnter(driver, HPPpage.txt_CardNumber, CardNumber,"CardNumber");
			actions.selectValfromDDUsingText(driver, HPPpage.drp_ExpMonth, ExpMonth,"ExpMonth");
			actions.selectValfromDDUsingText(driver, HPPpage.drp_ExpYear, ExpYear,"ExpYear");
			actions.clearAndEnter(driver, HPPpage.txt_cvv, cvv,"CVV");
			actions.Click1(driver, HPPpage.btn_PayNow,"PAYNOW");	
/*ACS screen page */
			actions.numofframes(driver);
			actions.switchToFrame(driver, HPPpage.frame1, "Secure Fram");
			Thread.sleep(2000);
			actions.switchToFrame(driver, HPPpage.frame2, "My Fram");
			Thread.sleep(2000);
/*ACS screen page 2*/
			actions.clearAndEnter(driver, HPPpage.txt_Password, password,"Password");
			Thread.sleep(1000);		
			actions.Click1(driver, HPPpage.btn_ACSSubmit,"ACSSubmit");
			driver.switchTo().parentFrame();
			driver.switchTo().parentFrame();
/*getElementText*/
			Thread.sleep(4000);
		}
	
		@AfterMethod
		public void afterMethod(ITestResult result) {
			if(result.getStatus() == ITestResult.SUCCESS) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",1,11,ref1);
			}else if(result.getStatus() == ITestResult.FAILURE) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","PSSWC",1,11,ref1);
			}
			driver.quit();
		}

}