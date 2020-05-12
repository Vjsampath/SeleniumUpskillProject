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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.UniformHomePOM;
import com.training.pom.UniformRegisterAccountPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import junit.framework.Assert;

public class UFM_001MyAccountRegistration {

	private WebDriver driver;
	private String baseUrl;
	private UniformHomePOM UniformHomePOM;
	private UniformRegisterAccountPOM UniformRegisterAccountPOM;
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
		UniformHomePOM = new UniformHomePOM(driver);
		UniformRegisterAccountPOM = new UniformRegisterAccountPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void myAccounRegistration() throws InterruptedException {
		
	//Action for mouseHover My Account icon	
		WebElement weMyAccount1 = driver
				.findElement(By.xpath("//li[@class='dropdown myaccount']//span[@class='caret']"));
		Actions Act1 = new Actions(driver);
		Action mouseOverMyAcc1 = Act1.moveToElement(weMyAccount1).build();
		mouseOverMyAcc1.perform();
		
		String expectedPageHeader = "REGISTER ACCOUNT";
		String expectedAccSuccessCreatedMsg = "Congratulations! Your new account has been successfully created!";
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Click Register to open Registration form
		UniformHomePOM.myAccountDropdownBtn();
		UniformHomePOM.clickmyAccountRegister();
	
		
		// Check for Register Form Header
		Assert.assertTrue("Page Header Does Not Match ", expectedPageHeader.equals(UniformRegisterAccountPOM.UniformRegisterPageHeader()));
		screenShot.captureScreenShot("UFM_001MyAccountReg Page Header is Register Account");
		
		
		// Enter of all mandatory field of Form
		//Personal Details
		UniformRegisterAccountPOM.sendfirstName("Neha");
		UniformRegisterAccountPOM.sendlastName("B");
		UniformRegisterAccountPOM.sendemail("neha3@gmail.com");
		UniformRegisterAccountPOM.sendtelephone("9241835892");
	
		//Address
		UniformRegisterAccountPOM.sendaddress1("Jayanagar");
		UniformRegisterAccountPOM.sendcity("Bangalore");
		UniformRegisterAccountPOM.sendpostcode("560082");
		UniformRegisterAccountPOM.selectcountry("India");
		UniformRegisterAccountPOM.selectzone("Karnataka");
		
		//Password
		UniformRegisterAccountPOM.sendPassword("Neha123");
		UniformRegisterAccountPOM.sendconfirmPwd("Neha123");
		
		//Newsletter
		UniformRegisterAccountPOM.checkrbnewsSubscribeNo();
		UniformRegisterAccountPOM.checkcbPrivPolicy();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		UniformRegisterAccountPOM.clickContinueBtn();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='content']//p[contains(text(),'Congratulations! Your new account has been success')]")));
		String successAccCreatedMsg = driver.findElement(By.xpath("//div[@id='content']//p[contains(text(),'Congratulations! Your new account has been success')]")).getText();

		System.out.println("success Account Created message " + successAccCreatedMsg);

		Assert.assertEquals(expectedAccSuccessCreatedMsg, successAccCreatedMsg);
		System.out.println("Assert Acct Creation Pass");
		screenShot.captureScreenShot("UFM_001MyAccountCreated success message");
		
	}
}
