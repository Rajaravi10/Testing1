package repository;

import org.openqa.selenium.By;

public class HPPpage {
/*Merchant info required*/	
	public static By txt_MerchantGUID = By.name("merchantId");
	public static By txt_MerchantKey = By.name("merchantKey");
	public static By txt_Url = By.name("url");
/*User Id and Amount*/	
	public static By txt_Amount = By.name("Amount");
	public static By txt_UserID = By.name("UserId ");
/*currency Type*/	
	public static By cur_Botswana = By.xpath("//option[@value='23'][@label='Botswana pula (072)']");
/*Transaction Type's*/	
	public static By dd_Authorize = By.xpath("//option[@value='Authorize']");
	public static By dd_PreAuth = By.xpath("//option[@value='PreAuthorize']");
	public static By dd_RecPay = By.xpath("//option[@value='RecPayment']");
/*Merchant Reference details*/
	public static By chkbx_GenRuntime = By.xpath("(//input[@type='checkbox'])[1]");
	public static By chkbx_Lightbox = By.xpath("(//input[@type='checkbox'])[2]");
	public static By txt_MerReference = By.name("MerchantReference");
/*buttons*/
	public static By btn_submit = By.xpath("//button[@class='btn btn-sm btn-primary'][@ng-click='submitHPP(model)']");
	public static By btn_Addcard = By.xpath("//button[@type='submit']");
	public static By btn_AddCard = By.xpath("//button[@class='btn btn-sm btn-warning']");
/*check boxes in HPP Tester*/
	public static By chkbx_ClientInfo = By.xpath("//input[@type='checkbox'][@ng-checked='!!conf.showClientInfo']");
	public static By chkbx_CostInfo = By.xpath("//input[@type='checkbox'][@ng-checked='!!conf.showCostInfo']");
	public static By chkbx_BillInfo = By.xpath("//input[@type='checkbox'][@ng-checked='!!conf.showBillingInfo']");
	public static By chkbx_ShipInfo = By.xpath("//input[@type='checkbox'][@ng-checked='!!conf.showShippingInfo']");
	public static By chkbx_ItemsInfo = By.xpath("//input[@type='checkbox'][@ng-checked='!!conf.showItemsInfo']");
	public static By chkbx_TerminalInfo = By.xpath("//input[@type='checkbox'][@ng-checked='!!conf.showTerminalInfo']");
	public static By chkbx_TrailSub = By.xpath("//input[@type='checkbox'][@ng-checked='!!conf.showTrialSubscription']");
/*Fields of sub merchant*/	
	public static By txt_TerminalID = By.name("CardAcceptorTerminalId");
	public static By txt_CardAcceptorName = By.name("CardAcceptorName");
	public static By txt_MerchCategoryCode = By.name("MerchantCategoryCode");
/*screen-2 (payment methods)*/
	public static By radbtn_cnp = By.xpath("//div[@class='control__indicator']");
	public static By RefNumoftran = By.xpath("//html/body/div/hpp-master/div/div/div/div/div/div/div[3]/ul/li[2]/span[@class='text-primary']");
	/*MPass*/
	public static By radbtn_mpass = By.xpath("html/body/div[1]/hpp-master/div/div/div[2]/div/div/ui-view/div/div/div/div[2]/ul/li[2]/div/label/div");
	/*Snapscan*/
	public static By radbtn_SScan = By.xpath("html/body/div[1]/hpp-master/div/div/div[2]/div/div/ui-view/div/div/div/div[2]/ul/li[3]/div/label/div");
	/*EFTSec*/
	public static By radbtn_eft= By.xpath("//html/body/div[1]/hpp-master/div/div/div[2]/div/div/ui-view/div/div/div/div[2]/ul/li[4]/div/label/div");
	/*SID*/	
	public static By radbtn_sid= By.xpath("//html/body/div[1]/hpp-master/div/div/div[2]/div/div/ui-view/div/div/div/div[2]/ul/li[3]/div/label/div");
	public static By btn_continue = By.xpath("//button[@class='btn btn-primary btn-lg']");
/*screen-3 (pay with new card)*/
	public static By btn_PaywithnewCard = By.xpath("//button[@class='btn btn-primary  pull-right'][@type='button']");
/*pay with new card, when saved cards are not available*/
	public static By btn_PaywithnewCard1 = By.name("name");
	public static By txt_CardName = By.name("name");
	public static By txt_CardNumber = By.name("creditCard");
	public static By drp_ExpMonth = By.name("month");
	public static By drp_ExpYear = By.name("year");
	public static By txt_cvv = By.name("securityCode");
	public static By mpgs_cvv = By.xpath("//input[@name='securityCode'][@placeholder='CVV']");
	public static By txt_Email = By.name("email");
	public static By chkbox_SaveCard = By.xpath("//input[@type='checkbox']");
	public static By btn_PayNow = By.xpath("//button[@class='btn btn-primary btn-lg']");
/*ACS screen-1*/
	public static By frame1 = By.name("secure3dFrame");
	public static By frame2 = By.name("myIFrame");
	public static By btn_ClickhereContin = By.name("submitBtn");
/*ACS screen-2*/
	public static By txt_Password = By.name("password");
	public static By btn_ACSSubmit = By.name("submit");
/*Summary of successful transaction*/
	/*Reference, Amount, Result*/
	public static By txt_RReference = By.xpath("//table//tbody//tr[1]/td[2]");
	public static By txt_RAmount = By.xpath("//table//tr[3]/td[2]");
	public static By txt_RResult = By.xpath("//table//tr[5]/td[2]");
/*EFT Secure Payment  screen*/
	public static By EFTSubmit = By.xpath("//div//button[@class='btn btn-primary']");	
/*EFTSec screen-1*/
	public static By frame3 = By.name("eftSecureFrame");
/*Fields inside EFTsecure panel*/
	public static By btn_fnb = By.xpath("//button[@type='submit'][@name='bank'][@value='fnb']");
	public static By btn_SignIn = By.xpath("//button[@type='submit'][@name='submit']");
	public static By btn_PaidResponse = By.xpath("//button[@type='submit'][@name='submit'][@value=1]");
/*SIDtran screen-1*/
	/*sidFrame*/
	public static By frame4 = By.name("sidFrame");
	public static By SIDTestCompleted = By.xpath("//div[@class='bank-text'][1]");
/*MPGS screen*/	
	public static By frame5 = By.tagName("iframe");
	public static By mpgs_submit = By.xpath("//input[@type='submit'][@value='Submit']");
/*AddCard functionality*/
	public static By NameOnCard = By.name("name");
	public static By CardNumber = By.name("creditCard");
	public static By EMonth = By.xpath("//input[@placeholder='MM']");
	public static By Eyear = By.xpath("//input[@placeholder='YY']");
}