package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UniformShoppingCartPagePOM {
	private WebDriver driver; 
	
	public UniformShoppingCartPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[contains(text(),'Shopping Cart')]")
	private WebElement pageTitle; 
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(xpath="//div[@class='pull-right']//a[contains(text(),'Checkout')]")
	private WebElement checkOutBtn; 
	
	public String pageTitle() {
		String getPageTitle = this.pageTitle.getText();
		return(getPageTitle);
		
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickCheckOutBtn() {
		this.checkOutBtn.click(); 
	}
}
