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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.UniformHomePOM;
import com.training.pom.UniformLoginPagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import junit.framework.Assert;

public class UFM_003MyAccountForgotPwd {

	private WebDriver driver;
	private String baseUrl;
	private UniformHomePOM UniformHomePOM;
	private UniformLoginPagePOM UniformLoginPagePOM;
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
		UniformLoginPagePOM = new UniformLoginPagePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void myAccounForgotPassword() throws InterruptedException {
		
	//Action for mouseHover My Account icon	
		WebElement weMyAccount1 = driver
				.findElement(By.xpath("//li[@class='dropdown myaccount']//span[@class='caret']"));
		Actions Act1 = new Actions(driver);
		Action mouseOverMyAcc1 = Act1.moveToElement(weMyAccount1).build();
		mouseOverMyAcc1.perform();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		String expectedEmailConfirm = "An email with a confirmation link has been sent your email address.";
		
		
		UniformHomePOM.myAccountDropdownBtn();
		UniformHomePOM.clickmyAccountLogin();
		UniformLoginPagePOM.sendEmailAdd("vj@gmail.com");
		UniformLoginPagePOM.sendPassword("vj124");
		UniformLoginPagePOM.clickLoginBtn();
		//validate Warning message for wrong credentials
		String warningNomatch = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		System.out.println("warning message " + warningNomatch);

		Assert.assertEquals(expectedWarning, warningNomatch);
		System.out.println("Assert Pass Warning No match Email or Password");
		screenShot.captureScreenShot("UFM_003MyAccountWarning message- Login");

		UniformLoginPagePOM.clickForgotPasswordlink();
		
	//Forgot password page
		UniformLoginPagePOM.sendEmailAdd("vj@gmail.com");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).submit();

		String successEmailSentMsg = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		System.out.println("warning message " + successEmailSentMsg);

		Assert.assertEquals(expectedEmailConfirm, successEmailSentMsg);
		System.out.println("Assert Pass Password sent to Email");
		
		screenShot.captureScreenShot("UFM_003MyAccountConfirm message- Pwd email msged");

	}
}
