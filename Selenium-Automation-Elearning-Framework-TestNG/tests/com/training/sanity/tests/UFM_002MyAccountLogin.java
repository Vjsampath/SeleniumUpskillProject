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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.UniformHomePOM;
import com.training.pom.UniformLoginPagePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import junit.framework.Assert;

public class UFM_002MyAccountLogin {

	private WebDriver driver;
	private String baseUrl;
	private UniformHomePOM UniformHomePOM;
	private UniformLoginPagePOM UniformLoginPagePOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUpBeforeClass1() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		UniformHomePOM = new UniformHomePOM(driver);
		UniformLoginPagePOM = new UniformLoginPagePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

	@Test(priority = 0)
	public void myAccountLoginTest() throws InterruptedException {

		WebElement weMyAccount = driver
				.findElement(By.xpath("//li[@class='dropdown myaccount']//span[@class='caret']"));
		Actions Act = new Actions(driver);
		Action mouseOverMyAcc = Act.moveToElement(weMyAccount).build();
		mouseOverMyAcc.perform();
		String expectedTitle = "MY ACCOUNT";

		UniformHomePOM.myAccountDropdownBtn();
		UniformHomePOM.clickmyAccountLogin();
		UniformLoginPagePOM.sendEmailAdd("vj@gmail.com");
		UniformLoginPagePOM.sendPassword("vj123");
		UniformLoginPagePOM.clickLoginBtn();

		
		String ActualText= driver.findElement(By.xpath("//div[@class='col-sm-9']//h1[contains(text(),'My Account')]")).getText();
		System.out.println("Actual Text after login " + ActualText);
		Assert.assertEquals(expectedTitle, ActualText);
				System.out.println("Assert Pass Success Login");
		screenShot.captureScreenShot("UFM_002MyAccountLogin-success");
	
	}

}
