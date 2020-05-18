package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UniformCheckOutPagePOM {
	private WebDriver driver; 
	
	public UniformCheckOutPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h1[contains(text(),'Checkout')]")
	private WebElement pageTitle; 
	
	@FindBy(xpath ="//input[@id='button-payment-address' and @value='Continue']")
	private WebElement billingContinueBtn;
	
	@FindBy(id="button-shipping-address")
	private WebElement deliveryDContinueBtn;
	
	@FindBy(id="button-shipping-method")
	private WebElement shippingMContinueBtn;
	
	@FindBy(id="button-payment-method")
	private WebElement paymentMContinueBtn;
	
	@FindBy(xpath="//div[@class='pull-right']//a[contains(text(),'Checkout')]")
	private WebElement checkOutBtn; 
	
	@FindBy(xpath="//textarea[@name='comment']")
	private WebElement addCommentsDeliMeth;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement checkBoxAgree;
	
	@FindBy(id="button-confirm")
	private WebElement confirmOrderContinueBtn;

	
	public String pageTitle() {
		String getPageTitle = this.pageTitle.getText();
		return(getPageTitle);
		
	}
	
	public void sendaddComments(String comments) {
		this.addCommentsDeliMeth.clear(); 
		this.addCommentsDeliMeth.sendKeys(comments);
	}
	
	
	public void clickConfirmOrderContinueBtn() {
		this.confirmOrderContinueBtn.click();
	}
	public void clickbillingContinueBtn() {
		this.billingContinueBtn.click();
	}
	
	public void clickdeliveryDContinueBtn() {
		this.deliveryDContinueBtn.click();
	}
	
	public void clickshippingMContinueBtn() {
		this.shippingMContinueBtn.click(); 
	}
	
	public void clickPaymentMContinueBtn() {
		this.paymentMContinueBtn.click(); 
	}
	
	public void clickcheckBoxAgree() {
		this.checkBoxAgree.click(); 
	}
}
