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

public class O15TNGTerminalInfoW {
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
	    	String marchantId = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,0);
			String MerchantKey = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,1);
			String Url = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,2);
			String MerchTerminalID = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,3);
			String CardAcceptorName = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,4);
			String MerchCatCode = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,5);
			String CardName = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,6);
			String CardNumber = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,7);
			String ExpMonth = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,8);
			String ExpYear = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,9);
			String cvv = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",i,10);
			String referenceNum = RetrefNumber.refNumber;
			Object data[][]={{marchantId,MerchantKey,Url,referenceNum,MerchTerminalID, CardAcceptorName, MerchCatCode,CardName,CardNumber,ExpMonth,ExpYear,cvv}};
			return data;
	    }
	    @Test (dataProvider = "dataProvi")
		public void TerminalInfoWRond(String marchantId,String MerchantKey,String Url,String referenceNum, String MerchTerminalID,String CardAcceptorName,String MerchCatCode, String CardName,
				String CardNumber,String ExpMonth,String ExpYear,String cvv) throws InterruptedException 
			{
	    	BrowserActionsHpp actions = new BrowserActionsHpp();
			actions.clearAndEnter(driver, HPPpage.txt_MerchantGUID, marchantId,"MerchantGUID");
			actions.clearAndEnter(driver, HPPpage.txt_MerchantKey, MerchantKey,"MerchantKey");
			actions.clearAndEnter(driver, HPPpage.txt_Url, Url,"Url");
			Thread.sleep(2000);
			actions.Click1(driver, HPPpage.chkbx_TerminalInfo,"Show Items Info");
			Thread.sleep(2000);
			actions.clearAndEnter(driver, HPPpage.txt_TerminalID, MerchTerminalID,"TerminalID");
			Thread.sleep(2000);
			actions.clearAndEnter(driver, HPPpage.txt_CardAcceptorName, CardAcceptorName,"CardAcceptorName");
			Thread.sleep(2000);
			actions.clearAndEnter(driver, HPPpage.txt_MerchCategoryCode, MerchCatCode,"MerchCategoryCode");
			Thread.sleep(2000);
			actions.Clickjs(driver, HPPpage.chkbx_GenRuntime,"Gen Runtime checkbox");
			actions.AutoRefNumbers(driver, HPPpage.txt_MerReference, referenceNum,"MerReference");
			actions.Click1(driver, HPPpage.btn_submit,"Submit");
			ref1= actions.getElementText(driver, HPPpage.RefNumoftran,"Reference number in tran");
			System.out.println("Reference of TerminalInfo with MCC transaction: "+ref1);
			actions.Click1(driver, HPPpage.btn_PaywithnewCard,"PAY with new Card");
			actions.clearAndEnter(driver, HPPpage.txt_CardName, CardName,"CardName");
			actions.clearAndEnter(driver, HPPpage.txt_CardNumber, CardNumber,"CardNumber");
			actions.selectValfromDDUsingText(driver, HPPpage.drp_ExpMonth, ExpMonth,"ExpMonth");
			actions.selectValfromDDUsingText(driver, HPPpage.drp_ExpYear, ExpYear,"ExpYear");
			actions.clearAndEnter(driver, HPPpage.txt_cvv, cvv,"CVV");
			actions.Click1(driver, HPPpage.btn_PayNow,"PAYNOW");	
/*getElementText*/
//			Thread.sleep(2000);
//			String ref= actions.getElementText(driver, HPPpage.txt_RReference,"MReference");
//			System.out.println(ref);
			
		}
		@AfterMethod
		public void afterMethod(ITestResult result) {
			if(result.getStatus() == ITestResult.SUCCESS) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",1,11,ref1);
			}else if(result.getStatus() == ITestResult.FAILURE) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","TermnlID",1,11,ref1);
			}
			driver.quit();
		}
}