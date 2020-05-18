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
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class UNF032CheckOutWOLogin {

	private WebDriver driver;
	private String baseUrl;
	private UniformHomePOM UniformHomePOM;
	private UniformItemDescPagePOM UniformItemDescPagePOM;
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
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void uniformShoptest() throws InterruptedException {

		String successMsgOfAddCart = "Success: You have added Regular T-Shirt (Maroon) to your shopping cart!";
		UniformHomePOM.clickShopUniform();
		
		
		Actions Act = new Actions(driver);
		Action TshritMouseOver = Act.moveToElement(UniformHomePOM.regTshritsReturn()).build();
		TshritMouseOver.perform();	
			
		UniformHomePOM.clickRegTshirtMaroon();
		
	
		UniformItemDescPagePOM.selchestSize("36");
		UniformItemDescPagePOM.sendQty("1");
		UniformItemDescPagePOM.clickAddToCartBtn();
		
		System.out.println("message " +UniformItemDescPagePOM.alertSuccMsg());
		Assert.assertTrue(UniformItemDescPagePOM.alertSuccMsg().contains(successMsgOfAddCart));
		screenShot.captureScreenShot("UNF_032CheckOutWOLogin_Success_AddCart_Msg");
		UniformItemDescPagePOM.clickCartIcon();
		UniformItemDescPagePOM.clickViewCartBtn();
		
		UniformItemDescPagePOM.clickShoppingCartCheckOutBtn();
		
		Assert.assertEquals("CHECKOUT", driver.findElement(By.xpath("//h1[contains(text(),'Checkout')]")).getText());
		
		screenShot.captureScreenShot("UNF_032CheckOutWOLogin_Check_out_Page");
	}
}
