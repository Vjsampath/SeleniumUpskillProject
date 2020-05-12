package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UniformLoginPagePOM {
	private WebDriver driver; 
	
	public UniformLoginPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	

	
	@FindBy(id="input-email")
	private WebElement emailAddress; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath ="//input[@class='btn btn-primary']")
	private WebElement loginBtn;

	@FindBy(xpath ="//div[@class='form-group']//a[contains(text(),'Forgotten Password')]")
	private WebElement forgetPwd;
	
	
	

	public void sendEmailAdd(String emailAdd) {
		this.emailAddress.clear();
		this.emailAddress.sendKeys(emailAdd);
	}
	
	public void sendPassword(String pwd) {
		this.password.clear(); 
		this.password.sendKeys(pwd); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	public void clickForgotPasswordlink() {
		this.forgetPwd.click(); 
	}
	
	
}
