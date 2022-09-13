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

public class O1TNGcnpinPayments {
	WebDriver driver ;
	ExcelUtils excelUtils = new ExcelUtils();
	RefNumber RetrefNumber = new RefNumber();
	String ref1;
//	private String String;
	static int resultRowNum;
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
	    	String marchantId = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,0);
			String MerchantKey = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,1);
			String Url = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,2);
			String Amount = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,3);
			String UserID = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,4);
			String CardName = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,5);
			String CardNumber = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,6);
			String ExpMonth = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,7);
			String ExpYear = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,8);
			String cvv = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,9);
			String password = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,10);
			String referenceNum = RetrefNumber.refNumber;
			
			Object data[][]={{marchantId,MerchantKey,Url,referenceNum,Amount,UserID,CardName,CardNumber,ExpMonth,ExpYear,cvv,password}};
			return data;
	    }
//	@DataProvider
//	 public Object[][] readAllData()
//	 {
//		 return excelUtils.readAllData(".\\IsandoData\\IsandoDailyReport.xlsx","CNP");
//	    	
//	 }	 
	@Test (dataProvider = "dataProvi")
//	@Test (dataProvider = "readAllData")
	public void cnppayment(String marchantId,String MerchantKey,String Url, String referenceNum,String Amount,String UserID,String CardName,
//				String CardNumber,String ExpMonth,String ExpYear,String cvv,String password, @Optional("Result") String result) throws InterruptedException
	String CardNumber,String ExpMonth,String ExpYear,String cvv,String password) throws InterruptedException
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
			actions.Click1(driver, HPPpage.btn_continue,"Continue Button");
			Thread.sleep(2000);
			actions.clearAndEnter(driver, HPPpage.txt_CardName, CardName,"CardName");
			actions.clearAndEnter(driver, HPPpage.txt_CardNumber, CardNumber,"CardNumber");
			actions.selectValfromDDUsingText(driver, HPPpage.drp_ExpMonth, ExpMonth,"ExpMonth");
			actions.selectValfromDDUsingText(driver, HPPpage.drp_ExpYear, ExpYear,"ExpYear");
			actions.clearAndEnter(driver, HPPpage.txt_cvv, cvv,"CVV");
			actions.Click1(driver, HPPpage.btn_PayNow,"PAYNOW");	
			Thread.sleep(6000);
/* ACS screen*/
			actions.switchToFrame(driver, HPPpage.frame1, "Secure Fram");
			actions.switchToFrame(driver, HPPpage.frame2, "My Fram");
			Thread.sleep(3000);
/*ACS screen page 2*/
			actions.clearAndEnter(driver, HPPpage.txt_Password, password,"Password");
			Thread.sleep(3000);		
			actions.Click1(driver, HPPpage.btn_ACSSubmit,"ACSSubmit");
			Thread.sleep(2000);
			driver.switchTo().parentFrame();
			driver.switchTo().parentFrame();
			
/*CNP transaction Result page*/
			//getElementText
			Thread.sleep(6000);
//			String ref= actions.getElementText(driver, HPPpage.txt_RReference,"MReference");
//			System.out.println(ref);
		}
		@AfterMethod
		public void afterMethod(ITestResult result) 
		{
			resultRowNum=resultRowNum+1;
			if(result.getStatus() == ITestResult.SUCCESS) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",1,11,ref1);
			}else if(result.getStatus() == ITestResult.FAILURE) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",1,11,ref1);
			}
			driver.quit();
		}

}