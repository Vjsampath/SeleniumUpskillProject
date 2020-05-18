package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.UniformHomePOM;
import com.training.pom.UniformItemDescPagePOM;
import com.training.pom.UniformLoginPagePOM;
import com.training.pom.UniformCheckOutPagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UNF_033CheckOutPlaceOrder {

	private WebDriver driver;
	private String baseUrl;
	private UniformHomePOM UniformHomePOM;
	private UniformLoginPagePOM UniformLoginPagePOM;
	private UniformItemDescPagePOM UniformItemDescPagePOM;
	private UniformCheckOutPagePOM UniformCheckOutPagePOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);

	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		UniformItemDescPagePOM = new UniformItemDescPagePOM(driver);
		UniformHomePOM = new UniformHomePOM(driver);
		UniformLoginPagePOM = new UniformLoginPagePOM(driver);
		UniformCheckOutPagePOM = new UniformCheckOutPagePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void uniformShoptest() throws InterruptedException {

		String successMsgOfAddCart = "Success: You have added Regular T-Shirt (Maroon) to your shopping cart!";
		// UFM_002MyAccountLogin login = new UFM_002MyAccountLogin();
		// login.myAccountLoginTest();

		UniformHomePOM.myAccountDropdownBtn();
		UniformHomePOM.clickmyAccountLogin();
		UniformLoginPagePOM.sendEmailAdd("vj@gmail.com");
		UniformLoginPagePOM.sendPassword("vj123");
		UniformLoginPagePOM.clickLoginBtn();

		driver.findElement(By.linkText("Uniform Store")).click();

		Thread.sleep(1000);
		UniformHomePOM.clickShopUniform();

		Actions Act = new Actions(driver);
		Action TshritMouseOver = Act.moveToElement(UniformHomePOM.regTshritsReturn()).build();
		TshritMouseOver.perform();

		UniformHomePOM.clickRegTshirtMaroon();

		UniformItemDescPagePOM.selchestSize("36");
		UniformItemDescPagePOM.sendQty("1");
		UniformItemDescPagePOM.clickAddToCartBtn();

		//Asserting Success of Adding to cart
		System.out.println("message " + UniformItemDescPagePOM.alertSuccMsg());
		Assert.assertTrue(UniformItemDescPagePOM.alertSuccMsg().contains(successMsgOfAddCart));
		screenShot.captureScreenShot("UNF_033CheckOutPlaceOrderLogin_Success_AddCart_Msg");
		
		UniformItemDescPagePOM.clickCartIcon();
		UniformItemDescPagePOM.clickViewCartBtn();
		UniformItemDescPagePOM.clickShoppingCartCheckOutBtn();
	
		//Checkout page 
		Assert.assertEquals("CHECKOUT", driver.findElement(By.xpath("//h1[contains(text(),'Checkout')]")).getText());
		screenShot.captureScreenShot("UNF_033CheckOutPlaceOrderLogin_Check_out_Page");
		
		//Assert Billing Accordion Open and continue
		String billingAccordOpen = driver.findElement(By.xpath("//a[@class='accordion-toggle' and contains(text(),'Billing')]")).getAttribute("aria-expanded");
		System.out.println("Billing Acccordion Open " +billingAccordOpen);
		Assert.assertEquals("true", billingAccordOpen);
		Thread.sleep(1000);
		screenShot.captureScreenShot("UNF_033CheckOutPlaceOrderBillingAccordionOpen_Check_out_Page");
		
	    UniformCheckOutPagePOM.clickbillingContinueBtn();
		
	    //Assert Delivery Details Accordion open and continue
        String DeliveryDAccordOpen = driver.findElement(By.xpath("//a[@class='accordion-toggle' and contains(text(),'Delivery')]")).getAttribute("aria-expanded");
		System.out.println("Delivery Details Acccordion Open " +DeliveryDAccordOpen);
		Assert.assertEquals("true", DeliveryDAccordOpen);
		screenShot.captureScreenShot("UNF_033CheckOutPlaceOrderDeliveryDAccordOpen_Check_out_Page");
		
		UniformCheckOutPagePOM.clickdeliveryDContinueBtn();
		UniformCheckOutPagePOM.sendaddComments("Please Deliver between 7am to 10 am");
		UniformCheckOutPagePOM.clickshippingMContinueBtn();
		Thread.sleep(1000);
		UniformCheckOutPagePOM.clickcheckBoxAgree();
		Thread.sleep(1000);
		
		UniformCheckOutPagePOM.clickPaymentMContinueBtn();
		UniformCheckOutPagePOM.clickConfirmOrderContinueBtn();
		Thread.sleep(1000);
		screenShot.captureScreenShot("UNF_033CheckOutPlaceOrderOrderConfirmationPage");

	}
}
