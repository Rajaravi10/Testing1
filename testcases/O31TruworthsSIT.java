package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basefunctions.BrowserActionsHpp;
import basefunctions.BrowserFactory;
import basefunctions.ExcelUtils;
import basefunctions.RefNumber;
import repository.HPPpage;

public class O31TruworthsSIT {
	WebDriver driver ;
	ExcelUtils excelUtils = new ExcelUtils();
	RefNumber RetrefNumber = new RefNumber();
	String ref1;
/*Launch browser method*/ 
		@BeforeMethod
	public void beforeMethod() {
		BrowserFactory factory1 = new BrowserFactory();
		driver = factory1.launchBrowser();
		
		BrowserActionsHpp actions = new BrowserActionsHpp();
		actions.navigateUrl(driver);
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	}
	
	 @DataProvider
	    public Object[][] dataProvi(){
	    	
	    	int i=1;
	    	String marchantId = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,0);
			String MerchantKey = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,1);
			String Url = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,2);
			String Amount = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,3);
			String UserID = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,4);
			String CardName = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,5);
			String CardNumber = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,6);
			String ExpMonth = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,7);
			String ExpYear = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,8);
			String cvv = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,9);
			String password = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",i,10);
			String referenceNum = RetrefNumber.refNumber;
			Object data[][]={{marchantId,MerchantKey,Url,referenceNum,Amount,UserID,CardName,CardNumber,ExpMonth,ExpYear,cvv,password}};
			return data;
	    }
	    @Test (dataProvider = "dataProvi")
		public void TruworthsSIT(String marchantId,String MerchantKey,String Url,String referenceNum,String Amount,String UserID,String CardName,
				String CardNumber,String ExpMonth,String ExpYear,String cvv,String password) throws InterruptedException 
			{
				
	    	BrowserActionsHpp actions = new BrowserActionsHpp();
			actions.clearAndEnter(driver, HPPpage.txt_MerchantGUID, marchantId,"MerchantGUID");
			actions.clearAndEnter(driver, HPPpage.txt_MerchantKey, MerchantKey,"MerchantKey");
			actions.clearAndEnter(driver, HPPpage.txt_Url, Url,"Url");
			Thread.sleep(2000);
//			actions.ClickEleWithoutWait(driver, HPPpage.dd_RecPay,"Clicked on CNP Recurring option");
			actions.clearAndEnter(driver, HPPpage.txt_Amount, Amount,"Amount");
			actions.clearAndEnter(driver, HPPpage.txt_UserID, UserID,"UserID");
			Thread.sleep(2000);
			actions.Clickjs(driver, HPPpage.chkbx_GenRuntime,"Gen Runtime checkbox");
			actions.AutoRefNumbers(driver, HPPpage.txt_MerReference, referenceNum,"MerReference");
			actions.Click1(driver, HPPpage.btn_submit,"Submit");
			ref1= actions.getElementText(driver, HPPpage.RefNumoftran,"Reference number in tran");
			System.out.println("Reference of Isando Truworths SIT transaction: "+ref1);
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

			Thread.sleep(5000);
		}
	    @AfterMethod
		public void afterMethod(ITestResult result) {
			if(result.getStatus() == ITestResult.SUCCESS) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",1,11,ref1);
			}else if(result.getStatus() == ITestResult.FAILURE) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\ShopriteandTruworthsMerchs.xlsx","TruSIT",1,11,ref1);
			}
			driver.quit();
	    }
}